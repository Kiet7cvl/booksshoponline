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
import model.Category;
import model.CategoryBlog;


public class CategoryDAO extends DBContext{

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "Select * from books_shop_online.category where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = Category.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public List<CategoryBlog> getAllCategoryBlog() {
        List<CategoryBlog> list = new ArrayList<>();
        String sql = "Select * from books_shop_online.category_blog where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryBlog c = CategoryBlog.builder()
                        .categoryBlog_id(rs.getInt(1))
                        .categoryBlog_name(rs.getString(2))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
   
    public static void main(String[] args) {
        CategoryDAO sc = new CategoryDAO();
        
//        System.out.println(sc.getTotalProduct(" ", "1", "1"));
        System.out.println(sc.getAllCategory());

    }
    
}
