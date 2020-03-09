package Presentation;

import BLL.InsufficientQuantityException;
import BLL.OrderBLL;
import Model.Product;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class OrderProdFrame {

    private JButton insertOrder;
    private JButton deleteOrder;
    private JButton viewAllOrder;
    private JButton addToOrder;
    private JButton back;

    private JPanel jPanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel po1;
    private JPanel po2;
    private JPanel po3;
    private JPanel pOrder;

    private JLabel idprod;
    private JLabel idorder;
    private JLabel quantity;

    private JTextField idProdText;
    private JTextField idOrderText;
    private JTextField quantityText;

    private JFrame frame;
    private ArrayList<Product> productList;

    public OrderProdFrame() {

        productList=new ArrayList<Product>();
        frame = new JFrame();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idprod = new JLabel("ID Product: ");
        idorder = new JLabel("ID Client: ");
        quantity = new JLabel("Quantity: ");

        idProdText = new JTextField(5);
        idOrderText = new JTextField(5);
        quantityText = new JTextField(5);


        JPanel po1 = new JPanel();
        JPanel po2 = new JPanel();
        JPanel po3 = new JPanel();
        JPanel pOrder = new JPanel();
        po1.add(idprod);
        po1.add(idProdText);
        po2.add(idorder);
        po2.add(idOrderText);
        po3.add(quantity);
        po3.add(quantityText);
        pOrder.add(po1);
        pOrder.add(po2);
        pOrder.add(po3);

        pOrder.setLayout(new GridLayout(2,2));

        jPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();

        insertOrder = new JButton("INSERT");
//        deleteOrder = new JButton("DELETE");
        viewAllOrder = new JButton("VIEW ALL");
        addToOrder = new JButton("ADD PRODUCT");
        back = new JButton("BACK");


        p1.setBackground(Color.black);
    //    p2.setBackground(Color.black);
        p3.setBackground(Color.black);
        p4.setBackground(Color.black);
        p5.setBackground(Color.black);


        insertOrder.setBackground(Color.white);
//        deleteOrder.setBackground(Color.white);
        viewAllOrder.setBackground(Color.white);
        addToOrder.setBackground(Color.white);
        back.setBackground(Color.white);

        p1.add(insertOrder);
        //p2.add(deleteOrder);
        p3.add(viewAllOrder);
        p4.add(addToOrder);
        p5.add(back);

        jPanel.add(p1);
        jPanel.add(p2);
        jPanel.add(p3);
        jPanel.add(p4);
        jPanel.add(p5);
        jPanel.add(pOrder);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setSize(500,300);
        frame.add(jPanel);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);

        addToOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idProdText.getText());
                int quantity = Integer.parseInt(quantityText.getText());
                productList.add(new Product(id,quantity,0,""));
                idProdText.setText("");
                quantityText.setText("");
            }
        });

        insertOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idOrderText.getText());
                OrderBLL orderBLL = new OrderBLL();

                try {
                    orderBLL.insertOrder(id,productList);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (InsufficientQuantityException e1) {
                    JOptionPane.showMessageDialog(frame,"Insufficient Quantity");
                } finally {
                    productList.clear();
                }
            }
        });

        viewAllOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();

                OrderView o = new OrderView(orderBLL.getAllOrders());
            }
        });

    }
}
