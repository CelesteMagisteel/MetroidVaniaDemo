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
import net.celestemagisteel.engine.GameState;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.solid.BasicTile;

import java.util.ArrayList;

public class Game extends Application {

    private static TileMap map;
    private static Canvas canvas;
    private static GameState state;
    private static final Tile background = new BasicTile("background");
    public static final int SPRITE_SIZE = 32;

    public static void main(String[] args) {
        canvas = new Canvas(TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_WIDTH * SPRITE_SIZE);
        state = new GameState(TileMap.generateBasicTileMap(), background, new Player("player", 20, 1, TileMap.MAP_HEIGHT-2), new ArrayList<>(), canvas);

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
            state.drawFullGameState(canvas);
        });
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_HEIGHT * SPRITE_SIZE);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case D: state.startWalkForward(); break;
                case A: state.startWalkBackwards(); break;
                case W: state.startGoUp(); break;
                case S: state.startGoDown(); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case D: state.stopWalkForward(); break;
                case A: state.stopWalkBackwards(); break;
                case W: state.stopGoUp(); break;
                case S: state.stopGoDown(); break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
