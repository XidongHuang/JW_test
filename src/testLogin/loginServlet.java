package testLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class loginServlet extends HttpServlet {

	userDAO userDAO = new userDAO();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputName = req.getParameter("inputUser");
		String inputPassword = req.getParameter("inputPassword");
		
		Connection connection = null;
		
		PrintWriter out = resp.getWriter();
		//System.out.println();
		try {
			connection = JDBCTools.getConnection();
			
			
			String sql = "SELECT name, password FROM test_user WHERE name = ? "
					+ "AND password = ?";
			
		
			
			Object obj = userDAO.get(connection, sql, inputName,inputPassword);
			
			if(obj != null){
				out.println("Hello: " + inputName);
			} else {
				out.print("Sorry: "+ inputName);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JDBCTools.releaseDB(null, null, connection);
			
		}
		
		
	}
	
}
