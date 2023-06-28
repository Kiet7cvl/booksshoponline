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

import model.Blog;
import model.Chart;

/**
 *
 * @author ADMIN
 */
public class BlogDAO extends DBContext {

    public List<Blog> getAllBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM books_shop_online.blog\n"
                + "              ORDER BY blog_id\n"
                + "                LIMIT 2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Blog> getBlogByCategoryBlogId(int categoryBlog_id) {
        List<Blog> list = new ArrayList<>();
        String sql = "Select * from books_shop_online.blog \n"
                + "where categoryBlog_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryBlog_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Blog getBlogByBlogId(int blog_id) {
        String sql = "Select * from books_shop_online.blog \n"
                + "where blog_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, blog_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .status(rs.getBoolean(9))
                        .build();

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Blog getBlogNew() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM books_shop_online.blog\n"
                + "WHERE status = 1\n"
                + "ORDER BY updated_date DESC\n"
                + "LIMIT 1;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .build();

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalBlog(String searchKey, String categoryId) {
        String sql = "SELECT COUNT(*)\n"
                + "FROM books_shop_online.blog\n"
                + "WHERE categoryBlog_id = " + categoryId + "\n"
                + "  AND status = 1\n"
                + "  AND title LIKE CONCAT('%', '" + searchKey + "', '%');";
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

    public List<Blog> getBlogWithPaging(int page, int PAGE_SIZE, String searchKey, String categoryId, String type, String value) {
        List<Blog> list = new ArrayList<>();
        int a = (page - 1) * 3;
        String sql = "select * from books_shop_online.blog \n"
                + "where categoryBlog_id " + categoryId + " and status = 1  and title like N'%" + searchKey + "%'\n"
                + " order by " + value + " " + type + "  LIMIT ?, ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a);
            st.setInt(2, PAGE_SIZE);
            // st.setInt(3, PAGE_SIZE);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .status(rs.getBoolean(9))
                        .build();

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Blog> getBlogWithPaging(int page, int PAGE_SIZE, String searchKey, String categoryId, String type, String value, String status, String authorId) {
        List<Blog> list = new ArrayList<>();
        int a = (page - 1) * PAGE_SIZE;
        String sql = "select * from books_shop_online.blog\n"
                + "where categoryBlog_id " + categoryId + " and status  " + status + " and author_id " + authorId + " and title like N'%" + searchKey + "%'\n"
                + " order by " + value + " " + type + " LIMIT ?, ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a);
            st.setInt(2, PAGE_SIZE);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author_id(rs.getInt(3))
                        .updated_date(rs.getDate(4))
                        .content(rs.getString(5))
                        .thumbnail(rs.getString(6))
                        .brief_infor(rs.getString(7))
                        .categoryBlog_id(rs.getInt(8))
                        .status(rs.getBoolean(9))
                        .build();

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalBlog(String searchKey, String categoryId, String status, String author) {
        String sql = "SELECT COUNT(blog_id) FROM blog\n"
                + "WHERE categoryBlog_id " + categoryId + " AND status " + status + " AND title LIKE '%" + searchKey + "%';";
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

    public String getUrlImageById(int id) {
        String sql = "select thumbnail from books_shop_online.blog where blog_id = ?";
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

    public List<Chart> getChartBlogBar(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM Blog WHERE updated_date = DATE_ADD(?, INTERVAL ? DAY) AND status = 1;";
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

    public List<Chart> getChartBlogArea(String start, int day) {
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "SELECT COUNT(*) FROM Blog WHERE updated_date <= DATE_ADD(?, INTERVAL ? DAY) AND status = 1;";
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

    public void addNewBlog(String title, int user_id, String content, String url_thumbnail, String brief_infor, int category_id, boolean status) {
        try {
            String sql = "INSERT INTO Blog\n"
                    + "           (title\n"
                    + "           ,author_id\n"
                    + "           ,content\n"
                    + "           ,thumbnail\n"
                    + "           ,brief_infor\n"
                    + "           ,categoryBlog_id\n"
                    + "           ,status)\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setInt(2, user_id);
            st.setString(3, content);
            st.setString(4, url_thumbnail);
            st.setString(5, brief_infor);
            st.setInt(6, category_id);
            st.setBoolean(7, status);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void changeStatusById(int blog_id, int status) {
        try {
            String sql = "UPDATE Blog\n"
                    + "   SET status = ?\n"
                    + " WHERE blog_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, blog_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void UpdateBlogById(String title, int user_id, String content, String url_thumbnail, String brief_infor, int category_id, boolean status, int blog_id) {
        try {
            String sql = "UPDATE Blog\n"
                    + "SET title = ?,\n"
                    + "    author_id = ?,\n"
                    + "    updated_date = CURRENT_TIMESTAMP,\n"
                    + "    content = ?,\n"
                    + "    thumbnail = ?,\n"
                    + "    brief_infor = ?,\n"
                    + "    categoryBlog_id = ?,\n"
                    + "    status = ?\n"
                    + "WHERE blog_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setInt(2, user_id);
            st.setString(3, content);
            st.setString(4, url_thumbnail);
            st.setString(5, brief_infor);
            st.setInt(6, category_id);
            st.setBoolean(7, status);
            st.setInt(8, blog_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        BlogDAO sc = new BlogDAO();
        //  System.out.println(sc.getChartBlogArea("", 4));
        // System.out.println(sc.getBlogWithPaging(1, 2, "bad");    
//        System.out.println(sc.getBlogNew());

    }
}
