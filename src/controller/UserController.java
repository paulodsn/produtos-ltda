package controller;

import database.dao.UserDAO;
import database.model.User;

import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public List<User> list() {
        return this.userDAO.find();
    }

    public void create(User user) {
        this.userDAO.create(user);
    }
}
