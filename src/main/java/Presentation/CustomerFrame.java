package Presentation;

import BLL.CustomerBLL;
import Model.Customer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFrame {
    private JButton updateClient;
    private JButton insertClient;
    private JButton deleteClient;
    private JButton viewAllClient;
    private JButton back;

    private JPanel jPanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel pCustomer;
    private JPanel pc1;
    private JPanel pc2;
    private JPanel pc3;
    private JPanel pc4;
    private JPanel pc5;

    private JLabel id;
    private JLabel name;
    private JLabel email;
    private JLabel adress;
   // private JLabel oldid;

    private JTextField idText;
    private JTextField nameText;
    private JTextField emailText;
    private JTextField adressText;
   // private JTextField OldIdText;

    private JFrame frame;

    public CustomerFrame() {

        frame = new JFrame();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        id = new JLabel("ID: ");
        name = new JLabel("Name: ");
        email = new JLabel("Email: ");
        adress = new JLabel("Adress: ");
        //oldid = new JLabel("Old ID: ");

        JPanel pc1 = new JPanel();
        JPanel pc2 = new JPanel();
        JPanel pc3 = new JPanel();
        JPanel pc4 = new JPanel();
        JPanel pc5 = new JPanel();

       // OldIdText = new JTextField(3);
        idText = new JTextField(3);
        nameText = new JTextField(10);
        emailText = new JTextField(10);
        adressText = new JTextField(10);

        pCustomer = new JPanel();
        pc1.add(id);
        pc1.add(idText);
        pc2.add(name);
        pc2.add(nameText);
        pc3.add(email);
        pc3.add(emailText);
        pc4.add(adress);
        pc4.add(adressText);
    //   pc5.add(oldid);
      //  pc5.add(OldIdText);
        pCustomer.add(pc1);
        pCustomer.add(pc2);
        pCustomer.add(pc3);
        pCustomer.add(pc4);
    //    pCustomer.add(pc5);

        pCustomer.setLayout(new GridLayout(2,2));

        jPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        insertClient = new JButton("INSERT");
        deleteClient = new JButton("DELETE");
        viewAllClient = new JButton("VIEW ALL");
        updateClient = new JButton("UPDATE");
        back = new JButton("BACK");


        p1.setBackground(Color.black);
        p2.setBackground(Color.black);
        p3.setBackground(Color.black);
        p4.setBackground(Color.black);
        p5.setBackground(Color.black);

        insertClient.setBackground(Color.white);
        deleteClient.setBackground(Color.white);
        viewAllClient.setBackground(Color.white);
        updateClient.setBackground(Color.white);
        back.setBackground(Color.white);

        p1.add(insertClient);
        p2.add(deleteClient);
        p3.add(viewAllClient);
        p4.add(updateClient);
        p5.add(back);

        jPanel.add(p1);
        jPanel.add(p2);
        jPanel.add(p3);
        jPanel.add(p4);
        jPanel.add(p5);
        jPanel.add(pCustomer);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setSize(500,300);
        frame.add(jPanel);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);

        insertClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerBLL customerBLL = new CustomerBLL();
                int id = Integer.parseInt(idText.getText());
                String name = nameText.getText();
                String address = adressText.getText();
                String email = emailText.getText();

                customerBLL.insertCustomer(new Customer(id, name, address, email));
                System.out.println("insert complete!");
            }
        });
        updateClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerBLL customerBLL = new CustomerBLL();
                int oldId = Integer.parseInt(idText.getText());
                int id = Integer.parseInt(idText.getText());
                String name = nameText.getText();
                String address = adressText.getText();
                String email = emailText.getText();

                customerBLL.updateCustomer(oldId,id,name,address,email);
                System.out.println("update complete!");
            }
        });
        deleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerBLL customerBLL=new CustomerBLL();
                int id = Integer.parseInt(idText.getText());

                customerBLL.deleteCustomer(id);
                System.out.println("delete complete!");
            }
        });

        viewAllClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerBLL customerBLL = new CustomerBLL();

                System.out.println("view All complete!");
                CustomerView customerView = new CustomerView( customerBLL.getCustomers());
            }
        });
    }
}
