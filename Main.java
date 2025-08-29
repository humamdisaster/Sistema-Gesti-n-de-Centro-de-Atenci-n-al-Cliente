import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaAtencion sistema = new SistemaAtencion();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Mostrar clientes");
            System.out.println("2. Mostrar tickets");
            System.out.println("3. Agregar ticket");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) {
                case 1:
                    sistema.mostrarClientes();
                    break;
                case 2:
                    sistema.mostrarTickets();
                    break;
                case 3:
                    System.out.print("ID Cliente: ");
                    String idC = sc.nextLine();
                    System.out.print("ID Ticket: ");
                    String idT = sc.nextLine();
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    sistema.agregarTicket(idC, idT, desc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while(opcion != 0);

        sc.close();
    }
}