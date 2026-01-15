/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static org.apache.tomcat.jni.User.username;
import utils.DbUtils;

/**
 *
 * @author Acer
 */
public class UserDAO {

    public ArrayList<UserDTO> list = new ArrayList<>();

    public UserDAO() {
    }

    public UserDTO searchById(String username) {
        UserDTO user = null;
        Connection cnt = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;

        try {
            cnt = DbUtils.getConnection();
            String sql = "SELECT * FROM tblUsers" + "WHERE roleID ='" + username + "'";
            System.out.println(sql);
            st = cnt.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password ");
                String roleID = rs.getString("roleID ");
                boolean status = rs.getBoolean("status");
                // lấy từng cột | userID | fullName | password | roleID | status |

                user = new UserDTO(userID, password, fullName, roleID, status);
            }
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    public UserDTO login(String username, String password) {
        UserDTO u = searchById(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
