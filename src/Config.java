import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Config {

    Gson gson = new Gson();
    public final static int PORT_ChatHandler = 2030;
    public static int PORT_HbHandler = 2031;


    public final static int BUFFER = 1024;
    public final static String hostname = "127.0.0.1";

    public static long TIME_INTERVAL = 1000;
    public static final int TIME_LIFE_FRAMES = 5;
    public static final int TIME_LOSS_COMMUNICATION_FRAME = 3;

    public Map<Peer, Boolean> client_list = new HashMap<>();

    private int[] ports = new int[5];


    public static String getHostname() {
        return hostname;
    }

    public static int getPORT_ChatHandler() {
        return PORT_ChatHandler;
    }

    public static int getPORT_HbHandler() {
        return PORT_HbHandler;
    }

    public static int getBUFFER() {
        return BUFFER;
    }


    public Config() {

        Peer p=new Peer() ;

        /*p.setPort(ports[1] = 1235);
        p.setPort(ports[2] = 1236);
        p.setPort(ports[3] = 1237);*/
       // ports[2] = 1236;



       // for (int i = 0; i < 3; i++) {
        //Peer peer = new Peer(1, "client01", new InetSocketAddress("127.0.0.1", 1234));

//            Peer peer = new Peer(0, "client", new InetSocketAddress("127.0.0.1", ports[0]));
//            peer.setPort(2009);
            client_list.put(new Peer(1, "client01", new InetSocketAddress("127.0.0.1", 1234)), false);
//            client_list.put(peer, false);
//            client_list.put(new Peer(3, "client03", new InetSocketAddress("127.0.0.1", 1236)), false);
            //client_list.put(new Peer(4, "client04", new InetSocketAddress("127.0.0.1", 1237)), false);

//            System.out.println(client_list);

       // }
        // client_list.add(new Peer(i,"client0"+ String.valueOf(i) , InetAddress.getByName("127.0.0.1"), 1230+i,false));


        Timer timer = new Timer();
/*        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("before messaging heartbeat: " + client_list.toString());

                for (Map.Entry<Peer, Boolean> set :
                        client_list.entrySet()) {

                    Heartbeat hb = null;
                    try {
                        Peer peer = set.getKey();
                        hb = new Heartbeat(hostname, peer.getPort(), 2000);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(client_ports.get(i));
                    //   clients.put(client_ports.get(i),false);
                    hb.run();
                }

            }
        }, 0, 10000);*/
    }
}
