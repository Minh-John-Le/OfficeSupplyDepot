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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
    	
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String account_type = request.getParameter("account-type");

        
        if (account_type.equals("customer"))
        {
        	CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
        	PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO(url,mySQLuser, mySQLpassword);
	        Customer customer = new Customer();
	        customer.setUsername(username);
	        customer.setPassword(password);
	        customer.setCustomerName(name);
	        customer.setEmail(email);
	        
	        PaymentAccount paymentAccount = new PaymentAccount(-1, -1, "","", "");
	        
	        // checking for existing customer
	        Customer existingCustomer = customerDAO.getCustomerByUsername(customer.getUsername());
	        ValidationUtil validationUtil = new ValidationUtil();
	        if(existingCustomer != null)
	        {
	        	errList.add("Username: " + customer.getUsername() + " already exist!");  	
	        
	        }
	        
	        if (username != null && username.equals(""))
	        {
	        	errList.add("username cannot be empty");
	        }
	        
	        if (name != null && !validationUtil.isValidDisplayName(name))
	        {
	        	errList.add("Display name cannot be empty and must be at max 20 characters!");
	        }
	        
	        if(password != null && !validationUtil.isValidPassword(password))
        	{
	        	errList.add("Invalid Password! Password must have at least 8 characters, 1 uppercase, 1 lowercase, and 1 special character" ); 
        	}
	        
	        
	        if(errList.isEmpty())
	        {
	        	customerDAO.addCustomer(customer);	
	        	customer = customerDAO.getCustomerByUsername(customer.getUsername());
	        	// add customer account as well
	        	paymentAccount.setCustomerId(customer.getId());
	        	paymentAccountDAO.addPaymentAccount(paymentAccount);
	        }
        }
        
        
        request.setAttribute("errlist", errList);
        
        if(!errList.isEmpty()) { //has some error
			request.setAttribute("errlist", errList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SignUp.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
        requestDispatcher.forward(request, response);
        return;
        
    }
    
    public void destroy() {
    }

}