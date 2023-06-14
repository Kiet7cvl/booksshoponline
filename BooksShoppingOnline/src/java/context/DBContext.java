package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    public Connection connection;
    
    public DBContext() {
        try {
            String url = "jdbc:mysql://localhost:3306/books_shop_online";
            String username = "root";

            String password = "12345678"; 

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//     Muon test thi bo comment  
    public static void main(String[] args) {
        DBContext db = new DBContext();
        System.out.println(db.connection);
    }
}