package net.celestemagisteel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.celestemagisteel.engine.ControlHandler;
import net.celestemagisteel.engine.GameState;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.events.EntityFireProjectileEvent;
import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.handlers.Handler;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.solid.BasicTile;

import java.io.IOException;
import java.util.ArrayList;

public class Game extends Application {

    public static final int SPRITE_SIZE = 32;
    private static Tile background;
    private static Canvas canvas;
    private static GameState state;
    private static ControlHandler controlHandler;

    static {
        try {
            background = new BasicTile("background");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerHandlers() {
        EventManager.registerHandler(new Handler<>(EntityMoveEvent.class));
        EventManager.registerHandler(new Handler<>(EntityFireProjectileEvent.class));
    }

    public static void main(String[] args) throws IOException {
        canvas = new Canvas(TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_WIDTH * SPRITE_SIZE);
        state = new GameState(TileMap.generateBasicTileMap(), new Player("player", 20, 1, TileMap.MAP_HEIGHT - 2), new ArrayList<>());
        controlHandler = new ControlHandler(state.getPlayer());
        registerHandlers();
        EventManager.registerEvents(state);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#1a2a3a"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPrefSize(TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_HEIGHT * SPRITE_SIZE);
        root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(TileMap.MAP_WIDTH * SPRITE_SIZE);
            canvas.setHeight(TileMap.MAP_HEIGHT * SPRITE_SIZE);
            //state.drawFullGameState(canvas);
        });
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_HEIGHT * SPRITE_SIZE);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case D:
                    controlHandler.startWalkForward();
                    break;
                case A:
                    controlHandler.startWalkBackwards();
                    break;
                case W:
                    controlHandler.startGoUp();
                    break;
                case S:
                    controlHandler.startGoDown();
                    break;
                case SPACE:
                    controlHandler.startFiring();
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case D:
                    controlHandler.stopWalkForward();
                    break;
                case A:
                    controlHandler.stopWalkBackwards();
                    break;
                case W:
                    controlHandler.stopGoUp();
                    break;
                case S:
                    controlHandler.stopGoDown();
                    break;
                case SPACE:
                    controlHandler.stopFiring();
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
