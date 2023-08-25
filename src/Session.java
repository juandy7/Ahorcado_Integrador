import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Session implements Runnable {

    private Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            while (true) {//evita que el server se cierre
                byte[] bf = new byte[300]; //los datos quedan en bytes
                socket.getInputStream().read(bf); //se queda esperando hasta el cliente haga su primer envio
                String rec = new String(bf, "UTF-8");
                BigServer.sendBroadcast(rec.trim());

            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public  void send(String message){
        try{
            socket.getOutputStream().write(message.getBytes("UTF-8"));
        }catch (IOException ex){
            ex.printStackTrace();
        }




    }

}
