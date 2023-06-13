
package Controller.Marketing;

import dal.SliderDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Slider;


public class SliderListController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String status_raw = request.getParameter("status");
            String status = "!= -1";
            if (status_raw != null) {
                status = "= " + status_raw;
            }
        List<Slider> SliderList = new SliderDAO().getALLSlider_True_False(status); 
        request.setAttribute("SliderList", SliderList);
        HttpSession session = request.getSession();
            
            String history = "slider-list?status=";
            if (status_raw != null) {
                 history = history + status_raw;
                request.setAttribute("historyStatus",  status_raw);
               
                request.setAttribute("status", status_raw);
            }
            session.setAttribute("historyUrl", history);
        request.getRequestDispatcher("MKTSliderList.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
