package BLL;

import DAO.CustomerDAO;
import Model.Customer;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomerBLL {

    public Customer findCustomerById(int id) {
       Customer customer = CustomerDAO.findById(id);
        if (customer == null) {
            throw new NoSuchElementException("The customer with id =" + id + " was not found!");
        }
        return customer;
    }

    public int insertCustomer(Customer customer) {

        return CustomerDAO.insert(customer);
    }
    public void updateCustomer(int oldId,int id,String name,String email,String address)
    {
        Customer customer=new Customer(id,name,email,address);
        CustomerDAO c = new CustomerDAO();
        c.update(customer,oldId);
    }
    public List<Customer> getCustomers()
    {
        return (List<Customer>) CustomerDAO.findAll();
    }
    public void deleteCustomer(int id)
    {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.deleteById(id);
    }
}
