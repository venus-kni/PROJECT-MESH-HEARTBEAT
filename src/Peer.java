import java.net.*;
import java.security.PublicKey;

class MessageSender implements Runnable {
    public final static int PORT = 2020;
    private DatagramSocket socket;
    private String hostName;

    MessageSender(DatagramSocket sock, String host) {
        socket = sock;
        hostName = host;

    }

    private void sendMessage(String s) throws Exception {
        byte buffer[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostName);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);


    }

    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage("New client connected - welcome!");
                connected = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //window.displayMessage(e.getMessage());
            }
        } while (!connected);

        while (true) {
            try {

                //  while (!window.message_is_ready) {

                Thread.sleep(500);


                // }
                ////  sendMessage(window.getMessage());
                //window.setMessageReady(false);

            } catch (Exception e) {
                // window.displayMessage(e.getMessage());
            }
        }
    }
}

class MessageReceiver implements Runnable {
    DatagramSocket socket;
    byte buffer[];
    // ClientWindow window;

    MessageReceiver(DatagramSocket sock) {
        socket = sock;
        buffer = new byte[1024];
        //window = win;
    }

    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println(received);
                // window.displayMessage(received);
                if (received.equals("ACK")) {
                    String ackMessage = "PING";
                    socket.send(new DatagramPacket(ackMessage.getBytes(), ackMessage.length(), new InetSocketAddress("127.0.0.1", 5348)));

                }
                // if(window.isActive())
                {

                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}

public class Peer {

    private int id;
    private String name;
    private InetAddress ip_addr;
    private int port;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getIp_addr() {
        return ip_addr;
    }

    public void setIp_addr(InetAddress ip_addr) {
        this.ip_addr = ip_addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag;

    public Peer() {

    }

    public Peer(int id, String name, InetSocketAddress inetSocketAddress) {
        this.id = id;
        this.name = name;
        this.ip_addr = ip_addr;
        this.port = port;
    }


    public Peer(int id, String name, InetAddress ip_addr, boolean flag) {
        this.id = id;
        this.name = name;
        this.ip_addr = ip_addr;
        this.port = port;
        this.flag = flag;


    }

    public static void main(String args[]) throws Exception {
        Config con = new Config();

        // ClientWindow window = new ClientWindow();
        //  String host = window.getHostName();
        //  window.setTitle("UDP CHAT  Server: " + host);
        DatagramSocket socket = new DatagramSocket();
        MessageReceiver receiver = new MessageReceiver(socket);
        MessageSender sender = new MessageSender(socket, con.hostname);
        Thread receiverThread = new Thread(receiver);
        Thread senderThread = new Thread(sender);
        receiverThread.start();
        senderThread.start();


    }
}