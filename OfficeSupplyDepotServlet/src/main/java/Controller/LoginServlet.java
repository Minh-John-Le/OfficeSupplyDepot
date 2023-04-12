package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
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
import Beans.OrderPageFilter;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.*;
import Utilities.Settings;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> errList = new LinkedList<String>();
    	boolean isCustomer = false;
    	Customer loginCustomer = null;
    	OSDAdmin loginAdmin = null;
    	PaymentAccount paymentAccount = null;
    	BankAccount bankAccount = null;
    	
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
        String account_type = request.getParameter("account-type");

        
        if (account_type.equals("customer"))
        {
        	CustomerDAO customerDAO = new CustomerDAO(url,mySQLuser, mySQLpassword);
        	PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO(url,mySQLuser, mySQLpassword);
        	loginCustomer = customerDAO.getCustomerByUsername(username);
        	
        	
        	if(loginCustomer == null)
        	{
        		errList.add("username does not exist");
        	}
        	else
        	{
        		if (!loginCustomer.getPassword().equals(password))
        		{
        			errList.add("Wrong password!");
        		}
        		
        		paymentAccount = paymentAccountDAO.searchByCustomerId(loginCustomer.getId());
        	}       	
        	isCustomer = true;
        }
        else if (account_type.equals("admin")) // Sign Up by store
        {
        	OSDAdminDAO adminDAO = new OSDAdminDAO(url,mySQLuser, mySQLpassword);

        	loginAdmin = adminDAO.getAdminByUsername(username);
        	if(loginAdmin == null)
        	{
        		errList.add("username does not exist");
        	}
        	else
        	{
        		if (!loginAdmin.getPassword().equals(password))
        		{
        			errList.add("Wrong password!");
        		}
     
        	}
        	isCustomer = false;
       
        }
        
        if(!errList.isEmpty()) { //has some error
			request.setAttribute("errlist", errList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
        
        // Set up some default filter 
        LocalDate toOrderDay = LocalDate.now();
        LocalDate toDeliveryDay = LocalDate.now().plusDays(7);
        String toOrderDayStr = toOrderDay.toString();
        String toDeliveryDayStr = toDeliveryDay.toString();
        
        LocalDate fromDay = LocalDate.now().minusDays(7);
        String fromDayStr = fromDay.toString();
        OrderPageFilter orderPageFilter = new OrderPageFilter();
        orderPageFilter.setOrderNumber("");
        orderPageFilter.setToOrderDay(toOrderDayStr);
        orderPageFilter.setToDeliveryDay(toDeliveryDayStr);
        orderPageFilter.setFromDeliveryDay(fromDayStr);
        orderPageFilter.setFromOrderDay(fromDayStr);
        orderPageFilter.setSortBy(""); 
        
        // session setting
		session .setAttribute("isCustomer", isCustomer);
        session.setAttribute("loginCustomer", loginCustomer);
        session.setAttribute("loginAdmin", loginAdmin);
        session.setAttribute("paymentAccount", paymentAccount);
        session.setAttribute("orderPageFilter", orderPageFilter);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
