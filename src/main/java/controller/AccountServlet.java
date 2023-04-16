package controller;

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

import beans.BankAccount;
import beans.Customer;
import beans.PaymentAccount;
import beans.OSDAdmin;
import dao.BankAccountDAO;
import dao.CustomerDAO;
import dao.PaymentAccountDAO;
import dao.OSDAdminDAO;
import utilities.Settings;
import utilities.ValidationUtil;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	List<String> errList = new LinkedList<String>();
	    	HttpSession session = request.getSession();
	    	ServletContext context = getServletContext();
	    	String clickButton = request.getParameter("button");
	    	
	    	// DAO 
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
	        
	        // Util package
	        ValidationUtil validationUtil = new ValidationUtil();
	    	
	    	//=============================================
	        // Front end input receive
	    	String username = request.getParameter("username").trim();
	        String password = request.getParameter("password").trim();
	        String name = request.getParameter("display-name").trim();
	        String email = request.getParameter("email").trim();
	        String address = request.getParameter("address");
	        String accountName = request.getParameter("account-name");
	        String accountNumber = request.getParameter("account-number");
	        String expDate = request.getParameter("exp");
	        
	        
	        
	        // Validation
	        if (name != null && !validationUtil.isValidDisplayName(name))
	        {
	        	errList.add("Display Name cannot be empty and must be at max 20 characters!");
	        }
	        
	        if (password != null && !validationUtil.isValidPassword(password))
	        {
	        	errList.add("Invalid Password! Password must have at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, and 1 special character" ); 
	        }
	        
	        if (expDate != null && !expDate.equals(""))
	        {
	        	if(!validationUtil.isValidExpDate(expDate))
	        	{
	        		errList.add("Expire Date should be in format MM/YY");
	        	}
	        }
	        
	        if (accountNumber != null && !accountNumber.equals(""))
	        {
	        	
	        	if(!validationUtil.isNumeric(accountNumber))
	        	{
	        		errList.add("Invalid Account Number!");
	        	}
	        }
	        
	        // Validation Error
	        if(!errList.isEmpty()) { //has some error
				request.setAttribute("errlist", errList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AccountPage.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
	        
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
			        	paymentAccount.setCardNumber(accountNumber);
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
					session.setAttribute("destination", null);
					
					// Cart, Ship, Checkout Page
					session.setAttribute("totalPrice", null);
					session.setAttribute("weight", null);
					session.setAttribute("cartItemList", null);
					session.setAttribute("subtotal", null);
					session.setAttribute("shipMethod", null);
					session.setAttribute("availableShipMethodList", null);
					session.setAttribute("totalItem", null);
					
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
			        requestDispatcher.forward(request, response);
			        return;
	        	}
	        }
	        
	    }
	    
	    public void destroy() {
	    }

	

}
