package auth;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuthentication {
    public static boolean validateUser(String username, String password) {
        boolean isValid = false;

        try {
            // Get database connection
            Connection conn = DBConnection.getConnection();

            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;  // User found
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}