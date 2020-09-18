package net.celestemagisteel;

import javafx.application.Application;
import javafx.stage.Stage;
import net.celestemagisteel.map.TileMap;

public class MetroidVaniaDemo extends Application {

    public static void main(String[] args) {
        TileMap map = TileMap.generateBasicTileMap();

        map.printTileMap();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
