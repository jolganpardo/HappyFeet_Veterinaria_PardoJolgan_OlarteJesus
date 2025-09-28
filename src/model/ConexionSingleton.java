package model;

import java.sql.*;

// La conexion como un Singleton
public class ConexionSingleton {
    private static String host = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String pass = "123123";
    private static String bd = "Happy_Feet";
    private static String strConn = host + bd;

    private static Connection connection;
    private static ConexionSingleton instancia;

    private ConexionSingleton() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(strConn, user, pass);
                System.out.println("Conectado a la base de datos correctamente");
            }
        } catch (SQLException e) {
            connection = null;
            System.out.println("Error al conectarse a la base de datos.\n" + e.getMessage());
        }
    }

    public static ConexionSingleton getInstance() {
        if (instancia == null) {
            instancia = new ConexionSingleton();
        }
        return instancia;
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try{
            if (connection != null || !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion cerrada");
            }
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion.\n" + e.getMessage());
        }
    }
}
