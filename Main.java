import java.util.Scanner;
import java.io.*;

/**
 * Main.java
 * Punto de entrada del sistema de atención de tickets.
 * Contiene el menú principal y la interacción con el usuario mediante consola.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Instancia del sistema que maneja los clientes y tickets
        SistemaAtencion sistema = new SistemaAtencion();
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        
        // Inicialización ventanas interfaz grafica
        AddTicketGUI menuTickets = new AddTicketGUI(sistema.getClientes());
        MenuGUI menu = new MenuGUI(menuTickets);
        
        // Lectura de datos desde archivo .csv
        File csvClientes = new File("clientes.csv");
    	
    	// Se verifica si el archivo clientes.csv existe o no, de no existir lo crear para guardar los de la sesion
    	if (csvClientes.exists()) {
    		// System.out.println("El archivo extiste :p");
    		FileReader fileR = new FileReader(csvClientes);
    		BufferedReader bufferR = new BufferedReader(fileR);
    		// System.out.println("Esto deberia de imprimir los clientes:p");
    		String linea;
    		int nTicket = 1;
    		while((linea = bufferR.readLine()) != null) {
    			String[] cadenas = linea.split(",");
    			sistema.agregarCliente(cadenas[0], cadenas[1], cadenas[2]);
    			// nTicket se asignara por orden de llegada
    			sistema.agregarTicket(cadenas[0], "T"+ nTicket, cadenas[3]);
    			nTicket += 1;
    			}
    		bufferR.close();
    	}
    	
    		
    	else {
			csvClientes.createNewFile();	
    		System.out.println("El archivo no existe :c, pero fue creado");
    		}
        
        do {  
        	menu.setVisible(true);
        	menuTickets.leerClientes();
            // Mostrar menú principal por consola
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

            // Leer opción seleccionada
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // Mostrar lista de clientes registrados en el sistema
                    sistema.mostrarClientes();
                    break;

                case 2:
                    // Mostrar todos los tickets asociados a todos los clientes
                    sistema.mostrarTickets();
                    break;

                case 3:
                	
                    // Agregar un nuevo ticket a un cliente
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
                    sistema.editarTicket(idClienteEditar, idTicketEditar, descripcionEditada, estadoEditado, tiempoEditado);
                    break;

                case 5:
                    // Eliminar un ticket por su ID
                    System.out.print("ID Cliente: ");
                    String idClienteEliminar = sc.nextLine();
                    System.out.print("ID Ticket: ");
                    String idTicketEliminar = sc.nextLine();
                    sistema.eliminarTicket(idClienteEliminar, idTicketEliminar);
                    break;

                case 6:
                    // Filtrar tickets por estado (Pendiente o Resuelto)
                    System.out.print("Ingrese estado a filtrar (Pendiente, Resuelto): ");
                    String estadoFiltrado = sc.nextLine();
                    sistema.filtrarTicketsPorEstado(estadoFiltrado);
                    break;

                case 7:
                    // Filtrar tickets según un tiempo de respuesta específico
                    System.out.print("Ingrese horas a filtrar: ");
                    int limiteHoras = sc.nextInt();
                    sc.nextLine();
                    sistema.filtrarTicketsPorTiempo(limiteHoras);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);

        // Cerrar scanner
        sc.close();
    }
}