
package Controller.Marketing;

import dal.SliderDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class AddSliderController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

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
            SliderDAO sd1 = new SliderDAO();
            String slider_title = request.getParameter("slider_title");
            String backlink = request.getParameter("backlink");
            boolean status;
            if (request.getParameter("status").equalsIgnoreCase("true")) {
                status = true;
            } else {
                status = false;
            }
            String note = request.getParameter("note");
            Part filePart = request.getPart("thumbnail");
            String fileName = getFileName(filePart);
            String url_thumbnail = "images/slider/";
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                File file = new File("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\shopping_online\\booksshop\\BooksShoppingOnline\\web\\images\\slider" + File.separator + fileName);
                url_thumbnail = url_thumbnail + file.getName();
                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();
                int read;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

            } catch (FileNotFoundException fne) {
                request.setAttribute("notification", "Bạn cần phải điền đầy đủ thông tin cơ bản của slider");
                request.getRequestDispatcher("MKTAddSlider.jsp").forward(request, response);
            }  
            sd1.AddSlider(slider_title, url_thumbnail, backlink, note, status);
            response.sendRedirect("slider-list");
        } catch (Exception ex) {
            
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