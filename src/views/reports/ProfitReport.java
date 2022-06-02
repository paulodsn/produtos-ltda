package views.reports;

import controller.OrderController;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfitReport extends JFrame {
    private List<database.model.ProfitReport> profitReportList;
    private CustomTableModel tableModel;
    private OrderController orderController;
    private LayoutConstraints layoutConstraints;

    public ProfitReport() {
        this.tableModel = new CustomTableModel();
        this.layoutConstraints = new LayoutConstraints();
        this.orderController = new OrderController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Relatório de Lucro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 400);
        this.setResizable(false);

        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        JTable table = getTableReport();
        layoutConstraints.allSpace();
        this.getContentPane().add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, constraints);
    }

    public JTable getTableReport() {
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
        return table;
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
