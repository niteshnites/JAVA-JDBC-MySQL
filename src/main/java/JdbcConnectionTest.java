import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionTest {
    public static void main(String[] args) {
        // Database credentials
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";  // Change URL to your database URL
        String username = "student";  // Replace with your DB username
        String password = "nites";  // Replace with your DB password

        // Test the connection
        try {
            // Load the JDBC driver (MySQL in this case, change for your DB)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // If successful, print this message
            System.out.println("Connection successful!");

            // Close the connection
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions and display error messages
            e.printStackTrace();
        }
    }
}
