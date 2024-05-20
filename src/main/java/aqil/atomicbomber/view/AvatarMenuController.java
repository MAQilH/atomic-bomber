package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.AvatarController;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.utils.FileSaver;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvatarMenuController extends MenuController implements Initializable {
    public Button select_btn;
    public ImageView avatar1;
    public ImageView avatar2;
    public ImageView avatar3;
    public ImageView choose_avatar;
    private Menu prevMenu;
    private ArrayList<ImageView> avatars;
    private ImageView selectedAvatar;
    private StringProperty selectedImagePath;

    private final AvatarController avatarController = new AvatarController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedImagePath = new SimpleStringProperty();
        selectedAvatar = avatar1;
        select_btn.setOnMouseClicked(e -> onSelect());

        initializeChooseAvatarImageView();
        initializeAvatarsImageView();
    }

    void initializeAvatarsImageView() {
        avatars = new ArrayList<>();
        avatars.add(avatar1);
        avatars.add(avatar2);
        avatars.add(avatar3);
        avatars.add(choose_avatar);

        for (ImageView avatar : avatars) {
            avatar.setOpacity(avatar == selectedAvatar ? 1 : 0.7);
            avatar.setOnMouseClicked(event -> {
                selectedAvatar = avatar;
                avatar.setOpacity(1);
                for (ImageView imageView : avatars) {
                    if (imageView != avatar)
                        imageView.setOpacity(0.7);
                }
                if (avatar == choose_avatar)
                    onChooseAvatar();
            });
        }
    }

    private void initializeChooseAvatarImageView() {
        choose_avatar.setOnDragOver(event -> {
            if (event.getGestureSource() != choose_avatar && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        choose_avatar.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File file = db.getFiles().getFirst();
                Image image = new Image(file.toURI().toString());
                choose_avatar.setImage(image);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        choose_avatar.setOnMouseClicked(event -> onChooseAvatar());
    }

    public void onChooseAvatar() {
        Image image = avatarController.chooseImage();
        if (image != null)
            choose_avatar.setImage(image);
    }

    private void onSelect() {
        MenuLoader.setMenu(prevMenu);
        selectedImagePath.set(selectedAvatar.getImage().getUrl());
    }

    public StringProperty getSelectedImagePath() {
        return selectedImagePath;
    }

    @Override
    public void reload(Menu prevMenu) {
        this.prevMenu = prevMenu;
    }

}
