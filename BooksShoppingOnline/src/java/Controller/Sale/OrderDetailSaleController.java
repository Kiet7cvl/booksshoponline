/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sale;

import dal.OrderDao;
import dal.OrderDetailDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author MSI Bravo
 */
@WebServlet(name = "OrderDetailSaleController", urlPatterns = {"/order-detail-sale"})

public class OrderDetailSaleController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String orderId_raw = request.getParameter("orderId");
        int orderId = Integer.parseInt(orderId_raw);
        // Lấy danh sách chi tiết đơn hàng từ lớp OrderDetailDAO dựa trên orderId và đặt vào thuộc tính "Order_Detail"
        List<OrderDetail> Order_Detail = new OrderDetailDAO().getDetailAllOrder(orderId);
        request.setAttribute("Order_Detail", Order_Detail);
        // Lấy danh sách đơn hàng của tôi trong chi tiết từ lớp OrderDao dựa trên orderId và đặt vào thuộc tính "listMyOrderinDetail"
        List<Order> listMyOrderinDetail = new OrderDao().getAllOrderInDetail(orderId);
        request.setAttribute("listMyOrderinDetail", listMyOrderinDetail);
        // Lấy danh sách người bán từ lớp UserDAO và đặt vào thuộc tính "listSaler"
        List<User> listSaler = new UserDAO().getAllSaler();
        request.setAttribute("listSaler", listSaler);


        session.setAttribute("historyUrl", "order-detail?orderId=" + orderId_raw);
        request.getRequestDispatcher("SaleOrderDetail.jsp").forward(request, response);
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
