package Presentation;


import BLL.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductFrame {
    private JButton updateProd;
    private JButton insertProd;
    private JButton deleteProd;
    private JButton viewAllProd;
    private JButton back;

    private JPanel jPanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel pProd;
    private JPanel po1;
    private JPanel po2;
    private JPanel po3;
    private JPanel po4;
    private JPanel po5;

    private JLabel id;
    private JLabel quantity;
    private JLabel price;
    private JLabel name;
 //   private JLabel oldId;

    private JTextField idText;
    private JTextField nameText;
    private JTextField quantityText;
    private JTextField priceText;
  //  private JTextField OldIdText;

    private JFrame frame;

    public ProductFrame() {

        frame = new JFrame();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        insertProd = new JButton("INSERT");
        deleteProd = new JButton("DELETE");
        viewAllProd = new JButton("VIEW ALL");
        updateProd = new JButton("UPDATE");
        back = new JButton("BACK");

        id = new JLabel("ID: ");
        name = new JLabel("Name: ");
        price = new JLabel("Price: ");
       quantity = new JLabel("Quantity: ");
      // oldId = new JLabel("Old ID: ");

        JPanel po1 = new JPanel();
        JPanel po2 = new JPanel();
        JPanel po3 = new JPanel();
        JPanel po4 = new JPanel();
        JPanel po5 = new JPanel();

        idText = new JTextField(3);
        nameText = new JTextField(10);
        priceText = new JTextField(10);
        quantityText = new JTextField(10);
     //   OldIdText = new JTextField(3);

        pProd = new JPanel();
        po1.add(id);
        po1.add(idText);
        po2.add(name);
        po2.add(nameText);
        po3.add(price);
        po3.add(priceText);
        po4.add(quantity);
        po4.add(quantityText);
     //   po5.add(oldId);
      //  po5.add(OldIdText);
        pProd.add(po1);
        pProd.add(po2);
        pProd.add(po3);
        pProd.add(po4);
        pProd.add(po5);

        pProd.setLayout(new GridLayout(2,2));

        p1.setBackground(Color.black);
        p2.setBackground(Color.black);
        p3.setBackground(Color.black);
        p4.setBackground(Color.black);
        p5.setBackground(Color.black);

        insertProd.setBackground(Color.white);
        deleteProd.setBackground(Color.white);
        viewAllProd.setBackground(Color.white);
        updateProd.setBackground(Color.white);
        back.setBackground(Color.white);

        p1.add(insertProd);
        p2.add(deleteProd);
        p3.add(viewAllProd);
        p4.add(updateProd);
        p5.add(back);

        jPanel.add(p1);
        jPanel.add(p2);
        jPanel.add(p3);
        jPanel.add(p4);
        jPanel.add(p5);
        jPanel.add(pProd);

        frame.add(jPanel);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);
        frame.setSize(450,200);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        insertProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBLL productBLL = new ProductBLL();

                int id = Integer.parseInt(idText.getText());
                int quantity = Integer.parseInt(quantityText.getText());
                int price = Integer.parseInt(priceText.getText());
                String name = nameText.getText();

                productBLL.insertProduct(new Product(id,quantity,price,name));
            }
        });

        deleteProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBLL productBLL = new ProductBLL();

                int id = Integer.parseInt(idText.getText());

                productBLL.deleteProduct(id);
            }
        });

        updateProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ProductBLL productBLL = new ProductBLL();

                int oldId = Integer.parseInt(idText.getText());
                int id = Integer.parseInt(idText.getText());
                int quantity = Integer.parseInt(quantityText.getText());
                int price = Integer.parseInt(priceText.getText());
                String name = nameText.getText();

                productBLL.updateProduct(oldId,id,quantity,price,name);
            }
        });
        viewAllProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBLL productBLL = new ProductBLL();

                ProductView productView = new ProductView( productBLL.getProducts());
            }
        });

    }
}
