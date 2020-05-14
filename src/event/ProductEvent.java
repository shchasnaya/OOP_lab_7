package event;

import model.IWeight;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;

public class ProductEvent extends EventObject {
    private IWeight product;
    Date date = new Date();

    public ProductEvent(Object source, IWeight product) {
        super(source);
        this.product = product;
    }

    public IWeight getProduct() {
        return product;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " "  + ": " + product;
    }

}
