package test;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TestFile {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser dialog = new JFileChooser();
        dialog.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {
                return "файли типу .txt";
            }
            @Override
            public boolean accept(File f) {
                if(f != null){
                    //Відображати усі папки та файли типу txt
                    return f.isDirectory() || f.toString().endsWith(".txt");
                }
                return false;
            }
        });
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();
        if(f != null){
            System.out.println(f.getName());
            System.out.println(f.getAbsolutePath());
        }
        //task 2
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write("Щасна А.П., ПІ-181\n");
            for(int i = 0; i < 10; i++){
                double x = Math.random();
                String s = String.valueOf(x);
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        //task 3


    }
}
