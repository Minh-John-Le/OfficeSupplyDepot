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
import Beans.Customer;
import Beans.OSDAdmin;
import Beans.Product;
import DAO.CustomerDAO;
import DAO.OSDAdminDAO;
import DAO.ProductDAO;
import Utilities.Settings;


@WebServlet("/inventory")
public class InventoryPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> errList = new LinkedList<String>();
		List<Product> searchProductList = new LinkedList<Product>();
		List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("cartItemList");
		
    	if (cartItemList == null)
    	{
    		cartItemList = new LinkedList<CartItem>();
    	}
    	
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
    	String button = request.getParameter("button");
        //String addToCartButton = request.getParameter("Add To Cart");
        String searchText = request.getParameter("search text");
        String category = request.getParameter("category");
        
        if (button != null)
        {
        	if (button.equals("search"))
        	{
        		ProductDAO productDAO = new ProductDAO(url, mySQLuser, mySQLpassword);
        		
        		if (category.equals("All"))
        		{
        			searchProductList = productDAO.searchProductsByName(searchText);
        		}
        		else 
        		{
        			searchProductList = productDAO.searchProductsByNameAndCategory(searchText, category);
        		}
        		
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
    }
	
	

}
