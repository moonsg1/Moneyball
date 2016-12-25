package com.example.kms.moneyball;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by KMS on 2015-02-06.
 */
public class GetEAR {



    public String[][] Result()
    {

        String query = "";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/moneyball";
        String userId = "root";
        String passwd = "1234";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(driver); // Driver Loading
            conn = DriverManager.getConnection(url, userId, passwd); // Connection
            stmt = conn.createStatement();
            query = "select * from ear order by WPP desc";
            String query2 = "select count(*) from ear" ;

            System.out.println("1.Open DB well, query is "+query);


            ResultSet rs2=stmt.executeQuery(query2);
            rs2.next();
            int cnt=rs2.getInt("count(*)");
            String[][] EARarray = new String[cnt][4];
            rs2.close();
            System.out.println("2.rs2 done, cnt =" + cnt);

            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while(rs.next()){
                EARarray[i][0]= rs.getString("name");
                EARarray[i][1]= rs.getString("PPY");
                EARarray[i][2]= rs.getString("WAR");
                EARarray[i][3]= rs.getString("WPP");
                i++;
            }
            rs.close();
            conn.close();
            System.out.println("3.rs done i="+ i );
            return EARarray;


        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            String[][] array = new String[1][];
            return array;
        }

        //System.out.println("2.EAR Operation done successfully");


    }

}
