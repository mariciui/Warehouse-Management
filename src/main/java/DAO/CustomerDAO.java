package DAO;

import Model.Customer;
import Connection.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerDAO {
    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO customer (name,address,email)" + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM customer where id = ?";
    private final static String deleteStatementString = "DELETE FROM customer where id = ?";
    private final static String selectAllStatementString ="SELECT * FROM customer ";
    private final static String updateStatementString = "UPDATE customer SET name=?, address=?, email=? where id=? ";

    public static Customer findById(int id)
    {
        Customer toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            toReturn = new Customer(id, name, address, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Customer customer) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, customer.getName());
            insertStatement.setString(3, customer.getAddress());
            insertStatement.setString(2, customer.getEmail());
            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(rs);
            ConnectionFactory.close(dbConnection);
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
            LOGGER.log(Level.WARNING,"CustomerDAO: delete");
        }
        finally{
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static List<Customer> findAll()
    {
        List<Customer> list = new ArrayList<Customer>();
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
                String name = rs.getString(2);
                String address = rs.getString(4);
                String email = rs.getString(3);
                list.add(new Customer(id,name,address,email));
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
    public void update(Customer customer, int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement.setString(1, customer.getName());
            updateStatement.setString(3, customer.getAddress());
            updateStatement.setString(2, customer.getEmail());
            updateStatement.setInt(4,id);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
}
}
