package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import Beans.ShipMethod;
import DAO.ShipMethodDAO;
import Utilities.CheckoutUtil;
import Utilities.Settings;


@WebServlet("/cartpage")
public class CartPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ShipMethodDAO shipMethodDAO;

	public void init() throws ServletException {
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
        
        shipMethodDAO = new ShipMethodDAO(url, mySQLuser, mySQLpassword);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	//=============================================
        // Front end input receive
      
		LinkedList<CartItem> cartItemList = (LinkedList<CartItem>) session.getAttribute("cartItemList");
		String removeButton = request.getParameter("remove");
		String nextButton = request.getParameter("next");
		if (removeButton != null)
		{
			for (int i = 0; i < cartItemList.size(); i++)
			{
				if (removeButton.equals(cartItemList.get(i).getProduct().getBarcode()))
				{
					
					for (int j = 0; j < cartItemList.size(); j++)
					{
						String quantity = request.getParameter("quantity_" + cartItemList.get(j).getProduct().getBarcode());
						cartItemList.get(j).setQuantity(Integer.parseInt(quantity));

					}
					cartItemList.remove(i);
					session.setAttribute("cartItemList", cartItemList);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("CartPage.jsp");
					requestDispatcher.forward(request, response);
					return;
				}
			}
		}
		
		if(nextButton != null)
		{
			for (int j = 0; j < cartItemList.size(); j++)
			{
				String quantity = request.getParameter("quantity_" + cartItemList.get(j).getProduct().getBarcode());
				cartItemList.get(j).setQuantity(Integer.parseInt(quantity));

			}
			
			
			CheckoutUtil checkoutUtil = new CheckoutUtil();
			
			
			BigDecimal subtotal = checkoutUtil.getSubtotal(cartItemList);
			BigDecimal weight = checkoutUtil.getWeight(cartItemList);
			String shipmethodListStr = checkoutUtil.getAvailableShippingMethod(cartItemList);
			int totalItem = checkoutUtil.getTotalItem(cartItemList);
			List<ShipMethod> availableShipMethodList = shipMethodDAO.getAvailableShipMethod(shipmethodListStr);
			
			
			session.setAttribute("cartItemList", cartItemList);
			session.setAttribute("subtotal", subtotal);
			session.setAttribute("weight", weight);
			session.setAttribute("totalItem", totalItem);
			session.setAttribute("availableShipMethodList", availableShipMethodList);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShipmentPage.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
        
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("CartPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
