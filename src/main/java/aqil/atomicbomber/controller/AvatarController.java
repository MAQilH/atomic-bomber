package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.Menu;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class AvatarController {
    public Image chooseImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null)
            return new Image(selectedFile.toURI().toString());
        return null;
    }

}
