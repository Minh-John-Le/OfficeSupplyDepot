package Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Beans.Product;
import DAO.ProductDAO;

@WebServlet("/addproduct")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		  maxFileSize = 1024 * 1024 * 10, // 10MB
		  maxRequestSize = 1024 * 1024 * 50 // 50MB
		)
public class AddProductPageServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;
    private static final String SAVE_DIR = "Resources";
    
	public void init() throws ServletException {
    	
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<String> errList = new LinkedList<String>();
    	ServletContext context = getServletContext();
        
        // Get the input stream for the properties file
        InputStream input = context.getResourceAsStream("./src/main/webapp/WEB-INF/classes/db.properties");
        
        // Load the properties from the file
        Properties props = new Properties();
		try {
			props.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String url = props.getProperty("db.url");
        String mySQLuser = props.getProperty("db.username");
        String mySQLpassword = props.getProperty("db.password");
        
    	
    	//=============================================
        // Front end input receive
    	String productName = request.getParameter("name");
        String warehouseId = request.getParameter("warehouse");
        String stock = request.getParameter("stock");
        String weight = request.getParameter("weight");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String clickButton = request.getParameter("button");
        String barcode = request.getParameter("barcode");
        String category = request.getParameter("category");
        String imageUrl ="";
        
        //=============================================
        // Create a directory for saving the uploaded file
        //This path for deployment
	    String appPath = request.getServletContext().getRealPath("");
	    //String savePath = appPath + File.separator + SAVE_DIR;
        
        // This path for testing
	    String savePath = "/home/shadowsong/Documents/GitHub/OfficeSupplyDepot/OfficeSupplyDepotServlet/src/main/webapp/"+ File.separator + SAVE_DIR;
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) {
	        fileSaveDir.mkdir();
	    }

	    
	    
	    if (clickButton != null)
        {
        	if (clickButton.equals("Add Product"))
        	{
        		// Prepare Product info
        		ProductDAO productDAO = new ProductDAO(url,mySQLuser, mySQLpassword);
        		Product product = new Product();
        		product.setName(productName);
        		product.setWarehouse_id(Integer.valueOf(warehouseId));
        		product.setStock(Integer.valueOf(stock));
        		product.setWeight(new BigDecimal(weight));
        		product.setDescription(description);
        		product.setPrice(new BigDecimal(price));
        		product.setBarcode(barcode);
        		product.setCategory(category);
        		
        		productDAO.addProduct(product);
        		product = productDAO.getProductByBarcode(product.getBarcode());
        		
        		
        		//========================================================================
        		// Get the file part from the request
        	    Part filePart = request.getPart("file");

        	    // Get the name of the uploaded file
        	    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        	    String fileName = "product_"+String.valueOf(product.getId()) + ".png";
        	    
        	    // Create a file object for the uploaded file
        	    File file = new File(savePath + File.separator + fileName);
        	    
        	    //imageUrl = savePath + File.separator + fileName;
        	    imageUrl = SAVE_DIR + File.separator + fileName;
        	    product.setImageURL(imageUrl);
        	    
        	    
        	    // Delete the file if it already exists
        	    if (file.exists()) {
        	        Files.delete(file.toPath());
        	    }

        	    // Save the file to the file system
        	    filePart.write(file.getAbsolutePath());
        	    
        	    // Update imageUrl
        	    productDAO.updateProduct(product);
        	    
        	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddProductPage.jsp");
		        requestDispatcher.forward(request, response);
        	    return;
        	}	
        }
        	
    }
    
    public void destroy() {
    }

}
