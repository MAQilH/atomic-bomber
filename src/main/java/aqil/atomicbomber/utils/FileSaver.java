package aqil.atomicbomber.utils;

import aqil.atomicbomber.model.User;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSaver {

    public static final String SAVED_AVATAR_PATH = "file:C:\\Users\\moham\\IdeaProjects\\AtomicBomber\\saved_avatar\\";

    public static void saveUserAvatar(User user, String imageUrl) {
        String targetPath = "saved_avatar/" + user.getId() + ".png";
        try {
            URL url = new URL(imageUrl);
            try (InputStream in = url.openStream()) {
                Files.copy(in, Path.of(targetPath), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image loadImage(String imageUrl) {
        return new Image(imageUrl);
    }

    public static Image getUserAvatar(User user) {
        Image image;
        try {
            image = loadImage(SAVED_AVATAR_PATH + user.getId() + ".png");
        } catch (Exception e) {
            image = loadImage(SAVED_AVATAR_PATH + "0.png");
        }
        return image;
    }
}
