/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ConnectionProvider {

    public static Connection getConnection() {
        //String url = "jdbc:sqlserver://aylesw-server.database.windows.net:1433;databaseName=database-1";
        String url = "jdbc:sqlserver://localhost\\Duy:1433;databaseName=test;sslProtocol=TLSv1.2;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "longan47";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
}
