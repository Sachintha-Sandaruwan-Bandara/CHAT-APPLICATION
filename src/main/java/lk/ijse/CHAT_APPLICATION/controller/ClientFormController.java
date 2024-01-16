package lk.ijse.CHAT_APPLICATION.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/13/2024 - 9:29 AM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientFormController {

    @FXML
    private JFXButton btnSend;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtFld;
    Socket remoteSocket;
    String massage="";

    public void initialize() {
        System.out.println("client");
        new Thread(() -> {
            try {

                 remoteSocket = new Socket("localhost", 3002);
                    DataInputStream dataInputStream = new DataInputStream(remoteSocket.getInputStream());
                while (!massage.equals("exit")) {
                    massage = dataInputStream.readUTF();
                    System.out.println("from server:  " + massage);
                    txtArea.appendText("\n from server:  " + massage);

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {

        DataOutputStream dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());
        String msg = txtFld.getText();
        txtArea.appendText("\nme  : "+msg);
        dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();

        }



}
