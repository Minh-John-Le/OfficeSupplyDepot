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

import org.eclipse.jdt.internal.compiler.util.Util.Displayable;

import Beans.BankAccount;
import Beans.Customer;
import Beans.PaymentAccount;
import Beans.OSDAdmin;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.OSDAdminDAO;
import Utilities.Settings;
import Utilities.ValidationUtil;
import Validation.DatabaseStringValidation;
import Validation.DisplayNameValidation;
import Validation.EmailValidation;
import Validation.PasswordValidation;

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
        String displayName = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String account_type = request.getParameter("account-type");
        
        // VERIFY LOCAL FIRST.
        if(!DatabaseStringValidation.isValid(username, 20)) {
        	errList.addAll(DatabaseStringValidation.getIssues(username, 20, "username"));
        }
        if(!PasswordValidation.isValid(password)) {
        	errList.addAll(PasswordValidation.getIssues(password));
        }
        if(!DisplayNameValidation.isValid(displayName)) {
        	errList.addAll(DisplayNameValidation.getIssues(displayName));
        }
        
        if(!EmailValidation.isValid(email)) {
        	errList.addAll(EmailValidation.getIssues(email));
        }
        
        // VERIFY new username for customer ONLY after local information checked.
        if (errList.isEmpty()) {
        	CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
        	// checking for existing customer
        	Customer customer = new Customer();
	        customer.setUsername(username);
	        customer.setPassword(password);
	        customer.setCustomerName(displayName);
	        customer.setEmail(email);
	        Customer existingCustomer = customerDAO.getCustomerByUsername(customer.getUsername());
	        if(existingCustomer != null)
	        {
	        	errList.add("Username: " + customer.getUsername() + " already exist!");  	
	        
	        }
	        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO(url,mySQLuser, mySQLpassword);   
	        PaymentAccount paymentAccount = new PaymentAccount(-1, -1, "","", "");
    
            //  NO INPUT ISSUES
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
