package controller;

import database.dao.OrderDAO;
import database.model.Order;
import database.model.OrderReport;
import database.model.ProfitReport;

import java.util.List;

public class OrderController {
    private OrderDAO orderDAO;

    public OrderController() {
        this.orderDAO = new OrderDAO();
    }

    public void create(Order order) {
        this.orderDAO.create(order);
    }

    public List<OrderReport> getOrderByCustomer() {
        return this.orderDAO.getOrderByCustomer();
    }

    public List<ProfitReport> getProfitByProduct() {
        return this.orderDAO.getProfitByProduct();
    }
}
