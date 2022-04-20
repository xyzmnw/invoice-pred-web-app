package hrc_backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteInvoice")
public class DeleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInvoice() {
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
//			
			Connection conn = Connector.getConnection();
			PreparedStatement ps = null;
			int sl_no = Integer.parseInt(request.getParameter("sl_no"));
			String query ="update winter_internship set is_deleted=1 where sl_no="+sl_no;
			System.out.println("delete servlet	");
			ps = conn.prepareStatement(query);
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
