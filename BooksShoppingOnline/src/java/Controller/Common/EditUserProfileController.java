/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;

import dal.UserDAO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import model.User;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet(name = "EditUserProfileController", urlPatterns = {"/edit"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class EditUserProfileController extends HttpServlet {

   
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
       
         
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");  
       
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        try {
            String uid_raw = request.getParameter("userId");
            String uname = request.getParameter("fullName");
            String umobile = request.getParameter("mobile");
            String uaddress = request.getParameter("address");
            boolean ugender = request.getParameter("gender").equals("1");
            
            Part filePart = request.getPart("avatar");
            String fileName = getFileName(filePart);            
            String url_avatar = "images/avatar/";        
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {

                
                File file = new File("E:\\Ki5\\SWP391\\ShoppingOnile\\BooksShoppingOnline\\web\\images\\avatar" + File.separator + fileName);

                url_avatar = url_avatar + file.getName();

                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
               
            } catch (FileNotFoundException fne) { 
                request.setAttribute("notification", "Bạn cần điền các thông tin hợp lệ");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } 
            int uid = Integer.parseInt(uid_raw);
            
            ud.editUserProfile(uname, url_avatar, ugender, umobile, uaddress, uid);
            User u = ud.getUserById(uid);
            session.setAttribute("us", u);
            TimeUnit.SECONDS.sleep(2);
            request.setAttribute("notification", "Bạn đã cập nhật hồ sơ thành công");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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