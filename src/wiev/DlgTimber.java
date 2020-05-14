package wiev;

import model.Timber;
import model.Wood;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.event.*;

public class DlgTimber extends JDialog implements IWoodDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldLenth;
    private JTextField textFieldWidth;
    private JComboBox<Wood> comboBox1;
    private JTextField textFieldHeight;
    private Timber timber;

    public DlgTimber() {
        setUiProperties();
        setEvents();
    }

    private void setUiProperties(){
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 400, 170);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Timber");
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
        float height = Float.parseFloat(textFieldHeight.getText());
        float width = Float.parseFloat(textFieldWidth.getText());
        Wood wood = (Wood) comboBox1.getSelectedItem();
        boolean flag = true;
        try {
            timber = new Timber(wood, length, height, width);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Waste Dialog Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag) {
            DlgTimber.this.setVisible(false);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        timber = null;
        DlgTimber.this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        DlgTimber dialog = new DlgTimber();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }



    @Override
    public Object getObject() {
        return timber;
    }

    @Override
    public void setWoodDirectory(WoodDirectory wd) {
        ComboBoxModel<Wood> model = new DefaultComboBoxModel<>(wd.getArr());
        comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Timber";
    }
}
