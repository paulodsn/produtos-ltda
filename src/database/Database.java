package database;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;


public class Database {
    private String host = "localhost";
    private String port = "3306";
    private String database = "produtos-ltda";

    private String user = "root";
    private String password = "root";

    protected Connection connection;

    public Database() {
        String url = "jdbc:mysql://" + host + ":"+ port + "/" + database;
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, this.user, this.password );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }
    
    /**
     *
     * @param query
     * @throws java.sql.SQLException    * @param query
     * @return
     */
    public ResultSet query(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    
    public ResultSet executeInsert(String query, ArrayList<Object> args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        Database.mapParams(statement, args);
        statement.execute();
        
        return statement.getGeneratedKeys();
    }
    
    public ResultSet query(String query, ArrayList<Object> args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        Database.mapParams(statement, args);
        return statement.executeQuery();
    }
    
    public static void mapParams(PreparedStatement ps, ArrayList<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {         
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
    }
}
