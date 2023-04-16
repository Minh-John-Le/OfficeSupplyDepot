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


import beans.CartItem;
import beans.Customer;
import beans.OSDAdmin;
import beans.OrderDetail;
import beans.OrderPackage;
import beans.OrderPageFilter;
import beans.Product;
import beans.ShipMethod;
import dao.CustomerDAO;
import dao.OSDAdminDAO;
import dao.OrderDetailDAO;
import dao.OrderPackageDAO;
import dao.ProductDAO;
import dao.ShipMethodDAO;
import utilities.Settings;


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
			OrderDetail viewedOrderDetail = new OrderDetail();
			List<OrderPackage> orderPackageList = new LinkedList<OrderPackage>();
			viewedOrderDetail = orderDetailDAO.getOrderDetailById(Integer.parseInt(viewOrderDetail));
			ProductDAO productDAO = new ProductDAO(url, mySQLuser, mySQLpassword);
			ShipMethodDAO shipMethodDAO = new ShipMethodDAO(url, mySQLuser, mySQLpassword);
			ShipMethod viewedShipMethod = new ShipMethod();
			
			OrderPackageDAO orderPackageDAO = new OrderPackageDAO(url, mySQLuser, mySQLpassword);
			orderPackageList = orderPackageDAO.getPackagesByOrderId(viewedOrderDetail.getId());
			for (OrderPackage orderPackage: orderPackageList)
			{
				CartItem cartItem = new CartItem();
				Product product = new Product();
				product = productDAO.getProductById(orderPackage.getProductID());
				
				cartItem.setProduct(product);
				cartItem.setQuantity(orderPackage.getQuantity());
				
				packageItemList.add(cartItem);
			}
			
			viewedShipMethod = shipMethodDAO.getShipMethodById(viewedOrderDetail.getShipmethodID());
			
			session.setAttribute("viewedShipMethod", viewedShipMethod);
			session.setAttribute("packageItemList", packageItemList);
			session.setAttribute("viewedOrderDetail", viewedOrderDetail);
        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("OrderDetailPage.jsp");
        	requestDispatcher.forward(request, response);
    		return;
        }
        
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("OrderPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }
	
}
