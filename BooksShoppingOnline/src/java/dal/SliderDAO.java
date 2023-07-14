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
import java.util.ArrayList;
import java.util.List;
import model.Slider;


public class SliderDAO extends DBContext {

    public Slider getFirstSlider() {
        String sql = "SELECT * FROM books_shop_online.slider WHERE status = 1 LIMIT 1;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = Slider.builder()
                        .id(rs.getInt(1))
                        .build();

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public int getcountSlider() {
        String sql = "SELECT count(*) FROM books_shop_online.slider where status = 1";
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

    public List<Slider> getALLSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM Slider where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = Slider.builder()
                        .id(rs.getInt(1))
                        .slider_title(rs.getString(2))
                        .slider_image(rs.getString(3))
                        .backlink(rs.getString(4))
                        .status(rs.getBoolean(6))
                        .updated_by(rs.getInt(7))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public void changeStatusById(int slider_id, int status) {
        try {
            String sql = "UPDATE Slider\n"
                    + "   SET `status` = ?\n"
                    + " WHERE slider_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, slider_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

     public List<Slider> getALLSlider_True_False(String status) {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM Slider where status " + status;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = Slider.builder()
                        .id(rs.getInt(1))
                        .slider_title(rs.getString(2))
                        .slider_image(rs.getString(3))
                        .backlink(rs.getString(4))
                        .status(rs.getBoolean(6))
                        .updated_by(rs.getInt(7))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public String getUrlSliderImageById(int id) {
        String sql = "select slider_image from Slider where slider_id = ?";
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
    public Slider GetSliderDetailbyID(int sliderId) {
        String sql = "SELECT * FROM Slider where slider_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sliderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = Slider.builder()
                        .id(rs.getInt(1))
                        .slider_title(rs.getString(2))
                        .slider_image(rs.getString(3))
                        .backlink(rs.getString(4))
                        .note(rs.getString(5))
                        .status(rs.getBoolean(6))
                        .updated_by(rs.getInt(7))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    
    public void UpdateSliderById(int slider_id, String slider_title, String url_thumbnail, String backlink, String note, int status, int updated_by) {
        try {
            String sql = "UPDATE Slider\n"
                    + "   SET `slider_title` = ?\n"
                    + "      ,`slider_image` = ?\n"
                    + "      ,`backlink` = ?\n"
                    + "      ,`note` = ?\n"
                    + "      ,`status` = ?\n"
                    + "      ,`updated_by` = ? \n"
                    + " WHERE `slider_id` = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, slider_title);
            st.setString(2, url_thumbnail);
            st.setString(3, backlink);
            st.setString(4, note);
            st.setInt(5, status);
            st.setInt(6, updated_by);
            st.setInt(7, slider_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void AddSlider(String slider_title, String slider_image, String backlink, String note, boolean status) {
        try {
            String sql = "INSERT INTO Slider\n"
                    + "           (`slider_title`\n"
                    + "           ,`slider_image`\n"
                    + "           ,`backlink`\n"
                    + "           ,`note`\n"
                    + "           ,`status`)\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, slider_title);
            st.setString(2, slider_image);
            st.setString(3, backlink);
            st.setBoolean(5, status);
            st.setString(4, note); 
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public static void main(String[] args) {
        SliderDAO sc = new SliderDAO();   
        System.out.println(sc.getUrlSliderImageById(5));
    }
    
}
