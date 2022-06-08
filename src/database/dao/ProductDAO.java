package database.dao;

import database.Database;
import database.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.sql.*;

public class ProductDAO {
    private Database db;

    public ProductDAO() {
        this.db = new Database();
    }

    public List<Product> find() {
        List<Product> products = new ArrayList<>();

        try {
            ResultSet result = db.query("SELECT * FROM product");
            
            while(result.next()) {
                Product product = new Product();
                product.setName(result.getString(2));
                product.setPrice(result.getDouble(3));
                product.setStock(result.getInt(4));

                products.add(product);
            };
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
