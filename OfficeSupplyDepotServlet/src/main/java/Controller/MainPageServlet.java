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

import Beans.Customer;
import Beans.Store;
import DAO.CustomerDAO;
import DAO.StoreDAO;
import Utilities.Settings;


@WebServlet("/mainpage")
public class MainPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> errList = new LinkedList<String>();
    	boolean isCustomer = false;
    	Customer loginCustomer = null;
    	Store loginStore = null;
    	
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
        
    	//=============================================
        // Front end input receive
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String account_type = request.getParameter("account-type");

        
		session .setAttribute("isCustomer", isCustomer);
        session.setAttribute("loginCustomer", loginCustomer);
        session.setAttribute("loginStore", loginStore);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
