package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Customer;
import DAO.CustomerDAO;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {    
    public void init() throws ServletException {
    	
          
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
    	
        
    	//=============================================
        // Front end input receive
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String account_type = request.getParameter("account-type");

        
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setCustomerName(name);
        customer.setEmail(email);
        
        //===============================================
      
		customerDAO.addCustomer(customer);
	

    }
    
    public void destroy() {
    }

}
