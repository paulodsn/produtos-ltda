package views.forms;

import controller.UserController;
import database.model.User;
import enums.Layout;
import views.ViewDefault;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserForm extends ViewDefault {
    private UserController userController;
    private JComboBox roleComboBox;

    private PassFieldsBorder tfPassword;
    private TextFieldsBorder tfNome, tfEmail;

    private Frames frame;
    private Panels2 panel;

    public UserForm() {
        userController = new UserController();
        frame = new Frames(400, 400);
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

        JLabel jbNome = new JLabel("Nome");
        jbNome.setBounds(20, 30, 100, 30);
        jbNome.setFont(selectFont(TypeField.DEFAULT, 20));
        jbNome.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbNome);

        tfNome = new TextFieldsBorder("Nome", 20, 60, 360, 33,TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.NOME, 100);
        tfNome.setText("Nome");
        panel.add(tfNome);

        JLabel jbCargo = new JLabel("Cargo");
        jbCargo.setBounds(20, 110, 100, 30);
        jbCargo.setFont(selectFont(TypeField.DEFAULT, 20));
        jbCargo.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbCargo);

        String[] choices = {"Atendente", "Administrador"};
        roleComboBox = new JComboBox<String>(choices);
        roleComboBox.setBounds(20, 140, 200, 33);
        roleComboBox.setFont(selectFont(TypeField.DEFAULT, 20));
        roleComboBox.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(roleComboBox);

        JLabel jbEmail = new JLabel("Email");
        jbEmail.setBounds(20, 190, 100, 30);
        jbEmail.setFont(selectFont(TypeField.DEFAULT, 20));
        jbEmail.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbEmail);

        tfEmail = new TextFieldsBorder("Email", 20, 220, 360, 33,TypeField.TEXTFIELDS, 20, StringLimiter.TypeText.EMAIL, 100);
        tfEmail.setText("Email");
        panel.add(tfEmail);

        JLabel jbPassword = new JLabel("Senha");
        jbPassword.setBounds(20, 270, 100, 30);
        jbPassword.setFont(selectFont(TypeField.DEFAULT, 20));
        jbPassword.setForeground(selectForeground(TypeField.TEXTFIELDS));
        panel.add(jbPassword);

        tfPassword = new PassFieldsBorder("Senha", 20, 300, 360, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue());
        tfPassword.setText("Senha");
        panel.add(tfPassword);

        LabelButton saveBtn = new LabelButton("Salvar", 100, 350, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionSaveUser();
            }
        });
        panel.add(saveBtn);


        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    private void actionSaveUser() {
        String name = tfNome.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String role = roleComboBox.getSelectedItem().toString();
        Integer roleId = role == "Administrador" ? 1 : 2;

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(roleId);

        userController.create(user);
        frame.dispose();
    }
}
