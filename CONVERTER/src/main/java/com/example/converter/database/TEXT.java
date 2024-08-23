package com.example.converter.database;

public class TEXT {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Good to go");
        } catch (Exception e) {
            System.out.println("JDBC Driver errror");
        }
    }
}
