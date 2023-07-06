/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sale;

import dal.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MSI Bravo
 */
@WebServlet(name = "UpdateOrderController", urlPatterns = {"/update-order"})

public class UpdateOrderController extends HttpServlet {

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
            String status_raw = request.getParameter("status");
            String note_raw = request.getParameter("note");
            String salerId_raw = request.getParameter("salerId");
            String orderId_raw = request.getParameter("orderId");

            // Khởi tạo biến với giá trị mặc định
            int status = 0;
            int salerId = 1;
            int orderId = 0;

            // Chuyển đổi chuỗi ban đầu thành số nguyên nếu chúng không null hoặc trống
            if (status_raw != null && !status_raw.isEmpty()) {
                status = Integer.parseInt(status_raw);
            }

            if (salerId_raw != null && !salerId_raw.isEmpty()) {
                salerId = Integer.parseInt(salerId_raw);
            }

            if (orderId_raw != null && !orderId_raw.isEmpty()) {
                orderId = Integer.parseInt(orderId_raw);
            }
            // Cập nhật đơn hàng bằng các giá trị đã lấy được
            OrderDao od = new OrderDao();
            od.updateOrder(orderId, status, salerId, note_raw);
            request.setAttribute("notification", "Cập nhật thành công.");
            request.getRequestDispatcher("order-detail-sale?orderId=" + orderId).forward(request, response);
            // response.sendRedirect("order-detail-sale?orderId="+orderId);
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
