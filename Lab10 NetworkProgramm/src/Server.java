package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    static final int SERVER_PORT = 1234;
    static Map<String, Map<String, String>>  clientsAddresses = new HashMap<>();

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(SERVER_PORT);
            server.setReuseAddress(true);
            System.out.printf("Server is listening on %s...%n", server.toString());

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected "
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            ObjectInputStream in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                // get the inputstream of client
                in = new ObjectInputStream(clientSocket.getInputStream());

                Map<String, String> request;
                request = (Map<String, String>) in.readObject();

                String sender = request.get("senderName");
                switch (request.get("type")) {
                    case "init" -> {
                        String senderPort = request.get("senderPort");
                        String senderAddress = clientSocket.getInetAddress().getHostAddress();
                        if (!clientsAddresses.containsKey(sender)) {
                            clientsAddresses.put(sender, new HashMap<String, String>());
                            clientsAddresses.get(sender).put("ip", senderAddress);
                            clientsAddresses.get(sender).put("port", senderPort);
                            System.out.printf("INFO: New client has been added: %s %s %s\n", sender, senderAddress, senderPort);
                        }
                    }
                    case "regular" -> {
                        String receiver = request.get("receiverName");
                        String message = request.get("message");
                        System.out.printf("INFO: %s -- %s --> %s\n", sender, message, receiver);
                        var receiversData = clientsAddresses.get(receiver);
                        String ip = receiversData.get("ip");
                        int port = Integer.parseInt(receiversData.get("port"));
                        Socket s = new Socket(ip, port);
                        ObjectOutputStream toReceiver = new ObjectOutputStream(s.getOutputStream());
                        toReceiver.writeObject(request);
                        toReceiver.flush();
                        toReceiver.close();
                        s.close();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
