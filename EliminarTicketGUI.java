import javax.swing.*;

/**
 * Ventana para eliminar tickets de clientes.
 * Permite seleccionar un cliente, ver sus tickets y eliminar el ticket seleccionado.
 */
public class EliminarTicketGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal;             // Panel contenedor de la ventana
    private JComboBox<String> comboClientes;   // Combo para seleccionar cliente
    private JComboBox<String> comboTickets;    // Combo para seleccionar ticket del cliente
    private JButton btnEliminar;               // Botón para eliminar ticket seleccionado
    private JButton btnVolver;                 // Botón para volver al menú principal
    private AppListener listener;              // Listener que conecta la GUI con SistemaAtencion

    /**
     * Constructor que inicializa la ventana de eliminación de tickets.
     */
    public EliminarTicketGUI() {
        setTitle("Eliminar Ticket");
        setResizable(false);
        setSize(500, 250);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        setContentPane(panelPrincipal);

        // Etiqueta y combo de clientes
        JLabel labelCliente = new JLabel("Seleccione Cliente:");
        labelCliente.setBounds(30, 30, 150, 25);
        panelPrincipal.add(labelCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(200, 30, 250, 25);
        panelPrincipal.add(comboClientes);

        // Etiqueta y combo de tickets
        JLabel labelTicket = new JLabel("Seleccione Ticket:");
        labelTicket.setBounds(30, 80, 150, 25);
        panelPrincipal.add(labelTicket);

        comboTickets = new JComboBox<>();
        comboTickets.setBounds(200, 80, 250, 25);
        panelPrincipal.add(comboTickets);

        // Botones
        btnEliminar = new JButton("Eliminar Ticket");
        btnEliminar.setBounds(80, 150, 150, 30);
        panelPrincipal.add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(260, 150, 150, 30);
        panelPrincipal.add(btnVolver);

        // --- Eventos ---

        // Al seleccionar un cliente, rellena el combo de tickets correspondientes
        comboClientes.addActionListener(e -> {
            if (listener != null) {
                String idClienteSeleccionado = (String) comboClientes.getSelectedItem();
                if (idClienteSeleccionado != null) {
                    listener.rellenarTicketsCliente(idClienteSeleccionado);
                }
            }
        });

        // Al presionar eliminar, elimina el ticket seleccionado en SistemaAtencion
        btnEliminar.addActionListener(e -> {
            if (listener != null) {
                String idClienteSeleccionado = (String) comboClientes.getSelectedItem();
                String ticketSeleccionado = (String) comboTickets.getSelectedItem();
                if (idClienteSeleccionado != null && ticketSeleccionado != null) {
                    // Extraer solo el ID antes del " - "
                    String idTicket = ticketSeleccionado.split(" - ")[0].trim();
                    listener.EliminarTicket(idClienteSeleccionado, idTicket);

                    // Actualiza comboTickets después de eliminar
                    listener.rellenarTicketsCliente(idClienteSeleccionado);
                }
            }
        });

        // Al presionar volver, cierra la ventana y abre menú principal
        btnVolver.addActionListener(e -> {
            setVisible(false);
            if (listener != null) {
                listener.AbrirMenuPrincipal();
            }
        });
    }

    /**
     * Asigna el AppListener que conecta la GUI con SistemaAtencion.
     *
     * @param l Listener que maneja eventos y operaciones sobre SistemaAtencion
     */
    public void setListener(AppListener l) {
        this.listener = l;
    }

    /** Agrega un cliente al combo de clientes.
    * @param idCliente ID del cliente a agregar al comboBox.
    */
    public void llenarComboClientes(String idCliente) {
        comboClientes.addItem(idCliente);
    }

    /** Limpia todos los clientes del combo. */
    public void limpiarComboClientes() {
        comboClientes.removeAllItems();
    }

    /** Agrega un ticket al combo de tickets. 
     *@param idTicket ID del ticket a agregar al comboBox.
     */
    public void llenarComboTickets(String idTicket) {
        comboTickets.addItem(idTicket);
    }

    /** Limpia todos los tickets del combo. */
    public void limpiarComboTickets() {
        comboTickets.removeAllItems();
    }
}