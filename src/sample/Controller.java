package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public TextField hometext;
    public TextField porttext;
    public TextField iptext;
    public Label info;
    public Stage secstage;

    public void setOk(ActionEvent actionEvent)
    {
        String ip=iptext.getText();
        String port=porttext.getText();
        String home=hometext.getText();
        if(ip.equals(""))
        {
            info.setText("please input ip address!");
        } else if (port.equals(""))
        {
            info.setText("please input port!");
        } else if(home.equals(""))
        {
            info.setText("please input home path!");
        }
        else
        {
            System.out.println(ip+" "+port+" "+home);
            secstage=new Stage();
            try
            {
                openWindow();   //启动新窗口
            }catch (IOException e)
            {
                e.printStackTrace();
            }

            Runnable a=new MConnection(home,Integer.parseInt(port));   //启动服务器
            new Thread(a).start();

        }
    }

    public void openWindow() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("second.fxml"));
        secstage.setTitle("Connection");
        secstage.setScene(new Scene(root, 800, 500));
        secstage.show();

    }
}
