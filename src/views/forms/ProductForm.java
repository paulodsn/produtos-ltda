package views.forms;

import controller.ProductController;
import database.model.Product;
import views.ViewDefault;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductForm extends ViewDefault {
    private Frames frame;
    private Panels2 panel;

    private TextFieldsBorder tfNome, tfPreco, tfQuantidade, tfCode, tfWeight;
    private ProductController productController;


    public ProductForm() {
        productController = new ProductController();
        frame = new Frames(400, 510);
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
        JLabel jbNome = new JLabel("Produto");
        jbNome.setBounds(20, 30, 100, 30);
        jbNome.setFont(selectFont(TypeField.DEFAULT, 20));
        jbNome.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbNome);

        tfNome = new TextFieldsBorder("Produto", 20, 60, 360, 33, TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NOME, 100);
        tfNome.setText("Produto");
        panel.add(tfNome);

        // PRICE GROUP
        JLabel jbPreco = new JLabel("Preço");
        jbPreco.setBounds(20, 110, 100, 30);
        jbPreco.setFont(selectFont(TypeField.DEFAULT, 20));
        jbPreco.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbPreco);

        tfPreco = new TextFieldsBorder("0.0", 20, 140, 360, 33, TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NUMERO_DECIMAL, 100);
        tfPreco.setText("0.0");
        panel.add(tfPreco);

        // QUANTITY GROUP
        JLabel jbQuantidade = new JLabel("Quantidade em Estoque");
        jbQuantidade.setBounds(20, 190, 300, 30);
        jbQuantidade.setFont(selectFont(TypeField.DEFAULT, 20));
        jbQuantidade.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbQuantidade);

        tfQuantidade = new TextFieldsBorder("0", 20, 220, 360, 33, TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfQuantidade.setText("0");
        panel.add(tfQuantidade);

        // CODE GROUP
        JLabel jbCode = new JLabel("Código");
        jbCode.setBounds(20, 270, 100, 30);
        jbCode.setFont(selectFont(TypeField.DEFAULT, 20));
        jbCode.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbCode);

        tfCode = new TextFieldsBorder("0", 20, 300, 360, 33, TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NUMERO_INTEIRO, 100);
        tfCode.setText("0");
        panel.add(tfCode);

        // WEIGHT GROUP
        JLabel jbWeight = new JLabel("Peso");
        jbWeight.setBounds(20, 350, 100, 30);
        jbWeight.setFont(selectFont(TypeField.DEFAULT, 20));
        jbWeight.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbWeight);

        tfWeight = new TextFieldsBorder("0.0", 20, 380, 360, 33, TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NUMERO_DECIMAL, 100);
        tfWeight.setText("0.0");
        panel.add(tfWeight);


        // SAVE BUTTON
        LabelButton saveBtn = new LabelButton("Salvar", 100, 430, 200, 33, TypeField.BUTTON_LOGIN, 20);
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionSaveProduct();
            }
        });
        panel.add(saveBtn);


        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    private void actionSaveProduct() {
        String name = tfNome.getText();
        Double price = Double.parseDouble(tfPreco.getText());
        Integer stock = Integer.parseInt(tfQuantidade.getText());
        String code = this.tfCode.getText();
        Float weight = Float.parseFloat(this.tfWeight.getText());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setWeight(weight);
        product.setCode(code);

        productController.create(product);
        frame.dispose();
    }
}
