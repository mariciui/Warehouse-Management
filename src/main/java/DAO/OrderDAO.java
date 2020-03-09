package DAO;

import Model.Orders;
import Connection.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static String clientOrders = " SELECT * FROM orders where customerID = ?";
    private final static String selectAllStatementString ="SELECT * FROM orders";
    private static final String insertStatementString = "INSERT INTO orders (price,customerID)" + " VALUES (?,?)";
    private final static String deleteStatementString = "DELETE FROM orders where id = ?";

    public static List<Orders> findAll() {
        List<Orders> list = new LinkedList<Orders>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectAllStatement = null;
        ResultSet rs = null;
        try{
            selectAllStatement = dbConnection.prepareStatement(selectAllStatementString,Statement.RETURN_GENERATED_KEYS);
            selectAllStatement.executeQuery();
            rs =  selectAllStatement.executeQuery();


            while(rs.next())
            {

                int id = rs.getInt(1);
                int price = rs.getInt(2);
                int customerID = rs.getInt(3);
                list.add(new Orders(id,price,customerID));
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findAll");
        }
        finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
            ConnectionFactory.close(rs);
        }
        return list;
    }
    public static int insert(Orders orders) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orders.getPrice());
            insertStatement.setInt(2, orders.getCustomerID());
            //insertStatement.setInt(3,orders.getCustomerID());
            insertStatement.executeUpdate();

           rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrdersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
            ConnectionFactory.close(rs);
        }
        return insertedId;
    }
    public int deleteById(int id)
    {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1,id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrdersDAO: delete");
        }
        finally{
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return id;
}
}

