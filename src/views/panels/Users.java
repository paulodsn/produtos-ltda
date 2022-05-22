package views.panels;

import controller.UserController;
import database.model.User;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.forms.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Users extends JPanel {
    private List<User> users;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private UserController userController;

    public Users() {
        this.layoutConstraints = new LayoutConstraints();
        this.userController = new UserController();

        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        JButton addButton = new JButton("Adicionar usuário");
        addButton.addActionListener(e -> this.goToUserForm(e));
        this.add(addButton, constraints);

        layoutConstraints.addColumn();
        JButton refreshButton = new JButton("Atualizar a lista");
        refreshButton.addActionListener(e -> this.findUsers());
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
        tableModel.addColumn("name");
        tableModel.addColumn("email");
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        findUsers();

        return table;
    }

    private void findUsers() {
        this.users = this.userController.list();
        tableModel.setRowCount(0);

        for(User u: this.users) {
            tableModel.addRow(new Object[]{ u.getName(), u.getEmail() });
        }
    }

    private void goToUserForm(ActionEvent e) {
        UserForm userForm = new UserForm();
        userForm.setVisible(true);
    }
}
