package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;

public class LoginController {
    public User getUser(String username){
        Database database = Database.getInstance();
        return database.getUserWithUsername(username);
    }

    public void enterUser(User user){
        App.getInstance().setUser(user);
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }
}
