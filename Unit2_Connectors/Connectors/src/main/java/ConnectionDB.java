
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author samuel
 */
class ManageDB {

    private Connection conn = null;

    String connection = "jdbc:mysql://localhost:33333/BDJocs?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";

    public Connection openConnection(String server, String user, String pass) throws SQLException {

        String DBName = "BDJocs";
        int port = 33333;

        server += ":" + port;
        server += "/" + DBName;
        server += "?useUnicode=true&characterEncoding=UTF-8";

        try {
//            conn = DriverManager.getConnection(connection);
            conn = DriverManager.getConnection(server, user, pass);
            System.out.println("Connection stablished ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn) throws SQLException {

        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {

        try {
            if (this.conn != null) {
                return this.conn;
            } else {
                this.openConnection("jdbc:mysql://localhost", "root", "root");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return this.conn;
    }

    public void getSelect(String table) throws SQLException {
        try {
            ResultSet rst = this.getConnection().createStatement().executeQuery("SELECT * FROM " + table);
            System.out.println("Content of " + table);
            System.out.println("******************************");
            ResultSetMetaData rsmdQuery = rst.getMetaData();
//            System.out.println(rsmdQuery);
            // print the columns name
//            for (int i = 1; i <= rsmdQuery.getColumnCount(); i++) {
//                System.out.print(String.format("%-25.25s", rsmdQuery.getColumnName(i)));
//            }

            while (rst.next()) {
                for (int i = 1; i <= rsmdQuery.getColumnCount(); i++) {
                    System.out.print(String.format("%-25.25s ", rst.getString(i)));
                }
                System.out.println();
            }

        } catch (SQLException e) {

        }
    }

    public void getDBTables() throws SQLException {
        DatabaseMetaData dbmd = this.getConnection().getMetaData();
        ResultSet rsmd = dbmd.getTables("BDJocs", null, null, null);
        while (rsmd.next()) {
            System.out.println(String.format("%-15s %-15s %-15s",
                    rsmd.getString(1),
                    rsmd.getString(3),
                    rsmd.getString(4)));
        }

    }

    public int insert() throws SQLException {
        int rows = 0;
        String SQL
                = "INSERT INTO Joc VALUES (1, 'Double Dragon', 'Dos germans bessons experts en "
                + "arts marcials s`han de fer camí en un escenari urbà on membres "
                + "de bandes rivals volen deixar - los fora de combat.', 1);";
        Statement query = this.getConnection().createStatement();
        rows = query.executeUpdate(SQL);
        
        System.out.println("Number of rows affected: " + rows);
        
        return rows;
    }
}

public class ConnectionDB {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("ConnectionDB ");
        Connection conn = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        ManageDB DB = new ManageDB();
        DB.openConnection("jdbc:mysql://localhost", "root", "root");
//        DB.getSelect("Genere");
//        DB.getDBTables();
        DB.insert();
        DB.closeConnection(DB.getConnection());

//        conn = DB.getConnection();
//        Connection conn = DriverManager.getConnection(connection);
    }

}
