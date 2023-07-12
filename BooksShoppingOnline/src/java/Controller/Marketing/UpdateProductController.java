package Controller.Marketing;

import dal.ProductDAO;
import jakarta.mail.Session;
import java.io.File;
import java.io.IOException;
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
import java.io.PrintWriter;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UpdateProductController extends HttpServlet {

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
        PrintWriter outu = response.getWriter();
        outu.print("0");
        try {
            ProductDAO p = new ProductDAO();
            String name = request.getParameter("name");
            String desciption = request.getParameter("desciption");
            String brief_infor = request.getParameter("brief_infor");
            int status = 0;
            if (request.getParameter("status") != null && request.getParameter("status").equalsIgnoreCase("true")) {
                status = 1;
            }

            int id = 0;
            String productId = request.getParameter("productId");
            if (productId != null && !productId.isEmpty()) {
                id = Integer.parseInt(productId);
            }

            int quantity = 0;
            String quantityParam = request.getParameter("quantity");
            if (quantityParam != null && !quantityParam.isEmpty()) {
                quantity = Integer.parseInt(quantityParam);
            }

            int original_price = 0;
            String originalPriceParam = request.getParameter("original_price");
            if (originalPriceParam != null && !originalPriceParam.isEmpty()) {
                original_price = Integer.parseInt(originalPriceParam);
            }

            int sale_price = 0;
            String salePriceParam = request.getParameter("sale_price");
            if (salePriceParam != null && !salePriceParam.isEmpty()) {
                sale_price = Integer.parseInt(salePriceParam);
            }

            int categoryId = 0;
            String categoryIdParam = request.getParameter("categoryId");

            if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
                categoryId = Integer.parseInt(categoryIdParam);
            }

            String author = request.getParameter("author");
            Part filePart = request.getPart("thumbnail");
            String fileName = null;
            String url_thumbnail = "images/product/";
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                if (filePart != null) {
                    fileName = getFileName(filePart);
                }
                String storePath = servletContext.getRealPath("/images/product");
                File file = new File( storePath + File.separator + fileName);
                String x = file.getName();

                if (x.equalsIgnoreCase("product")) {
                    url_thumbnail = p.getUrlProductImageById(id);
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
                // Handle file not found error
            }
            if (original_price <= 0) {
                    request.setAttribute("notification", "Giá gốc sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("product-detail?product_id=" + id).forward(request, response);
            } else if (sale_price <= 0) {
                request.setAttribute("notification", "Giá khuyến mãi sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("product-detail?product_id=" + id).forward(request, response);
            } else if (quantity <= 0) {
                request.setAttribute("notification", "số lượng sản phẩm phải lớn hơn 0");
                    request.getRequestDispatcher("product-detail?product_id=" + id).forward(request, response);
            } else if (sale_price >= original_price) {
                request.setAttribute("notification", "Giá khuyến mãi phải thấp hơn giá gốc");
                    request.getRequestDispatcher("product-detail?product_id=" + id).forward(request, response);
            }
            else {
            p.UpdateProduct(id, name, desciption, brief_infor, quantity, status, original_price, sale_price, categoryId, author, url_thumbnail);
            response.sendRedirect("product-detail?product_id=" + id);
            }
        } catch (Exception e) {
            // Handle generic exception
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
