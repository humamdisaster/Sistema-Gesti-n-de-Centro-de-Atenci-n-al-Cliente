import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * La clase {@code BuscarClienteGUI} representa la ventana gráfica
 * para buscar y visualizar información de clientes dentro del sistema.
 * 
 * <p>Permite seleccionar un cliente de un comboBox y mostrar sus datos,
 * incluyendo nombre, correo y tickets asociados.</p>
 * 
 * <p>La clase utiliza un {@link AppListener} para interactuar con la
 * lógica de negocio y la navegación hacia el menú principal.</p>
 */
public class BuscarClienteGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /** Panel principal de la ventana. */
    private JPanel contentPane;
    
    /** ComboBox para seleccionar clientes disponibles. */
    private JComboBox<String> comboClientes;
    
    /** Área de texto donde se muestra la información del cliente seleccionado. */
    private JTextArea areaInfo;
    
    /** Botón para volver al menú principal. */
    private JButton btnVolver;
    
    /** Listener para manejar eventos de la aplicación y comunicarse con la lógica del programa. */
    private AppListener listener;
    
    /** Mapa de clientes cargados en la interfaz, indexados por ID. */
    private Map<String, Cliente> clientes;

    /**
     * Constructor de la ventana {@code BuscarClienteGUI}.
     * Inicializa la interfaz y sus componentes gráficos.
     */
    public BuscarClienteGUI() {
        setTitle("Buscar Cliente");
        setResizable(false);
        setBounds(100, 150, 500, 550);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblCliente = new JLabel("Seleccione un Cliente:");
        lblCliente.setBounds(30, 30, 200, 25);
        contentPane.add(lblCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(200, 30, 250, 25);
        contentPane.add(comboClientes);

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBounds(30, 80, 420, 250);
        contentPane.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 340, 120, 30);
        contentPane.add(btnVolver);

        // Eventos
        comboClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInfoCliente();
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
     * Carga el mapa de clientes en la interfaz y llena el comboBox.
     * 
     * <p>Selecciona automáticamente el primer cliente disponible y
     * muestra su información.</p>
     * 
     * @param clientes mapa de clientes, indexados por su ID
     */
    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
        comboClientes.removeAllItems();

        for (Cliente c : clientes.values()) {
            comboClientes.addItem(c.getId() + " - " + c.getNombre());
        }

        if (comboClientes.getItemCount() > 0) {
            comboClientes.setSelectedIndex(0);
            mostrarInfoCliente();
        }
    }

    /**
     * Muestra la información del cliente seleccionado en el área de texto.
     * 
     * <p>Incluye ID, nombre, correo, cantidad de tickets y detalles de cada ticket
     * (ID, descripción, estado, tiempo de respuesta y satisfacción).</p>
     */
    private void mostrarInfoCliente() {
        String seleccionado = (String) comboClientes.getSelectedItem();
        if (seleccionado == null) return;

        String idCliente = seleccionado.split(" - ")[0];
        Cliente c = clientes.get(idCliente);
        if (c != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(c.getId()).append("\n")
              .append("Nombre: ").append(c.getNombre()).append("\n")
              .append("Email: ").append(c.getEmail()).append("\n")
              .append("Tickets: ").append(c.getTickets().size()).append("\n\n");

            if (!c.getTickets().isEmpty()) {
                sb.append("Lista de Tickets:\n");
                for (Ticket t : c.getTickets()) {
                    sb.append(" - ").append(t.getId()).append(": ")
                      .append(t.getDescripcion())
                      .append(" | Estado: ").append(t.getEstado())
                      .append(" | Horas: ").append(t.getTiempoRespuesta())
                      .append(" | Satisfacción: ").append(t.getSatisfaccion())
                      .append("\n");
                }
            }

            areaInfo.setText(sb.toString());
        }
    }
}