package sample;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chendl on 17-5-4.
 */
public class Drequst implements Runnable {
    private Socket cSocket;
    private File home;

    private OutputStream mdo;
    private byte[] req;
    private File mpath;
    private String method;
    private String subpath;


    public Drequst(Socket cSocket,File home) {
        this.cSocket = cSocket;
        this.home=home;
    }

    @Override
    public void run() {
        try {
            InputStream di =new BufferedInputStream(cSocket.getInputStream());
            mdo =new BufferedOutputStream(cSocket.getOutputStream());
            req = new byte[500];
            di.read(req);
            for (byte a : req) {
                if (a != 0) {
                    System.out.print((char) a);
                }
            }
            String requst=new String(req);
            Scanner scan=new Scanner(requst);
            method=scan.next();
            if(method.equals("GET"))
            {
                subpath=scan.next();
                mpath=new File(home,subpath);
                if(mpath.exists())
                {
                    if(subpath.equals("/"))
                    {
                        mpath=new File(home,"index.html");
                        doget();
                        //System.out.println("root");
                    }
                    else
                    {
                        //mdo.write("HTTP/1.1 200 OK\n\n".getBytes());
                        doget();
                    }

                }
                else
                {
                    notFound();
                }

            }
            else if (method.equals("POST"))
            {

            }

            mdo.flush();
            di.close();
            mdo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void notFound() {
        try {
            mdo.write("HTTP/1.1 404 Not Found\n\n".getBytes());

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void doget()
    {
        try
        {
            mdo.write("HTTP/1.1 200 OK\n\n".getBytes());
            BufferedInputStream bi=new BufferedInputStream(new FileInputStream(mpath));
            byte[] reply=new byte[512];
            while(bi.read(reply)!=-1)
            {
                mdo.write(reply);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}