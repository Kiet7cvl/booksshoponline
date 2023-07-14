/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
 

@WebServlet(name = "DetailsUserController", urlPatterns = {"/UpUser"})
public class DetailsUserController extends HttpServlet {

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
            out.println("<title>Servlet UpUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpUser at " + request.getContextPath() + "</h1>");
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
        //int user_Id = Integer.parseInt(request.getParameter("user_Id"));
//            int status = 0;
        String user_Id = request.getParameter("user_Id");
        UserDAO userDAO = new UserDAO();
        User up = userDAO.getUserByIdd(user_Id);// Lấy thông tin người dùng từ UserDAO bằng user_Id
//            userDAO.UpdateStatusUser(status, user_Id);
        request.setAttribute("us", up);
        request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
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
        //int user_Id = Integer.parseInt(request.getParameter("user_Id"));

        String full_Name = request.getParameter("full_Name");
        String password = request.getParameter("password");
        int gender = Integer.parseInt(request.getParameter("sex_id"));
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        int status = Integer.parseInt(request.getParameter("status"));

        int role_Id = Integer.parseInt(request.getParameter("role_Id"));
        int  user_Id = Integer.parseInt(request.getParameter("user_Id"));
        UserDAO dao = new UserDAO();
        dao.UpUser(user_Id, full_Name, password, gender, email, mobile, address, status, role_Id);//gọi  UpUser() để cập nhật thông tin người dùng với các thông tin mới.

        response.sendRedirect("list-user");
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
