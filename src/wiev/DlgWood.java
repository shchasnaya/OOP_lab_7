package wiev;

import model.Wood;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.event.*;

public class DlgWood extends JDialog implements IWoodDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldID;
    private JTextField textFieldName;
    private JTextField textFieldDensity;
    private JTextArea textArea1;
    private Wood wood;
    private WoodDirectory wd;


    public DlgWood() {
        setUiProperties();
        setEvents();
    }
    private void setUiProperties() {
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 345, 250);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Wood");

    }

    private void setEvents(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int id = Integer.parseInt(textFieldID.getText());
        String name = textFieldName.getText();
        float density = Float.parseFloat(textFieldDensity.getText());
        wd.add(new Wood(id, name, density));
        DlgWood.this.setVisible(false);
        dispose();
    }

    private void onCancel() {
        DlgWood.this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        DlgWood dialog = new DlgWood();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public Object getObject() {
        return wood;
    }

    @Override
    public void setWoodDirectory(WoodDirectory wd) {
        this.wd = wd;
        textArea1.setText(wd.toString());
    }

    @Override
    public String toString() {
        return "Wood";
    }
}
