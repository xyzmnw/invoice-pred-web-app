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
 * Servlet implementation class AddInvoice
 */
@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInvoice() {
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
//			int sl_no = Integer.parseInt(request.getParameter("sl_no"));
			String business_code = request.getParameter("business_code");
			int cust_number = Integer.parseInt(request.getParameter("cust_number"));
			String clear_date = request.getParameter("clear_date");
			String buisness_year = request.getParameter("buisness_year");
			String doc_id = request.getParameter("doc_id");
			String posting_date = request.getParameter("posting_date");
			String document_create_date = request.getParameter("document_create_date");
			String document_create_date1 = request.getParameter("document_create_date1");
			String due_in_date = request.getParameter("due_in_date");
			String invoice_currency = request.getParameter("invoice_currency");
			String document_type = request.getParameter("document_type");
			int posting_id = Integer.parseInt(request.getParameter("posting_id"));
			Double total_open_amount = Double.parseDouble(request.getParameter("total_open_amount"));
			String baseline_create_date = request.getParameter("baseline_create_date");
			String cust_payment_terms = request.getParameter("cust_payment_terms");
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			
			PreparedStatement ps = null;
			String query = "insert into winter_internship(business_code , cust_number , clear_date , buisness_year , doc_id , posting_date , document_create_date , document_create_date1 , due_in_date , invoice_currency , document_type , posting_id , total_open_amount , baseline_create_date , cust_payment_terms , invoice_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1,business_code);
			ps.setInt(2,cust_number);
			ps.setString(3,clear_date);
			ps.setString(4,buisness_year);
			ps.setString(5,doc_id);
			ps.setString(6,posting_date);
			ps.setString(7,document_create_date);
			ps.setString(8,document_create_date1);
			ps.setString(9,due_in_date);
			ps.setString(10,invoice_currency);
			ps.setString(11,document_type);
			ps.setInt(12,posting_id);
			ps.setDouble(13, total_open_amount);
			ps.setString(14,baseline_create_date);
			ps.setString(15,cust_payment_terms);
			ps.setInt(16,invoice_id);
			
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
