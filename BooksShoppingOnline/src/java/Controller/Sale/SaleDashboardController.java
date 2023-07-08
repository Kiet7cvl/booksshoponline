/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sale;

import dal.DateDAO;
import dal.OrderDao;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Chart;
import model.Date;
import model.User;

/**
 *
 * @author MSI Bravo
 */
@WebServlet(name = "SaleDashboardController", urlPatterns = {"/sale-dashboard"})

public class SaleDashboardController extends HttpServlet {

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
        OrderDao od = new OrderDao();
        DateDAO dd = new DateDAO();
        UserDAO ud = new UserDAO();
        // Lấy ngày trong khoảng 7 ngày trước đến ngày hiện tại
        Date date = dd.get7day();
        String start = date.getStart().toString();
        String end = date.getEnd().toString();
        String salerId = "!= -1";
        // Lấy giá trị tham số từ request nếu có
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        String salerId_raw = request.getParameter("salerId");
        // Kiểm tra nếu có giá trị start và end từ request thì gán lại
        if (start_raw != null) {
            start = start_raw;
            end = end_raw;
        }
        // Kiểm tra nếu có giá trị salerId từ request thì gán lại
        if(salerId_raw != null){
            salerId = "= "+salerId_raw;
        }
        // Tính số ngày trong khoảng thời gian start và end
        int day = dd.CountDayByStartEnd(start, end);
        
        
        // Thiết lập tham số cho đơn hàng
        int totalOrder = od.getTotalOrder(salerId,start, end);
        int totalOrderSubmited = od.getTotalOrderSubmited(salerId,start, end);
        int totalOrderSuccesful = od.getTotalOrderSuccesful(salerId,start, end);
        int totalOrderCanceled = od.getTotalOrderCanceled(salerId,start, end);

        // Thiết lập biểu đồ cho đơn hàng
        List<Chart> listChartOrderBar = od.getChartOrderBar(salerId,start, day);
        int maxListChartOderBar = -1;
        for (Chart o : listChartOrderBar) {
            if (o.getValue() > maxListChartOderBar) {
                maxListChartOderBar = o.getValue();
            }
        }
        
        // Thiết lập biểu đồ doanh thu
        List<Chart> listChartRevenueBar = od.getChartRevenueBar(salerId,start, day);
        List<Chart> listChartRevenueArea = od.getChartRevenueArea(salerId,start, day);
        int maxListChartRevenueBar = -1;
        for (Chart o : listChartRevenueBar) {
            if (o.getValue() > maxListChartRevenueBar) {
                maxListChartRevenueBar = o.getValue();
            }
        }

        maxListChartRevenueBar = (maxListChartRevenueBar / 1000000 + 1) * 1000000;
        int maxListChartRevenueArea = -1;
        for (Chart o : listChartRevenueArea) {
            if (o.getValue() > maxListChartRevenueArea) {
                maxListChartRevenueArea = o.getValue();
            }
        }
        maxListChartRevenueArea = (maxListChartRevenueArea / 1000000 + 1) * 1000000;

        maxListChartOderBar = (maxListChartOderBar / 10 + 1) * 10;
        List<User> listSaler = ud.getAllSaler();

        
        // Thiết lập các tham số cho biểu đồ trong JSP
        request.setAttribute("listChartRevenueBar", listChartRevenueBar);
        request.setAttribute("listChartRevenueArea", listChartRevenueArea);
        request.setAttribute("maxListChartRevenueBar", maxListChartRevenueBar);
        request.setAttribute("maxListChartRevenueArea", maxListChartRevenueArea);
        
        request.setAttribute("listChartOrderBar", listChartOrderBar);
        request.setAttribute("maxListChartOderBar", maxListChartOderBar);
        request.setAttribute("listSaler", listSaler);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("total", totalOrder);
        request.setAttribute("totalSubmit", totalOrderSubmited);
        request.setAttribute("totalSucces", totalOrderSuccesful);
        request.setAttribute("totalCancel", totalOrderCanceled);
        request.setAttribute("salerId", salerId_raw);
        
        request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
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
