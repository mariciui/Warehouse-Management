package BLL;

import DAO.CustomerDAO;
import DAO.ProductDAO;
import Model.Customer;
import Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    public Product findProductById(int id) {
       Product product = ProductDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The customer with id =" + id + " was not found!");
        }
        return product;
    }

    public int insertProduct(Product product) {

        return ProductDAO.insert(product);
    }
    public void updateProduct(int oldId,int id,int quantity,int price,String name)
    {
        Product product = new Product(id,quantity,price,name);
       ProductDAO p = new ProductDAO();
       p.update(product,oldId);
    }
    public List<Product> getProducts()
    {
        return (List<Product>) ProductDAO.findAll();
    }
    public void deleteProduct(int id)
    {
       ProductDAO productDAO = new ProductDAO();
       productDAO.deleteById(id);
    }
}
