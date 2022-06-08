package database.dao;

import database.Database;
import database.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.SQLException;
import java.sql.*;

public class CustomerDAO {
    private Database db;

    public CustomerDAO() {
        this.db = new Database();
    }

    public List<Customer> find() {
        List<Customer> customers = new ArrayList<>();

        try {
            ResultSet result = this.db.query("SELECT * FROM customer");
            
            while(result.next()) {
                Customer customer = new Customer();
                customer.setName(result.getString(2));
                customer.setEmail(result.getString(3));
                customer.setCpf(result.getString(4));
                customer.setAddress(result.getString(5));
                customer.setPhone(result.getString(6));
                
                customers.add(customer);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return customers;
    }

    public void create(Customer customer) {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(customer.getName());
        fields.add(customer.getEmail());
        fields.add(customer.getPhone());
        fields.add(customer.getAddress());
        fields.add(customer.getCpf());

        try {
            this.db.executeInsert("INSERT INTO customer (name, email, phone, address, cpf) VALUES (?, ?, ?, ?, ?)", fields);
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
