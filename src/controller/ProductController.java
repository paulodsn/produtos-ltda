package controller;

import database.dao.ProductDAO;
import database.model.Product;

import java.util.List;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> list() {
        return this.productDAO.find();
    }

    public void create(Product product) {
        this.productDAO.create(product);
    }
}
