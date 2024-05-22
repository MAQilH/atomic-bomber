package aqil.atomicbomber.model;

import aqil.atomicbomber.utils.FileSaver;
import javafx.scene.image.Image;

public class User {
    private final String username;
    private final String password;
    private final int id;

    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public Image getAvatar() {
        return FileSaver.getUserAvatar(this);
    }
}
