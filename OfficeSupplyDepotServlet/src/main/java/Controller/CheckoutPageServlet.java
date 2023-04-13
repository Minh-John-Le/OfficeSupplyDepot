package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import Beans.CartItem;
import Beans.Customer;
import Beans.PaymentAccount;
import Beans.ShipMethod;
import Beans.OSDAdmin;
import Beans.OrderDetail;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import DAO.*;
import Utilities.CheckoutUtil;
import Utilities.Settings;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/checkoutPage")
public class CheckoutPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
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
		
		String checkoutButton = request.getParameter("checkout");
		
		// Request info
		String shipName = request.getParameter("ship-name");
        String address = request.getParameter("ship-address");
        String cardName = request.getParameter("account-name");
        String cardNumber = request.getParameter("account-number");
        String expDate = request.getParameter("exp");
		
		// Session Info
		BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
		BigDecimal weight = (BigDecimal) session.getAttribute("weight");
		ShipMethod shipMethod = (ShipMethod) session.getAttribute("shipMethod");
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		LinkedList<CartItem> cartItemList = (LinkedList<CartItem>) session.getAttribute("cartItemList");
		int totalItem = (int) session.getAttribute("totalItem");
		
		if(checkoutButton != null)
		{
			
			ProductDAO productDAO = new ProductDAO(url, mySQLuser, mySQLpassword);
			
			LinkedList<CartItem> newcartItem = (LinkedList<CartItem>) productDAO.updateProductStockAfterOrder(cartItemList);
			
			if (newcartItem != null)
			{
				session.setAttribute("cartItemList", newcartItem);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("CheckoutPage.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			OrderDetailDAO orderDetailDAO = new OrderDetailDAO(url, mySQLuser, mySQLpassword);
			OrderPackageDAO orderPackageDAO = new OrderPackageDAO(url, mySQLuser, mySQLpassword);
			String orderCode = UUID.randomUUID().toString();
			
			// Calculate order speed
			LocalDate orderDay = LocalDate.now();
			int speed = shipMethod.getSpeed();
			LocalDate deliveryDay = LocalDate.now().plusDays(speed);
			String orderDayStr = orderDay.toString();
			String deliveryDayStr = deliveryDay.toString();
			
			// Order Detail
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setCardName(cardName);
			orderDetail.setCustomerID(loginCustomer.getId());
			orderDetail.setDeliveryName(shipName);
			orderDetail.setExpireDate(expDate);
			orderDetail.setPaymentCardNumber(Integer.parseInt(cardNumber));
			orderDetail.setShipAddress(address);
			orderDetail.setShipmethodID(shipMethod.getId());
			orderDetail.setTotalPrice(totalPrice);
			orderDetail.setTotalWeight(weight);
			orderDetail.setOrderCode(orderCode);
			orderDetail.setOrderDate(orderDayStr);
			orderDetail.setDeliveryDate(deliveryDayStr);
			orderDetail.setTotalItem(totalItem);
			
			
			// Add everything to database
			orderDetailDAO.addOrderDetail(orderDetail);
			orderDetail = orderDetailDAO.getOrderDetailByOrderCode(orderCode);			
			orderPackageDAO.addPackage(cartItemList, orderDetail.getId());
			
			// reset all cart value
			session.setAttribute("totalPrice", null);
			session.setAttribute("weight", null);
			session.setAttribute("cartItemList", null);
			session.setAttribute("subtotal", null);
			session.setAttribute("shipMethod", null);
			session.setAttribute("availableShipMethodList", null);
			session.setAttribute("totalItem", null);
			
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainPage.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
 
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("CheckoutPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
