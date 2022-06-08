package database.dao;

import database.Database;
import database.model.Order;
import database.model.OrderDetail;
import database.model.OrderReport;
import database.model.ProfitReport;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.sql.*;

public class OrderDAO {
    private Database db;

    public OrderDAO() {
        this.db = new Database();
    }

    public void create(Order order) {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(order.getOrderStatusId());
        fields.add(order.getCustomerId());

        List<OrderDetail> orderDetailList = order.getOrderDetailList();

        try {
            ResultSet result = this.db.executeInsert("INSERT INTO `order` (order_status_id, customer_id) VALUES (?, ?)", fields);
            if(result.next()) {
                for (int i = 0; i < orderDetailList.size(); i++) {
                    OrderDetail x = orderDetailList.get(i);
                    ArrayList<Object> detailFields = new ArrayList<>();
                    detailFields.add(x.getProductId());
                    detailFields.add(result.getInt(1));
                    detailFields.add(x.getQuantity());

                    try {
                        db.executeInsert("INSERT INTO order_detail (product_id, order_id, quantity) VALUE(?, ?, ?) ", detailFields);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderReport> getOrderByCustomer() {
        List<OrderReport> orderReport = new ArrayList<>();

        try {
            ResultSet result = db.query("" +
                    "SELECT \n" +
                        " c.name as customer_name,\n" +
                        " c.address as customer_address,\n" +
                        " od.order_id as order_id,\n" +
                        " p.name as product_name, \n" +
                        " p.price as price,\n" +
                        " od.quantity as quantity\n" +
                    "FROM\n" +
                        "`order` as o, \n" +
                        "customer as c, \n" +
                        "product as p, \n" +
                        "order_detail as od \n" +
                    "WHERE\n" +
                        "od.product_id = p.id\n" +
                    "AND\n" +
                        "o.customer_id = c.id\n" +
                    "AND\n" +
                    "   o.id = od.order_id\n" +
                    "ORDER BY \n" +
                        "order_id \n" +
                    ";"
            );
            
            while(result.next()) {
                OrderReport order = new OrderReport();
                order.setCustomerName(result.getString(1));
                order.setCustomerAddress(result.getString(2));
                order.setOrderId(result.getInt(3));
                order.setProductName(result.getString(4));
                order.setPrice(result.getDouble(5));
                order.setQuantity(result.getInt(6));

                orderReport.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderReport;
    }

    public List<ProfitReport> getProfitByProduct() {
        List<ProfitReport> profitReport = new ArrayList<>();

        try {
            ResultSet result = db.query("" +
                    "SELECT \n" +
                        "name, \n" +
                        "price, \n" +
                        "quantity,  \n" +
                        "ROUND(price * quantity) as profit\n" +
                    "FROM \n" +
                        "(\n" +
                            "SELECT \n" +
                                "p.name as name, \n" +
                                "p.price as price,\n" +
                                " SUM(od.quantity) as quantity\n" +
                            "FROM \n" +
                                "product as p,\n" +
                                "order_detail as od\n" +
                            "WHERE\n" +
                                "od.product_id = p.id\n" +
                            "GROUP BY\n" +
                                "name,\n" +
                                "price\n" +
                        ") as groupedOrder\n" +
                    ";"
            );
            
            while(result.next()) {
                ProfitReport profit = new ProfitReport();
                profit.setName(result.getString(1));
                profit.setPrice(result.getDouble(2));
                profit.setQuantity(result.getBigDecimal(3));
                profit.setProfit(result.getDouble(4));

                profitReport.add(profit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profitReport;
    }
}
