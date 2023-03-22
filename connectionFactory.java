package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class connectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/WillApp";
    public static final String USER = "root";
    public static final String PASS = ""; // senha da database
    

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
     }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            
            if (statement != null) {
                statement.close();
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com a database");
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resulSet) {
        try {
            if (connection != null) {
                connection.close();
                
                  if (statement != null) {
                   statement.close();
            }
            }
              } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com a database");
        }
    }
}
         
