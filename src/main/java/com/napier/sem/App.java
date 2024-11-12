package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.math.* ;

public class App
{
    /**
     * Connect to MySQL database on local system using port 33060
     */

   private Connection con = null;

   public static void main(String[] args)  {

            App a = new App();

            if (args.length < 1) {
                //local
                a.connect("localhost:33060", 0);
            } else {
                a.connect(args[0], Integer.parseInt(args[1]));
            }
       try {
           a.report1();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       //Disconnect from database
            a.disconnect();
    }

    private void disconnect() {
        System.out.println("Disconnecting");
    }

    private void connect(String arg, int i) {
       System.out.println("Connecting to " + arg);
   }

    public void report1() throws IOException {
            StringBuilder sb = new StringBuilder();
            try {
                //Create an SQL statement
                //Create an SQL statement
                Statement stmt = con.createStatement();
                //Create string for SQL statement
                String sql = "SELECT * FROM country";
                //Execute SQL statement
                ResultSet rset = stmt.executeQuery(sql);
                //cycle
                while (rset.next()) {
                    String name = rset.getString("name");
                    Integer population = rset.getInt("population");
                    sb.append(name + "\t" + population + "\r\n");
                }
                new File("./output").mkdir();
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(new File("./output/report1.txt"))
                );
                writer.write(sb.toString());
                writer.close();
                System.out.println(sb.toString());
            } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Failed to get details");
                   System.out.println(e.getStackTrace());
                   e.printStackTrace();
                    return;
        }
            System.out.println(sb.toString());
    }
}
