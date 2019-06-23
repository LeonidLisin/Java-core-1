package Lesson_6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class Main {
    private Vector<ClientHandler> clients;
    int clientId = 0;

    public Main() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;


        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                clientId++;
                clients.add(new ClientHandler(socket, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadCastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void clientRemove(){
        Iterator<ClientHandler> iter = clients.iterator();
        while(iter.hasNext()){
            ClientHandler o = iter.next();
            if (o.clientId == -1) {
                iter.remove();
                System.out.println("Клиент удален из списка");
                System.out.println("Подключено клиентов: " + clients.size());
            }
        }

//        for (ClientHandler o : clients) {    так вылетает исключение ConcurrentModificationException, пришлось делать через итератор
//            if (o.clientId == -1) {
//                clients.remove(o);
//                System.out.println("Клиент удален из списка");
//                System.out.println("Подключено клиентов: " + clients.size());
//            }
//        }
    }
}
