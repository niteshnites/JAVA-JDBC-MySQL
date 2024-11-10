import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

public class BlobWriteDemo {

    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        InputStream input = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "student", "nites");

            // 2. Prepare statement
            String sql = "UPDATE employees SET resume=? WHERE email='john.doe@foo.com'";
            myStmt = myConn.prepareStatement(sql);

            // 3. Load file from the resources folder
            ClassLoader classLoader = BlobWriteDemo.class.getClassLoader();
            input = classLoader.getResourceAsStream("sample_resume.pdf");

            // If the file is not found in resources, throw an exception
            if (input == null) {
                throw new IOException("File 'sample_resume.pdf' not found in resources.");
            }

            System.out.println("Reading input file from resources.");

            // 4. Set parameter for resume file (Binary stream)
            myStmt.setBinaryStream(1, input);

            // 5. Execute the statement to store the resume in the database
            System.out.println("\nStoring resume in database...");
            myStmt.executeUpdate();

            System.out.println("\nCompleted successfully!");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // Close resources
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            close(myConn, myStmt);
        }
    }

    private static void close(Connection myConn, Statement myStmt)
            throws SQLException {
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }
}
