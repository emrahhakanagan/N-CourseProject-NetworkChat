package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String host;
    private int port;

    private ServerPropertiesLoading server;

    public Client() {
        server = new ServerPropertiesLoading();
        this.host = server.getHost();
        this.port = server.getPort();
    }

    public void start() {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Введите ваше имя:");
            String userName = scanner.nextLine();
            out.println(userName); // Отправка имени на сервер

            // Создаем новый поток для чтения сообщений от сервера
            new Thread(new ReceivedMessagesHandler(in)).start();

            // Чтение сообщений с консоли и отправка на сервер
            while (true) {
                String message = scanner.nextLine();

                if ("/exit".equalsIgnoreCase(message)) {
                    break; // Выход из цикла, если пользователь ввел команду выхода
                }

                out.println(message); // Отправка сообщения на сервер
            }
        } catch (IOException ex) {
            System.out.println("Ошибка при подключении к серверу: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static class ReceivedMessagesHandler implements Runnable {
        private BufferedReader in;

        public ReceivedMessagesHandler(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException ex) {
                System.out.println("Ошибка при получении сообщений: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    // Внутренний класс для обработки сообщений от сервера

}

