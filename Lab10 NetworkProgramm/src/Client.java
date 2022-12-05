package src;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    static final String SERVER_IP = "localhost";
    static final int SERVER_PORT = 1234;
    static int clientPort;
    static String name;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter client name:");
        name = br.readLine();
        name = name.toLowerCase();

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(0));
        clientPort = ss.getLocalPort();
        new Thread(new ServerHandler(ss)).start();


        Map<String, String> initRequest = new HashMap<>();
        initRequest.put("type", "init");
        initRequest.put("senderName", name);
        initRequest.put("senderPort", String.valueOf(clientPort));
        sendData(SERVER_IP,SERVER_PORT, initRequest);
        initRequest = null;


        while (true) {
            System.out.println("Enter receiver name:");
            String receiver = br.readLine();
            receiver = receiver.toLowerCase();
            System.out.println("Enter your message:");
            String message = br.readLine();

            Map<String, String> request = new HashMap<>();
            request.put("type", "regular");
            request.put("senderName", name);
            request.put("receiverName", receiver);
            request.put("message", message);

            sendData(SERVER_IP,SERVER_PORT, request);
        }
    }

    static private void sendData(String ip, int port, Map<String, String> obj) throws IOException {
        Socket s = new Socket(ip, port);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeObject(obj);
        out.flush();
        out.close();
        s.close();
    }

    static class ServerHandler implements Runnable {
        ServerSocket ss;

        public ServerHandler(ServerSocket ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            try {
                System.out.printf("INFO: ServerHandler has been created: %s\n", ss.toString());
                while (true) {
                    Socket s = ss.accept();
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                    Map<String, String> response;
                    try {
                        response = (Map<String, String>) in.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.printf("[%s]: %s\n", response.get("senderName"), response.get("message"));
                    s.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
