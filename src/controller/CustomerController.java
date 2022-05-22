package controller;

import database.dao.CustomerDAO;
import database.model.Customer;

import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAO();
    }

    public List<Customer> list() {
        return this.customerDAO.find();
    }

    public void create(Customer customer) {
        this.customerDAO.create(customer);
    }
}
