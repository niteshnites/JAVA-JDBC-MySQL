import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ConnectionDemo {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {

            // 1. Load the properties file from the resources folder
            Properties props = new Properties();
            InputStream inputStream = ConnectionDemo.class.getClassLoader().getResourceAsStream("demo.properties");

            // Check if the file is found
            if (inputStream == null) {
                System.out.println("Sorry, unable to find demo.properties");
                return;
            }

            // Load the properties
            props.load(inputStream);

            // 2. Read the properties
            String theUser = props.getProperty("user");
            String thePassword = props.getProperty("password");
            String theDburl = props.getProperty("dburl");

            System.out.println("Connecting to database...");
            System.out.println("Database URL: " + theDburl);
            System.out.println("User: " + theUser);

            // 3. Get a connection to database
            myConn = DriverManager.getConnection(theDburl, theUser, thePassword);

            System.out.println("\nConnection successful!\n");

            // 4. Create a statement
            myStmt = myConn.createStatement();

            // 5. Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");

            // 6. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    /**
     * Prompts the user. Return true if they enter "yes", false otherwise
     *
     * @return
     */
    private static boolean askUserIfOkToSave() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Is it okay to save?  yes/no: ");
        String input = scanner.nextLine();

        scanner.close();

        return input.equalsIgnoreCase("yes");
    }

    private static void showSalaries(Connection myConn, String theDepartment)
            throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        System.out.println("Show Salaries for Department: " + theDepartment);

        try {
            // Prepare statement
            myStmt = myConn
                    .prepareStatement("select * from employees where department=?");

            myStmt.setString(1, theDepartment);

            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            while (myRs.next()) {
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName,
                        department, salary);
            }

            System.out.println();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myStmt, myRs);
        }

    }

    private static void close(Connection myConn, Statement myStmt,
                              ResultSet myRs) throws SQLException {
        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private static void close(Statement myStmt, ResultSet myRs)
            throws SQLException {

        close(null, myStmt, myRs);
    }
}
