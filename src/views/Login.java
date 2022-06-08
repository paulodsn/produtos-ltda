package views;

import controller.AuthController;
import enums.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Login extends ViewDefault {
    private AuthController authController;

    private Frames frame;
    private Panels panel;

    private LogoLabel logoLabel;
    private TextFieldsBorder emailInput;
    private PassFieldsBorder passwordInput;

    private ButtonsHover sairButton;

    public LabelButton loginButton;

    public Login() throws IOException {
        this.authController = new AuthController();

        frame = new Frames(500, 500);
        panel = new Panels(0,0, frame.getWidth(), frame.getHeight());
        logoLabel = new LogoLabel("logo", 250-(200/2), 90, 200, 130);

        emailInput = new TextFieldsBorder("E-mail", 250-(210/2), 250, 210, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue(), StringLimiter.TypeText.EMAIL, 30);
        emailInput.setText("E-mail");
        passwordInput = new PassFieldsBorder("Senha", 250-(210/2), 293, 210, 33, TypeField.TEXTFIELDS, Layout.FONT.getValue());
        passwordInput.setText("Senha");

        loginButton = new LabelButton("Entrar",250-(210/2), 336, 210, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionLogin();
            }
        });

        sairButton = new ButtonsHover("sair", 460, 10, 30, 30);
        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        panel.add(logoLabel);
        panel.add(emailInput);
        panel.add(passwordInput);
        panel.add(loginButton);
        panel.add(sairButton);


        frame.add(panel);
        frame.setVisible(true);
        panel.requestFocusPanel();
        frame.setAlwaysOnTop(true);
    }

    public void actionLogin() {
        String email = emailInput.getText();
        String password = String.valueOf(passwordInput.getPassword());

        boolean IsValidUser = authController.login(email, password);

        if (IsValidUser) {
            Logged logged = new Logged();
            frame.dispose();
        } else {
            frame.setEnabled(false);
            Frames frameMsgErrorLogin = new Frames(250,150);
            Panels2 panelMsgErrorLogin = new Panels2(0,0,frameMsgErrorLogin.getWidth(),frameMsgErrorLogin.getHeight());
            frameMsgErrorLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JTextArea msgError = new JTextArea("       E-mail ou senha\n            incorreta!");
            msgError.setBounds(10,30,250,50);
            msgError.setFont(new Font("Arial", Font.BOLD, 20));
            msgError.setForeground(Color.BLACK);
            msgError.setEditable(false);
            msgError.setFocusable(false);
            msgError.setOpaque(false);


            LabelButton msgErrorButton = new LabelButton("OK", 125-(50/2), 150-53, 50, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
            msgErrorButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setEnabled(true);
                    frameMsgErrorLogin.dispose();
                }
            });

            panelMsgErrorLogin.add(msgError);
            panelMsgErrorLogin.add(msgErrorButton);
            frameMsgErrorLogin.add(panelMsgErrorLogin);
            frameMsgErrorLogin.setVisible(true);
            frameMsgErrorLogin.setAlwaysOnTop(true);
        }
    }
}
