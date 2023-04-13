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
import Beans.Customer;
import Beans.PaymentAccount;
import Beans.OSDAdmin;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.OSDAdminDAO;
import Utilities.Settings;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	List<String> errList = new LinkedList<String>();
	    	HttpSession session = request.getSession();
	    	ServletContext context = getServletContext();
	    	String clickButton = request.getParameter("button");
	    	Customer loginCustomer =  (Customer) session.getAttribute("loginCustomer");
	    	OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
	    	PaymentAccount paymentAccount = (PaymentAccount) session.getAttribute("paymentAccount");
	    	
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
	    	String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String name = request.getParameter("display-name");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String accountName = request.getParameter("account-name");
	        String accountNumber = request.getParameter("account-number");
	        String expDate = request.getParameter("exp");
	        boolean isCustomer = (boolean) session.getAttribute("isCustomer");

	        if (clickButton != null)
	        {
	        	if (clickButton.equals("update-btn"))
	        	{
			        if (loginCustomer != null)
			        {
			        	CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
			        	
			        	loginCustomer.setUsername(username);
			        	loginCustomer.setPassword(password);
			        	loginCustomer.setCustomerName(name);
			        	loginCustomer.setEmail(email);
			        	loginCustomer.setAddress(address);
				        customerDAO.updateCustomer(loginCustomer);	      
			        }
			        else  if (loginAdmin != null)
			        {
			        	OSDAdminDAO adminDAO = new OSDAdminDAO(url,mySQLuser, mySQLpassword);
			        	loginAdmin.setUsername(username);
			        	loginAdmin.setPassword(password);
			        	loginAdmin.setAdminName(name);
			        	loginAdmin.setEmail(email);   			  
			            adminDAO.updateAdmin(loginAdmin);
			        }
			        
			        if (paymentAccount != null)
			        {
			        	PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO(url,mySQLuser, mySQLpassword);
			        	paymentAccount.setName(accountName);
			        	paymentAccount.setCardNumber(Integer.parseInt(accountNumber));
			        	paymentAccount.setExpireDate(expDate);
			        	paymentAccountDAO.updatePaymentAccount(paymentAccount);
			        }		    
			        
			        session.setAttribute("loginCustomer", loginCustomer);
			        session.setAttribute("loginAdmin", loginAdmin);
			        session.setAttribute("paymentAccount", paymentAccount);
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AccountPage.jsp");
			        requestDispatcher.forward(request, response);
			        return;
	        	}
	        	else if (clickButton.equals("logout-btn"))
	        	{
	        		// reset all attribute
	        		// Account
	        		session.setAttribute("loginAdmin", null);
			        session.setAttribute("loginCustomer", null);
			        session.setAttribute("paymentAccount", null);
			       
			        // Cart 
			        session.setAttribute("totalPrice", 0);
					session.setAttribute("weight", 0);
					session.setAttribute("cartItemList", null);
					session.setAttribute("subtotal", 0);
					session.setAttribute("shipMethod", null);
					session.setAttribute("availableShipMethodList", null);
					
					//Main Page
					session.setAttribute("searchProductList", null);
					session.setAttribute("searchProductFilter", null);
					
					// OrderPage
					session.setAttribute("orderPageFilter", null);
					
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
			        requestDispatcher.forward(request, response);
			        return;
	        	}
	        }
	        
	    }
	    
	    public void destroy() {
	    }

	

}
