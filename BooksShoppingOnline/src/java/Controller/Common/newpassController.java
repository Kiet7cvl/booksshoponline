/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author MSI Bravo
 */
@WebServlet(name = "newpass", urlPatterns = {"/newpass"})
public class newpassController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newpass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newpass at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            HttpSession session1 = request.getSession();
            Long time = (Long) session1.getAttribute("time");
            if (System.currentTimeMillis() < time && time != null) {
                request.getRequestDispatcher("components/newpassword.jsp").forward(request, response);
            } else {
                request.setAttribute("notification", "Link không còn tồn tại. Hãy thực hiện lại việc gửi email");
                request.getRequestDispatcher("home").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("notification", "Link không còn tồn tại. Hãy thực hiện lại việc gửi email");
            request.getRequestDispatcher("home").forward(request, response);
        }
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
        int userId = Integer.parseInt(request.getParameter("user_Id"));
//        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String pass2 = request.getParameter("pass2");
        if (pass.matches("((?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,31})")
                && pass2.matches("((?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,31})")
                && (pass.equals(pass2))) {
            UserDAO u = new UserDAO();
            u.changePassword(userId, pass);
            request.setAttribute("notification", "Đổi mật khẩu thành công.");

            request.getRequestDispatcher("home").forward(request, response);
        } else if (!pass.equals(pass2)) {
            request.setAttribute("notification", "Mật khẩu mới hai lần nhập không giống nhau.Hãy nhập lại");
            request.getRequestDispatcher("components/newpassword.jsp").forward(request, response);

        } else if (pass.length() <= 8 || pass.length() > 32) {
            request.setAttribute("notification", "Mật khẩu của bạn ít hơn 8 ký tự hoặc nhiều hơn 32 ký tự.Hãy nhập lại");
            request.getRequestDispatcher("components/newpassword.jsp").forward(request, response);
        }
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
