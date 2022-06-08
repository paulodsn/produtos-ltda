package views.forms;

import controller.OrderController;
import database.model.Order;
import database.model.OrderDetail;
import enums.Layout;
import views.ViewDefault;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class OrderForm extends ViewDefault {
    private OrderController orderController;
    private TextFieldsBorder tfProductId, tfCustomerId, tfStatusId, tfQuantity;

    private Frames frame;
    private Panels2 panel;

    public OrderForm() {
        this.orderController = new OrderController();
        frame = new Frames(400, 500);
        panel = new Panels2(0,0, frame.getWidth(), frame.getHeight());
        frame.add(panel);

        ButtonsHover sairButton = new ButtonsHover("sair", 360, 10, 30, 30);
        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        panel.add(sairButton);

        // Customer ID group
        JLabel jbCustomerId = new JLabel("ID do cliente");
        jbCustomerId.setBounds(20, 30, 400, 30);
        jbCustomerId.setFont(selectFont(TypeField.DEFAULT, 20));
        jbCustomerId.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbCustomerId);

        tfCustomerId = new TextFieldsBorder("0", 20, 60, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfCustomerId.setText("0");
        panel.add(tfCustomerId);

        // Status ID group
        JLabel jbStatusId = new JLabel("ID do status");
        jbStatusId.setBounds(20, 110, 400, 30);
        jbStatusId.setFont(selectFont(TypeField.DEFAULT, 20));
        jbStatusId.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbStatusId);

        tfStatusId = new TextFieldsBorder("0", 20, 140, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfStatusId.setText("0");
        panel.add(tfStatusId);

        // Product ID group
        JLabel jbProductId = new JLabel("ID do produto");
        jbProductId.setBounds(20, 190, 400, 30);
        jbProductId.setFont(selectFont(TypeField.DEFAULT, 20));
        jbProductId.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbProductId);

        tfProductId = new TextFieldsBorder("0", 20, 220, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfProductId.setText("0");
        panel.add(tfProductId);


        // Quantity group
        JLabel jbQuantity = new JLabel("Quantidade");
        jbQuantity.setBounds(20, 270, 400, 30);
        jbQuantity.setFont(selectFont(TypeField.DEFAULT, 20));
        jbQuantity.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbQuantity);

        tfQuantity = new TextFieldsBorder("0", 20, 300, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfQuantity.setText("0");
        panel.add(tfQuantity);

        //SAVE BUTTON
        LabelButton saveBtn = new LabelButton("Salvar", 100, 350, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveOrder();
            }
        });
        panel.add(saveBtn);

        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    private void saveOrder() {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        String statusId = this.tfStatusId.getText();
        String customerId = this.tfCustomerId.getText();

        Number productId = Integer.parseInt(this.tfProductId.getText());
        Number quantityId = Integer.parseInt(this.tfQuantity.getText());

        OrderDetail product = new OrderDetail();
        product.setProductId(productId);
        product.setQuantity(quantityId);

        orderDetailList.add(product);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderStatusId(statusId);
        order.setOrderDetailList(orderDetailList);

        this.orderController.create(order);
        frame.dispose();
    }
}
