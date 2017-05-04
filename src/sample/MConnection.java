package sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by chendl on 17-4-30.
 */
public class MConnection implements Runnable {

    private String homePath;
    private ServerSocket myss;
    private int port;
    private File home;

    public MConnection(String homePath,int port) {
        this.homePath = homePath;
        this.port=port;
        home=new File(homePath);
    }

    @Override
    public void run() {

        try
        {
            myss = new ServerSocket(port);
            while(true)
            {
                Socket mys = myss.accept();
                new Thread(new Drequst(mys,home)).start();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
