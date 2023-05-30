/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public List<Product> get4ProductRandom() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY RAND() LIMIT 4;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .desciption(rs.getString(5))
                        .brief_infor(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .category_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .author(rs.getString(11))
                        .image(getImgProduct(rs.getInt(1)))
                        .rated_star(getRatedProduct(rs.getInt(1)))
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getImgProduct(int id) {
        String sql = "select images from Products_Images where product_id = ?";
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

    public double getRatedProduct(int id) {
        String sql = "select AVG(rated_star) from Feedback where product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalProduct(String searchKey, String categoryId, String status) {
        String sql = "SELECT COUNT(product_id) FROM Product\n"
                + "WHERE category_id " + categoryId + " AND status " + status + " AND product_name LIKE '%" + searchKey + "%';";
//        String sql = "Select count(product_id) from Product "
//                + "where category_id " + categoryId + " and status " + status + " and product_name like N'%" + searchKey + "%'\n";
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

    public List<Product> getProductWithPaging(int page, int PAGE_SIZE, String searchKey, String categoryId, String type, String value, String status) {
        List<Product> list = new ArrayList<>();
        int a = (page - 1) * 8;
        String sql = "select * from product\n"
                + "where category_id " + categoryId + " and status " + status + " and product_name like N'%" + searchKey + "%'\n"
                + " order by " + value + " " + type + " LIMIT ?, ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a);
            st.setInt(2, PAGE_SIZE);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .desciption(rs.getString(5))
                        .brief_infor(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .category_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .image(getImgProduct(rs.getInt(1)))
                        .rated_star(getRatedProduct(rs.getInt(1)))
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductNew() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY update_date DESC LIMIT 1;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .desciption(rs.getString(5))
                        .brief_infor(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .category_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .image(getImgProduct(rs.getInt(1)))
                        .rated_star(getRatedProduct(rs.getInt(1)))
                        .build();

                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getProductById(int productId) {
        String sql = "select * from Product where product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .desciption(rs.getString(5))
                        .brief_infor(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .category_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .image(getImgProduct(rs.getInt(1)))
                        .rated_star(getRatedProduct(rs.getInt(1)))
                        .build();

                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<Product> getProductTop4Category(int productId, int categoryId) { //getProductTop4Category Ramdom
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE category_id = ? AND product_id != ? ORDER BY RAND() LIMIT 4;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .desciption(rs.getString(5))
                        .brief_infor(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .category_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .image(getImgProduct(rs.getInt(1)))
                        .rated_star(getRatedProduct(rs.getInt(1)))
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }


    public void UpdateProduct(int id, String name, String desciption, String brief_infor, int quantity, int status, int original_price, int sale_price, int categoryId, String url) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [product_name] = ?\n"
                    + "      ,[original_prices] = ?\n"
                    + "      ,[sale_prices] = ?\n"
                    + "      ,[product_details] = ?\n"
                    + "      ,[brief_infor] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[category_id] = ?\n"
                    + "      ,[update_date] = GETDATE()\n"
                    + " WHERE product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, original_price);
            st.setInt(3, sale_price);
            st.setString(4, desciption);
            st.setString(5, brief_infor);
            st.setInt(6, status);
            st.setInt(7, quantity);
            st.setInt(8, categoryId);
            st.setInt(9, id);
            st.executeUpdate();
            sql = "UPDATE [dbo].[Products_Images]\n"
                    + "   SET   [images] = ?\n"
                    + " WHERE product_id = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, url);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int addNewProduct(String name, String desciption, String brief_infor, int quantity, boolean status, int original_price, int sale_price, int categoryId) {
        try {
            String sql = "INSERT INTO [dbo].[Product]\n"
                    + "           ([product_name]\n"
                    + "           ,[original_prices]\n"
                    + "           ,[sale_prices]\n"
                    + "           ,[product_details]\n"
                    + "           ,[brief_infor]\n"
                    + "           ,[status]\n"
                    + "           ,[quantity]\n"
                    + "           ,[category_id])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setInt(2, original_price);
            st.setInt(3, sale_price);
            st.setString(4, desciption);
            st.setString(5, brief_infor);
            st.setBoolean(6, status);
            st.setInt(7, quantity);
            st.setInt(8, categoryId);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public void AddImageProduct(int id, String imageUrl) {
        try {
            String sql = "INSERT INTO [dbo].[Products_Images]\n"
                    + "           ([product_id]\n"
                    + "           ,[images])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, imageUrl);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public int getTotalProduct() {
        String sql = "select COUNT(product_id) from Product";
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

    public int getTotalPublishedProduct() {
        String sql = "select COUNT(product_id) from Product where status = 1";
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

    public int getTotalProduct(String end) {
        String sql = "select COUNT(*) from Product where update_date < ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, end);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalProduct(int i, String end) {
        String sql = "select COUNT(*) from Product where category_id = ? and update_date < ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            st.setString(2, end);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

//    public List<Chart> getChartProductBar(String start, int day) {
//        List<Chart> list = new ArrayList<>();
//        for (int i = 1; i <= day; i++) {
//            int value = 0;
//            String sql = "select count(*) from Product where update_date = DATEADD(DAY, ?, ?) and status = 1";
//            try {
//                PreparedStatement st = connection.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                ResultSet rs = st.executeQuery();
//                while (rs.next()) {
//                    value = rs.getInt(1);
//                }
//                sql = "select  DATEADD(DAY, ?, ?)";
//                st = connection.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                rs = st.executeQuery();
//                while (rs.next()) {
//                    Chart c = Chart.builder()
//                            .date(rs.getDate(1))
//                            .value(value)
//                            .build();
//                    list.add(c);
//                }
//
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//
//        return list;
//    }
//    public List<Chart> getChartProductArea(String start, int day) {
//        List<Chart> list = new ArrayList<>();
//        for (int i = 1; i <= day; i++) {
//            int value = 0;
//            String sql = "select count(*) from Product where update_date <= DATEADD(DAY, ?, ?) and status = 1 ";
//            try {
//                PreparedStatement st = connection.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                ResultSet rs = st.executeQuery();
//                while (rs.next()) {
//                    value = rs.getInt(1);
//                }
//                sql = "select  DATEADD(DAY, ?, ?)";
//                st = connection.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                rs = st.executeQuery();
//                while (rs.next()) {
//                    Chart c = Chart.builder()
//                            .date(rs.getDate(1))
//                            .value(value)
//                            .build();
//                    list.add(c);
//                }
//
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//
//        return list;
//    }
    public String getUrlImageById(int id) {
        String sql = "SELECT images FROM Products_Images where product_id = ?";
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

    public void changeStatusById(int product_id, int status) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, product_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        ProductDAO sc = new ProductDAO();

//        System.out.println(sc.getTotalProduct(" ", "!= 1", "= 1"));
//        System.out.println(sc.getProductWithPaging(1, 8, "", "1", "desc", "update_date", "1"));
//          System.out.println(sc.getProductById(1));
//        System.out.println(sc.getUrlImageById(5));
    }

}

