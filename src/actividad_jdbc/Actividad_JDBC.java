/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad_jdbc;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*; 

public class Actividad_JDBC {

    public static void main(String[] args) {
        
        String usuario = "root", url = "jdbc:mysql://localhost:3306/my_proyect";
   
        Connection conexion;
    
        Statement statement;
    
        ResultSet rs; 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conexion = DriverManager.getConnection(url, usuario,"");
            
            statement = conexion.createStatement();
            
            statement.executeUpdate("delete from categoria");
            
            statement.executeUpdate(
                    "insert into Categoria(nombre)"
                    + " values"
                    + "('sofas'),('general'),('cocinas')"
            );
            
            rs = statement.executeQuery("select * from categoria");
            
            while(rs.next()){
                System.out.println( rs.getInt("id_categoria" )+ " : " + rs.getString("nombre"));
            }
            
            System.out.println("------actulizar------");
            
            statement.executeUpdate("update categoria SET nombre = 'cocinas 2' where nombre = 'cocinas';");
            
            
            rs = statement.executeQuery("select * from categoria");
             
            while(rs.next()){
                System.out.println( rs.getInt("id_categoria" )+ " : " + rs.getString("nombre"));
            }
            
            System.out.println("------eliminar------");
            
            statement.executeUpdate("delete from categoria where nombre = 'general';");
            
            rs = statement.executeQuery("select * from categoria");
             
            while(rs.next()){
                System.out.println( rs.getInt("id_categoria" )+ " : " + rs.getString("nombre"));
            }
            
            
            System.out.println("------agregar------"); 
             
            statement.executeUpdate(
                    "insert into Categoria(nombre)"
                    + " values"
                    + "('comedores'),('armarios'),('puertas')"
            );
            
            rs = statement.executeQuery("select * from categoria");
             
            while(rs.next()){
                System.out.println( rs.getInt("id_categoria" )+ " : " + rs.getString("nombre"));
            }
                 
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
