package Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.ShipMethod;
import Utilities.CheckoutUtil;


@WebServlet("/shipmentPage")
public class ShipmentPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	//=============================================
        // Front end input receive
        String shipmentMethodCheckBox = request.getParameter("myCheckbox");
		String nextButton = request.getParameter("next");
		
		if(nextButton != null)
		{
			if (shipmentMethodCheckBox != null)
			{
				LinkedList<ShipMethod> availableShipMethodList = (LinkedList<ShipMethod>) session.getAttribute("availableShipMethodList");
				ShipMethod  shipMethod = new ShipMethod();
				
				int shipMethodId = Integer.parseInt(shipmentMethodCheckBox);
				
				for (int i = 0; i < availableShipMethodList.size(); i++)
				{
					if(availableShipMethodList.get(i).getId() == shipMethodId)
					{
						shipMethod = availableShipMethodList.get(i);
						break;
					}
				}
				
				BigDecimal subtotal = (BigDecimal) session.getAttribute("subtotal");
				CheckoutUtil checkoutUtil = new CheckoutUtil();
				BigDecimal totalPrice = checkoutUtil.getTotal(subtotal,shipMethod.getPrice());
				
				session.setAttribute("shipMethod", shipMethod);
				session.setAttribute("totalPrice", totalPrice);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("CheckoutPage.jsp");
				requestDispatcher.forward(request, response);
				return;		
			}
		}
		
        
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShipmentPage.jsp");
		requestDispatcher.forward(request, response);
		return;
    }

}
