package Presentation;

import BLL.OrderBLL;
import DAO.OrderDAO;
import Model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersFrame {

    private JButton addOrder;
    private JButton deleteOrder;
    private JButton viewOrder;
    private JButton back;

    private JPanel jPanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel po1;
    private JPanel po2;
    private JPanel po3;
    private JPanel pOrder;

    private JLabel customer;
    private JLabel product;
    private JLabel quantity;

    private JTextField cText;
    private JTextField pText;
    private JTextField qText;

    private JFrame frame;

    public OrdersFrame() {

        frame = new JFrame();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        customer = new JLabel("ID: ");
        product = new JLabel("Price: ");
        quantity = new JLabel("CustomerID: ");

        cText = new JTextField(10);
        pText = new JTextField(10);
        qText = new JTextField(5);

        jPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        po1 = new JPanel();
        po2 = new JPanel();
        po3 = new JPanel();
        pOrder = new JPanel();

        addOrder = new JButton("INSERT");
        deleteOrder = new JButton("DELETE");
        viewOrder = new JButton("VIEW ALL");
        back = new JButton("BACK");


        p1.setBackground(Color.black);
        p2.setBackground(Color.black);
        p3.setBackground(Color.black);
        p4.setBackground(Color.black);
        pOrder.add(customer);
        pOrder.add(cText);
        pOrder.add(product);
        pOrder.add(pText);
        pOrder.add(quantity);
        pOrder.add(qText);


        addOrder.setBackground(Color.white);
        deleteOrder.setBackground(Color.white);
        viewOrder.setBackground(Color.white);
        back.setBackground(Color.white);

        p1.add(addOrder);
        p2.add(deleteOrder);
        p3.add(viewOrder);
        p4.add(back);

        jPanel.add(p1);
        jPanel.add(p2);
        jPanel.add(p3);
        jPanel.add(p4);
        jPanel.add(pOrder);


        frame.add(jPanel);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);
        frame.setSize(600,200);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        addOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();

                int Cid = Integer.parseInt(cText.getText());
                int product = Integer.parseInt(pText.getText());
                int quantity = Integer.parseInt(qText.getText());
                orderBLL.insertOrders(new Orders(Cid,product,quantity));


            }
        });

        deleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();

                int id = Integer.parseInt(cText.getText());
                orderBLL.deleteOrders(id);
            }
        });

        viewOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();

                orderBLL.getAllOrders();
                OrderView o = new OrderView(orderBLL.getAllOrders());
            }
        });

    }

}
