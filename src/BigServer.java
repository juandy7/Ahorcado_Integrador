import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class BigServer { //es preparar

    private static ArrayList<Session> sessions = new ArrayList<>(); //es un arreglos de las sesiones para tenerlas guardadas 
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(6000);

        while(true){
            System.out.println("Esperando cliente");
            Socket socketClient = server.accept(); //esto genera el socket, tiene un nombre igual pero en cada iteracion es diferente
            System.out.println("Cliente conectado");
            Session session = new Session(socketClient);
            new Thread(session).start();
            sessions.add(session);
        }
    }

    public static void sendBroadcast(String message){

        for(Session session : sessions){

            session.send(message);

        }
    }

    public static void Time(){
        System.out.println(">>>> "+ ZonedDateTime.now());


    }
    public static void MyIp() {

        try{
            InetAddress localHost = InetAddress.getLocalHost() ;
            String ipAddress = localHost.getHostAddress() ;
            System.out.println(ipAddress);
        }catch (UnknownHostException e) {
            System.out.println("NO SE PUDO DETERMINAR IP DEL SERVER");
        }


    }

}