import java.util.Scanner;

/**
 * Main.java
 * Punto de entrada del sistema de atención de tickets.
 * Contiene el menú principal y la interacción con el usuario mediante consola.
 */
public class Main {

    /**
     * Método principal que arranca el programa.
     * Presenta un menú interactivo al usuario para gestionar clientes y tickets.
     */
    public static void main(String[] args) {
        // Instancia del sistema que maneja los clientes y tickets
        SistemaAtencion sistema = new SistemaAtencion();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar menú
            System.out.println("\n--- MENU ---");
            System.out.println("1. Mostrar clientes");
            System.out.println("2. Mostrar tickets");
            System.out.println("3. Agregar ticket");
            System.out.println("4. Editar ticket");
            System.out.println("5. Eliminar ticket");
            System.out.println("6. Filtrar tickets por estado");
            System.out.println("7. Filtrar tickets por tiempo de respuesta");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            // Leer opción
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    // Mostrar lista de clientes cargados en el sistema
                    sistema.mostrarClientes();
                    break;

                case 2:
                    // Mostrar todos los tickets (de todos los clientes)
                    sistema.mostrarTickets();
                    break;

                case 3:
                    // Agregar un nuevo ticket a un cliente existente
                    System.out.print("ID Cliente: ");
                    String idClienteNuevo = sc.nextLine();
                    System.out.print("ID Ticket: ");
                    String idTicketNuevo = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcionNueva = sc.nextLine();
                    sistema.agregarTicket(idClienteNuevo, idTicketNuevo, descripcionNueva);
                    break;

                case 4:
                    // Editar un ticket ya existente
                    System.out.print("ID Cliente: ");
                    String idClienteEditar = sc.nextLine();
                    System.out.print("ID Ticket: ");
                    String idTicketEditar = sc.nextLine();
                    System.out.print("Nueva descripción: ");
                    String descripcionEditada = sc.nextLine();
                    System.out.print("Nuevo estado: ");
                    String estadoEditado = sc.nextLine();
                    System.out.print("Nuevo tiempo de respuesta (h): ");
                    int tiempoEditado = sc.nextInt();
                    sc.nextLine();
                    sistema.editarTicket(idClienteEditar, idTicketEditar, 
                                         descripcionEditada, estadoEditado, tiempoEditado);
                    break;

                case 5:
                    // Eliminar un ticket
                    System.out.print("ID Cliente: ");
                    String idClienteEliminar = sc.nextLine();
                    System.out.print("ID Ticket: ");
                    String idTicketEliminar = sc.nextLine();
                    sistema.eliminarTicket(idClienteEliminar, idTicketEliminar);
                    break;

                case 6:
                    // Filtrar tickets según estado (ej: Pendiente o Resuelto)
                    System.out.print("Ingrese estado a filtrar (Pendiente, Resuelto): ");
                    String estadoFiltrado = sc.nextLine();
                    sistema.filtrarTicketsPorEstado(estadoFiltrado);
                    break;

                case 7:
                    // Filtrar tickets por cierto tiempo de respuesta
                    System.out.print("Ingrese horas a filtrar: ");
                    int limiteHoras = sc.nextInt();
                    sc.nextLine();
                    sistema.filtrarTicketsPorTiempo(limiteHoras);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        } while(opcion != 0);

        // Cerrar scanner
        sc.close();
    }
}