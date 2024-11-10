import java.sql.*;

public class InsertJdbcDemo {

    public static void main(String[] args) throws SQLException {

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

            // Insert a new employee
            System.out.println("Inserting a new employees to database");
            int rowsAffected = myStmt.executeUpdate(
                    "insert into employees (last_name, first_name, email, department, salary)" +
                            "values ('Bhardwaj', 'Nitesh', 'nites@email.com' ,'DEV', '33000.00')"
            );

            // Verify this by getting a list of employees
            myRs = myStmt.executeQuery("select * from employees order by last_name");

            // Process the result set
            while(myRs.next()) {
                System.out.println(myRs.getString("first_name") + ", " + myRs.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(myRs != null) {
                myRs.close();
            }
        }
    }

}
