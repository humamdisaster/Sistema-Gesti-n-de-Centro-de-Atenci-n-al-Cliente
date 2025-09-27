import javax.swing.*;
import java.util.Map;

/**
 * Ventana para visualizar todos los tickets registrados en el SistemaAtencion.
 */
public class VerTicketsGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal;      // Panel contenedor
    private JTextArea areaTickets;      // Área de texto donde se muestran los tickets
    private JButton btnVolver;          // Botón para volver al menú principal
    private AppListener listener;       // Listener que maneja la interacción con SistemaAtencion

    public VerTicketsGUI() {
        // Configuración básica de la ventana
        setTitle("Lista de Tickets");           // Título de la ventana
        setResizable(false);                    // Evita que el usuario cambie el tamaño
        setBounds(100, 100, 500, 400);         // Posición y tamaño inicial de la ventana

        // Panel principal que contendrá todos los componentes
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);         // Layout nulo para colocar los componentes manualmente
        setContentPane(panelPrincipal);         // Se asigna el panel como contenido de la ventana

        // Área de texto para mostrar los tickets
        areaTickets = new JTextArea();
        areaTickets.setEditable(false);         // No se permite editar el contenido
        JScrollPane scrollTickets = new JScrollPane(areaTickets); // Scroll para cuando haya muchos tickets
        scrollTickets.setBounds(10, 10, 460, 300); // Posición y tamaño del scroll dentro del panel
        panelPrincipal.add(scrollTickets);      // Se agrega el scroll (y por ende el área de texto) al panel

        // Botón Volver para regresar al menú principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 320, 100, 30); // Posición y tamaño del botón
        panelPrincipal.add(btnVolver);          // Se agrega el botón al panel

        // Evento que se ejecuta al presionar el botón Volver
        btnVolver.addActionListener(e -> {
            setVisible(false);                   // Oculta esta ventana
            listener.AbrirMenuPrincipal();       // Llama al método del listener para abrir el menú principal
        });
    }

    /** Setter para asignar el listener. */
    public void setListener(AppListener listener) {
        this.listener = listener;
    }

    /** Muestra la lista de tickets por cliente en el área de texto. */
    public void mostrarTickets(Map<String, Cliente> clientes) {
        areaTickets.setText("");
        for (Cliente cliente : clientes.values()) {
            areaTickets.append("Tickets de " + cliente.info(true) + "\n");
            for (Ticket ticket : cliente.getTickets()) {
                areaTickets.append(" - " + ticket.resumen(true) + "\n");
            }
            areaTickets.append("\n");
        }
    }
}