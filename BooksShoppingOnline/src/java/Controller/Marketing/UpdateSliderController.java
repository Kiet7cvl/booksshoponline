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
public class UpdateSliderController extends HttpServlet {

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
            SliderDAO sd = new SliderDAO();
            String slider_title = request.getParameter("slider_title");
            String backlink = request.getParameter("backlink");
            String note = request.getParameter("note");
            int status = 0;
            if (request.getParameter("status") != null && request.getParameter("status").equalsIgnoreCase("true")) {
                status = 1;
            }
            int id = 0;
            String slider_id = request.getParameter("slider_id");
            if (slider_id != null && !slider_id.isEmpty()) {
                id = Integer.parseInt(slider_id);
            }
            int updated_by = 0;
            String userid = request.getParameter("userid");
            if (userid != null && !userid.isEmpty()) {
                updated_by = Integer.parseInt(userid);
            }
            PrintWriter outu = response.getWriter();
            Part filePart = request.getPart("thumbnail");
            String fileName = null;
            String url_thumbnail = "images/slider/";
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();
            try {
                if (filePart != null) {
                    fileName = getFileName(filePart);
                }
                String storePath = servletContext.getRealPath("/images/slider");
                File file = new File(storePath + File.separator + fileName);
                String x = file.getName();
                if (x.equalsIgnoreCase("slider")) {
                    url_thumbnail = sd.getUrlSliderImageById(id);
                } else {
                    url_thumbnail = url_thumbnail + x;
                }

                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();
                int read;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (FileNotFoundException fne) {
                
            }
            
            sd.UpdateSliderById(id, slider_title, url_thumbnail, backlink, note, status, updated_by);
            response.sendRedirect("slider-detail?sliderId=" + id);
        } catch (Exception e) {

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
