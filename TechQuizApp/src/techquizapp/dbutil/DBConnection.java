/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Sameer
 */
public class DBConnection {
    public static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//VIJAY:1521/xe","onlineexam","student");
            JOptionPane.showMessageDialog(null, "Connected successfully to DataBase", "Success!!", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Not Connected to DataBase"+ex, "Error!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
     
            try
            {
                conn.close();
                JOptionPane.showMessageDialog(null, "Disconnected successfully from DataBase", "Success!!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Not Disconnected from DataBase"+ex, "Error!!", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
