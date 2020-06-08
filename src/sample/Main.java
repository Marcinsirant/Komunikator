package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/fxml/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("KomunikatorV1");
        primaryStage.setOnCloseRequest( event ->
        {
            System.out.println("CLOSING");
            primaryStage.close();
            //client.sendExitUser();
            Controller controller = (Controller)loader.getController();
            try {
                controller.ExitApplication();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.exit();
            System.exit(0);
        });
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 459, 469));
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
