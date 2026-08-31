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
        PreparedStatement pstmtId = null;
        PreparedStatement pstmtDetails = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Insert into 'users' table using sequence
            String userQuery = "INSERT INTO users (user_id, username, email, password) VALUES (users_seq.NEXTVAL, ?, ?, ?)";
            pstmtUser = conn.prepareStatement(userQuery);
            pstmtUser.setString(1, username);
            pstmtUser.setString(2, email);
            pstmtUser.setString(3, password); // For real apps, hash the password!
            int userInserted = pstmtUser.executeUpdate();

            if (userInserted == 0) {
                conn.rollback();
                return false;
            }

            // Step 2: Get the generated user_id from sequence
            String idQuery = "SELECT users_seq.CURRVAL FROM dual";
            pstmtId = conn.prepareStatement(idQuery);
            rs = pstmtId.executeQuery();
            if (rs.next()) {
                userId = rs.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // Step 3: Insert into 'user_details' table
            String detailsQuery = "INSERT INTO user_details (user_id, phone, address) VALUES (?, ?, ?)";
            pstmtDetails = conn.prepareStatement(detailsQuery);
            pstmtDetails.setInt(1, userId);
            pstmtDetails.setString(2, phone);
            pstmtDetails.setString(3, address);
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
                if (pstmtId != null) pstmtId.close();
                if (pstmtUser != null) pstmtUser.close();
                if (pstmtDetails != null) pstmtDetails.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}