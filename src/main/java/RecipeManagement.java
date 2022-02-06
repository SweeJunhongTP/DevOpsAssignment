import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/RecipeManagement")
public class RecipeManagement  extends HttpServlet {
	//step1 - prepare list of variables used for database connections
		private String jdbcURL = "jdbc:mysql://localhost:3306/recipedetails";
		private String jdbcUsername = "root";
		private String jdbcPassword = "password";
		
		//step2 - prepare list of sql prepared statements to perform crud to database
		
		private static final String INSERT_RECIPE_SQL = "INSERT INTO recipedetails" + " (recipeName, description, createdOn, author) VALUES " + " (?, ?, ?);";
		private static final String SELECT_RECIPE_BY_ID = "select recipeName, description, createdOn, author from recipedetails where recipeName =?";
		private static final String SELECT_ALL_RECIPES = "SELECT * FROM `recipedetails`";
		private static final String DELETE_RECIPE_SQL = "delete from recipedetails where recipeName = ?;";
		private static final String UPDATE_RECIPE_SQL = "update recipedetails set recipeName = ?, description= ?, createdOn =?, author =? where recipeName = ?;";	
		
		//step3 - implement the getConnection method which facilitates connection to the database via JDBC
		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		} 
		
		
		//step5 - listUsers function to connect to the database and retrieve all user records
		private void listRecipe (HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException
		{
			List <Recipe> recipes = new ArrayList <> ();
			try (Connection connection = getConnection();
					//step 5.1 - create statement using connection object
					PreparedStatement preparedStatement = 
							connection.prepareStatement(SELECT_ALL_RECIPES);) {
				//step5.2 - execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				//step5.3 - process the result set object
				while(rs.next()) {
					String recipeName = rs.getString("recipeName");
					String description	  = rs.getString("description");
					String createdOn  = rs.getString("createdOn");
					String author	  = rs.getString("author");
					recipes.add(new Recipe (recipeName, description, createdOn , author	 ));
				}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			//step 5.4 - set the users list into the listusers attribute to be pass to the usermangement.jsp
			request.setAttribute("listRecipe", recipes);
			request.getRequestDispatcher("/Recipe.jsp").forward(request, response);
		}
		

		
		
		
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public RecipeManagement() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//step4 - depending on the request servelet path, determine the function to invoke using the follow switch statement 
			String action = request.getServletPath();
			try {
				switch (action) {
				case "/RecipeManagement/edit":
					showEditForm(request,response);
					break;
				case "/RecipeManagement/update":
					updateRecipe(request,response);
					break;
				case "/RecipeManagement/delete":
					deleteRecipe(request,response);
					break;
				case "/RecipeManagement/recipe":
					listRecipe(request, response);
					 break;
				}
			}catch (SQLException ex) {
				throw new ServletException(ex);
			}
			
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
		
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
					//get parameter passed in url 
					String recipeName = request.getParameter("recipeName");
					Recipe existingRecipe = new Recipe ("", "", "", "");
					
					//step1 - establishing a connection
					try (Connection connection = getConnection();
							//step2 - create statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIPE_BY_ID);) {
						preparedStatement.setString(1, recipeName);
						//step3 - execute query / update query
						ResultSet rs = preparedStatement.executeQuery();
						//step4 - process the resultSet object
						while(rs.next()) {
							recipeName = rs.getString("recipeName");
							String description = rs.getString("description");
							String createdOn = rs.getString("createdOn");
							String author = rs.getString("author");
							existingRecipe = new Recipe (recipeName, description, createdOn, author);
						}
					}catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					request.setAttribute("recipe", existingRecipe);
					request.getRequestDispatcher("/recipeEdit.jsp").forward(request, response);
				}
		
		//method to update the user table base on the form data
		private void updateRecipe(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {
			//step1 - retrieve values from request
			String oriName = request.getParameter("oriName");
			String recipeName = request.getParameter("recipeName");
			String description = request.getParameter("description");
			String createdOn = request.getParameter("createdOn");
			String author = request.getParameter("author");
			
			//step2 - attempt connection with database and execute update user sql query
			try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_SQL);){
				statement.setString(1, recipeName);
				statement.setString(2, description);
				statement.setString(3, createdOn);
				statement.setString(4, author);
				statement.setString(5, oriName);
				
				int i = statement.executeUpdate();
			}
			//step3 - redirect back to userServlet 
			response.sendRedirect("http://localhost:8090/DevOpsProject/RecipeManagement/recipe");
		}
		
		private void deleteRecipe(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {
			//step1 - retrieve value from request
			String recipeName = request.getParameter("recipeName");
			
			//step2 - attempt connection with database and execute delete user sql query
			try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_SQL);){
				statement.setString(1, recipeName);
				int i = statement.executeUpdate();
			}
			//step3 - redirect back to UserServlet dashboard 
			response.sendRedirect("http://localhost:8090/DevOpsProject/RecipeManagement/recipe");
		}
}



