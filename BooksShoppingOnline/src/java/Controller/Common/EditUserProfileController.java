/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;

import dal.UserDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;



/**
 *
 * @author Veetu
 */
//@WebServlet(name = "EditUserProfileController", urlPatterns = {"/edit"})
//public class EditUserProfileController extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        HttpSession session = request.getSession();
//        UserDAO ud = new UserDAO();
//
//        String url_avatar = "images/avatar/";
//
//        // Create a factory for disk-based file items
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//// Configure a repository (to ensure a secure temp location is used)
//        ServletContext servletContext = this.getServletConfig().getServletContext();
//       // File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
//        factory.setRepository(repository);
//
//// Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        upload.setHeaderEncoding("UTF-8");
//
//        try {
//            // Parse the request
//            List<FileItem> items = upload.parseRequest((RequestContext) request);
//            // Process the uploaded items
//            Iterator<FileItem> iter = items.iterator();
//            HashMap<String, String> fields = new HashMap<>();
//            while (iter.hasNext()) {
//                FileItem item = iter.next();
//
//                if (item.isFormField()) {
//                    fields.put(item.getFieldName(), item.getString("UTF-8"));
//
//                } else {
//                    String filename = item.getName();
//                    if (filename == null || filename.equals("")) {
//                        String url_old = ud.getUrlImageById(Integer.parseInt(fields.get("userId")));
//                        url_avatar = url_old;
//                        break;
//                    } else {
//                        Path path = Paths.get(filename);
//                        String storePath = servletContext.getRealPath("../../../../web/images/avatar");
//                        File uploadFile = new File(storePath + "/" + path.getFileName());
//                        item.write(uploadFile);
//                        url_avatar += filename;
//                    }
//
//                }
//            }
//
//            String uid_raw = fields.get("userId");
//            String uname = fields.get("fullName");
//            String umobile = fields.get("mobile");
//            String uaddress = fields.get("address");
//            String ugender = fields.get("gender");
//            int uid = Integer.parseInt(uid_raw);
//
//            ud.editUserProfile(uname, url_avatar, ugender, umobile, uaddress, uid);
//            
//            User u = ud.getUserById(uid);
//            session.setAttribute("us", u);
//            TimeUnit.SECONDS.sleep(2);
//            response.sendRedirect("home");
//            
//        } catch (FileUploadException ex) {
//
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}





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
//
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
//
//        

        // Create a factory for disk-based file items
        org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

// Create a new file upload handler
        org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        try {

//
            String uid_raw = request.getParameter("userId");
            String uname = request.getParameter("fullName");
            String umobile = request.getParameter("mobile");
            String uaddress = request.getParameter("address");
            String url_avatar = "images/avatar/";
            boolean ugender;
            if (request.getParameter("gender") == "1") {
                ugender = true;
            } else {
                ugender = false;
            }
            Part filePart = request.getPart("avatar");
            String fileName = getFileName(filePart);
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();
            try {
                File file = new File("E:\\Ki5\\SWP391\\ShoppingOnile\\BooksShoppingOnline\\web\\WEB-INF\\images\\avatar" + File.separator + uid_raw+"_" + fileName);
                url_avatar = file.getCanonicalPath();
                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (FileNotFoundException fne) {
                writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }

            int uid = Integer.parseInt(uid_raw);

            ud.editUserProfile(uname, url_avatar, ugender, umobile, uaddress, uid);

            User u = ud.getUserById(uid);
            session.setAttribute("us", u);
            TimeUnit.SECONDS.sleep(2);
            response.sendRedirect("home");
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