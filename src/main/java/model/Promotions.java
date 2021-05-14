package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Promotions {
	private String newPromotion;

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String readPromotions() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Promotion Name</th>"
					+ "<th>Promotion Type</th><th>Discount Percentage</th>" + "<th>Start Date</th>" + "<th>End Date</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from promotions";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String promotionID = Integer.toString(rs.getInt("promotionID"));
				String promotionName = rs.getString("promotionName");
				String promotionType = rs.getString("promotionType");
				String discountPercentage = Double.toString(rs.getDouble("discount_percentage"));
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");

				// Add into the html table

				output += "<tr><td><input id='hidpromotionIDUpdate' name='hidpromotionIDUpdate' type='hidden' value='"
						+ promotionID + "'>" + promotionName + "</td>";

				output += "<td>" + promotionType + "</td>";
				output += "<td>" + discountPercentage + "</td>";
				output += "<td>" + startDate + "</td>";
				output += "<td>" + endDate + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-promotionID='"
						+ promotionID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the promotion.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert appointment
	public String insertPromotion(String promotionName, String promotionType, String discountPercentage,
			String startDate, String endDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into research (`promotionID`,`promotionName`,`promotionType`,`discountPercentage`,`startDate`,`endDate`)"
					+ " values (?, ?, ?, ?, ?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, promotionName);
			preparedStmt.setString(3, promotionType);
			preparedStmt.setDouble(4, Double.parseDouble(discountPercentage));
			preparedStmt.setString(5, startDate);
			preparedStmt.setString(6, endDate);


			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newpromotion = readPromotion();
			output = "{\"status\":\"success\", \"data\": \"" + readPromotions() + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting .\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update 
	public String updatepromotion(String promotionID, String promotionName,String promotionType, String discountPercentage,
			String startDate, String endDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE promotion SET promotionName=?,promotionType=?,discountPercentage=?,startDate=?,endDate=? WHERE promotionID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, promotionName);
			preparedStmt.setString(2, promotionType);
			preparedStmt.setDouble(3, Double.parseDouble(discountPercentage));
			preparedStmt.setString(4, startDate);
			preparedStmt.setString(4, endDate);
			preparedStmt.setInt(5, Integer.parseInt(promotionID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newpromotion = readPromotion();
			output = "{\"status\":\"success\", \"data\": \"" + newPromotion + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Promotion Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePromotion(String promotionID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM research WHERE promotionID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(promotionID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newPromotion = readPromotion();
			output = "{\"status\":\"success\", \"data\": \"" + newPromotion + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting .\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

	private String readPromotion() {
		// TODO Auto-generated method stub
		return null;
	}
}