import javax.swing.*;

public class EliminarTicketGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboTickets;
    private JButton btnEliminar, btnVolver;
    private AppListener listener;

    public EliminarTicketGUI() {
        setTitle("Eliminar Ticket");
        setResizable(false);
        setSize(500, 250);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblCliente = new JLabel("Seleccione Cliente:");
        lblCliente.setBounds(30, 30, 150, 25);
        contentPane.add(lblCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(200, 30, 250, 25);
        contentPane.add(comboClientes);

        JLabel lblTicket = new JLabel("Seleccione Ticket:");
        lblTicket.setBounds(30, 80, 150, 25);
        contentPane.add(lblTicket);

        comboTickets = new JComboBox<>();
        comboTickets.setBounds(200, 80, 250, 25);
        contentPane.add(comboTickets);

        btnEliminar = new JButton("Eliminar Ticket");
        btnEliminar.setBounds(80, 150, 150, 30);
        contentPane.add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(260, 150, 150, 30);
        contentPane.add(btnVolver);

        // --- Eventos ---
        comboClientes.addActionListener(e -> {
            if (listener != null) {
                String idCliente = (String) comboClientes.getSelectedItem();
                if (idCliente != null) {
                    listener.rellenarTicketsCliente(idCliente); 
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            if (listener != null) {
                String idCliente = (String) comboClientes.getSelectedItem();
                String ticketSeleccionado = (String) comboTickets.getSelectedItem();
                if (idCliente != null && ticketSeleccionado != null) {
                    // Extraer solo el ID antes del " - "
                    String idTicket = ticketSeleccionado.split(" - ")[0].trim();
                    listener.EliminarTicket(idCliente, idTicket);
                    // Actualizar comboTickets después de eliminar
                    listener.rellenarTicketsCliente(idCliente);
                }
            }
        });

        btnVolver.addActionListener(e -> {
            setVisible(false);
            if (listener != null) {
                listener.AbrirMenuPrincipal();
            }
        });
    }

    public void setListener(AppListener l) {
        this.listener = l;
    }

    public void llenarComboClientes(String idCliente) {
        comboClientes.addItem(idCliente);
    }

    public void limpiarComboClientes() {
        comboClientes.removeAllItems();
    }

    public void llenarComboTickets(String idTicket) {
        comboTickets.addItem(idTicket);
    }

    public void limpiarComboTickets() {
        comboTickets.removeAllItems();
    }
}