package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private final Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;
    private String nameFileLog = "file.log";

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.clientName = in.readLine(); // Первое сообщение от клиента предполагается быть его именем
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("/exit".equalsIgnoreCase(inputLine.trim())) {
                    break;
                }
                String message = "[" + currentTime() + "] " + clientName + ": " + inputLine;
                System.out.println(message); // Вывод в консоль сервера
                server.broadcastMessage(message, this); // Отправка сообщения всем клиентам
                logMessage(message); // Логирование сообщения
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void logMessage(String message) {
        try (FileWriter fw = new FileWriter(nameFileLog, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String currentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date());
    }
}
