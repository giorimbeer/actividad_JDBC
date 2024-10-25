/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad_jdbc;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

import java.sql.*; 

public class Actividad_JDBC {

    public static void main(String[] args) {
        
        //Dsarrollo de un metodo para realizar un crud en la tabla categoria
        //de la base de datos del proyecto que estoy desarrollando
        //usando jdbc, solo se requiere canbiar el primer parametro para
        //ejecutar las diferentes acciones del crud (crear, leer, actulizar, eliminar)
        
        categoriaCRUD("crear", "root", "", "jdbc:mysql://localhost:3306/my_proyect");
        
    }
    
    
     public static void categoriaCRUD(String accion, String usuario, String contraseña, String url) {
       
        String value;
        String donde;
        Connection conexion;
        Statement statement = null;
        
        Scanner scanner = new Scanner(System.in);

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conexion = DriverManager.getConnection(url,usuario,contraseña);
            
            statement = conexion.createStatement();
            
             switch (accion.toLowerCase()) {
            case "crear":
                System.out.println("Ingresa el nombre de la nueva categoria");
                value = System.in.toString();
                crear(value, statement);
                break;
            case "leer":
                System.out.println("Ingresa el ID de la categoria a leer");
                value = scanner.nextLine();
                leer(value, statement);
                break;
            case "actualizar":
                System.out.println("Ingresa el nuevo nombre:");
                value = scanner.nextLine();
                System.out.println("Ingresa ID dondde se quiere modificar el nombre");
                donde = scanner.nextLine();
                actualizar(donde,value,statement);
                break;
            case "eliminar":
                System.out.println("Ingresa el ID de la categoria a eliminar");
                value = scanner.nextLine();
                eliminar(value, statement);
                break;
            default:
                System.out.println("Acción no válida");
        }
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
        
    }

    private static void crear(String valor, Statement statement) {
        try {
            statement.executeUpdate("insert into Categoria(nombre) values ('" + valor +"')");
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void leer(String valor, Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("select * from categoria where id_categoria  = " + valor);
            rs.next();
            System.out.println("ID: " + rs.getInt("id_categoria" )+ " --> "+"Nombre: " + rs.getString("nombre"));
                   
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void actualizar(String donde,String valor, Statement statement) {
        try {
            statement.executeUpdate("update categoria SET nombre = '" + valor + "' where id_categoria = " + donde + ";");
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void eliminar(String valor, Statement statement) {
        try {
            statement.executeUpdate("delete from categoria where id_categoria = " + valor + ";");
        } catch (SQLException ex) {
            Logger.getLogger(Actividad_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
