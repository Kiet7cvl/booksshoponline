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

    public static void main(String[] args) {
//        System.out.println(new OrderDao().checkProductOrderByUser(1, 2));
        OrderDao o = new OrderDao();
        System.out.println(o.checkProductOrderByUser(11, 1));
    }

    public List<Order> getAllOrder(int userId) {
        List<Order> list = new ArrayList<>();
//        String sql = "select * from [Order] join Status_Order\n"
//                + "on [Order].status_order= Status_Order.status_order_id\n"
//                + "where userId = ?";

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

    public int createNewOrder(int sum, String fullname, String phone, String address, int user_id, String note) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([total_cost]\n"
                + "           ,[fullName]\n"
                + "           ,[mobile]\n"
                + "           ,[address]\n"
                + "           ,[userId]\n"
                + "           ,[note])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?) ";
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

    public void updateStatusOrder(int orderID, int i) {
        String sql = "UPDATE `Order`\n"
                + "SET `status_order` = ?\n"
                + "WHERE order_id = ?";
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
}
