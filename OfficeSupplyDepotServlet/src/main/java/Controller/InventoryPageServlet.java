package Controller;

import java.io.IOException;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Beans.CartItem;
import Beans.Product;
import Beans.SearchProductFilter;
import DAO.ProductDAO;
import Utilities.Settings;


@WebServlet("/inventory")
public class InventoryPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;
	
	public void init() throws ServletException{
		ServletContext context = getServletContext();
        
    	// Get the input stream for the properties file
    	InputStream input = null ;        
    	String propertiesFile = Settings.getPropertyFile();
        input = context.getResourceAsStream(propertiesFile);
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
        
        productDAO = new ProductDAO(url, mySQLuser, mySQLpassword);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Product> searchProductList = new LinkedList<Product>();
		List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("cartItemList");
		
    	if (cartItemList == null)
    	{
    		cartItemList = new LinkedList<CartItem>();
    	}
    	
    	
        
    	//=============================================
        // Front end input receive
    	String button = request.getParameter("button");
        //String addToCartButton = request.getParameter("Add To Cart");
        String searchText = request.getParameter("search text");
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");
        String updateButton = request.getParameter("Update");
        
        if (button != null)
        {
        	if (button.equals("search"))
        	{
        		
        		if (category.equals("All"))
        		{
        			searchProductList = productDAO.searchProductsByName(searchText, sortBy);
        		}
        		else 
        		{
        			searchProductList = productDAO.searchProductsByNameAndCategory(searchText, category, sortBy);
        		}
        		// update the filter so that it update the search information
        		SearchProductFilter searchProductFilter = (SearchProductFilter) session.getAttribute("searchProductFilter");
        		searchProductFilter.setCategory(category);
        		searchProductFilter.setSearchTerm(searchText);
        		searchProductFilter.setSortBy(sortBy);
        		
        		session.setAttribute("searchProductFilter", searchProductFilter);
        		session.setAttribute("searchProductList", searchProductList);
        		
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("InventoryPage.jsp");
        		requestDispatcher.forward(request, response);
        		return;
        	}
        	
        	if (button.equals("add item"))
        	{
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddProductPage.jsp");
        		requestDispatcher.forward(request, response);
        		return;
        	}
        			
        }
        
        // Update button
        if (updateButton != null)
        {
        	
        	Product updateProduct = productDAO.getProductByBarcode(updateButton);
        	session.setAttribute("updateProduct", updateProduct);
    		
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateProductPage.jsp");
    		requestDispatcher.forward(request, response);
    		return;
        	
        }
    }
	
	

}
