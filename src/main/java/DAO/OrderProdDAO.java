package DAO;

import Model.Customer;

import Connection.*;
import Model.OrderProd;

import java.sql.*;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderProdDAO {

        protected static final Logger LOGGER = Logger.getLogger(DAO.OrderDAO.class.getName());
        private static final String insertStatementString = "INSERT INTO orderprod (quantity,id_prod,id_orders) VALUES (?,?,?)";
        private final static String selectAllStatementString ="SELECT * FROM orderprod ";

    public static List<OrderProd> findAll() {
        List<OrderProd> list = new LinkedList<OrderProd>();
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
                int quantity = rs.getInt(2);
                int idProd = rs.getInt(3);
                int idOrder = rs.getInt(4);
                list.add(new OrderProd(id,quantity,idProd,idOrder));
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderProdDAO:findAll");
        }
        finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
            ConnectionFactory.close(rs);
        }
        return list;
    }
    public static int insert(OrderProd orderProd) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            //insertStatement.setInt(1, orderProd.getIdorderprod());
            insertStatement.setInt(1, orderProd.getQuantity());
            insertStatement.setInt(2, orderProd.getId_prod());
            insertStatement.setInt(3,orderProd.getId_orders());
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

}
