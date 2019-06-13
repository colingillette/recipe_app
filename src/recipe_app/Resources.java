package recipe_app;

import java.sql.*;

public class Resources {
	
	// Constructor
	public Resources() {
		
	}
	
	/*
	 *  Static methods 
	 */
	
	// Returns boolean on successful or failed close.
	public static boolean close(Connection conn, Statement stmt, ResultSet results) {
		try {
			if (results != null) {
                results.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
            
            return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean close(Connection conn, PreparedStatement stmt) {
		try {
			if (conn != null) {
                conn.close();
            }

            if (stmt != null) {
                stmt.close();
            }
            
            return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// Writes ResultSet items line-by-line in console
	public static void writeResultSet(ResultSet resultSet, String table) throws SQLException {
		switch (table) {
			case "categories":
				int categoryID;
				String category;
				
				while (resultSet.next()) {
					categoryID = resultSet.getInt("category_id");
					category = resultSet.getString("category");
					System.out.println("(" + categoryID + ") " + category);
				}
				break;
			default:
				break;
		}
	}
}
