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
    }
}
