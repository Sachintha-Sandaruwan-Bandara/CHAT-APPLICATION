package lk.ijse.CHAT_APPLICATION.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/13/2024 - 9:48 AM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    @FXML
    private JFXButton btnSend;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtFld;
    ServerSocket serverSocket;
    Socket localSocket;
    String massage="";

    public void initialize() {
        System.out.println("server");
        new Thread(() -> {
            try {

                 serverSocket = new ServerSocket(3002);
                System.out.println("server is started");
                txtArea.appendText("server is started");
                 localSocket = serverSocket.accept();
                System.out.println("request accepted");

                DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());

                while (!massage.equals("exit")) {

                        massage = dataInputStream.readUTF();
                        System.out.println("from client:  " + massage);
                        txtArea.appendText("\n from client:  " + massage);

                    }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {

           DataOutputStream dataOutputStream = new DataOutputStream(localSocket.getOutputStream());
           String msg = txtFld.getText();
           txtArea.appendText("\n me  : "+msg);


           dataOutputStream.writeUTF(msg);
           dataOutputStream.flush();
           txtFld.clear();


    }
}
