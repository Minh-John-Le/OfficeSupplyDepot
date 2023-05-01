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
import Beans.OrderPackage;
import Beans.OrderPageFilter;
import Beans.Product;
import Beans.ShipMethod;
import DAO.OrderDetailDAO;
import DAO.OrderPackageDAO;
import DAO.ProductDAO;
import DAO.ShipMethodDAO;
import Utilities.Settings;


@WebServlet("/orderPage")
public class OrderPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private OrderDetailDAO orderDetailDAO; 
	private ShipMethodDAO shipMethodDAO;
	private OrderPackageDAO orderPackageDAO;
	
	public void init() throws ServletException {
        ServletContext context = getServletContext();

        // Get the input stream for the properties file
        try (InputStream input = context.getResourceAsStream(Settings.getPropertyFile())) {
            // Load the properties from the file
            Properties props = new Properties();
            props.load(input);
            String url = props.getProperty("db.url");
            String mySQLuser = props.getProperty("db.username");
            String mySQLpassword = props.getProperty("db.password");
            orderDetailDAO = new OrderDetailDAO(url, mySQLuser, mySQLpassword);
            productDAO = new ProductDAO(url, mySQLuser, mySQLpassword);
            shipMethodDAO = new ShipMethodDAO(url, mySQLuser, mySQLpassword);
            orderPackageDAO = new OrderPackageDAO(url, mySQLuser, mySQLpassword);
        } catch (IOException e) {
            throw new ServletException("Failed to read configuration", e);
        }
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
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
			OrderDetail viewedOrderDetail = new OrderDetail();
			List<OrderPackage> orderPackageList = new LinkedList<OrderPackage>();
			viewedOrderDetail = orderDetailDAO.getOrderDetailById(Integer.parseInt(viewOrderDetail));
			
			ShipMethod viewedShipMethod = new ShipMethod();
			
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
