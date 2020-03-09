package DAO;

import Connection.*;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (quantity,price,name)" + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where idproduct = ?";
    private final static String deleteStatementString = "DELETE FROM product where idproduct = ?";
    private final static String updateStatementString = "UPDATE product SET name=?, quantity=?, price=? where idproduct=?";
    private final static String selectAllStatementString ="SELECT * FROM product ";

    public static Product findById(int id)
    {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            int quantity = rs.getInt("quantity");
            int price = rs.getInt("price");
            String name = rs.getString("name");

            toReturn = new Product(id, quantity, price, name);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,product.getQuantity());
            insertStatement.setInt(2, product.getPrice());
            insertStatement.setString(3, product.getName());
            insertStatement.executeUpdate();

           rs= insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
            ConnectionFactory.close(rs);
        }
        return insertedId;
    }

    public void deleteById(int id)
    {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1,id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: delete");
        }
        finally{
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    public void update(Product product, int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement.setInt(2,product.getQuantity());
            updateStatement.setInt(3,product.getPrice());
            updateStatement.setString(1,product.getName());
            updateStatement.setInt(4, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO: update" + e.getMessage());
        } finally {

            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static List<Product> findAll()
    {
        List<Product> list = new ArrayList<Product>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectAllStatement = null;
        ResultSet rs = null;
        try{
            selectAllStatement = dbConnection.prepareStatement(selectAllStatementString,Statement.RETURN_GENERATED_KEYS);
            selectAllStatement.executeQuery();
            rs = selectAllStatement.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt(1);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                String name = rs.getString(2);

                list.add(new Product(id,quantity,price,name));
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findAll");
        }
        finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
}
