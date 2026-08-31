package registration;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(String username, String email, String password, String phone, String address) {
        Connection conn = null;
        PreparedStatement pstmtUser = null;
        PreparedStatement pstmtDetails = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            // Get database connection
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Insert into 'users' table
            String userQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            pstmtUser = conn.prepareStatement(userQuery);
            pstmtUser.setString(1, username);
            pstmtUser.setString(2, email);
            pstmtUser.setString(3, password); // Consider hashing the password
            int userInserted = pstmtUser.executeUpdate();

            if (userInserted == 0) {
              //  conn.rollback();
              //  return false;
                
                System.out.println("is it here");
            }

            // Get the generated user_id
          /*  rs = pstmtUser.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            } else {
                conn.rollback();
                return false;
            }*/

            // 2. Insert into 'user_details' table
            String detailsQuery = "INSERT INTO user_details ( phone_number, address) VALUES (?, ?)";
            pstmtDetails = conn.prepareStatement(detailsQuery);
          
            pstmtDetails.setString(1, phone);
            pstmtDetails.setString(2, address);
            int detailsInserted = pstmtDetails.executeUpdate();

            if (detailsInserted == 0) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtUser != null) pstmtUser.close();
                if (pstmtDetails != null) pstmtDetails.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}