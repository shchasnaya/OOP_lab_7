package store;

import model.Wood;

import java.io.Serializable;
import java.util.Arrays;

public class WoodDirectory implements Serializable {
    private Wood[] arr = new Wood[3];
    private int count = 0;
    //ініціалізація
    {
        arr[0] = new Wood(1, "Модрина", 1.1f);
        arr[1] = new Wood(2, "Ялина", 0.9f);
        arr[2] = new Wood(3, "Сосна", 0.7f);
        count = 3;
    }
    //повертає копію масиву без пустих елементів
    public Wood[] getArr() {
        return Arrays.copyOf(arr,count);
    }

    //вибір каталогу за допомогою ID
    public Wood get(int id){
        for(int i = 0; i < count; i++){
            if(i == id)
                return arr[i];
        }
        return null;
    }

    public boolean add(Wood newWood) {
        //перевірка на унікальність id
        if(get(newWood.getId()) != null)
        {
            return false;
        }
        //запобігаємо переповненню масиву
        if(arr.length == count)
            arr = Arrays.copyOf(arr, count + count/2);
        //додаємоновий елемент
        arr[count++] = newWood;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог деревини:\n");
        for(int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}

