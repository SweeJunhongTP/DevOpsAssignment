

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RecipeManagement")
public class RecipeManagement extends HttpServlet {
	private String jdbcURL = "jdbc:mysql://localhost:3306/recipedetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	private static final long serialVersionUID = 1L;
	private static final String INSERT_RECIPE_SQL = "INSERT INTO recipedetails" + " (recipeName, description, createdOn, author) VALUES " + " (?, ?, ?);";
	private static final String SELECT_RECIPE_BY_ID = "select recipeName, description, createdOn, author from recipedetails where recipeName =?";
	private static final String SELECT_ALL_RECIPES = "select * from recipedetails ";
	private static final String DELETE_RECIPE_SQL = "delete from recipedetails where recipeName = ?;";
	private static final String UPDATE_RECIPE_SQL = "update recipedetails set recipeName = ?, description= ?, createdOn =?, author =? where recipeName = ?;";	
	
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
	
	private void listOfRecipe (HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
				List <Recipe> recipes = new ArrayList <> ();
				try (Connection connection = getConnection();
						
						PreparedStatement preparedStatement = 
								connection.prepareStatement(SELECT_ALL_RECIPES);) {
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						String recipeName = rs.getString("recipeName");
						String description = rs.getString("description");
						String createdOn = rs.getString("createdOn");
						String author = rs.getString("author");
						recipes.add(new Recipe (recipeName, description, createdOn, author));
					}
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				request.setAttribute("listOfRecipe", recipes);
				request.getRequestDispatcher("/recipe.jsp").forward(request, response);
			}
    
    public RecipeManagement() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//step4 - depending on the request servelet path, determine the function to invoke using the follow switch statement 
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/RecipeManagement/delete":
				 deleteUser(request, response);
				 break;
			case "/RecipeManagement/edit":
				 showEditForm(request, response);
				 break;
			case "/RecipeManagement/update":
				 updateUser(request, response);
				 break;
			case "/RecipeManagement/recipe":
				 listOfRecipe(request, response);
				 break;
			}
		}catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
				
				//step5 - set existing user to request and serve up the editUser form
				request.setAttribute("recipe", existingRecipe);
				request.getRequestDispatcher("/recipeEdit.jsp").forward(request, response);
			}
			
//			updateUser
			//method to update the user table base on the form data
			private void updateUser(HttpServletRequest request, HttpServletResponse response)
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
			
//			deleteUser
			//method to delete user
			private void deleteUser(HttpServletRequest request, HttpServletResponse response)
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
