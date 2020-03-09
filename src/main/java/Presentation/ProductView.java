package Presentation;

import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class ProductView {
    private Class type;
    private List<Product> list;
    private JScrollPane jScrollPane;
    private JTable jTable;
    private JFrame jFrame;
    private DefaultTableModel defaultTableModel;

    public ProductView(List<Product> list) {

        this.list = list;

        if(list.size()!=0)
        {
            jFrame = new JFrame("Product ");
            jFrame.setSize(500,500);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



            type=list.get(0).getClass();
            Object[][] data = new Object[list.size()][type.getDeclaredFields().length];
            Object[] colName = new Object[type.getDeclaredFields().length];

            int i =0, j=0;
            for (Product p : list)
            {
                for (Field field : type.getDeclaredFields())
                {
                    field.setAccessible(true);
                    try{
                        data[i][j] = field.get(p);
                    } catch(IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    j++;
                }
                j=0;
                i++;

            }
            i=0;

            for (Field f : type.getDeclaredFields())
            {
                colName[i]=f.getName();
                i++;
            }
            defaultTableModel=new DefaultTableModel(data,colName);
            jTable=new JTable(defaultTableModel);
            jTable.setEnabled(false);

            jScrollPane = new JScrollPane(jTable);
            jFrame.add(jScrollPane);
        }

    }
}
