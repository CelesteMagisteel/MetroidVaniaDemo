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
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.solid.BasicTile;

public class Game extends Application {

    private static TileMap map;
    private static Canvas canvas;
    private static final Tile background = new BasicTile("background");
    private static final int SPRITE_SIZE = 32;

    public static void main(String[] args) {
        map = TileMap.generateBasicTileMap();
        canvas = new Canvas(TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_WIDTH * SPRITE_SIZE);
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
            drawCanvas();
        });
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, TileMap.MAP_WIDTH * SPRITE_SIZE, TileMap.MAP_HEIGHT * SPRITE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void drawCanvas() {
        for (int y = 0; y < TileMap.MAP_HEIGHT; y++) {
            for (int x = 0; x < TileMap.MAP_WIDTH; x++) {
                Tile tile = map.getTile(x, y);
                canvas.getGraphicsContext2D().drawImage(tile == null ? background.getTexture(SPRITE_SIZE, SPRITE_SIZE) : tile.getTexture(SPRITE_SIZE, SPRITE_SIZE), x * SPRITE_SIZE, y * SPRITE_SIZE);
            }
        }
    }
}
