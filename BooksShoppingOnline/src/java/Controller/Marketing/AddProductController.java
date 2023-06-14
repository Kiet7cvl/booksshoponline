package Controller.Marketing;

import dal.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class AddProductController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        ProductDAO p = new ProductDAO();
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String desciption = request.getParameter("desciption");
        String brief_infor = request.getParameter("brief_infor");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int original_price = Integer.parseInt(request.getParameter("original_price"));
        int sale_price = Integer.parseInt(request.getParameter("sale_price"));
        String imageUrl = "images/product/" + request.getParameter("image");

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String author = request.getParameter("author");
        int new_id = p.addNewProduct(name, desciption, brief_infor, quantity, status, original_price, sale_price, categoryId, author);
        p.AddImageProduct(new_id, imageUrl);
        response.sendRedirect("marketingproductlist");
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
            ProductDAO p = new ProductDAO();
            String name = request.getParameter("name");
            String desciption = request.getParameter("desciption");
            String brief_infor = request.getParameter("brief_infor");
//            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            boolean status;
            if (request.getParameter("status").equalsIgnoreCase("true")) {
                status = true;
            } else {
                status = false;
            }

            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int original_price = Integer.parseInt(request.getParameter("original_price"));
            int sale_price = Integer.parseInt(request.getParameter("sale_price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String author = request.getParameter("author");

            Part filePart = request.getPart("thumbnail");
            String fileName = getFileName(filePart);
            String url_avatar = "images/product";
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
//                File file = new File("images/product" + File.separator + fileName);
                File file = new File("C:/Users/ADMIN/Documents/NetBeansProjects/shopping_online/booksshop/BooksShoppingOnline/web/images/product" + File.separator + fileName);
                url_avatar = "images/product/"+file.getName();
                out = new FileOutputStream(file);
                filecontent = filePart.getInputStream();
                int read;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

            } catch (FileNotFoundException fne) {
                request.setAttribute("notification", "Bạn cần phải điền đầy đủ thông tin cơ bản của sản phẩm");
                request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
            }

            int new_id = p.addNewProduct(name, desciption, brief_infor, quantity, status, original_price, sale_price, categoryId, author);
            p.AddImageProduct(new_id, url_avatar);
            Product u = p.getProductById(new_id);
            response.sendRedirect("marketingproductlist");
        } catch (Exception e) {
            request.setAttribute("notification", "Bạn cần phải điền đầy đủ thông tin cơ bản của sản phẩm");
            request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
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
