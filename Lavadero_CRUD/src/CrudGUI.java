import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class CrudGUI {
    public static void main(String[] args) {
        // Inicializar conexión con la base de datos
        Dbmanager.initConnection();

        // Crear la ventana principal
        JFrame mainFrame = new JFrame("Gestión de Clientes");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);

        // Crear el panel principal con botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Diseño vertical para botones

        // Crear botones
        JButton verClientesBtn = new JButton("Ver Clientes");
        JButton crearClienteBtn = new JButton("Crear Cliente");
        JButton editarClienteBtn = new JButton("Editar Cliente");
        JButton eliminarClienteBtn = new JButton("Eliminar Cliente");
        JButton salirBtn = new JButton("Salir");

        // Funcionalidad para "Ver Clientes"
        verClientesBtn.addActionListener(e -> {
            // Llama a Dbmanager para obtener clientes y los muestra en consola
            Dbmanager.getClientes();
        });

        // Funcionalidad para "Crear Cliente"
        crearClienteBtn.addActionListener(e -> {
            // Mostrar cuadro de diálogo para capturar datos del cliente
            try {
                String nombre = JOptionPane.showInputDialog(mainFrame, "Ingrese nombre:");
                String apellido = JOptionPane.showInputDialog(mainFrame, "Ingrese apellido:");
                String tipoDocumento = JOptionPane.showInputDialog(mainFrame, "Ingrese tipo de documento:");
                int numeroDocumento = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ingrese número de documento:"));
                String fechaNacimientoInput = JOptionPane.showInputDialog(mainFrame, "Ingrese fecha de nacimiento (YYYY-MM-DD):");
                Date fechaNacimiento = Date.valueOf(fechaNacimientoInput);
                String genero = JOptionPane.showInputDialog(mainFrame, "Ingrese género:");
                String correoElectronico = JOptionPane.showInputDialog(mainFrame, "Ingrese correo electrónico:");
                String direccion = JOptionPane.showInputDialog(mainFrame, "Ingrese dirección:");
                int telefono = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ingrese teléfono:"));

                Dbmanager.insertClientes(nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono);
                JOptionPane.showMessageDialog(mainFrame, "Cliente creado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "Error al crear cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Funcionalidad para "Editar Cliente"
        editarClienteBtn.addActionListener(e -> {
            Dbmanager.getClientes(); // Mostrar clientes disponibles para editar
            try {
                long clienteID = Long.parseLong(JOptionPane.showInputDialog(mainFrame, "Ingrese el ID del cliente a editar:"));
                String nuevoNombre = JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo nombre:");
                String nuevoApellido = JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo apellido:");
                String nuevoTipoDocumento = JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo tipo de documento:");
                int nuevoNumeroDocumento = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo número de documento:"));
                String nuevaFechaNacimiento = JOptionPane.showInputDialog(mainFrame, "Ingrese la nueva fecha de nacimiento (YYYY-MM-DD):");
                Date nuevaFecha = Date.valueOf(nuevaFechaNacimiento);
                String nuevoGenero = JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo género:");
                String nuevoCorreo = JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo correo electrónico:");
                String nuevaDireccion = JOptionPane.showInputDialog(mainFrame, "Ingrese la nueva dirección:");
                int nuevoTelefono = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ingrese el nuevo teléfono:"));

                Dbmanager.uptateClientes(clienteID, nuevoNombre, nuevoApellido, nuevoTipoDocumento, nuevoNumeroDocumento, nuevaFecha, nuevoGenero, nuevoCorreo, nuevaDireccion, nuevoTelefono);
                JOptionPane.showMessageDialog(mainFrame, "Cliente actualizado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "Error al editar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Funcionalidad para "Eliminar Cliente"
        eliminarClienteBtn.addActionListener(e -> {
            Dbmanager.getClientes(); // Mostrar clientes disponibles para eliminar
            try {
                long clienteID = Long.parseLong(JOptionPane.showInputDialog(mainFrame, "Ingrese el ID del cliente a eliminar:"));
                Dbmanager.deleteClientes(clienteID);
                JOptionPane.showMessageDialog(mainFrame, "Cliente eliminado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "Error al eliminar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Funcionalidad para "Salir"
        salirBtn.addActionListener(e -> System.exit(0));

        // Agregar botones al panel
        panel.add(verClientesBtn);
        panel.add(crearClienteBtn);
        panel.add(editarClienteBtn);
        panel.add(eliminarClienteBtn);
        panel.add(salirBtn);

        // Agregar panel a la ventana principal
        mainFrame.add(panel);

        // Mostrar ventana
        mainFrame.setVisible(true);
    }
}
