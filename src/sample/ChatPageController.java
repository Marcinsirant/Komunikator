package sample;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.text.LabelView;
import java.io.IOException;

public class ChatPageController {
    Client client;
    String actualGroup;
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
    private TableView<String> chatTableView;

    @FXML
    private TableColumn<String, String> chatTableColumn;

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
    private TextFlow chatTextFlow;


    @FXML
    private AnchorPane anoch;


    @FXML
    private ScrollPane scr;



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



    public TableView<String> getChatTableView() {
        return chatTableView;
    }

    public void setChatTableView(ObservableList <String> chatTableV) {

        chatTableView.setItems(chatTableV);
    }

    void initController(Client client, String name, ObservableList <String> listO){
        Text text = new Text("Now this is a text node");
        chatTextFlow.getChildren().add(text);

        this.client = client;
        groupNameLabel.setText(name);
        actualGroup=name;
        chatTableColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue()));

        chatTableView.getItems().add("asdasd");

    }
    @FXML
    void sendButtonImageViewClick(MouseEvent event) throws IOException {
        Message newMessage = new Message(4, fieldToSendTextArea.getText());
        System.out.println(actualGroup);
        newMessage.setDirection(actualGroup);
        newMessage.setSource(client.getUserName());
        fieldToSendTextArea.setText("");
        client.userSendMessage(newMessage);

    }

    public void addMessageToTextArea(Message message){
        //if(message.getSource().equals(client.getUserName())){


            Platform.runLater(()->{
                Text t1 = new Text();
                t1.setStyle("-fx-fill: red;-fx-font-weight:bold; -fx-text-alignment: right;");
                t1.setText("\n" + "YOU: " + message.getMessageContent());
                chatTextFlow.getChildren().add(t1);


                Label label = new Label("\n" + "YOU: " + message.getMessageContent());
                label.setMinWidth(250);
                anoch.getChildren().add(label);
            });

            chatTextArea.appendText("\n" + "YOU: " + message.getMessageContent());
        //}else {

            Platform.runLater(()->{
                Text t1 = new Text();
                t1.setStyle("-fx-fill: black;-fx-font-weight:bold; -fx-text-alignment: left;");
                t1.setText("\n" + message.getSource() + ": " + message.getMessageContent());
                chatTextFlow.getChildren().add(t1);
            });

            chatTextArea.appendText("\n" + message.getSource() + ": " + message.getMessageContent());

        //}


    }

}
