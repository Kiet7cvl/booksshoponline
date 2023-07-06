/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Marketing;

import dal.CustomerDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.User;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateCustomerController", urlPatterns = {"/update-customer"})
public class UpdateCustomerController extends HttpServlet {

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
        int cid = Integer.parseInt(request.getParameter("customer_id"));
        String cname = request.getParameter("customer_name");
        String cemail = request.getParameter("customer_email");
        String cmobile = request.getParameter("customer_mobile");

        CustomerDAO cus = new CustomerDAO();
      

        //sua thanh cong
        Customer c = cus.checkCustomer(cemail, cmobile, cid);
        if (c != null) {
            request.setAttribute("notification", "Khách hàng đã tồn tại");
            request.getRequestDispatcher("customer-detail?cid=" + cid).forward(request, response);
        } else {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("us");
            cus.updateCustomer(cname, cemail, cmobile, cid);
            cus.updateHistory(cid, cemail, cname, cmobile, u.getUser_Id());

            response.sendRedirect("customer-detail?cid=" + cid);
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
