package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.controller.ScoreController;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.model.game.GameRecord;
import aqil.atomicbomber.model.game.GameResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreboardMenuController extends MenuController implements Initializable {
    public TableView table;
    public TableColumn rank_column;
    public TableColumn username_column;
    public TableColumn kill_column;
    public TableColumn wave_column;
    public TableColumn hardness_column;
    public TableColumn accurate_column;
    public ImageView back_btn;

    private ScoreController scoreController = new ScoreController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        back_btn.setOnMouseClicked(e -> onBack());
    }

    private void initializeTable() {
        rank_column.setCellValueFactory(new PropertyValueFactory<>("rank"));
        kill_column.setCellValueFactory(new PropertyValueFactory<>("kills"));
        hardness_column.setCellValueFactory(new PropertyValueFactory<>("hardness"));
        wave_column.setCellValueFactory(new PropertyValueFactory<>("wave"));
        username_column.setCellValueFactory(new PropertyValueFactory<>("username"));
        accurate_column.setCellValueFactory(new PropertyValueFactory<>("accurate"));

        table.setRowFactory(tv -> new TableRow<GameRecord>() {
            @Override
            public void updateItem(GameRecord item, boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() < 3 && !empty) {
                    if (!getStyleClass().contains("top-three")) getStyleClass().add("top-three");
                }
            }
        });
    }

    private void onBack() {
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }

    public void reload(Menu prevMenu) {
        ObservableList<GameRecord> data = scoreController.getScoreDate(10);
        table.getItems().clear();
        table.setItems(data);
    }
}
