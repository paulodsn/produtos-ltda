package views.reports;

import controller.OrderController;
import helpers.CustomTableModel;
import views.ViewDefault;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class OrderReport extends ViewDefault {
    private List<database.model.OrderReport> orderReportList;
    private CustomTableModel tableModel;
    private OrderController orderController;

    private Frames frame;
    private Panels panel;

    public OrderReport() {
        this.tableModel = new CustomTableModel();
        this.orderController = new OrderController();

        frame = new Frames(800, 500);
        panel = new Panels(0,0, frame.getWidth(), frame.getHeight());
        frame.add(panel);

        ButtonsHover sairButton = new ButtonsHover("sair", 760, 10, 30, 30);
        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        panel.add(sairButton);

        JTable table = new JTable(tableModel);
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Número do pedido");
        tableModel.addColumn("Produto");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Quantidade");

        table.getColumnModel().getColumn(0)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(2)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(3)
                .setPreferredWidth(100);
        table.getColumnModel().getColumn(4)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(5)
                .setPreferredWidth(40);

        this.fetchReportData();
        table.setBounds(20, 60, 760, 400);
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 760, 400);
        panel.add(scrollPane);

        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    private void fetchReportData() {
        this.orderReportList = this.orderController.getOrderByCustomer();
        tableModel.setRowCount(0);

        for(database.model.OrderReport o: this.orderReportList) {
            tableModel.addRow(new Object[]{
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getOrderId(),
                    o.getProductName(),
                    o.getPrice(),
                    o.getQuantity(),
            });
        }
    }
}
