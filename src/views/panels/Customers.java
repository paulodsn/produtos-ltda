package views.panels;

import controller.CustomerController;
import database.model.Customer;
import enums.Layout;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.forms.CustomerForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Customers extends JPanel {
    private List<Customer> customers;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private CustomerController customerController;

    public Customers() {
        this.layoutConstraints = new LayoutConstraints();
        this.customerController = new CustomerController();
        this.setBackground(Color.decode("#D9D9D9"));
        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        LabelButton addButton = new LabelButton("Adicionar Cliente", 20, 20, 250, 33, Layout.FONT.getValue());
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToCustomerForm();
            }
        });

        this.add(addButton, constraints);

        layoutConstraints.addColumn();
        LabelButton refreshButton = new LabelButton("Atualizar Lista", 20, 20, 250, 33, Layout.FONT.getValue());
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findCustomers();
            }
        });

        this.add(refreshButton, constraints);

        layoutConstraints.addLine();
        JTable table = this.createTable();
        layoutConstraints.allSpace();
        constraints.gridwidth = 2;
        this.add(table, constraints);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, constraints);
    }

    private JTable createTable() {
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Phone");
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(60);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(2)
                .setPreferredWidth(60);
        table.getColumnModel().getColumn(3)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(4)
                .setPreferredWidth(60);
        this.findCustomers();

        return table;
    }

    private void findCustomers() {
        this.customers = this.customerController.list();
        tableModel.setRowCount(0);

        for(Customer c: this.customers) {
            tableModel.addRow(new Object[]{ c.getName(), c.getEmail(), c.getCpf(), c.getAddress(), c.getPhone() });
        }
    }

    private void goToCustomerForm() {
        new CustomerForm();
    }

    public class LabelButton extends JLabel {
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
            default: {
                return Color.BLACK;
            }
        }
    }

}
