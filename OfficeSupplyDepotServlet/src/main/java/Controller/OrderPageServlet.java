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


import Beans.CartItem;
import Beans.Customer;
import Beans.OSDAdmin;
import Beans.OrderDetail;
import Beans.OrderPageFilter;
import Beans.Product;
import DAO.CustomerDAO;
import DAO.OSDAdminDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import Utilities.Settings;


@WebServlet("/orderPage")
public class OrderPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

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
    	String orderNumber = request.getParameter("order-number");
    	String fromOrderDate = request.getParameter("from-order-date");
    	String toOrderDate = request.getParameter("to-order-date");
    	String fromDeliveryDate = request.getParameter("from-delivery-date");
    	String toDeliveryDate = request.getParameter("to-delivery-date");
    	String sortBy = request.getParameter("sortBy");
    	String searchButton = request.getParameter("search-button");
    	String trackPackage = request.getParameter("track Package");
    	String viewOrderDetail = request.getParameter("view details");
    	// Session information
    	OrderPageFilter orderPageFilter = (OrderPageFilter) session.getAttribute("orderPageFilter");
    	Customer loginCustomer =  (Customer) session.getAttribute("loginCustomer");
    	OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
    			
        if (orderNumber == null)
        {
        	orderNumber ="";
        }
        
        if (searchButton != null)
        {
        	List<OrderDetail> orderDetailList = new LinkedList<OrderDetail>();
        	OrderDetailDAO orderDetailDAO = new OrderDetailDAO(url, mySQLuser, mySQLpassword);
        	orderPageFilter.setOrderNumber(orderNumber);
        	orderPageFilter.setFromOrderDay(fromOrderDate);
        	orderPageFilter.setToOrderDay(toOrderDate);
        	orderPageFilter.setFromDeliveryDay(fromDeliveryDate);
        	orderPageFilter.setToDeliveryDay(toDeliveryDate);
        	orderPageFilter.setSortBy(sortBy);
        	
        	if (loginCustomer != null)
        	{
        		orderDetailList = orderDetailDAO.getOrderDetailByCustomerId(loginCustomer.getId() , orderPageFilter);	
        	}
        	else if (loginAdmin != null)
        	{
        		orderDetailList = orderDetailDAO.getAllOrderDetail(orderPageFilter);	
        	}
        	
        	
        	session.setAttribute("orderDetailList", orderDetailList);
        	session.setAttribute("orderPageFilter", orderPageFilter);
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("OrderPage.jsp");
    		requestDispatcher.forward(request, response);
    		return;
        			
        }
        
        if (trackPackage != null)
        {
        	session.setAttribute("destination", trackPackage);
        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("TrackPackageMap.jsp");
        	requestDispatcher.forward(request, response);
    		return;
        }
        
        if (viewOrderDetail != null)
        {
			LinkedList<CartItem> packageItemList =  new LinkedList<CartItem>();
			OrderDetailDAO orderDetailDAO = new OrderDetailDAO(url, mySQLuser, mySQLpassword);
			OrderDetail selectedOrderDetail = new OrderDetail();
			selectedOrderDetail = orderDetailDAO.getOrderDetailById(0);
			
			//packageItemList = 
			

        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("OrderDetailPage.jsp");
        	requestDispatcher.forward(request, response);
    		return;
        }
        
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("OrderPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }
	
}
