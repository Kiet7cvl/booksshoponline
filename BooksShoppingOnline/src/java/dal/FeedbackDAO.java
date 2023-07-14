/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Chart;
import model.ChartStar;
import model.Feedback;


public class FeedbackDAO extends DBContext {

        public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.*, p.product_name \n" +
                     "FROM Feedback f \n" +
                     "JOIN Product p ON f.product_id = p.product_id;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback f = Feedback.builder()
                        .id(rs.getInt(1))
                        .fullName(rs.getString(2))
                        .rated_star(rs.getInt(3))
                        .feedback(rs.getString(4))
                        .image(rs.getString(5))
                        .status(rs.getBoolean(6))
                        .product_id(rs.getInt(7))
                        .user_id(rs.getInt(8))
                        .date(rs.getDate(9))
                        .product_name(rs.getString(10))
                        .build();

                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    public int getTotalFeedback(int product_id) {
        String sql = "SELECT COUNT(feedBack_id) FROM Feedback WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<ChartStar> getChartAvgStar(String start, int day) {
        List<ChartStar> list = new ArrayList<>();
        for (int i = 1; i <= day; i++) {
            double value = 0;
            String sql = "SELECT CAST(AVG(rated_star) AS DECIMAL(10,1))\n"
                    + "FROM feedback\n"
                    + "WHERE date < DATE_ADD(?, INTERVAL ? DAY);";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(2, i);
                st.setString(1, start);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getDouble(1);
                }
                sql = "SELECT DATE_ADD(?, INTERVAL ? DAY);";
                st = connection.prepareStatement(sql);
                st.setInt(2, i);
                st.setString(1, start);
                rs = st.executeQuery();
                while (rs.next()) {
                    ChartStar c = ChartStar.builder()
                            .date(rs.getDate(1))
                            .value(value)
                            .build();
                    list.add(c);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return list;
    }

    public List<Feedback> getAllFeedbackByProductId(int productId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "select * from Feedback where product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback f = Feedback.builder()
                        .id(rs.getInt(1))
                        .fullName(rs.getString(2))
                        .rated_star(rs.getInt(3))
                        .feedback(rs.getString(4))
                        .image(rs.getString(5))
                        .status(rs.getBoolean(6))
                        .product_id(rs.getInt(7))
                        .user_id(rs.getInt(8))
                        .date(rs.getDate(9))
                        .build();

                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }


    public int getAVGStar() {
        String sql = "SELECT AVG(rated_star) "
           + "FROM Feedback;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }


    public int addNewFeedback(String full_Name, int star, String subject, String image, int i, int product_id, int user_Id) {
        try {
            String sql = " INSERT INTO `books_shop_online`.`feedback` ( `fullName`, `rated_star`, `feedback`, `image`, `status`, `product_id`, `userId`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, full_Name);
            st.setInt(2, star);
            st.setString(3, subject);
            st.setString(4, image);
            st.setInt(5, i);
            st.setInt(6, product_id);
            st.setInt(7, user_Id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

        public void deleteFeedbacktById(int id) {
        String sql = "DELETE FROM `Feedback` "
           + "WHERE feedBack_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

        public Feedback getFeedbackUserById(int feedbackId) {
        String sql = "SELECT f.*, p.product_name, u.email, u.mobile\n" +
                     "FROM Feedback f\n" +
                     "JOIN Product p ON f.product_id = p.product_id\n" +
                     "JOIN `User` u ON f.userId = u.userId\n" +
                     "WHERE f.feedback_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, feedbackId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback f = Feedback.builder()
                        .id(rs.getInt(1))
                        .fullName(rs.getString(2))
                        .rated_star(rs.getInt(3))
                        .feedback(rs.getString(4))
                        .image(rs.getString(5))
                        .status(rs.getBoolean(6))
                        .product_id(rs.getInt(7))
                        .user_id(rs.getInt(8))
                        .date(rs.getDate(9))
                        .product_name(rs.getString(10))
                        .email(rs.getString(11))
                        .phone(rs.getString(12))
                        .build();
                return f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    public void changeStatusFeedback(int feeback_id, int status) {
        try {
            String sql = "UPDATE `Feedback` "
                    + "SET `status` = ? "
                    + "WHERE `feedBack_id` = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, feeback_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    

     public List<Chart> getChartFeedbackBar(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM Feedback WHERE `date` = DATE_ADD(?, INTERVAL ? DAY) AND `status` = 1;";
            try {
                PreparedStatement st = connection.prepareStatement(sql);           
                st.setString(1, start);
                st.setInt(2, i);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "SELECT DATE_ADD(?, INTERVAL ? DAY);";
                st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                rs = st.executeQuery();
                while (rs.next()) {
                    Chart c = Chart.builder()
                            .date(rs.getDate(1))
                            .value(value)
                            .build();
                    list.add(c);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return list;
    }

    public List<Chart> getChartFeedbackArea(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM Feedback WHERE `date` <= DATE_ADD(?, INTERVAL ? DAY) AND `status` = 1; ";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "SELECT DATE_ADD(?, INTERVAL ? DAY);";
                st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                rs = st.executeQuery();
                while (rs.next()) {
                    Chart c = Chart.builder()
                            .date(rs.getDate(1))
                            .value(value)
                            .build();
                    list.add(c);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return list;
    }


}
