package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "select * from user\n"
                + "where email = ?";
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

    public void register(String fullName, String password, String gender, String email, String mobile){
        String sql = "INSERT INTO user (`fullName`, `password`,`gender`, `email`, `mobile`, `status`, `role_id`) VALUES \n" +
                  "(?,?,b?,?,?,0,1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, password);
            st.setString(3, gender);
            st.setString(4, email);
            st.setString(5, mobile);
            st.executeUpdate();
        } catch (Exception e) {
        }
            
    }
    
//    public void editUserProfile(String uname, String uavatar, String ugender, String umobile, String uaddress, int uid) {
//        String sql = "UPDATE `User`\n" +
//    "SET `fullName` = ?,\n" +
//"    `avatar` = ?,\n" +
//"    `gender` = ?,\n" +
//"    `mobile` = ?,\n" +
//"    `address` = ?\n" +
//"WHERE `userId` = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, uname);
//            st.setString(2, uavatar);
//            st.setString(3, ugender);
//            st.setString(4, umobile);
//            st.setString(5, uaddress);
//            st.setInt(6, uid);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("editUserProfile "+e.getMessage());
//        }
//    }
//    
//      public User getUserById(int uid) {
//        try {
//            String sql = "SELECT *\n" +
//"FROM `User`\n" +
//"WHERE `userId` = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, uid);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                User user = User.builder()
//                        .user_Id(rs.getInt(1))
//                        .full_Name(rs.getString(2))
//                        .password(rs.getString(3))
//                        .avatar(rs.getString(4))
//                        .gender(rs.getBoolean(5))
//                        .email(rs.getString(6))
//                        .mobile(rs.getString(7))
//                        .address(rs.getString(8))
//                        .status(rs.getBoolean(9))
//                        .role_Id(rs.getString(10))
//                        .build();
//                return user;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//      
//       public String getUrlImageById(int id) {
//        String sql = "SELECT `avatar`\n" +
//"FROM `books_shop_online`.`User`\n" +
//"WHERE `userId` = 1";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                return rs.getString(1);
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    
//
//    public static void main(String[] args){
//  //       System.out.println(new UserDAO().login("lamdthe@gmail.com", "123456789"));
////       System.out.println(new UserDAO().checkUserExist("kiet1@gmail.com"));
////System.out.println(new UserDAO().editUserProfile("dieu bo", 0, 0123456789, dcxvdgas, 1));
//    }
    
    
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
   
     public User getUserById(int uid) {
        try {
            String sql = "select * from User where userId = ?";
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
                + "  Where userId = 1";
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
    
    
}
