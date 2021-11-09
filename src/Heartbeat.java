import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Heartbeat implements Runnable {


   // public final static int PORT = 2020;
    private static DatagramSocket socket;
    private static String hostName;

    final static Logger logger =
            LogManager.getLogManager().getLogger(Heartbeat.class.getName());

    private int portClient;
    private long timeStamp;





    public Heartbeat(String hostName, int portClient, long timeStamp) throws UnknownHostException {

        this.hostName = hostName;
        this.timeStamp = timeStamp;
        this.portClient = portClient;
    }


    @Override
    public void run() {
        //byte buffer[] = s.getBytes();
        Config con=new Config();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();



        //socket.setSoTimeout(1000);
        try {
            InetAddress address = InetAddress.getByName(hostName);

            DatagramSocket socket = new DatagramSocket(con.PORT_HbHandler, address);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",portClient);
            socket.setSoTimeout((int) con.TIME_INTERVAL);
            String message = "PING";
            socket.send(new DatagramPacket(message.getBytes(),message.length(),inetSocketAddress));
            byte buffer[]= new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            if(received.equals("ACK")){
                //Config.clients.put(portClient,true);
            }
            System.out.println(received);

            socket.close();
            /*if (socket.isConnected()) {
                System.out.println("reply");
            } else if (socket.isClosed())
                System.out.println("client left");
*/
        } catch (SocketException e) {
            //logger.log();
            e.printStackTrace();
        }catch (SocketTimeoutException e){
          //  Config.clients.put(portClient,false);
            System.out.println("client is down, port: "+portClient);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            socket.close();
        }

    }

    public long getBeatTimeStamp() {
        return timeStamp;
    }
    public String getSource() {
        return hostName;
    }

}
