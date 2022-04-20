package hrc_backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditInvoice
 */
@WebServlet("/EditInvoice")
public class EditInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection conn = Connector.getConnection();
			PreparedStatement ps = null;
			int sl_no = Integer.parseInt(request.getParameter("sl_no"));
			String invoice_currency = request.getParameter("invoice_currency");
			String cust_payment_terms = request.getParameter("cust_payment_terms");
		
			String query = "Update winter_internship SET invoice_currency='"+invoice_currency+"', cust_payment_terms='"+cust_payment_terms+"'  Where sl_no="+sl_no;
			ps = conn.prepareStatement(query);
			ps.execute();
			int count = ps.executeUpdate();
			if(count == 1) {
				response.getWriter().write("Success");
			}
			else {
				response.getWriter().write("Fail");
			}
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

}
