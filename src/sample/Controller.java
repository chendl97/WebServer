package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public TextField hometext;
    public TextField porttext;
    public TextField iptext;
    public Label info;

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
            Runnable a=new MConnection(home,Integer.parseInt(port));
            new Thread(a).start();
        }
    }
}
