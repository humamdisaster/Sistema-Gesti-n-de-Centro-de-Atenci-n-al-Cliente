import javax.swing.*;
import java.util.Map;

public class EditarTicketGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboTickets;
    private JTextArea txtDescripcion;
    private JComboBox<String> comboEstado;
    private JComboBox<Integer> comboSatisfaccion;
    private JButton btnGuardar, btnVolver;
    private AppListener listener;
    private Map<String, Cliente> clientes;

    public EditarTicketGUI() {
        setTitle("Editar Ticket");
        setResizable(false);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Combo clientes
        comboClientes = new JComboBox<>();
        comboClientes.setBounds(30, 30, 150, 25);
        contentPane.add(comboClientes);
        comboClientes.addActionListener(e -> cargarTickets());

        // Combo tickets
        comboTickets = new JComboBox<>();
        comboTickets.setBounds(200, 30, 200, 25);
        contentPane.add(comboTickets);
        comboTickets.addActionListener(e -> mostrarDatosTicket());

        // Descripción
        JLabel lblDesc = new JLabel("Descripción:");
        lblDesc.setBounds(30, 80, 100, 25);
        contentPane.add(lblDesc);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setBounds(130, 80, 250, 80);
        contentPane.add(scrollDesc);

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(30, 130, 100, 25);
        contentPane.add(lblEstado);

        comboEstado = new JComboBox<>(new String[]{"Pendiente", "Resuelto"});
        comboEstado.setBounds(130, 130, 120, 25);
        contentPane.add(comboEstado);

        // Satisfacción
        JLabel lblSatisfaccion = new JLabel("Satisfacción:");
        lblSatisfaccion.setBounds(30, 180, 100, 25);
        contentPane.add(lblSatisfaccion);

        comboSatisfaccion = new JComboBox<>(new Integer[]{1,2,3,4,5});
        comboSatisfaccion.setBounds(130, 180, 60, 25);
        contentPane.add(comboSatisfaccion);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 250, 100, 30);
        contentPane.add(btnGuardar);
        btnGuardar.addActionListener(e -> guardarCambios());

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(220, 250, 100, 30);
        contentPane.add(btnVolver);
        btnVolver.addActionListener(e -> {
            setVisible(false);
            listener.AbrirMenuPrincipal();
        });
    }

    public void setListener(AppListener l) { this.listener = l; }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
        comboClientes.removeAllItems();
        for (Cliente clienteActual : clientes.values()) {
            comboClientes.addItem(clienteActual.getId() + " - " + clienteActual.getNombre());
        }
        cargarTickets();
    }

    private void cargarTickets() {
        comboTickets.removeAllItems();
        String clienteSeleccionado = (String) comboClientes.getSelectedItem();
        if (clienteSeleccionado == null) return;
        String idCliente = clienteSeleccionado.split(" - ")[0];
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            for (Ticket t : cliente.getTickets()) {
                comboTickets.addItem(t.getId() + " - " + t.getDescripcion());
            }
        }
        mostrarDatosTicket();
    }

    private void mostrarDatosTicket() {
        String clienteSeleccionado = (String) comboClientes.getSelectedItem();
        String ticketSeleccionado = (String) comboTickets.getSelectedItem();
        if (clienteSeleccionado == null || ticketSeleccionado == null) return;

        String idCliente = clienteSeleccionado.split(" - ")[0];
        String idTicket = ticketSeleccionado.split(" - ")[0];

        Cliente cliente = clientes.get(idCliente);
        Ticket ticket = cliente != null ? cliente.buscarTicket(idTicket) : null;
        if (ticket != null) {
            txtDescripcion.setText(ticket.getDescripcion());
            comboEstado.setSelectedItem(ticket.getEstado());
            comboSatisfaccion.setSelectedItem(ticket.getSatisfaccion());
        }
    }

    private void guardarCambios() {
        String clienteSeleccionado = (String) comboClientes.getSelectedItem();
        String ticketSeleccionado = (String) comboTickets.getSelectedItem();
        if (clienteSeleccionado == null || ticketSeleccionado == null) return;

        String idCliente = clienteSeleccionado.split(" - ")[0];
        String idTicket = ticketSeleccionado.split(" - ")[0];

        String nuevaDesc = txtDescripcion.getText();
        String nuevoEstado = (String) comboEstado.getSelectedItem();
        int nuevaSatisfaccion = (Integer) comboSatisfaccion.getSelectedItem();

        listener.editarTicketGUI(idCliente, idTicket, nuevaDesc, nuevoEstado, nuevaSatisfaccion);
        JOptionPane.showMessageDialog(this, "Ticket actualizado correctamente.");
        setVisible(false);
        listener.AbrirMenuPrincipal();
    }
}