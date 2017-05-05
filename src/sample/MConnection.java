package sample;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chendl on 17-4-30.
 * 该文件用于建立连接，创建新线程
 */
public class MConnection implements Runnable {

    private String homePath;
    private ServerSocket myss;
    private int port;   //端口
    private File home;   //home目录

    public MConnection(String homePath,int port) {
        this.homePath = homePath;
        this.port=port;
        home=new File(homePath);
    }

    @Override
    public void run() {   //线程，用于监听连接

        try
        {
            myss = new ServerSocket(port);
            while(true)
            {
                if(Secontroller.flag==1)  //用于实现按下按钮退出
                {
                    break;
                }
                Socket mys = myss.accept();    //接受连接
                new Thread(new Drequst(mys,home)).start();    //创建新线程进行事务处理
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
