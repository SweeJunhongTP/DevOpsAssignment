

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecipeServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				PrintWriter out = response.getWriter();
				
				String n = request.getParameter("recipeName");
				String d = request.getParameter("description");
				String t = request.getParameter("createdOn");
				String a = request.getParameter("author");
				
				try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection con = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/recipedetails", "root", "password");
				
				 PreparedStatement ps = con.prepareStatement("insert into recipedetails values(?,?,?,?)");
				
				 ps.setString(1, n);
				 ps.setString(2, d);
				 ps.setString(3, t);
				 ps.setString(4, a);
				
				 int i = ps.executeUpdate();
				
				 if (i > 0){
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "You have successfully added a new recipe" + "</h1>");
				writer.close();
				}
				}
				//Step 8: catch and print out any exception
				catch (Exception exception) {
				 System.out.println(exception);
				 out.close();
				}
				doGet(request, response);

			}

}
