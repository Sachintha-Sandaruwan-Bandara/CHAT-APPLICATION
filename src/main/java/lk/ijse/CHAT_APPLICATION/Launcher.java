package lk.ijse.CHAT_APPLICATION;
/* 
    @author Sachi_S_Bandara
    @created 1/13/2024 - 9:23 AM 
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Launcher.class.getResource("/view/serverForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
        stage.show();

        Stage stage1 = new Stage();
        Parent root1 = FXMLLoader.load(Launcher.class.getResource("/view/clientForm.fxml"));
        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setTitle("");
        stage1.show();
    }
}
