package com.prototypelab.crud_android_mssql.main.constants;

import com.prototypelab.crud_android_mssql.main.Model.Customer;

public class sql {


    public static String CREATE_TABLE(){
        return "CREATE TABLE Customer ( Id VARCHAR(25) PRIMARY KEY, Name VARCHAR(255),Address VARCHAR(255))";
    }

    public static String SELECT(String id){
        return "SELECT Id, Name, Address FROM Customer WHERE Id = '"+id+"';";
    }

    public static String MERGE(Customer c){
        return "MERGE INTO Customer AS target\n" +
                "USING (VALUES ('"+c.getId()+"', '"+c.getName()+"', '"+c.getAddress()+"')) AS source (Id, Name, Address)\n" +
                "ON target.Id = source.Id\n" +
                "WHEN MATCHED THEN\n" +
                "    UPDATE SET Name = source.Name, Address = source.Address\n" +
                "WHEN NOT MATCHED THEN\n" +
                "    INSERT (Id, Name, Address) VALUES (source.Id, source.Name, source.Address);\n";
    }

    public static String DELETE(String id){
        return "DELETE FROM Customer WHERE Id = '"+id+"';";
    }

}
