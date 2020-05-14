package test;

import model.Wood;
import store.WoodDirectory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TestStoreObject {
    public static void main(String[] args) {
        //Створення довідника деревини
        WoodDirectory wd = new WoodDirectory();
        //Додаємо новий вид деревини
        wd.add(new Wood(4,"Дуб", 1f));
        //Збуруження каталогу у файлі
        File f = new File("wd.object");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wd);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
