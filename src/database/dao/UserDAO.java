package database.dao;

import database.Connection;
import database.model.User;
import groovy.sql.GroovyRowResult;
import groovy.sql.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Sql db;

    public UserDAO()  {
        this.db = new Connection().getInstance();
    }

    public User findByNameAndPassword(String email, String password) {
        GroovyRowResult userData = null;
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(email);
            params.add(password);
            userData = db.firstRow("select * from user where email = ? and password = ?", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userData == null) {
            return null;
        }

        User user = new User();
        user.setName((String) userData.getAt(1));
        user.setEmail((String) userData.getAt(2));
        return user;
    }

    public List<User> find() {
        List<User> users = new ArrayList<>();

        try {
            db.rows("select * from user").forEach(row -> {
                User user = new User();
                user.setName((String) row.getAt(1));
                user.setEmail((String) row.getAt(2));
                
                users.add(user);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void create(User user) {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(user.getName());
        fields.add(user.getEmail());
        fields.add(user.getPassword());
        fields.add(user.getRole());

        try {
            db.executeInsert("INSERT INTO user (name, email, password, user_type_id) VALUES (?, ?, ?, ?)", fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
