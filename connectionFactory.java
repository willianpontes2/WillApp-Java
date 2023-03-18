/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author willi
 */
public class connectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/WillApp";
    public static final String USER = "root";
    private static String PASS;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException ex) {
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

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resulSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            
           if (statement != null) {
                statement.close();
            } 
           
           if (resultSet != null)
               resultSet.close();
        }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com a database");
        }
    }
}
