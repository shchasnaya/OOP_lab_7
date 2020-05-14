package wiev;

import model.Cilinder;
import model.Wood;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.event.*;

public class DlgCilinder extends JDialog implements IWoodDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldLenth;
    private JTextField textFieldDiameter;
    private JComboBox<Wood> comboBox1;
    private Cilinder cilinder;

    public DlgCilinder() {
        setUiProperties();
        setEvents();
    }

    private void setUiProperties(){
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 400, 170);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Cilinder");
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
        float length = Float.parseFloat(textFieldLenth.getText());
        float diameter = Float.parseFloat(textFieldDiameter.getText());
        Wood wood = (Wood) comboBox1.getSelectedItem();
        boolean flag = true;
        try {
            cilinder = new Cilinder(wood, length, diameter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Waste Dialog Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag) {
            DlgCilinder.this.setVisible(false);
            dispose();
        }
    }

    private void onCancel() {
        cilinder = null;
        DlgCilinder.this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        DlgCilinder dialog = new DlgCilinder();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public Object getObject() {
        return cilinder;
    }

    @Override
    public void setWoodDirectory(WoodDirectory wd) {
        ComboBoxModel<Wood> model = new DefaultComboBoxModel<>(wd.getArr());
        comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Cilinder";
    }
}
