package com.example.kms.moneyball;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by KMS on 2015-02-05.
 */
public class GetSearchR {




        public String SearchResult(String Year, String Tuta,String Field, String ar1, String ar2)
        {
            String SearchResult = "";
            String query = "";
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/moneyball";
            String userId = "root";
            String passwd = "1234";
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            //ar1 = URLEncoder.encode(ar1,"UTF-8");
            //System.out.println(ar1);
            try {

                Class.forName(driver); // Driver Loading
                conn = DriverManager.getConnection(url, userId, passwd); // Connection

                System.out.println("1.Opened MySQL successfully Query=" + ar1);

                if(Year!=""){//연도를 입력한 경우 타자와 투수를 구분하고 필드의 유무를 파악하여 내림차순으로 데이터를 정렬하여 보여준다.
                    if(Field.equals("name")||Field.equals("Team")){
                        query = "select * from " +Year+Tuta+" where " + Field + " = " +"'" +ar1+"'" + "order by WAR desc";
                    }
                    else if(ar1!=""&&ar2!="") {
                        query = "select * from " +Year+Tuta+" where "+Field+">="+ar1+" and " +Field +"<="+ar2 + " order by "+Field+" desc";
                    }else if(ar1!=""&&ar2==""){
                        query = "select * from " +Year+Tuta+" where "+Field+">="+ar1 + " order by "+Field+" desc";
                    }else if(ar1==""&&ar2!=""){
                        query = "select * from " +Year+Tuta+" where "+Field +"<="+ar2 + " order by "+Field+" desc";
                    }else{
                        query = "select * from " +Year+Tuta+" order by "+Field+" desc";
                    }
                }else if(Year==""){//연도를 입력하지 않은 경우 for문을 이용하여 모든 연도의 데이터를 구분하여 보여준다.
                    for(int i=2014;i>=2014;i--){
                        if(Field.equals("name")||Field.equals("Team")){
                            query = "select * from " +i+Tuta+" where " + Field + " = " +"'" +ar1+"'" + "order by WAR desc";
                        }
                        else if(ar1!=""&&ar2!="") {
                            query = "select * from " +i+Tuta+" where "+Field+">="+ar1+" and " +Field +"<="+ar2 + " order by "+Field+" desc";
                        }else if(ar1!=""&&ar2==""){
                            query = "select * from " +i+Tuta+" where "+Field+">="+ar1 + " order by "+Field+" desc";
                        }else if(ar1==""&&ar2!=""){
                            query = "select * from " +i+Tuta+" where "+Field +"<="+ar2 + " order by "+Field+" desc";
                        }else{
                            query = "select * from " +i+Tuta +" order by "+Field+" desc";
                        }
                    }
                }


                System.out.println("2.IF statement done  " + Tuta +" // "+ query);

                stmt = conn.createStatement();

                rs = stmt.executeQuery(query);
                String[] HtArray = new String[34];
                while ( rs.next() ) {//선택된 모든 컬럼을 하나의 스트링으로 담아 저장한다. 후에 테이블 형태로 바꾸거나 적절한 형태로 바꾸는 것이 필요함.

                    if(Tuta.equals("Hitter")){

                        HtArray[0]= rs.getString("name");
                        HtArray[1]= rs.getString("Team");
                        HtArray[2]= rs.getString("Avg");
                        HtArray[3]= rs.getString("Game");
                        HtArray[4]= rs.getString("PA");
                        HtArray[5]= rs.getString("AB");
                        HtArray[6]= rs.getString("RB");
                        HtArray[7]= rs.getString("H");
                        HtArray[8]= rs.getString("2B");
                        HtArray[9]= rs.getString("3B");
                        HtArray[10]= rs.getString("HR");
                        HtArray[11]= rs.getString("R");
                        HtArray[12]= rs.getString("SB");
                        HtArray[13]= rs.getString("FSB");
                        HtArray[14]= rs.getString("SacH");
                        HtArray[15]= rs.getString("SacF");
                        HtArray[16]= rs.getString("BB");
                        HtArray[17]= rs.getString("IBB");
                        HtArray[18]= rs.getString("HBP");
                        HtArray[19]= rs.getString("SOut");
                        HtArray[20]= rs.getString("DP");
                        HtArray[21]= rs.getString("SLG");
                        HtArray[22]= rs.getString("OBP");
                        HtArray[23]= rs.getString("Error");
                        HtArray[24]= rs.getString("SB_T");
                        HtArray[25]= rs.getString("BB_K");
                        HtArray[26]= rs.getString("LH_H");
                        HtArray[27]= rs.getString("MultiH");
                        HtArray[28]= rs.getString("OPS");
                        HtArray[29]= rs.getString("ScoreAvg");
                        HtArray[30]= rs.getString("PinchAvg");
                        HtArray[31]= rs.getString("wOBA");
                        HtArray[32]= rs.getString("wRRA");
                        HtArray[33]= rs.getString("WAR");
                        SearchResult +="<tr>";
                        for(String i : HtArray){
                            SearchResult +="<td>"+i+"</td>";
                        }
                        SearchResult +="</tr>";


                    }else if(Tuta.equals("Pitcher")){

                        HtArray[0]= rs.getString("name");
                        HtArray[1]= rs.getString("Team");
                        HtArray[2]= rs.getString("ERA");
                        HtArray[3]= rs.getString("Game");
                        HtArray[4]= rs.getString("CG");
                        HtArray[5]= rs.getString("SHO");
                        HtArray[6]= rs.getString("Win");
                        HtArray[7]= rs.getString("Lose");
                        HtArray[8]= rs.getString("Save");
                        HtArray[9]= rs.getString("Hold");
                        HtArray[10]= rs.getString("WRA");
                        HtArray[11]= rs.getString("PA");
                        HtArray[12]= rs.getString("BF");
                        HtArray[13]= rs.getString("INN");
                        HtArray[14]= rs.getString("HIT");
                        HtArray[15]= rs.getString("H2");
                        HtArray[16]= rs.getString("H3");
                        HtArray[17]= rs.getString("HR");
                        HtArray[18]= rs.getString("SH");
                        HtArray[19]= rs.getString("SF");
                        HtArray[20]= rs.getString("BB");
                        HtArray[21]= rs.getString("IB");
                        HtArray[22]= rs.getString("HP");
                        HtArray[23]= rs.getString("KK");
                        HtArray[24]= rs.getString("WP");
                        HtArray[25]= rs.getString("BK");
                        HtArray[26]= rs.getString("R");
                        HtArray[27]= rs.getString("ER");
                        HtArray[28]= rs.getString("BS");
                        HtArray[29]= rs.getString("WHIP");
                        HtArray[30]= rs.getString("OAVG");
                        HtArray[31]= rs.getString("QS");
                        HtArray[32]= rs.getString("FIP");
                        HtArray[33]= rs.getString("WAR");



                        for(String i : HtArray){
                            SearchResult +="<td>"+i+"</td>";
                        }
                        SearchResult +="</tr>";
                    }

                }

                System.out.println("3.Resultset done");

                rs.close();
                conn.close();
            } catch ( ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            System.out.println("4.Operation done successfully");

            return SearchResult;
        }
}




