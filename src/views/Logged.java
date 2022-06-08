package views;

import enums.Layout;
import helpers.LayoutConstraints;
import views.panels.Customers;
import views.panels.Products;
import views.panels.Users;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Logged extends ViewDefault {
    private Frames frame;
    private Panels panel;

    private int listShow;

    private String USERS_ITEM = "users";
    private String CUSTOMERS_ITEM = "customers";
    private String PRODUCTS_ITEM = "products";

    Users usersPanel;
    Customers customersPanel;
    Products productsPanel;

    public Logged() {

        frame = new Frames(800, 500);
        panel = new Panels(0,0, frame.getWidth(), frame.getHeight());
        frame.add(panel);
        this.changeContent(USERS_ITEM);
        listShow = 1;

        LabelButton usersBtn = new LabelButton("Usuários",50,400, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        usersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToUsers();
            }
        });
        panel.add(usersBtn);

        LabelButton customersBtn = new LabelButton("Clientes",300,400, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        customersBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToCustomers();
            }
        });
        panel.add(customersBtn);

        LabelButton productsBtn = new LabelButton("Produtos",550,400, 200, 33, TypeField.BUTTON_LOGIN, Layout.FONT.getValue());
        productsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToProducts();
            }
        });
        panel.add(productsBtn);

        ButtonsHover sairButton = new ButtonsHover("sair", 760, 10, 30, 30);
        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Login login = new Login();
                    frame.dispose();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        panel.add(sairButton);

        frame.setVisible(true);
    }

    public void removeContent(){
        switch (listShow){
            case 1:
                panel.remove(usersPanel);
                break;
            case 2:
                panel.remove(customersPanel);
                break;
            case 3:
                panel.remove(productsPanel);
                break;
            default:
                break;
        }
    }

    public void changeContent(String menuItem) {
        if (menuItem == USERS_ITEM) {
            usersPanel = new Users();
            usersPanel.setBounds(20,50, 760, 300);
            panel.add(usersPanel);
        }

        if (menuItem == CUSTOMERS_ITEM) {
            customersPanel =  new Customers();
            customersPanel.setBounds(20,50, 760, 300);
            panel.add(customersPanel);
        }

        if (menuItem == PRODUCTS_ITEM) {
            productsPanel =  new Products();
            productsPanel.setBounds(20,50, 760, 300);
            panel.add(productsPanel);
        }

        frame.repaint();
        frame.revalidate();
    }

    public void goToUsers() {
        removeContent();
        listShow = 1;
        changeContent(USERS_ITEM);
    }
    public void goToCustomers() {
        removeContent();
        listShow = 2;
        changeContent(CUSTOMERS_ITEM);
    }

    public void goToProducts() {
        removeContent();
        listShow = 3;
        changeContent(PRODUCTS_ITEM);
    }
}
