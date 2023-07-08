/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Chart;
import model.Customer;
import model.UpdateCustomer;
import model.User;

public class CustomerDAO extends DBContext {

    public Customer checkCustomer(String fullName, String email, String mobile) {
        String sql = "SELECT customer_id, customer_name, customer_email, customer_mobile "
                + "FROM books_shop_online.customer "
                + "WHERE customer_name = ? AND customer_email = ? OR customer_mobile = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, mobile);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void storedNewCustomer(String fullName, String email, String mobile) {
        try {
            String sql = "INSERT INTO books_shop_online.customer "
                    + "(customer_name, customer_email, customer_mobile) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, mobile);
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Customer> getAllCustomerByRoleId() {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from books_shop_online.customer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .updated_date(rs.getDate(5))
                        .build();
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Customer getCustomerById(int customer_Id) {
        String sql = "select * from customer\n"
                + "where customer_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customer_Id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .updated_date(rs.getDate(5))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateCustomer(String cname, String cemail, String cmobile, int cid) {
        String sql = "UPDATE customer\n"
                + "SET customer_name = ?,\n"
                + "    customer_email = ?,\n"
                + "    customer_mobile = ?,\n"
                + "    updated_date = CURRENT_TIMESTAMP\n"
                + "WHERE customer_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, cname);
            st.setString(2, cemail);
            st.setString(3, cmobile);
            st.setInt(4, cid);

            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Customer checkCustomerExist(String email) {
        String sql = "select * from books_shop_online.customer\n"
                + "where customer_email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .updated_date(rs.getDate(5))
                        .build();
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

   public void addCustomer(String customer_name, String customer_email, String customer_mobile, Boolean status) {
    String sql = "INSERT INTO customer (customer_name, customer_email, customer_mobile, updated_date, status) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?)";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, customer_name);
        st.setString(2, customer_email);
        st.setString(3, customer_mobile);
        st.setBoolean(4, status);
        st.executeUpdate();
    } catch (Exception e) {
        // Xử lý lỗi
    }
}

    public List<Chart> getChartCustomerBar(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM books_shop_online.customer WHERE updated_date = DATE_ADD(?, INTERVAL ? DAY)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "SELECT DATE_ADD(?, INTERVAL ? DAY)";
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

    public List<Chart> getChartCustomerArea(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM books_shop_online.customer WHERE updated_date <= DATE_ADD(?, INTERVAL ? DAY)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "SELECT DATE_ADD(?, INTERVAL ? DAY)";
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

    public int getTotalCustomer(String searchKey, String status) {
        String sql = "Select count(*) from books_shop_online.customer\n"
                + "where status " + status + " and customer_name like N'%" + searchKey + "%'";
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

    public List<Customer> getCustomerWithPaging(int page, int PAGE_SIZE, String searchKey, String type, String value, String status) {
        List<Customer> list = new ArrayList<>();
        int a = (page - 1) * PAGE_SIZE;
        String sql = "select * from Customer\n"
                + "where status " + status + " and customer_name like N'%" + searchKey + "%'\n"
                + " order by " + value + " " + type + " LIMIT ?, ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a);
            st.setInt(2, PAGE_SIZE);
          
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .updated_date(rs.getDate(5))
                        .status(rs.getBoolean(6))
                        .build();
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<UpdateCustomer> getAllUpdateCustomerById(int customer_id) {
        List<UpdateCustomer> list = new ArrayList<>();
        String sql = "SELECT uc.*, u.fullName\n"
                + "FROM Update_Customer uc\n"
                + "JOIN User u ON uc.update_by = u.userId\n"
                + "WHERE uc.customer_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customer_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UpdateCustomer c = UpdateCustomer.builder()
                        .id(rs.getInt(1))
                        .customer_id(rs.getInt(2))
                        .customer_email(rs.getString(3))
                        .customer_name(rs.getString(4))
                        .customer_mobile(rs.getString(5))
                        .update_date(rs.getDate(6))
                        .update_by(rs.getString(8))
                        .build();
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateHistory(int cid, String cemail, String cname, String cmobile, int user_Id) {
        String sql = "INSERT INTO Update_Customer\n"
                + "           (customer_id\n"
                + "           ,email\n"
                + "           ,fullName\n"
                + "           ,mobile\n"
                + "           ,update_by)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setString(2, cemail);
            st.setString(3, cname);
            st.setString(4, cmobile);
            st.setInt(5, user_Id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Customer checkCustomer(String cemail, String cmobile, int cid) {
        String sql = "SELECT customer_id, customer_name, customer_email, customer_mobile\n"
                + "FROM Customer\n"
                + "WHERE (customer_email = ? AND customer_id != ?) OR (customer_mobile = ? AND customer_id != ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cemail);
            st.setInt(2, cid);
            st.setString(3, cmobile);
            st.setInt(4, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = Customer.builder()
                        .customer_id(rs.getInt(1))
                        .customer_name(rs.getString(2))
                        .customer_email(rs.getString(3))
                        .customer_mobile(rs.getString(4))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void changeStatusById(int customerId, int status) {
        try {
            String sql = "UPDATE Customer\n"
                    + "   SET status = ?\n"
                    + " WHERE customer_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, customerId);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
