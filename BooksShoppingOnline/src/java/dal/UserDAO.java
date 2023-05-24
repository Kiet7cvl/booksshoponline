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

    public static void main(String[] args){
         System.out.println(new UserDAO().login("kiet1@gmail.com", "11112012"));
//       System.out.println(new UserDAO().checkUserExist("kiet1@gmail.com"));
    }
}