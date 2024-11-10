import java.sql.*;

public class SelectJdbcDemo {
    public static void main(String[] args) {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "student";
        String pass = "nites";

        try {
            // Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);

            // Create a statement
            myStmt = myConn.createStatement();

            // Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");

            // Process the result set
            while(myRs.next()) {
                System.out.println(myRs.getString("first_name")
                        + ", " + myRs.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
