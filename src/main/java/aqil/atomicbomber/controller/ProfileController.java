package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.utils.FileSaver;
import javafx.scene.image.Image;

public class ProfileController {
    public void saveUser(String newUsername, String newPassword, Image newAvatarImage) {
        Database database = Database.getInstance();
        if (!newUsername.isEmpty()) {
            User user = database.updateUserUsername(App.getInstance().getUser().getId(), newUsername);
            App.getInstance().setUser(user);
        }
        if (!newPassword.isEmpty()) {
            User user = database.updateUserPassword(App.getInstance().getUser().getId(), newPassword);
            App.getInstance().setUser(user);
        }
        if (newAvatarImage != null) {
            try {
                FileSaver.saveUserAvatar(App.getInstance().getUser(), newAvatarImage.getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
