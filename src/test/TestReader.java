package test;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestReader {
    public static void main(String[] args) {
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();
        if(f != null){
            System.out.println(f.getName());
            System.out.println(f.getAbsolutePath());
        }
        BufferedReader reader = null;
        if(f != null){
            try{
                reader = new BufferedReader(new FileReader(f));
                String s;
                while ((s = reader.readLine()) != null){
                    System.out.println(s);
                }
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
