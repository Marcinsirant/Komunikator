package sample;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.text.LabelView;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    private VBox vb;


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
        if(message.getSource().equals(client.getUserName())){

            // if we dont use plaform.runLater, program isnt working
            Platform.runLater(()->{
                Label label = new Label( "YOU: " + message.getMessageContent());
                label.setMaxWidth(360);
                label.setWrapText(true);
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setStyle("-fx-padding : 1em; -fx-text-fill: black; -fx-background-radius: 10; -fx-background-color:  #E0E0E0;");


                HBox hBox=new HBox();
                hBox.setPrefWidth(550);
                hBox.setMaxWidth(550);
                hBox.getChildren().add(label);
                hBox.setAlignment(Pos.BASELINE_RIGHT);

                vb.setAlignment(Pos.CENTER);
                vb.getChildren().add(hBox);
            });

        }else {

            Platform.runLater(()->{
                SoundManager.soundReceived();
                Label label = new Label(  message.getSource() + ": "+"\n" + message.getMessageContent());
                label.setMaxWidth(360);
                label.setWrapText(true);
                label.setAlignment(Pos.CENTER_LEFT);
                label.setStyle("-fx-padding : 1em;-fx-text-fill: white; -fx-background-radius: 10; -fx-background-color:  #5999F1;");

                HBox hBox=new HBox();
                hBox.setStyle("-fx-padding : 1em;");
                hBox.setPrefWidth(550);
                hBox.setMaxWidth(550);
                hBox.getChildren().add(label);
                hBox.setAlignment(Pos.BASELINE_LEFT);

                vb.setAlignment(Pos.CENTER);
                vb.getChildren().add(hBox);

            });

        }

        // scroll bottom
        scr.vvalueProperty().bind(vb.heightProperty());
    }

}
