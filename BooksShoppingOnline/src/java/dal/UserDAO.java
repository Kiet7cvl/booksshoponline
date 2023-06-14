package dal;

import context.DBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import model.User;

public class UserDAO extends DBContext {

    public User login(String email, String password) {
        String sql = "select * from user\n"
                + "where email = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = User.builder()
                        .user_Id(rs.getInt(1))
                        .full_Name(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .mobile(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_Id(rs.getString(10))
                        .build();
                return u;

            }
        } catch (Exception e) {
        }
        return null;
    }

    public User checkUserExist(String email) {
        String sql = "SELECT * FROM `User` WHERE userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = User.builder()
                        .user_Id(rs.getInt(1))
                        .full_Name(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .mobile(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_Id(rs.getString(10))
                        .build();
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }


    public void register(String fullName, String password, String gender, String email, String mobile, String address) {
        String sql = "INSERT INTO user (`fullName`, `password`,`gender`, `email`, `mobile`, `address`, `status`, `role_id`) VALUES \n"
                + "(?,?,b?,?,?,?,0,1)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, password);
            st.setString(3, gender);
            st.setString(4, email);
            st.setString(5, mobile);
            st.setString(6, address);
            st.executeUpdate();
        } catch (Exception e) {
        }

    }


    public void changePassword(int userId, String new_pass1) {
        try {
            String sql = "UPDATE `User`\n"
                    + "   SET \n"
                    + "      `password` = ?\n"
                    + " WHERE `userId` = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, new_pass1);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUser(int userId, String old_pass) {
        try {
            String sql = "SELECT * FROM `User` WHERE userId = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, old_pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
                        .user_Id(rs.getInt(1))
                        .full_Name(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .mobile(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_Id(rs.getString(10))
                        .build();
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean chekcAccount(String email) {
        try {
            String sql = "select * from user\n"
                    + "where email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Register error : " + e.getMessage());
        }
        return false;
    }

    public User getUserByEmail(String email) {
        try {
            String sql = "select * from user\n"
                    + "where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
                        .user_Id(rs.getInt(1))
                        .full_Name(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .mobile(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_Id(rs.getString(10))
                        .build();
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }



    public String UpdatePassword(String pass, String email){
        try{
            
           String sql = "UPDATE `user`  SET `password` = ? WHERE `email` = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Get all error " + e.getMessage());
        }
        return null;
    }


 

    public void editUserProfile(String uname, String uavatar, boolean ugender, String umobile, String uaddress, int uid) {
        String sql = "update books_shop_online.User\n"
                + "set fullName = ?,\n"
                + "avatar = ?,\n"
                + "gender = ?,\n"
                + "mobile = ?,\n"
                + "address = ?\n"
                + "where userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, uname);
            st.setString(2, uavatar);
            st.setBoolean(3, ugender);
            st.setString(4, umobile);
            st.setString(5, uaddress);
            st.setInt(6, uid);
            st.executeUpdate();
        } catch (Exception e) {
        }

    }

    public User getUserById(int uid) throws IOException {
        try {
            String sql = "select * from books_shop_online.user where userId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
                        .user_Id(rs.getInt(1))
                        .full_Name(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .mobile(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_Id(rs.getString(10))
                        .build();
                System.out.println();
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getUrlImageById(int id) {
        String sql = "SELECT avatar\n"
                + "  FROM books_shop_online.User\n"
                + "  Where userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getAuthorById(int author_id) {
        String sql = "select * from books_shop_online.User where userId = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, author_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                return rs.getString(2);
            }
        } catch (Exception e) {

                 System.out.println("Get all error "+ e.getMessage());
        }
        return null;
    }

    public void editUserProfile(String uname, String url_avatar, String ugender, String umobile, String uaddress, int uid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}

    