package database.dao;

import database.Connection;
import database.model.OrderReport;
import database.model.ProfitReport;
import groovy.sql.Sql;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Sql db;

    public OrderDAO() {
        this.db = new Connection().getInstance();
    }

    public List<OrderReport> getOrderByCustomer() {
        List<OrderReport> orderReport = new ArrayList<>();

        try {
            db.rows("" +
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
            ).forEach(row -> {
                OrderReport order = new OrderReport();
                order.setCustomerName(row.getAt(0).toString());
                order.setCustomerAddress(row.getAt(1).toString());
                order.setOrderId((Integer) row.getAt(2));
                order.setProductName(row.getAt(3).toString());
                order.setPrice((Double) row.getAt(4));
                order.setQuantity((Integer) row.getAt(5));

                orderReport.add(order);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderReport;
    }

    public List<ProfitReport> getProfitByProduct() {
        List<ProfitReport> profitReport = new ArrayList<>();

        try {
            db.rows("" +
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
                                "price,\n" +
                                "quantity\n" +
                        ") as groupedOrder\n" +
                    ";"
            ).forEach(row -> {
                ProfitReport profit = new ProfitReport();
                profit.setName(row.getAt(0).toString());
                profit.setPrice((Double) row.getAt(1));
                profit.setQuantity((BigDecimal) row.getAt(2));
                profit.setProfit((Double) row.getAt(3));

                profitReport.add(profit);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profitReport;
    }
}
