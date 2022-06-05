package database.dao;

import database.Connection;
import database.model.Product;
import groovy.sql.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Sql db;

    public ProductDAO() {
        this.db = new Connection().getInstance();
    }

    public List<Product> find() {
        List<Product> products = new ArrayList<>();

        try {
            db.rows("SELECT * FROM product").forEach(row -> {
                Product product = new Product();
                product.setName(row.getAt(1).toString());
                product.setPrice((Double) row.getAt(2));
                product.setStock((Integer) row.getAt(3));

                products.add(product);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void create(Product product) {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(product.getName());
        fields.add(product.getPrice());
        fields.add(product.getStock());
        fields.add(product.getCode());
        fields.add(product.getWeight());

        try {
            db.executeInsert("INSERT INTO product (name, price, stock, code, weight) VALUES (?, ?, ?, ?, ?)", fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
