package server;

//import java.sql.Connection;
//import java.sql.SQLException;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnector {

	// connects to the database
    public static DataSource getDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/utech_complaint_system");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        return mysqlDataSource;
    }
    
    // test
    /*
    public static void main(String[] args) {
        DataSource dataSource = getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database:");
            e.printStackTrace();
        }
    }
    */
}
