package chat.server;

/*
Задание: Разработайте простой чат на основе сокетов. Ваше приложение должно включать в себя сервер, который
принимает сообщения от клиентов и пересылает их всем участникам чата.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(33000);
            Server server = new Server(serverSocket);
            server.runServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
