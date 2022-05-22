package database.dao;

import database.Connection;
import database.model.Customer;
import groovy.sql.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Sql db;

    public CustomerDAO() {
        this.db = new Connection().getInstance();
    }

    public List<Customer> find() {
        List<Customer> customers = new ArrayList<>();

        try {
            db.rows("SELECT * FROM customer").forEach(row -> {
                Customer customer = new Customer();
                customer.setName(row.getAt(1).toString());
                customer.setEmail(row.getAt(2).toString());

                customers.add(customer);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public void create(Customer customer) {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(customer.getName());
        fields.add(customer.getEmail());

        try {
            db.executeInsert("INSERT INTO customer (name, email) VALUES (?, ?)", fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
