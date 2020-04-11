package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    Client client = new Client("127.0.0.1", 5000, this);

    @FXML
    private ImageView chatImage;

    @FXML
    private ImageView groupImage;

    @FXML
    private AnchorPane groupAnchorPane;

    @FXML
    private TableView groupTableView;

    @FXML
    private TableColumn<String, String> groupColumnName;

    @FXML
    private Label nickLabel;

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
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button loginButton;

    public TableView<String> getChatTableView() {
        return chatTableView;
    }

    public void setChatTableView(TableView<String> chatTableView) {
        this.chatTableView = chatTableView;
    }

    private void chooseGroup(){

        groupTableView.setRowFactory( tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    chatTableView.getItems().clear();
                    String rowData = row.getItem();
                    client.setActualGroup(rowData);
                    System.out.println("click group: "+rowData);
                    changeAnchorPage();
                    chatTableColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue()));
                    try {
                        client.getUsersInGroupFromServer(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });


    }

    @FXML
    void chatImageClick(MouseEvent event) {
        changeAnchorPage();

    }

    @FXML
    void groupButtonClick(ActionEvent event) throws IOException {
        client.userCreateOrAddGroup(groupTextField.getText());

        groupColumnName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue()));

        groupTableView.getItems().add(groupTextField.getText());

        groupTextField.setText("");

        this.chooseGroup();
    }

    @FXML
    void groupImageClick(MouseEvent event) {
        changeAnchorPage();
    }

    @FXML
    void loginButtonClick(ActionEvent event) {
        loginAnchorPane.setVisible(false);
        groupAnchorPane.setVisible(true);
        groupImage.setOpacity(1);
        chatImage.setOpacity(0.5);

        try {
            client.userLogin(loginTextField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

        nickLabel.setText(loginTextField.getText());

    }

    @FXML
    void sendButtonImageViewClick(MouseEvent event) throws IOException, ClassNotFoundException {
        Message newMessage = new Message(4, fieldToSendTextArea.getText());
        System.out.println(client.getActualGroup());
        newMessage.setDirection(client.getActualGroup());
        newMessage.setSource(loginTextField.getText());
        fieldToSendTextArea.setText("");
        client.userSendMessage(newMessage);
    }

    void changeAnchorPage(){
        if(groupAnchorPane.isVisible() == true ){
            groupImage.setOpacity(0.5);
            chatImage.setOpacity(1);
            groupAnchorPane.setVisible(false);
            chatAnchorPage.setVisible(true);
        }else{
            groupImage.setOpacity(1);
            chatImage.setOpacity(0.5);
            groupAnchorPane.setVisible(true);
            chatAnchorPage.setVisible(false);
        }

    }

    public void addMessageToTextArea(Message message){
        if(message.getSource().equals(loginTextField.getText())){
            chatTextArea.setText(chatTextArea.getText() + "\n" + "                                                                                         " + message.getMessageContent());
        }else {
            chatTextArea.setText(chatTextArea.getText() + "\n" + message.getSource() + ": " + message.getMessageContent());
        }
    }
}
