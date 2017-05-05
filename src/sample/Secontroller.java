package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by chen_dl on 2017/5/4.
 */
public class Secontroller {
    public static int flag;
    public  TableView requstable;
    public TableView replytable;
    public TableColumn<Person,String> rqfisrt;
    public TableColumn<Person,String> rqsecond;
    public TableColumn<Person,String> rqthird;
    public static ObservableList<Person> data;
    public static ObservableList<Person> data2;
    public TableColumn<Person,String> rpfirst;
    public TableColumn<Person,String> rpsecond;
    public TableColumn<Person,String> rpthird;


    public void initialize()
    {
        rqfisrt.setCellValueFactory(cellDate->cellDate.getValue().firstProperty());
        rqsecond.setCellValueFactory(cellDate->cellDate.getValue().secondProperty());
        rqthird.setCellValueFactory(cellDate->cellDate.getValue().thirdProperty());   //指定列与属性的对应关系
        data= FXCollections.observableArrayList();
        requstable.setItems(data);   //绑定

        rpfirst.setCellValueFactory(cellDate->cellDate.getValue().firstProperty());
        rpsecond.setCellValueFactory(cellDate->cellDate.getValue().secondProperty());
        rpthird.setCellValueFactory(cellDate->cellDate.getValue().thirdProperty());
        data2= FXCollections.observableArrayList();
        replytable.setItems(data2);

    }

    public void stopserver(ActionEvent actionEvent) {
        flag=1;

        //initialize();
    }
}
