package controller;

import database.dao.UserDAO;
import database.model.User;

public class AuthController {
    private UserDAO userDAO;

    public AuthController() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String email, String password) {
        User user = this.userDAO.findByNameAndPassword(email, password);
        return user != null;
    }
}
