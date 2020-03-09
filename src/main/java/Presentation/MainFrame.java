package Presentation;

import Connection.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MainFrame extends JFrame{
    private JButton productButton;
    private JButton customerButton;
    private JButton ordersButton;
    private JButton exitButton;
    private JFrame frame;

    private JPanel jpanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;

    public MainFrame()
    {
        productButton = new JButton("Product");
        customerButton = new JButton("Customer");
        ordersButton = new JButton("Orders");
        exitButton=new JButton("Exit");

        productButton.setBackground(Color.white);
        customerButton.setBackground(Color.white);
        ordersButton.setBackground(Color.white);
        exitButton.setBackground(Color.white);
        jpanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();

        frame = new JFrame();
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1.setBackground(Color.black);
        p2.setBackground(Color.black);
        p3.setBackground(Color.black);
        p4.setBackground(Color.black);

        p1.add(productButton);
        p2.add(customerButton);
        p3.add(ordersButton);
        p4.add(exitButton);

        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductFrame productFrame = new ProductFrame();

            }
        });

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerFrame customerFrame = new CustomerFrame();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        ordersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderProdFrame ordersFrame= new OrderProdFrame();
            }
        });
        jpanel.add(p1);
        jpanel.add(p2);
        jpanel.add(p3);
        jpanel.add(p4);
        frame.add(jpanel);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);


    }


}
