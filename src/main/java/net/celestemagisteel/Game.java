package net.celestemagisteel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public static void main(String[] args) {
        map = TileMap.generateBasicTileMap();
        canvas = new Canvas(TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#1a2a3a"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPrefSize(TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(TileMap.MAP_SIZE*16);
            canvas.setHeight(TileMap.MAP_SIZE*16);
            drawCanvas();
        });
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void drawCanvas() {
        for (int y = 0; y < TileMap.MAP_SIZE; y++) {
            for (int x = 0; x < TileMap.MAP_SIZE; x++) {
                Tile tile = map.getTile(x, y);
                canvas.getGraphicsContext2D().drawImage(tile == null ? background.get16Texture() : tile.get16Texture(), x * 16, y * 16);
            }
        }
    }
}
