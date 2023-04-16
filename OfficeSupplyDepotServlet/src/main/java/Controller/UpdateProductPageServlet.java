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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Beans.Product;
import DAO.ProductDAO;
import Utilities.ValidationUtil;

@WebServlet("/updateproduct")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		  maxFileSize = 1024 * 1024 * 10, // 10MB
		  maxRequestSize = 1024 * 1024 * 50 // 50MB
		)
public class UpdateProductPageServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;
    private static final String SAVE_DIR = "Resources";
    
	public void init() throws ServletException {
    	
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	List<String> errList = new LinkedList<String>();
    	ServletContext context = getServletContext();
        
        // Get the input stream for the properties file
        InputStream input = context.getResourceAsStream("/WEB-INF/classes/db.properties");
        
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
    	String productName = request.getParameter("name").trim();
        String warehouseId = request.getParameter("warehouse");
        String stock = request.getParameter("stock");
        String weight = request.getParameter("weight");
        String description = request.getParameter("description").trim();
        String price = request.getParameter("price");
        String clickButton = request.getParameter("button");
        String barcode = request.getParameter("barcode").trim();
        String category = request.getParameter("category");
        String imageUrl ="";
        String myCheckbox = request.getParameter("myCheckbox");  
        Product updateProduct = (Product) session.getAttribute("updateProduct");
        imageUrl = updateProduct.getImageURL();
        int id = updateProduct.getId();
        
        
        // ================================================
        //Util Package
        ValidationUtil validationUtil = new ValidationUtil();
        
        
        //=============================================
        // Create a directory for saving the uploaded file
        //This path for deployment
	    String appPath = request.getServletContext().getRealPath("");
	    String savePath = appPath + File.separator + SAVE_DIR;
        
        // This path for testing
	    //String savePath = "/home/shadowsong/Documents/GitHub/OfficeSupplyDepot/OfficeSupplyDepotServlet/src/main/webapp/"+ File.separator + SAVE_DIR;
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) {
	        fileSaveDir.mkdir();
	    }
	    
	    
	    
	    
	    if (clickButton != null)
        {
        	if (clickButton.equals("Update Product"))
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
        		product.setImageURL(imageUrl);
        		product.setId(id);	
	
        		if (myCheckbox != null)
        		{
	        		// Get the file part from the request
	        	    Part myfilePart = request.getPart("file");
	        	    
	        	    if (!validationUtil.isPNG(myfilePart))
	        	    {
	        	    	errList.add("Image must be in PNG format!");
	        	    }
        		}
        		
        		if (productName.equals(""))
        		{
        			errList.add("Product Name cannot be empty!");
        		}
        		
        		
        		// Validation Error
    	        if(!errList.isEmpty()) { //has some error
    				request.setAttribute("errlist", errList);
    				RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateProductPage.jsp");
    				requestDispatcher.forward(request, response);
    				return;
    			}
    	        
    	        
        		//========================================================================
        		//Update Image
        		if (myCheckbox != null)
        		{
        			Part filePart = request.getPart("file"); 	    
	        	    
	        	    // Get the name of the uploaded file
	        	    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	        	    String fileName = "product_"+String.valueOf(product.getBarcode()) + ".png";
	        	    
	        	    
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

        		}
        		
        		// Update the product
        		productDAO.updateProduct(product);
        		product = productDAO.getProductByBarcode(product.getBarcode());
        		   		
        		session.setAttribute("updateProduct", product);
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateProductPage.jsp");
		        requestDispatcher.forward(request, response);
        	    return;
        	}	
        }
        	
    }
    
    public void destroy() {
    }

}
