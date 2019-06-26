package Lesson_7.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller{
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    TextField loginFiled;

    @FXML
    PasswordField passwordField;

    private boolean isAuthorized;
    private String nick;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    // метод для показа нижней панели или верхней
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // блок для авторизации
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/authok")) {
                                setAuthorized(true);
                                String[] tokens = str.split(" ");
                                nick = tokens[1];
                                System.out.println(nick);
                                textArea.clear();
                                break;
                            } else {
                                textArea.appendText(str + "\n");
                            }
                        }

                        // блок для разбора сообщений
                        while (true) {
                            String str = in.readUTF();
                            String[] tokens = str.split(" ");

                            if(str.equals("/serverClosed")) {
                                break;
                            }
                            if(tokens[1].equals("/w")) {
                                if (tokens.length >= 4) {
                                    int strStart = tokens[0].length() + tokens[1].length() + tokens[2].length() + 3;

                                        if (tokens[0].equals(nick))
                                            textArea.appendText("Вы отправили личное сообщение для "
                                                    + tokens[2] + ":\n" + str.substring(strStart) + "\n");

                                        if (tokens[2].equals(nick))
                                            textArea.appendText("Вам личное сообщение от "
                                                    + tokens[0] + ":\n" + str.substring(strStart) + "\n");
                                }
                            } else if(str.startsWith("/err")) {
                                switch (tokens[1]) {
                                    case "wrongW": {
                                        textArea.appendText("Некорректный ввод\n");
                                        break;
                                    }
                                    case "wrongNick":{
                                        textArea.appendText("Такого пользователя нет в чате\n");
                                        break;
                                    }
                                }
                            } else textArea.appendText(str + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод для авторизации
    public void tryToAuth() {
        if(socket == null || socket.isClosed()) {
            // сначала подключаемся к серверу
            connect();
        }
        try {
            // отправка сообщений на сервер для авторизации
            out.writeUTF("/auth " + loginFiled.getText() + " " + passwordField.getText());
            loginFiled.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод для отправки сообщений
    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
