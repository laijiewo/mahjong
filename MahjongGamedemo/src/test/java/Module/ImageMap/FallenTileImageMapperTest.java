package Module.ImageMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import Module.*;

import java.util.List;
import java.util.Map;

class FallenTileImageMapperTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create an instance of the FallenTileImageMapper
        FallenTileImageMapper mapper = new FallenTileImageMapper();

        // Create a TilePane to hold the images
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(5); // Set the preferred number of columns

        // Get the map of images

        Map<Tile, Image> imageMap = mapper.getImageMap();

        TileFactory tileFactory = new TileFactory();
        List<Tile> hand = tileFactory.createTiles();
        // Add each image to the TilePane
        for (Tile tile : hand) {
            ImageView imageView = new ImageView(imageMap.get(tile));
            imageView.setFitWidth(50); // Adjust the width as needed
            imageView.setFitHeight(50); // Adjust the height as needed
            tilePane.getChildren().add(imageView);
        }

        // Create a Scene and add the TilePane to it
        Scene scene = new Scene(tilePane, 800, 800); // Adjust the width and height as needed

        // Set the Stage
        primaryStage.setTitle("Image Map Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}