/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Public;

import dal.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author tr498
 */
public class SuccessfulCheckoutController extends HttpServlet {

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

            String vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_CardType = request.getParameter("vnp_CardType");
            if (vnp_CardType != null && vnp_CardType.isEmpty()) {
                vnp_CardType = "QRCODE";
            }

            if (vnp_BankCode != null && vnp_BankCode.isEmpty()) {
                vnp_BankCode = "VNPAY";
            }
            OrderDao od = new OrderDao();
            String id_raw = request.getParameter("vnp_OrderInfo");
            int id = Integer.parseInt(id_raw);
            if (id != -1) {
                if (vnp_BankCode.equalsIgnoreCase("VNPAY") && vnp_CardType.equalsIgnoreCase("QRCODE")) {
                    od.updateStatusOrder(id, 5);
                    request.setAttribute("notification", "Bạn đã hủy thanh toán thành công");
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                } else {
                    od.updateStatusOrder(id, 3);
                    request.setAttribute("notification", "Thanh toán bằng VNPAY thành công.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                }
            } else {
                od.updateStatusOrder(id, 2);
                request.setAttribute("notification", "Bạn đã chọn phương thức thanh toán khi nhận hàng.");
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
