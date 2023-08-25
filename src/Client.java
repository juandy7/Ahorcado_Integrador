
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String [] args) throws IOException{

        //2. Solicitud de conexión
        Socket socket = new Socket("8.tcp.ngrok.io", 14724) ;

          

        new Thread(()->{
            while(true) {//evita que el server se cierre
                try {
                    byte[] bf = new byte[300];
                    socket.getInputStream().read(bf); //se queda esperando hasta el cliente haga su primer envio
                    String rec = new String(bf, "UTF-8");
                    System.out.println(rec.trim());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }).start();

        Scanner scanner  = new Scanner(System.in) ;

        while(true){
            String msg = scanner.nextLine() ;
            socket.getOutputStream().write(msg.getBytes("UTF-8"));
            if(msg.equals("whatTimeIsIt")){
                BigServer.Time() ;

            } else if(msg.equals("remoteIpConfig")){
                BigServer.MyIp();

            } else if(msg.equals("interfaces")){

                System.out.println("3");

            }
            if(msg.equals("RTT")) {
                byte[] mess = new byte[1024];
                String envio = new String(mess);
                socket.getOutputStream().write(envio.getBytes());


            }else {
                System.out.println("Ese comando no existe");

            }


            }





        }

    }







