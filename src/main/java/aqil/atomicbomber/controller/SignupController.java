package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.utils.FileSaver;

public class SignupController {

    public void signupUser(String username, String password, String avatarUrl) {
        Database database = Database.getInstance();
        User user = database.saveUser(username, password);
        FileSaver.saveUserAvatar(user, avatarUrl);
        MenuLoader.setMenu(Menu.LOGIN_MENU);
    }
}
