
package Controller.Common;


import model.SendMail;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String avatar = "images/avatar/1.png";
        // get value of parameter from screen
 
        if (!password.equals(repassword)) {
            request.setAttribute("notification", "Nhập lại mật khẩu không giống nhau");       // gui thong bao check lai re-password
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            UserDAO dao = new UserDAO();
            boolean u = dao.chekcAccount(email);        // check account dùng mail đã tồn tại chưa
            if (!mobile.matches("[0-9]*")) {
                request.setAttribute("notification", "Số điện thoại không hợp lệ");                  // gui thong bao check lai phone
                request.getRequestDispatcher("index.jsp").forward(request, response); 
            } else if (password.length() < 8 || password.length() > 32) {
                request.setAttribute("notification", "Mật khẩu của bạn phải từ 8-32 kí tự");    // gui thong bao check lai password
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else if (u == false) {
                //dang ky thanh cong
                dao.register(fullName,password, avatar, gender, email, mobile, address);       

                SendMail.sendEmailSignup(email);       // gửi tin nhắn về mail người đăng kí
                request.setAttribute("notification", "Đăng kí thành công, vui lòng kiểm tra hòm thư của bạn");     // gửi thống báo thành công cho người dùng 
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("notification", "Email đã tồn tại");                // gửi thống báo lỗi cho người dùng 
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
