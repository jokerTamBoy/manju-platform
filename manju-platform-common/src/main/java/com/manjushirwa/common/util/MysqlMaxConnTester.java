package com.manjushirwa.common.util;
/*
以前工作的时候由于Oracle8i数据库经常出现用户过多的错误,由于数据量大,经常出现ORA:12500错误,但主要原因是访问过多而引起的,后来就用Java写了个简单的用JDBC连接来测试Oracle最大连接数的程序.现在常用MySQL,所以又写了一个简单的测试其最大连接数的程序，在此介绍给大家：
*/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

class MysqlMaxConnTester{
    public static void main(String args[]){
        int count=0;
        Connection []conn=new Connection[1000];
        Statement  []stmt =new Statement[1000];
        ResultSet  []rs =new ResultSet[1000];
        try{
            Class.forName
                    ("com.MySQL.jdbc.Driver").newInstance();
            for(count=0;count<300;count++){
                conn[count] = DriverManager.getConnection
                        ("jdbc:mysql://localhost/MySQL", "root", "");
                stmt[count]=conn[count].createStatement();
                rs[count]=stmt[count].executeQuery
                        ("SELECT * FROM user");
                while (rs[count].next()){
                    //System.out.println(rs.getString(1)+ "\t "+rs.getString(2));
                }
                System.out.print(count+"\t");
            }


        }catch(SQLException ex1){
            System.out.println("\n"+ex1.toString());
        }catch(InstantiationException ex2){
            System.out.println("\n"+ex2.toString());
        }catch(ClassNotFoundException ex3){
            System.out.println("\n"+ex3.toString());
        }catch(IllegalAccessException ex4){
            System.out.println("\n"+ex4.toString());
        }finally{
            try{ System.in.read();
                System.out.println ("\nClose the Connections:");
                for(;count>=0;count--){
                    rs[count].close();
                    stmt[count].close();
                    conn[count].close();
                    System.out.print(count+"\t");
                }
            }catch(SQLException ex){
                System.out.println
                        ("\n Close connection exception:"+ex.toString());
            }catch(IOException io_ex){}
        }//end the first "try"
    }
}
 
