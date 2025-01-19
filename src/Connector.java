import java.sql.*;

public class Connector {
    public Connection conn = null;
    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "root");
        } catch (ClassNotFoundException ex) {
            System.out.println("SQLite is not installed. System exits with error!");
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println(sqle);
            System.exit(1);
        }
        return conn;
    }
}
