package database.dao;

import database.Database;
import database.model.User;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.sql.*;

public class UserDAO {
    private Database db;

    public UserDAO()  {
        this.db = new Database();
    }

    public User findByNameAndPassword(String email, String password) {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(email);
            params.add(password);
            
            ResultSet result = db.query("select * from user where email = ? and password = ?", params);
            if(result.next()) {
                User user = new User();
                user.setName(result.getString(2));
                user.setEmail(result.getString(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> find() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet result = db.query("select * from user");

            while(result.next()) {
                User user = new User();
                user.setName(result.getString(2));
                user.setEmail(result.getString(3));
                
                users.add(user);
            }
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
