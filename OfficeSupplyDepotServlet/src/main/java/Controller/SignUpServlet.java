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

import Beans.Customer;
import Beans.Store;
import DAO.CustomerDAO;
import DAO.StoreDAO;
import Utilities.Settings;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {    
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
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String account_type = request.getParameter("account-type");

        
        if (account_type.equals("customer"))
        {
        	CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
	        Customer customer = new Customer();
	        customer.setUsername(username);
	        customer.setPassword(password);
	        customer.setCustomerName(name);
	        customer.setEmail(email);
	        
	        // checking for existing customer
	        Customer existingCustomer = customerDAO.getCustomerByUsername(customer.getUsername());
	        Settings.customer = customer;
	        if(existingCustomer != null)
	        {
	        	errList.add("Username: " + Settings.customer.getUsername() + " already exist!");  	
	        
	        }
	        if(password.equals(""))
        	{
	        	errList.add("Password cannot be empty!"); 
        	}
	        
	        if(!errList.isEmpty())
	        {
	        	customerDAO.addCustomer(customer);	
	        }
        }
        else if (account_type.equals("store")) // Sign Up by store
        {
        	StoreDAO storeDAO = new StoreDAO(url,mySQLuser, mySQLpassword);
        	Store store = new Store();
        	store.setUsername(username);
        	store.setPassword(password);
            store.setStoreName(name);
            store.setEmail(email);
        
         // checking for existing customer
	        Store existingStore = storeDAO.getStoreByUsername(store.getUsername());
	        Settings.store = store;
	        if(existingStore != null)
	        {
	        	errList.add("Username: " + Settings.store.getUsername() + " already exist!");   	
	        }
	        if(password.equals(""))
	        {
	        	errList.add("Password cannot be empty!"); 
	        }
	        
	        
	        if(!errList.isEmpty())
	        {
	        	storeDAO.addStore(store);
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
