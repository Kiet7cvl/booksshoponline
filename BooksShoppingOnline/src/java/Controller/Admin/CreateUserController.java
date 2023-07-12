/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SendMail;


@WebServlet(name = "CreateUserController", urlPatterns = {"/create-user"})
public class CreateUserController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
           
            String role_id = request.getParameter("role_id");
            String gender = request.getParameter("gender");


       
            UserDAO dao = new UserDAO();
            boolean u = dao.chekcAccount(email);
            if (!mobile.matches("[0-9]*")) {
                request.setAttribute("notification", "Your Mobile Invalid");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else if (password.length() < 8 || password.length() > 32) {
                request.setAttribute("notification", "Mật khẩu của bạn ít hơn 8 ký tự hoặc nhiều hơn 32 ký tự");
                request.getRequestDispatcher("AddUser.jsp").forward(request, response);
            } else if (u == false) {
                SendMail.sendEmailSignup(email);
                dao.crNewUser(fullName, password, gender, email, mobile, address,  role_id);
                request.setAttribute("notification", "Thêm thành công!");
                request.getRequestDispatcher("list-user").forward(request, response);
            } else {
                request.setAttribute("notification", "Email đã tồn tại");
                request.getRequestDispatcher("AddUser.jsp").forward(request, response);
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
