/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author dongh
 */
public class OrderDao extends DBContext {

        public int createNewOrder(int sum, String fullname, String phone, String address, int user_id, String note) {
       String sql = "INSERT INTO `Order` " +
             "(`total_cost`, `fullName`, `mobile`, `address`, `userId`, `note`) " +
             "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, sum);
            st.setString(2, fullname);
            st.setString(3, phone);
            st.setString(4, address);
            st.setInt(5, user_id);
            st.setString(6, note);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public Order checkProductOrderByUser(int user_Id, int productId) {
        String sql = "SELECT * \n"
                + "FROM `Order` o \n"
                + "JOIN Order_Detail od ON o.order_id = od.order_id \n"
                + "WHERE o.userId = ? AND od.product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_Id);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = Order.builder()
                        .orderID(rs.getInt(1))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }



    public List<Order> getAllOrder(int userId) {
        List<Order> list = new ArrayList<>();
       String sql = "SELECT *\n"
                + "FROM `Order`\n"
                + "JOIN Status_Order ON `Order`.status_order = Status_Order.status_order_id\n"
                + "WHERE userId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = Order.builder()
                        .orderID(rs.getInt(1))
                        .date(rs.getDate(2))
                        .total_cost(rs.getInt(3))
                        .countProduct(getCountProduct(rs.getInt(1)))
                        .fullNameFirstProduct(getFullNameFirstProduct(rs.getInt(1)))
                        .fullName(rs.getString(4))
                        .mobile(rs.getString(5))
                        .address(rs.getString(6))
                        .status_order(rs.getInt(7))
                        .status_order_name(rs.getString(12))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    private int getCountProduct(int id) {
        String sql = "SELECT COUNT(*)\n"
                + "FROM Order_Detail\n"
                + "JOIN Product ON Order_Detail.product_id = Product.product_id\n"
                + "WHERE order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) - 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    private String getFullNameFirstProduct(int id) {
        String sql = "SELECT Product.product_name\n"
                + "FROM Order_Detail\n"
                + "JOIN Product ON Order_Detail.product_id = Product.product_id\n"
                + "WHERE order_id = ?\n"
                + "LIMIT 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
   public void updateStatusOrder(int orderID, int i) {
        String sql = "UPDATE `Order` " +
             "SET `status_order` = ? " +
             "WHERE `order_id` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Order> getAllOrderInDetail(int orderId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT *FROM books_shop_online.Order\n"
                + "                JOIN books_shop_online.Status_Order ON books_shop_online.Order.status_order = books_shop_online.Status_Order.status_order_id\n"
                + "                join books_shop_online.user on books_shop_online.Order.userId=books_shop_online.user.userId\n"
                + "                WHERE order_id =  ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = Order.builder()
                        .orderID(rs.getInt(1))
                        .date(rs.getDate(2))
                        .total_cost(rs.getInt(3))
                        .countProduct(getCountProduct(rs.getInt(1)))
                        .fullNameFirstProduct(getFullNameFirstProduct(rs.getInt(1)))
                        .fullName(rs.getString(4))
                        .mobile(rs.getString(5))
                        .address(rs.getString(6))
                        .status_order(rs.getInt(7))
                        .saler_id(rs.getInt(9))
                        .status_order_name(rs.getString(12))
                        .gender(rs.getString(18))
                        .email(rs.getString(19))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }
    public Order getOrderNew(int user_id) {
        String sql = "SELECT * FROM `Order` WHERE `userId` = ? AND `status_order` IS NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order p = Order.builder()
                        .orderID(rs.getInt(1))
                        .date(rs.getDate(2))
                        .total_cost(rs.getInt(3))
                        .fullName(rs.getString(4))
                        .mobile(rs.getString(5))
                        .address(rs.getString(6))
                        .status_order(rs.getInt(7))
                        .UserId(rs.getInt(8))
                        .saler_id(rs.getInt(9))
                        .note(rs.getString(10))
                        .build();

                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
        public int getAssignOrder() {
        String sql = "SELECT * FROM `User` WHERE `role_id` = 3 " +
             "ORDER BY RAND() LIMIT 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public void updateSalerOrder(int orderID, int saler_id) {
        String sql = "UPDATE `Order` SET `saler_id` = ? WHERE `order_id` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, saler_id);
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
        public static void main(String[] args) {
        OrderDao o = new OrderDao();
        System.out.println(o.getAllOrder(12));
    }
}
