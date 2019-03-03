package 实例一;

import org.springframework.context.ApplicationEvent;

/**
 * Created by james on 2017/7/7.
 */
public class EmailEvent extends ApplicationEvent {

    private String address;

    private String text;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(String address, String text) {
        super(address);
        this.address = address;
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
