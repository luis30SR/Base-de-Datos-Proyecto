import org.postgresql.gss.GSSOutputStream;

import java.sql.*;

public class Dbmanager {

    private static final String User = "postgres";
    private static final String Pass = "C.l.f3012";
    private static Connection connection;

    public static void initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lavadero_luissalgado", User, Pass);
            System.out.println("Conexión exitosa.");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void insertClientes(String nombre, String apellido, String tipoDocumento, int numeroDocumento,
                                      Date fechaNacimiento, String genero, String correoElectronico, String direccion, int telefono) {
        try {
            // Ajustamos la consulta para omitir clienteID si es autoincremental
            String query = "INSERT INTO clientes(nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmn = connection.prepareStatement(query);

            stmn.setString(1, nombre);
            stmn.setString(2, apellido);
            stmn.setString(3, tipoDocumento);
            stmn.setInt(4, numeroDocumento);
            stmn.setDate(5, fechaNacimiento); // Conversión de fecha a SQL Date
            stmn.setString(6, genero);
            stmn.setString(7, correoElectronico);
            stmn.setString(8, direccion);
            stmn.setInt(9, telefono);

            stmn.executeUpdate();
            System.out.println("Cliente insertado correctamente.");

        } catch (Exception e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        }

    }

    public static void getClientes() {

        try {
                PreparedStatement stm = connection.prepareStatement("SELECT * FROM clientes order by ClienteID");

                ResultSet result = stm.executeQuery();

            System.out.println("id | nombre | apellido| tipoDocumento| numeroDocumento| fechaNacimiento| genero| correoElectronico| direccion| telefono ");

                while (result.next()){

                    long clienteID = result.getLong("clienteID");
                    String nombre = result.getString("nombre");
                    String apellido = result.getString("apellido");
                    String tipoDocumento = result.getString("tipoDocumento");
                    int numeroDocumento = result.getInt("numeroDocumento");
                    Date fechaNacimiento = result.getDate("fechaNacimiento");
                    String genero = result.getString("genero");
                    String correoElectronico = result.getString("correoElectronico");
                    String direccion = result.getString("direccion");
                    int telefono = result.getInt("telefono");

                    System.out.println(clienteID + "\t" + nombre +"\t"+ apellido + "\t"+ tipoDocumento+ "\t" + numeroDocumento +"\t"+fechaNacimiento+"\t"+ genero+"\t"+correoElectronico+"\t"+direccion+"\t"+telefono+"\t");


                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
        }



    }


    public static void uptateClientes(long Nuevo_clienteID, String Nuevo_nombre, String Nuevo_apellido, String Nuevo_tipoDocumento, int Nuevo_numeroDocummento, Date Nuevo_fechaNacimiento, String Nuevo_genero, String Nuevo_correoElectronico, String Nuevo_direccion, int Nuevo_telefono ){

        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE clientes SET nombre = ?, apellido = ?, tipoDocumento = ?, numeroDocumento = ?, fechaNacimiento = ?, genero = ?, correoElectronico = ?, direccion = ?, telefono = ? WHERE clienteID = ?");


            stm.setString(1, Nuevo_nombre);
            stm.setString(2, Nuevo_apellido);
            stm.setString(3, Nuevo_tipoDocumento);
            stm.setInt(4, Nuevo_numeroDocummento);
            stm.setDate(5, Nuevo_fechaNacimiento);
            stm.setString(6, Nuevo_genero);
            stm.setString(7, Nuevo_correoElectronico);
            stm.setString(8, Nuevo_direccion);
            stm.setInt(9, Nuevo_telefono);
            stm.setLong(10, Nuevo_clienteID);

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static void deleteClientes(long clienteID){

       try {

           PreparedStatement stm = connection.prepareStatement("DELETE FROM clientes WHERE clienteID = ?");
           stm.setLong(1,clienteID);

           int row = stm.executeUpdate();

           if (row == 0){

               System.out.println("No se elimino el registro");


           } else  {

               System.out.println("Se elimino el registro");

           }


       }catch (SQLException throwables){

           throwables.printStackTrace();
       }









    }
}