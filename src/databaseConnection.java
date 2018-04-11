import java.sql.*;
import java.util.Properties;

public class databaseConnection {
    //username
    private final String usrname = "root";

    //password
    private final String password = "root";

    //sever name
    private final String serverName = "127.0.0.1";

    //port number
    private final int portNumber =  3306;

    //database name
    private final String dbName = "movieDB";

    //default statement
    private Statement sttmnt;

    //default result set
    private ResultSet rs;

    //our conncection
    private Connection conn;

    // creates a connection to the SQL database
    public Connection getConnection() throws SQLException {
        Properties connProps = new Properties();

        connProps.put("user", this.usrname);
        connProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName + ":"
                        + this.portNumber + "/"
                        + this.dbName + "?characterEncoding=UTF-8&useSSL=false",
                connProps);

        return conn;
    }

    //connect to the db in the constructor
    public databaseConnection() {
        try {
            this.conn = this.getConnection();
            System.out.println("Connection successful, congratulations this is a great feat, no really, great.");
            sttmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sttmnt.executeQuery("SELECT Title FROM Film");
            while (rs.next()) {
                String s = rs.getString("Title");
                System.out.println(s);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: Connection failed");
            e.printStackTrace();
        }
    }
    //to query data
    public void queryData(String query) {
        try {
            sttmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sttmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL exception thrown:");
            e.printStackTrace();
        }
    }

    //to insert or remove data
    public void updateData(String query) {
        try {
            sttmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sttmnt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQL exception thrown:");
            e.printStackTrace();
        }
    }

    //get the result set
    public ResultSet getRs() {
         return rs;
    }

    //Closes the connection
    public void close(boolean toClose) {
        try {
            if (toClose)
                conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception thrown");
            e.printStackTrace();
        }
    }

}
