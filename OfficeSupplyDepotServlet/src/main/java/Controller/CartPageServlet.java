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

import Beans.BankAccount;
import Beans.CartItem;
import Beans.Customer;
import Beans.PaymentAccount;
import Beans.OSDAdmin;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.*;
import Utilities.Settings;


@WebServlet("/cartpage")
public class CartPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
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
			session.setAttribute("cartItemList", cartItemList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Checkout.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
        
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("CartPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
