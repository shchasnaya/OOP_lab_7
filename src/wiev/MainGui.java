package wiev;

import event.IProductListener;
import event.ProductEvent;
import file.OpenAndSave;
import model.IWeight;
import store.ProductStore;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MainGui {
    public JFrame frame;
    private JList list1;
    private JTextArea textArea1;
    private JPanel MainPanel;
    private JScrollPane scrollArea1;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenu menu_event = new JMenu("Event");
    private JMenuItem menuItem_save = new JMenuItem("Save");
    private JMenuItem menuItem_open = new JMenuItem("Open");
    private JMenuItem menuItem_addlistener = new JMenuItem("Add listener");
    private JMenuItem menuItem_removelistener = new JMenuItem("Remove listener");
    private JMenuItem menuItem_showLog = new JMenuItem("Show log");

    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();
    private DlgTimber dlgTimber = new DlgTimber();
    private DlgCilinder dlgCilinder = new DlgCilinder();
    private DlgWaste dlgWaste = new DlgWaste();
    private DlgWood dlgWood = new DlgWood();

    /**
     * Create the application
     */
    public MainGui() {
        initialize();
    }

    /**
     * Initilize the contens of the frame
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 600, 400);
        frame.setMinimumSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Lab7 OOP");
        frame.setContentPane(MainPanel);
        //frame.setLocationRelativeTo(textArea1);
        //frame.setSize(600,300);
        menuBar.add(menu);
        menuBar.add(menu_event);
        menu.add(menuItem_open);
        menu.add(menuItem_save);
        menu_event.add(menuItem_addlistener);
        menu_event.add(menuItem_removelistener);
        menu_event.add(menuItem_showLog);
        frame.setJMenuBar(menuBar);
        //list1 = new JList<>();
        DefaultListModel<IWoodDialog> model = new DefaultListModel<>();
        model.addElement(dlgTimber);
        model.addElement(dlgCilinder);
        model.addElement(dlgWaste);
        model.addElement(dlgWood);
        list1.setModel(model);

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("Log.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        IProductListener pLis = new IProductListener() {
            @Override
            public void onProductEvent(ProductEvent e) {
                System.err.println(e);
                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter("Log.txt", true));
                    bf.write(e.toString());
                    bf.newLine();
                    bf.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        menuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OpenAndSave.save(wd, ps);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File save error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        menuItem_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Object[] obj = OpenAndSave.open();
                    if(obj != null){
                        wd = (WoodDirectory) obj[0];
                        ps = (ProductStore) obj[1];
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File open error", "Error", JOptionPane.ERROR_MESSAGE);
                    wd = new WoodDirectory();
                    ps = new ProductStore();
                }
                textArea1.setText(ps.toString());
            }

        });

        menuItem_addlistener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.addProductListener(pLis);
            }
        });

        menuItem_removelistener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.removeProductListener(pLis);
            }
        });

        menuItem_showLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, OpenAndSave.readLog(), "Log", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                IWoodDialog dlg = (IWoodDialog) list1.getSelectedValue();
                dlg.setWoodDirectory(wd);
                dlg.setVisible(true);
                Object obj = dlg.getObject();
                if (obj != null) {
                    ps.add((IWeight) obj);
                }
                textArea1.setText(ps.toString());
            }
        });
    }
}
