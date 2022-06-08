package views.panels;

import controller.UserController;
import database.model.User;
import enums.Layout;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.ViewDefault;
import views.forms.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Users extends JPanel {
    private List<User> users;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private UserController userController;

    public Users() {
        this.layoutConstraints = new LayoutConstraints();
        this.userController = new UserController();
        this.setBackground(Color.decode("#D9D9D9"));
        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        LabelButton addButton = new LabelButton("Adicionar Usuário", 20, 20, 250, 33, Layout.FONT.getValue());
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToUserForm();
            }
        });
        this.add(addButton, constraints);


        layoutConstraints.addColumn();
        LabelButton refreshButton = new LabelButton("Atualizar Lista", 20, 20, 250, 33, Layout.FONT.getValue());
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findUsers();
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
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        this.findUsers();

        return table;
    }

    private void findUsers() {
        this.users = this.userController.list();
        tableModel.setRowCount(0);

        for(User u: this.users) {
            tableModel.addRow(new Object[]{ u.getName(), u.getEmail() });
        }
    }

    private void goToUserForm() {
        UserForm userForm = new UserForm();
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
