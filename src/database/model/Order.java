package database.model;

import java.util.List;

public class Order {
    private List<Product> products[];
    private Customer customer;
    private Integer status;

    public List<Product>[] getProducts() {
        return products;
    }

    public void setProducts(List<Product>[] products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
