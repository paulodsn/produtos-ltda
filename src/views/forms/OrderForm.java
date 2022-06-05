package views.forms;

import controller.OrderController;
import database.model.Order;
import database.model.OrderDetail;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class OrderForm extends JFrame {
    private JPanel panel;
    private LayoutConstraints layoutConstraints;
    private OrderController orderController;

    private JTextField productIdInput;
    private JTextField customerIdInput;
    private JTextField statusIdInput;
    private JTextField quantityInput;

    public OrderForm() {
        this.layoutConstraints = new LayoutConstraints();
        this.orderController = new OrderController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Formulário de Pedidos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 700);
        this.setResizable(false);

        JPanel form = getOrderForm();
        this.getContentPane().add(form);
    }

    public JPanel getOrderForm(){
        GridBagConstraints constraints = layoutConstraints.constraints();

        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel(layout);

        // Customer ID group
        layoutConstraints.addLine();
        JLabel customerIdLabel = new JLabel("ID do Cliente");
        panel.add(customerIdLabel, constraints);
        layoutConstraints.addLine();

        this.customerIdInput = new JTextField(30);
        panel.add(customerIdInput, constraints);
        layoutConstraints.addLine();

        // Status ID group
        layoutConstraints.addLine();
        JLabel statusIdLabel = new JLabel("ID do Status");
        panel.add(statusIdLabel, constraints);
        layoutConstraints.addLine();

        this.statusIdInput = new JTextField(30);
        panel.add(statusIdInput, constraints);
        layoutConstraints.addLine();

        // Product ID group
        layoutConstraints.addLine();
        JLabel productIdLabel = new JLabel("ID do produto");
        panel.add(productIdLabel, constraints);
        layoutConstraints.addLine();

        this.productIdInput = new JTextField(30);
        panel.add(productIdInput, constraints);
        layoutConstraints.addLine();

        // Quantity group
        layoutConstraints.addLine();
        JLabel quantityLabel = new JLabel("Quantidade");
        panel.add(quantityLabel, constraints);
        layoutConstraints.addLine();

        this.quantityInput = new JTextField(30);
        panel.add(quantityInput, constraints);
        layoutConstraints.addLine();

        JButton orderSaveButton = new JButton("Salvar");
        orderSaveButton.addActionListener(e -> this.actionSaveCustomer(e));
        panel.add(orderSaveButton, constraints);

        return panel;
    }

    private void actionSaveCustomer(ActionEvent e) {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        String statusId = this.statusIdInput.getText();
        String customerId = this.customerIdInput.getText();

        Number productId = Integer.parseInt(this.productIdInput.getText());
        Number quantityId = Integer.parseInt(this.quantityInput.getText());

        OrderDetail product = new OrderDetail();
        product.setProductId(productId);
        product.setQuantity(quantityId);

        orderDetailList.add(product);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderStatusId(statusId);
        order.setOrderDetailList(orderDetailList);

        this.orderController.create(order);
        this.dispose();

    }
}
