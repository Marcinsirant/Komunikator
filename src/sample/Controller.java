package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ImageView chatImage;

    @FXML
    private ImageView groupImage;

    @FXML
    private AnchorPane groupAnchorPane;

    @FXML
    private TableView<?> groupTableView;

    @FXML
    private Label nickLabel;

    @FXML
    private TextField groupTextField;

    @FXML
    private Button groupButton;

    @FXML
    private AnchorPane chatAnchorPage;

    @FXML
    private TableView<?> TableView;

    @FXML
    private ImageView sendButtonImageView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextArea fieldToSendTextArea;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button loginButton;

    @FXML
    void chatImageClick(MouseEvent event) {
        groupImage.setOpacity(0.5);
        chatImage.setOpacity(1);
        groupAnchorPane.setVisible(false);
        chatAnchorPage.setVisible(true);
    }

    @FXML
    void groupButtonClick(ActionEvent event) {

    }

    @FXML
    void groupImageClick(MouseEvent event) {
        groupImage.setOpacity(1);
        chatImage.setOpacity(0.5);
        groupAnchorPane.setVisible(true);
        chatAnchorPage.setVisible(false);
    }

    @FXML
    void loginButtonClick(ActionEvent event) {
        loginAnchorPane.setVisible(false);
        groupAnchorPane.setVisible(true);
        groupImage.setOpacity(1);
        chatImage.setOpacity(0.5);

    }

    @FXML
    void sendButtonImageViewClick(MouseEvent event) {

    }

}
