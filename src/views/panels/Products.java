package views.panels;

import controller.ProductController;
import database.model.Product;
import enums.Layout;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.ViewDefault;
import views.forms.OrderForm;
import views.forms.ProductForm;
import views.reports.OrderReport;
import views.reports.ProfitReport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Products extends JPanel {
    private List<Product> products;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private ProductController productController;

    public Products() {
        this.layoutConstraints = new LayoutConstraints();
        this.productController = new ProductController();
        this.setBackground(Color.decode("#D9D9D9"));
        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        LabelButton addButton = new LabelButton("Adicionar Produto", 20, 20, 100, 33, Layout.FONT.getValue());
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToProductForm();
            }
        });
        this.add(addButton, constraints);

        layoutConstraints.addColumn();
        LabelButton refreshButton = new LabelButton("Atualizar Lista",20, 20, 100, 33, Layout.FONT.getValue());
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findProducts();
            }
        });
        this.add(refreshButton, constraints);

        layoutConstraints.addColumn();
        LabelButton orderButton = new LabelButton("Gerar Pedido",20, 20, 100, 33, Layout.FONT.getValue());
        orderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToOrderForm();
            }
        });
        this.add(orderButton, constraints);

        layoutConstraints.addColumn();
        LabelButton orderReportButton = new LabelButton("Relatório de Vendas",20, 20, 100, 33, Layout.FONT.getValue());
        orderReportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToOrderReport();
            }
        });
        this.add(orderReportButton, constraints);

        layoutConstraints.addColumn();
        LabelButton profitReportButton = new LabelButton("Relatório de Lucro",20, 20, 100, 33, Layout.FONT.getValue());
        profitReportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToProfitReport();
            }
        });
        this.add(profitReportButton, constraints);
        layoutConstraints.addLine();

        JTable table = this.createTable();
        layoutConstraints.allSpace();
        constraints.gridwidth = 5;
        this.add(table, constraints);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, constraints);
    }

    private JTable createTable() {
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Estoque");
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(60);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(40);
        this.findProducts();

        return table;
    }

    private void findProducts() {
        this.products = this.productController.list();
        tableModel.setRowCount(0);

        for(Product p: this.products) {
            tableModel.addRow(new Object[]{ p.getName(), p.getPrice(), p.getStock() });
        }
    }

    private void goToProductForm() {
        new ProductForm();
    }

    private void goToOrderReport() {
        new OrderReport();
    }

    private void goToProfitReport() {
        new ProfitReport();
    }

    private void goToOrderForm() {
        new OrderForm();
    }

    public class LabelButton extends JLabel {

        public ViewDefault.TypeField typeField;
        public int colors = 1;
        public String texts;

        public LabelButton(String text, int posX, int posY, int width, int height, int sizeFont) {
            this.texts = text;
            setText(this.texts);
            setBounds(posX, posY, width, height);
            setFont(new Font("Arial", Font.BOLD, sizeFont));
            setForeground(Color.decode("#FFFBFB"));
            setHorizontalAlignment(CENTER);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 5));

            addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    colors = 1;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }

                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    colors = 2;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    colors = 1;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }
            });

        }

        protected void paintComponent(Graphics g) {
            g.setColor(selectColor(colors));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight(), 10, 10);
            super.paintComponent(g);
        }
    }

    private Color selectColor(int ID) {
        switch (ID) {
            case 1:	{
                return Color.decode("#1B2B55");
            }
            case 2:	{
                return Color.decode("#3A91C3");
            }
            default:{
                return Color.BLACK;
            }
        }
    }
}
