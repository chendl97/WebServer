package sample;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chendl on 17-5-4.
 * 用于事务处理
 * 接受请求报文，并发送响应报文
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
            di.read(req);   //读出请求
            synchronized (System.out) {
                for (byte a : req) {
                    if (a != 0) {
                        System.out.print((char) a);
                    }
                }
            }
            //解析请求
            String requst=new String(req);
            Scanner scan=new Scanner(requst);
            method=scan.next();
            subpath=scan.next();
            synchronized (Secontroller.data)  //对象锁，防止data对象同时被多个线程访问
            {
                Secontroller.data.add(new Person(method,"localhost:8080",subpath));  //输出请求信息到tableview
            }
            if(method.equals("GET"))
            {
                mpath=new File(home,subpath);
                if(mpath.exists())
                {
                    if(subpath.equals("/"))
                    {
                        mpath=new File(home,"index.html");
                        doget();   //发送响应报文
                    }
                    else
                    {
                        doget();
                    }

                }
                else
                {
                    notFound();   //未找到资源
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
            synchronized (Secontroller.data2)
            {
                Secontroller.data2.add(new Person("400","Not Found","HTTP/1.1"));
            }
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
            synchronized (Secontroller.data2)
            {
                Secontroller.data2.add(new Person("200","OK","HTTP/1.1"));
            }
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