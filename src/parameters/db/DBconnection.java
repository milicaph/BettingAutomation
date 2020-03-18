package parameters.db;

import java.sql.*;
import java.util.ArrayList;

public class DBconnection {
    private static String url = "jdbc:mysql://localhost/ada_bets";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "MMarkovic10.07.";
    private static Connection con;
    private String[][] inputArr;

    public ArrayList<String> urls = new ArrayList<String>();
    public ArrayList<String> usernames = new ArrayList<String>();
    public ArrayList<String> passwords = new ArrayList<String>();
    public ArrayList<String> hostsDB = new ArrayList<String>();
    public ArrayList<String> guestsDB = new ArrayList<String>();
    public ArrayList<String> betsDB = new ArrayList<String>();

    public void readDBCredentionals() {
        try {
            Class.forName(driverName);
            try {
                //Create a connection to DB by passing Url,Username,Password as parameters
                con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();

                //Executing the Queries
                ResultSet rs = stmt.executeQuery("SELECT * FROM creditionals");
                System.out.println(rs.toString());


                //Iterating the data in the Table
                while (rs.next()) {
                    String link = rs.getString("link");
                    String uname = rs.getString("username");
                    String pass = rs.getString("password");

                    urls.add(link);
                    usernames.add(uname);
                    passwords.add(pass);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Driver not found.");
        }

        }

    public void readDBTickets() {
        try {
            Class.forName(driverName);
            try {
                //Create a connection to DB by passing Url,Username,Password as parameters
                con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();


                //Executing the Queries
                ResultSet rs = stmt.executeQuery("SELECT * FROM tickets;");

                //Iterating the data in the Table
                while (rs.next()) {
                    String host = rs.getString("host");
                    String guest = rs.getString("guest");
                    String tip = rs.getString("tip");

                    hostsDB.add(host);
                    guestsDB.add(guest);
                    betsDB.add(tip);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Driver not found.");
        }

    }

}










