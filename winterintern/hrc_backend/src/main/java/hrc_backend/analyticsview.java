package hrc_backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchInvoice
 */
@WebServlet("/analyticsview")
public class analyticsview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public analyticsview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		try {
			Connection conn = Connector.getConnection();
			PreparedStatement ps = null;
			String start_cl_date = request.getParameter("start_cl_date");
			String end_cl_date = request.getParameter("end_cl_date");
			String start_due_date = request.getParameter("start_due_date");
			String end_due_date = request.getParameter("end_due_date");
			String start_base_date = request.getParameter("start_base_date");
			String end_base_date = request.getParameter("end_base_date");
			String curr = request.getParameter("curr");
			
			String query = "SELECT business_code,  COUNT(cust_number) AS total_customer, SUM(total_open_amount)  AS total_amount FROM winter_internship WHERE due_in_date BETWEEN \""+start_due_date+"\" AND \""+end_due_date+"\"  AND baseline_create_date BETWEEN \""+start_base_date+"\" AND \""+end_base_date+"\"  AND clear_date BETWEEN \""+start_cl_date+"\" AND \""+end_cl_date+"\"  AND invoice_currency = \""+curr+"\" GROUP BY business_code";
			
			ps = conn.prepareStatement(query);
			System.out.println(ps);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			
			List<InvoicePojo> ar = new ArrayList<>();  
			while(rs.next()) {
			InvoicePojo obj = new InvoicePojo();
			obj.setBusiness_code(rs.getString("business_code"));
			obj.setTotal_customer(rs.getInt("Total_customer"));
			obj.setTotal_amount(rs.getDouble("Total_amount"));
			
			ar.add(obj);
			}
			Response.put("user", ar);
			PrintWriter out = response.getWriter();
			String json = new Gson().toJson(Response); // [[],[]] => [{},{}]
			out.print(json);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
