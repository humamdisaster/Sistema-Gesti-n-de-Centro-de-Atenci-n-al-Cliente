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
    private JTextField txtHoras;
    private JButton btnGuardar, btnVolver;
    private AppListener listener;
    private Map<String, Cliente> clientes;
    
    public EditarTicketGUI() {
        setTitle("Editar Ticket");
        setResizable(false);
        setBounds(100, 100, 450, 450); // ventana más alta
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
        scrollDesc.setBounds(130, 80, 250, 100); // más alto para que no se solape
        contentPane.add(scrollDesc);
        
        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(30, 200, 100, 25);
        contentPane.add(lblEstado);
        
        comboEstado = new JComboBox<>(new String[]{"Pendiente", "Resuelto"});
        comboEstado.setBounds(130, 200, 120, 25);
        contentPane.add(comboEstado);
        
        // Satisfacción
        JLabel lblSatisfaccion = new JLabel("Satisfacción:");
        lblSatisfaccion.setBounds(30, 240, 100, 25);
        contentPane.add(lblSatisfaccion);
        
        comboSatisfaccion = new JComboBox<>(new Integer[]{1,2,3,4,5});
        comboSatisfaccion.setBounds(130, 240, 60, 25);
        contentPane.add(comboSatisfaccion);
        
        // Horas de resolución
        JLabel lblHoras = new JLabel("Tiempo de resolución (h):");
        lblHoras.setBounds(30, 280, 160, 25);
        contentPane.add(lblHoras);
        
        txtHoras = new JTextField();
        txtHoras.setBounds(200, 280, 60, 25);
        contentPane.add(txtHoras);
        
        // Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 340, 100, 30);
        contentPane.add(btnGuardar);
        btnGuardar.addActionListener(e -> guardarCambios());
        
        btnVolver = new JButton("Atrás");
        btnVolver.setBounds(220, 340, 100, 30);
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
            txtHoras.setText(String.valueOf(ticket.getTiempoRespuesta()));
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
        
        try {
            // Validar descripción
            if (nuevaDesc.length() > 50) {
                throw new DescripcionLargaException("La descripción no puede superar los 50 caracteres.");
            }

            // Validar tiempo de respuesta
            double horas;
            try {
                horas = Double.parseDouble(txtHoras.getText());
            } catch (NumberFormatException e) {
                throw new TiempoRespuestaInvalidoException("Por favor, ingrese un número válido para las horas.");
            }

            if (horas < 0) {
                throw new TiempoRespuestaInvalidoException("El tiempo de resolución no puede ser negativo.");
            }

            // Actualizar ticket
            listener.editarTicketGUI(idCliente, idTicket, nuevaDesc, nuevoEstado, nuevaSatisfaccion, horas);
            JOptionPane.showMessageDialog(this, "Ticket actualizado correctamente.");

            setVisible(false);
            listener.AbrirMenuPrincipal();

        } catch (DescripcionLargaException | TiempoRespuestaInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
}