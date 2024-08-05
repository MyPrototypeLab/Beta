package com.prototypelab.crud_android_mssql.main.connecction;


import static com.prototypelab.crud_android_mssql.main.constants.Constants.DATABASE;
import static com.prototypelab.crud_android_mssql.main.constants.Constants.PASSWORD;
import static com.prototypelab.crud_android_mssql.main.constants.Constants.SERVER_IP;
import static com.prototypelab.crud_android_mssql.main.constants.Constants.USERNAME;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMSSQL {

    //Conexion a la base de datos
    public Connection connDB(){
        Connection cnn =null;
        try {
            StrictMode.ThreadPolicy politica= new  StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn= DriverManager
                    .getConnection(
                            "jdbc:jtds:sqlserver://"+SERVER_IP
                                    + ";databaseName="+DATABASE
                                    +";user=" +USERNAME
                                    +";password="+PASSWORD
                                    +";");

        }catch (Exception e){
            e.printStackTrace();
        }
        return cnn;
    }


    //Ejecutar consultas SQL UPDATES, DELETES, INSERTS
    public void ExecuteSQLQueries(String sql) throws SQLException {
        Statement stm = null;
        stm = connDB().createStatement();
        stm.executeUpdate(sql);
        stm.close();
    }

    //Ejecutar consultas SQL SELECT
    public ResultSet ExecuteSQLSelect(String sql) throws SQLException {
        Statement stm = null;
        stm = connDB().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        if(rs.next()){
            return rs;
        }else{
            return null;
        }
    }



}
