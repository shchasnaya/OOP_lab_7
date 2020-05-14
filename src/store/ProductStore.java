package store;

import event.IProductListener;
import event.ProductEvent;
import model.IWeight;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductStore implements Serializable {
    private IWeight[] arr = new IWeight[3];
    private int count = 0;
    List<IProductListener> productListeners = new CopyOnWriteArrayList<>();

    public void addProductListener(IProductListener listener) {
        productListeners.add(listener);
    }

    public void removeProductListener(IProductListener listener) {
        productListeners.remove(listener);
    }

    protected void fireProductEvent(ProductEvent obj) {
        productListeners.forEach((lsn) -> lsn.onProductEvent(obj));
    }

    //повертає копію масиву без пустих елементів
    public IWeight[] getArr() {
        return Arrays.copyOf(arr,count);
    }

    public void add(IWeight newProduct) {
        //запобігаємо переповненню масиву
        if(arr.length == count)
            arr = Arrays.copyOf(arr, count + count/2);
        //додаємоновий елемент
        arr[count++] = newProduct;
        fireProductEvent(new ProductEvent(this, newProduct));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог брусів:\n");
        for(int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }

}


