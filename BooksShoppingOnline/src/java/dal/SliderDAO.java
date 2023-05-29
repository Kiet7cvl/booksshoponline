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
import model.Slider;

/**
 *
 * @author ADMIN
 */
public class SliderDAO extends DBContext{
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
    public List<Slider> getALLSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM books_shop_online.slider where status = 1";
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
    public static void main(String[] args) {
        System.out.println(new SliderDAO().getFirstSlider());
    }
}
