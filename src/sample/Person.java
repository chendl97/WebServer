package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by chen_dl on 2017/5/5.
 */
public class Person {
    private StringProperty first;
    private StringProperty second;
    private StringProperty third;

    public Person(String first, String second, String third) {
        this.first = new SimpleStringProperty(first);
        this.second =new SimpleStringProperty(second);
        this.third =new SimpleStringProperty(third);
    }

    public String getFirst() {
        return first.get();
    }

    public StringProperty firstProperty() {
        return first;
    }

    public void setFirst(String first) {
        this.first.set(first);
    }

    public String getSecond() {
        return second.get();
    }

    public StringProperty secondProperty() {
        return second;
    }

    public void setSecond(String second) {
        this.second.set(second);
    }

    public String getThird() {
        return third.get();
    }

    public StringProperty thirdProperty() {
        return third;
    }

    public void setThird(String third) {
        this.third.set(third);
    }
}
