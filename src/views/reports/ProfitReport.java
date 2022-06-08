package views.reports;

import controller.OrderController;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.ViewDefault;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProfitReport extends ViewDefault {
    private List<database.model.ProfitReport> profitReportList;
    private CustomTableModel tableModel;
    private OrderController orderController;

    private Frames frame;
    private Panels panel;

    public ProfitReport() {
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
        tableModel.addColumn("Nome do produto");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Lucro");

        table.getColumnModel().getColumn(0)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(2)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(3)
                .setPreferredWidth(60);

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
        this.profitReportList = this.orderController.getProfitByProduct();
        tableModel.setRowCount(0);

        for(database.model.ProfitReport o: this.profitReportList) {
            tableModel.addRow(new Object[]{
                    o.getName(),
                    o.getPrice(),
                    o.getQuantity(),
                    o.getProfit(),
            });
        }
    }
}
