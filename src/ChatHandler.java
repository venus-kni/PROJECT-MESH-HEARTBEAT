import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


public class ChatHandler implements Runnable {

    private final static int BUFFER = 1024;

    private Config con = new Config();
    private DatagramSocket socket;

    String hostIp = con.hostname;
    //String name = Peer.getName();

   // Peer peer = new Peer(Peer.id, Peer.name, Peer.ip_addr, Peer.port, Peer.flag);


    private Config config=new Config();
    int PORT_ChatHandler = con.getPORT_ChatHandler();

    //public static Map<Integer,Boolean> connected_client = new HashMap<>();


    public ChatHandler(DatagramSocket socket, String hostIp,  int PORT_ChatHandler) {
        this.socket = socket;
        this.hostIp = hostIp;
        this.config = config;
        this.PORT_ChatHandler = PORT_ChatHandler;
    }

    public ChatHandler() throws IOException {
        socket = new DatagramSocket(PORT_ChatHandler);
        System.out.println("Server is running and is listening on port " + PORT_ChatHandler);

    }

    public void run() {
        byte[] buffer = new byte[BUFFER];
        //while (true) {
        try {
              Arrays.fill(buffer, (byte) 0);

            for (Map.Entry<Peer, Boolean> set :
                    config.client_list.entrySet()) {

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("----:"+config.client_list.values());
                System.out.println("port:"+packet.getPort());
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String message = new String(buffer, 0, buffer.length);
                InetAddress clientAddress = packet.getAddress();

                String id = clientAddress + "|" + set.getKey().getPort();

                System.out.println(id + " : " + message);


            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }


    public static void main(String args[]) throws Exception {
        ChatHandler server_thread = new ChatHandler();
        server_thread.run();

    }
}


