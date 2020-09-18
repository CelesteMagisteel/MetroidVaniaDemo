package net.celestemagisteel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileMap;

import java.util.Observable;

public class MetroidVaniaDemo extends Application {

    private static TileMap map;

    public static void main(String[] args) {
        map = TileMap.generateBasicTileMap();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Pane root = new Pane();
        root.setPrefSize(TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        Canvas canvas = new Canvas(TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(TileMap.MAP_SIZE*16);
            canvas.setHeight(TileMap.MAP_SIZE*16);
            for (int y = 0; y < TileMap.MAP_SIZE; y++) {
                for (int x = 0; x < TileMap.MAP_SIZE; x++) {
                    Tile tile = map.getTile(x, y);
                    if (tile != null) {
                        context.drawImage(tile.get16Texture(), x * 16, y * 16);
                    }
                }
            }
        });
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, TileMap.MAP_SIZE*16, TileMap.MAP_SIZE*16);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
