package wiev;

import model.Waste;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.event.*;

public class DlgWaste extends JDialog implements IWoodDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldWeight;
    //private JComboBox comboBox1;
    private Waste waste;
    private Exception e1;

    public DlgWaste() {
        setUiProperties();
        setEvents();
    }

    private void setUiProperties() {
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 400, 170);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Waste");
    }

    private void setEvents() {
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
        float weight = Float.parseFloat(textFieldWeight.getText());
        boolean flag = true;
        try {
            waste = new Waste(weight);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Waste Dialog Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag){
            DlgWaste.this.setVisible(false);
            dispose();
        }
    }

    private void onCancel() {
        waste = null;
        DlgWaste.this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        DlgWaste dialog = new DlgWaste();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public Object getObject() {
            return waste;
    }

    @Override
    public void setWoodDirectory(WoodDirectory wd) {
        return;
        //ComboBoxModel<Wood> model = new DefaultComboBoxModel<>(wd.getArr());
        //comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Waste";
    }
}
