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
@WebServlet("/SearchInvoice")
public class SearchInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInvoice() {
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
			int cust_number = Integer.parseInt(request.getParameter("cust_number"));
			String query = "select * from winter_internship where cust_number="+cust_number;
			
			ps = conn.prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			
			List<InvoicePojo> ar = new ArrayList<>();  
			while(rs.next()) {
			InvoicePojo obj = new InvoicePojo();
			obj.setSl_no(rs.getInt("sl_no"));
			obj.setBusiness_code(rs.getString("business_code"));
			obj.setCust_number(rs.getInt("cust_number"));
			obj.setBuisness_year(rs.getInt("buisness_year"));
			obj.setDoc_id(rs.getString("doc_id"));
			obj.setPosting_date(rs.getString("posting_date"));
			obj.setDocument_create_date(rs.getString("document_create_date"));
			obj.setDocument_create_date1(rs.getString("document_create_date1"));
			obj.setInvoice_currency(rs.getString("invoice_currency"));
			obj.setDocument_type(rs.getString("document_type"));
			obj.setPosting_id(rs.getInt("posting_id"));
			obj.setTotal_open_amount(rs.getDouble("total_open_amount"));
			obj.setBaseline_create_date(rs.getString("baseline_create_date"));
			obj.setCust_payment_terms(rs.getString("cust_payment_terms"));
			obj.setInvoice_id(rs.getInt("invoice_id"));
			
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
