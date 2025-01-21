package com.phonebook.data;

public class DbData {
    //URL connection Syntax: "jdbc:mysql://ipaddress/db_name"
    public static final String dbUrl = "jdbc:mysql://localhost:3306/test";

    //database user name
    public static final String username = "root";

    //database password
    public static final String password = "4785";

    //query to execute
    public static final String querySelect = "select * from contacts;";

    public static final String queryDelete = "delete from contacts where firstName='Oliver';";
}
