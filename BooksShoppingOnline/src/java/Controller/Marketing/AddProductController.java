package Controller.Marketing;

import dal.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
public class AddProductController extends HttpServlet {

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
                String storePath = servletContext.getRealPath("/images/product");
                File file = new File(storePath + File.separator + fileName);
                url_avatar = "images/product/" + file.getName();
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
            if (original_price <= 0) {
                    request.setAttribute("notification", "Giá gốc sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
            } else if (sale_price <= 0) {
                request.setAttribute("notification", "Giá khuyến mãi sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
            } else if (quantity <= 0) {
                request.setAttribute("notification", "số lượng sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
            } else if (sale_price >= original_price) {
                request.setAttribute("notification", "Giá khuyến mãi phải thấp hơn giá gốc");
                    request.getRequestDispatcher("MKTAddProduct.jsp").forward(request, response);
            }
            else {
            int new_id = p.addNewProduct(name, desciption, brief_infor, quantity, status, original_price, sale_price, categoryId, author);
            p.AddImageProduct(new_id, url_avatar);
            response.sendRedirect("marketingproductlist");
            }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
