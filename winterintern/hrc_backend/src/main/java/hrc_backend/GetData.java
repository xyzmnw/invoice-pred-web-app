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
 * Servlet implementation class GetData
 */
@WebServlet("/GetData")
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		List<InvoicePojo> ar = new ArrayList<>();
		try {
			Connection conn = Connector.getConnection();
			PreparedStatement ps = null;
			
			String query = "select * from winter_internship where is_deleted=0";
			
			ps = conn.prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			
			  
			while(rs.next()) {
			InvoicePojo obj = new InvoicePojo();
			obj.setSl_no(rs.getInt("sl_no"));
			obj.setBusiness_code(rs.getString("business_code"));
			obj.setCust_number(rs.getInt("cust_number"));
			obj.setClear_date(rs.getString("clear_date"));
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
			
			String json = new Gson().toJson(Response); // [[],[]] => [{},{}]
			
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().append(json);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
