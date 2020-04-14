package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChatPageController {
    Client client;
    @FXML
    private ImageView chatImage;

    @FXML
    private ImageView groupImage;

    @FXML
    private AnchorPane groupAnchorPane;

    @FXML
    private Label nickLabel;

    @FXML
    private TableView<?> groupTableView;

    @FXML
    private TableColumn<?, ?> groupColumnName;

    @FXML
    private TextField groupTextField;

    @FXML
    private Button groupButton;

    @FXML
    private AnchorPane chatAnchorPage;

    @FXML
    private TableView<?> chatTableView;

    @FXML
    private TableColumn<?, ?> chatTableColumn;

    @FXML
    private ImageView sendButtonImageView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextArea fieldToSendTextArea;

    @FXML
    private Label groupNameLabel;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    void ExitApplication(ActionEvent event) {

    }

    @FXML
    void chatImageClick(MouseEvent event) {

    }

    @FXML
    void groupButtonClick(ActionEvent event) {

    }

    @FXML
    void groupImageClick(MouseEvent event) {

    }

    @FXML
    void loginButtonClick(ActionEvent event) {

    }
    void initController( Client client){
        this.client = client;
        client.setControllerChat(this);
    }
    @FXML
    void sendButtonImageViewClick(MouseEvent event) throws IOException {
        Message newMessage = new Message(4, fieldToSendTextArea.getText());
        System.out.println(client.getActualGroup());
        newMessage.setDirection(client.getActualGroup());
        newMessage.setSource(client.getUserName());
        fieldToSendTextArea.setText("");
        client.userSendMessage(newMessage);
    }

    public void addMessageToTextArea(Message message){
        if(message.getSource().equals(client.getUserName())){
            chatTextArea.setText(chatTextArea.getText() + "\n" + "                                                                                         " + message.getMessageContent());
        }else {
            chatTextArea.setText(chatTextArea.getText() + "\n" + message.getSource() + ": " + message.getMessageContent());
        }
    }

}