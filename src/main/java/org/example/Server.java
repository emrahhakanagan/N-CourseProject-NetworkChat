package org.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private int port;
    private Set<ClientHandler> clientHandlers = new HashSet<>(); // Коллекция для управления подключенными клиентами
    private ServerPropertiesLoading serverPropertiesLoading;

    public Server() {
        serverPropertiesLoading = new ServerPropertiesLoading();
        this.port = serverPropertiesLoading.getPort();
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(10); // Пул потоков для обработки клиентских подключений

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Сервер запущен на порту " + this.port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Ожидание нового подключения клиента
                System.out.println("Новое подключение: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.add(clientHandler); // Добавление нового клиента в коллекцию
                pool.submit(clientHandler); // Запуск обработчика клиента в отдельном потоке
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для рассылки сообщений всем клиентам
    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }

}


