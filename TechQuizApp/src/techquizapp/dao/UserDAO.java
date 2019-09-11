/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.UserPojo;

/**
 *
 * @author Sameer
 */
public class UserDAO {
    public static boolean validateUser(UserPojo user) throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        String qry = "Select * from Users where userid = ? and password = ? and usertype = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public static boolean adduser(UserPojo user)throws SQLException
    {
        String qry = "Select * from users where userid=?";
        boolean status = true;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, user.getUserId());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            status = false;
        else
        {
            qry = "Insert into users values(?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            ps.executeQuery();
        }
        return status;
    }
    public static boolean changePassword(String userId , String password) throws SQLException
    {
        String qry = "Update users set password=? where userId =?";
        boolean status = true;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, password);
        ps.setString(2, userId);
        int ans = ps.executeUpdate();
        if(ans == 0)
            status = false;
        return status;
    }
}
