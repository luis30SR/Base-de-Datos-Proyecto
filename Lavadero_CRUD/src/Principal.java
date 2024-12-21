import java.util.Scanner;
import java.sql.Date;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Dbmanager.initConnection();

        int opc;

        do {
            System.out.println("\n1. Ver cliente");
            System.out.println("2. Crear cliente");
            System.out.println("3. Editar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opc = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer después de nextInt()

            switch (opc) {
                case 1 -> verClientes();
                case 2 -> crearClientes();
                case 3 -> editarClientes();
                case 4 -> eliminarClientes();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opc != 5);
    }

    public static void verClientes() {
        // Método para implementar la visualización de clientes
        Dbmanager.getClientes();


    }

    public static void crearClientes() {
        try {
            System.out.println("Ingrese nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese apellido: ");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese tipo de documento: ");
            String tipoDocumento = scanner.nextLine();

            System.out.println("Ingrese número de documento: ");
            int numeroDocumento = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer después de nextInt()

            System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimientoInput = scanner.nextLine();

            // Convertir fecha de String a java.sql.Date
            Date fechaNacimiento = Date.valueOf(fechaNacimientoInput);

            System.out.println("Ingrese género: ");
            String genero = scanner.nextLine();

            System.out.println("Ingrese correo electrónico: ");
            String correoElectronico = scanner.nextLine();

            System.out.println("Ingrese dirección: ");
            String direccion = scanner.nextLine();

            System.out.println("Ingrese teléfono: ");
            int telefono = scanner.nextInt();

            // Insertar cliente
            Dbmanager.insertClientes(nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono);
            System.out.println("Cliente creado con éxito.");

        } catch (Exception e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
        }
    }

    public static void editarClientes() {
        // Mostrar los clientes disponibles para edición
        Dbmanager.getClientes();

        // Solicitar el ID del cliente a editar
        System.out.println("Ingrese ClienteID de la persona que quiere editar:");
        long clienteID = scanner.nextLong();
        scanner.nextLine(); // Limpia el buffer después de nextLong()

        // Solicitar y capturar los datos actualizados del cliente
        System.out.println("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese nuevo apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese nuevo tipo de documento: ");
        String tipoDocumento = scanner.nextLine();

        System.out.println("Ingrese nuevo número de documento: ");
        int numeroDocumento = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer después de nextInt()

        System.out.println("Ingrese nueva fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoInput = scanner.nextLine();

        // Convertir fecha de String a java.sql.Date
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = Date.valueOf(fechaNacimientoInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: El formato de fecha es inválido. Use YYYY-MM-DD.");
            return; // Salir del método si la fecha es inválida
        }

        System.out.println("Ingrese nuevo género: ");
        String genero = scanner.nextLine();

        System.out.println("Ingrese nuevo correo electrónico: ");
        String correoElectronico = scanner.nextLine();

        System.out.println("Ingrese nueva dirección: ");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese nuevo teléfono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer después de nextInt()

        // Llamar al método que actualiza los datos en la base de datos
        Dbmanager.uptateClientes(clienteID, nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono);
    }
    public static void eliminarClientes() {
        // Método para implementar la eliminación de clientes

        Dbmanager.getClientes();
        System.out.println("Ingrese el Id del cliente que que quiere eliminar");
        long clienteID = scanner.nextLong();

        Dbmanager.deleteClientes(clienteID);

    }
}