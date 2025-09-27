import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * La clase {@code BuscarTicketGUI} representa la ventana gráfica
 * para buscar y visualizar información de tickets dentro del sistema.
 * 
 * <p>Permite seleccionar un ticket de un comboBox y mostrar sus datos,
 * incluyendo cliente asociado, descripción, estado, tiempo de resolución
 * y nivel de satisfacción.</p>
 * 
 * <p>La clase utiliza un {@link AppListener} para interactuar con la
 * lógica de negocio y la navegación hacia el menú principal.</p>
 */
public class BuscarTicketGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    /** Panel principal de la ventana. */
    private JPanel contentPane;
    
    /** ComboBox para seleccionar tickets disponibles. */
    private JComboBox<String> comboTickets;
    
    /** Área de texto donde se muestra la información del ticket seleccionado. */
    private JTextArea areaInfo;
    
    /** Botón para volver al menú principal. */
    private JButton btnVolver;
    
    /** Listener para manejar eventos de la aplicación y comunicarse con la lógica del programa. */
    private AppListener listener;
    
    /** Mapa de clientes cargados en la interfaz, utilizado para buscar tickets por cliente. */
    private Map<String, Cliente> clientes;
    
    /**
     * Constructor de la ventana {@code BuscarTicketGUI}.
     * Inicializa la interfaz y sus componentes gráficos.
     */
    public BuscarTicketGUI() {
        setTitle("Buscar Ticket");
        setResizable(false);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel lblTicket = new JLabel("Seleccione un Ticket:");
        lblTicket.setBounds(30, 30, 200, 25);
        contentPane.add(lblTicket);
        
        comboTickets = new JComboBox<>();
        comboTickets.setBounds(200, 30, 250, 25);
        contentPane.add(comboTickets);
        
        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBounds(30, 80, 420, 200);
        contentPane.add(scroll);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 300, 120, 30);
        contentPane.add(btnVolver);
        
        // Eventos
        comboTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInfoTicket();
            }
        });
        
        btnVolver.addActionListener(e -> {
            setVisible(false);
            if (listener != null) listener.AbrirMenuPrincipal();
        });
    }
    
    /**
     * Asigna el listener que manejará los eventos de la aplicación.
     * 
     * @param l instancia de {@link AppListener} para manejar eventos y lógica de negocio
     */
    public void setListener(AppListener l) {
        this.listener = l;
    }
    
    /**
     * Carga el mapa de clientes en la interfaz y llena el comboBox con todos los tickets.
     * 
     * <p>Selecciona automáticamente el primer ticket disponible y muestra su información.</p>
     * 
     * @param clientes mapa de clientes, cada uno con sus tickets asociados
     */
    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
        comboTickets.removeAllItems();
        
        for (Cliente cliente : clientes.values()) {
            for (Ticket t : cliente.getTickets()) {
                comboTickets.addItem(t.getId() + " - " + t.getDescripcion());
            }
        }
        
        if (comboTickets.getItemCount() > 0) {
            comboTickets.setSelectedIndex(0);
            mostrarInfoTicket();
        }
    }
    
    /**
     * Muestra la información del ticket seleccionado en el área de texto.
     * 
     * <p>Incluye el cliente asociado, correo del cliente, descripción del ticket,
     * estado, horas de resolución y satisfacción.</p>
     */
    private void mostrarInfoTicket() {
        String seleccionado = (String) comboTickets.getSelectedItem();
        if (seleccionado == null) return;
        
        String idTicket = seleccionado.split(" - ")[0];
        
        // Buscar el ticket en los clientes
        for (Cliente cliente : clientes.values()) {
            Ticket t = cliente.buscarTicket(idTicket);
            if (t != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cliente: ").append(cliente.getNombre())
                  .append(" (").append(cliente.getId()).append(")\n")
                  .append("Email: ").append(cliente.getEmail()).append("\n\n")
                  .append("Descripción: ").append(t.getDescripcion()).append("\n")
                  .append("Estado: ").append(t.getEstado()).append("\n")
                  .append("Horas de resolución: ").append(t.getTiempoRespuesta()).append("\n")
                  .append("Satisfacción: ").append(t.getSatisfaccion()).append("\n");
                areaInfo.setText(sb.toString());
                break;
            }
        }
    }
}