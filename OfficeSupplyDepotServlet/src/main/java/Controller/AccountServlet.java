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
import Beans.Store;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.StoreDAO;
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
	    	Store loginStore = (Store) session.getAttribute("loginStore");;
	    	PaymentAccount paymentAccount = (PaymentAccount) session.getAttribute("paymentAccount");
	    	BankAccount bankAccount = (BankAccount) session.getAttribute("bankAccount");
	    	
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
			        else  if (loginStore != null)
			        {
			        	StoreDAO storeDAO = new StoreDAO(url,mySQLuser, mySQLpassword);
			        	loginStore.setUsername(username);
			        	loginStore.setPassword(password);
			        	loginStore.setStoreName(name);
			        	loginStore.setEmail(email);  
			        	loginStore.setAddress(address);  			  
			            storeDAO.updateStore(loginStore);
			        }
			        
			        if (paymentAccount != null)
			        {
			        	PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO(url,mySQLuser, mySQLpassword);
			        	paymentAccount.setName(accountName);
			        	paymentAccount.setCardNumber(Integer.parseInt(accountNumber));
			        	paymentAccount.setExpireDate(expDate);
			        	paymentAccountDAO.updatePaymentAccount(paymentAccount);
			        }
			        else if (bankAccount != null)
			        {
			        	BankAccountDAO bankAccountDAO = new BankAccountDAO(url,mySQLuser, mySQLpassword);
			        	bankAccount.setName(accountName);
			        	bankAccount.setBankAccountNumber(Integer.parseInt(accountNumber));
			        	bankAccount.setExpireDate(expDate);
			        	bankAccountDAO.updateBankAccount(bankAccount);
			        }
			    
			        
			        session.setAttribute("loginCustomer", loginCustomer);
			        session.setAttribute("loginStore", loginStore);
			        session.setAttribute("paymentAccount", paymentAccount);
			        session.setAttribute("bankAccount", bankAccount);
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AccountPage.jsp");
			        requestDispatcher.forward(request, response);
			        return;
	        	}
	        	else if (clickButton.equals("logout-btn"))
	        	{
	        		session.setAttribute("loginCustomer", null);
			        session.setAttribute("loginStore", null);
			        session.setAttribute("paymentAccount", null);
			        session.setAttribute("bankAccount", null);
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
			        requestDispatcher.forward(request, response);
			        return;
	        	}
	        }
	        
	        
	  
	        
	    }
	    
	    public void destroy() {
	    }

	

}