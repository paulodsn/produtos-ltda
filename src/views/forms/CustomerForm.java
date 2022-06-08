package views.forms;

import controller.CustomerController;
import database.model.Customer;
import enums.Layout;
import views.ViewDefault;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerForm extends ViewDefault {
    private CustomerController customerController;
    private TextFieldsBorder tfNome, tfEmail, tfPhone, tfAddress, tfCpf;

    private Frames frame;
    private Panels2 panel;

    public CustomerForm() {
        customerController = new CustomerController();
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

        // NAME GROUP
        JLabel jbNome = new JLabel("Nome");
        jbNome.setBounds(20, 30, 100, 30);
        jbNome.setFont(selectFont(TypeField.DEFAULT, 20));
        jbNome.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbNome);

        tfNome = new TextFieldsBorder("Nome", 20, 60, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NOME, 100);
        tfNome.setText("Nome");
        panel.add(tfNome);

        // EMAIL GROUP
        JLabel jbEmail = new JLabel("Email");
        jbEmail.setBounds(20, 110, 100, 30);
        jbEmail.setFont(selectFont(TypeField.DEFAULT, 20));
        jbEmail.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbEmail);

        tfEmail = new TextFieldsBorder("Email", 20, 140, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.EMAIL, 100);
        tfEmail.setText("Email");
        panel.add(tfEmail);

        // CPF GROUP
        JLabel JbCpf = new JLabel("CPF");
        JbCpf.setBounds(20, 190, 100, 30);
        JbCpf.setFont(selectFont(TypeField.DEFAULT, 20));
        JbCpf.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(JbCpf);

        tfCpf = new TextFieldsBorder("0000000000", 20, 220, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfCpf.setText("0000000000");
        panel.add(tfCpf);

        // ADDRESS GROUP
        JLabel jbAddress = new JLabel("Endereço");
        jbAddress.setBounds(20, 270, 100, 30);
        jbAddress.setFont(selectFont(TypeField.DEFAULT, 20));
        jbAddress.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbAddress);

        tfAddress = new TextFieldsBorder("Endereço", 20, 300, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.ENDERECO, 100);
        tfAddress.setText("Endereço");
        panel.add(tfAddress);

        // PHONE GROUP
        JLabel jbPhone = new JLabel("Telefone");
        jbPhone.setBounds(20, 350, 100, 30);
        jbPhone.setFont(selectFont(TypeField.DEFAULT, 20));
        jbPhone.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbPhone);

        tfPhone = new TextFieldsBorder("999999999", 20, 380, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfPhone.setText("999999999");
        panel.add(tfPhone);

        // SAVE BUTTON
        LabelButton saveBtn = new LabelButton("Salvar", 100, 430, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionSaveCustomer();
            }
        });
        panel.add(saveBtn);


        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    private void actionSaveCustomer() {
        String name = this.tfNome.getText();
        String email = this.tfEmail.getText();
        String phone = this.tfPhone.getText();
        String address = this.tfAddress.getText();
        String cpf = this.tfCpf.getText();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCpf(cpf);

        customerController.create(customer);
        frame.dispose();
    }
}
