package database;

import groovy.sql.Sql;

import java.sql.SQLException;

public class Connection {
    private String host = "localhost";
    private String port = "3306";
    private String database = "produtos-ltda";

    private String user = "root";
    private String password = "root";

    private Sql instance;

    public Connection() {
        String url = "jdbc:mysql://" + host + ":"+ port + "/" + database;
        String driver = "com.mysql.jdbc.Driver";

        try {
            this.instance = Sql.newInstance(url, user, password, driver);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Sql getInstance() {
        return instance;
    }
}
