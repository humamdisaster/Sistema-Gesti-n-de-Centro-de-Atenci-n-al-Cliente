import javax.swing.*;
import java.util.Map;

/**
 * La clase {@code EliminarClienteGUI} representa la ventana gráfica
 * para eliminar un cliente dentro del sistema.
 * 
 * <p>Permite seleccionar un cliente de un comboBox y eliminarlo de manera
 * irreversible. Muestra un aviso de precaución al usuario antes de confirmar
 * la eliminación.</p>
 * 
 * <p>La clase utiliza un {@link AppListener} para interactuar con la
 * lógica de negocio y la navegación hacia el menú principal.</p>
 */
public class EliminarClienteGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /** Panel principal de la ventana. */
    private JPanel contentPane;
    
    /** ComboBox para seleccionar el cliente a eliminar. */
    private JComboBox<String> comboClientes;
    
    /** Botón para eliminar al cliente seleccionado y 
     * botón para volver al menú principal. 
     */
    private JButton btnEliminar, btnVolver;
    
    /** Área de texto con aviso de precaución sobre la acción irreversible. */
    private JTextArea areaAviso;
    
    /** Listener para manejar eventos de la aplicación y comunicarse con la lógica del programa. */
    private AppListener listener;
    
    /** Mapa de clientes cargados en la interfaz. */
    private Map<String, Cliente> clientes;

    /**
     * Constructor de la ventana {@code EliminarClienteGUI}.
     * Inicializa la interfaz y sus componentes gráficos.
     */
    public EliminarClienteGUI() {
        setTitle("Eliminar Cliente");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblCliente = new JLabel("Seleccione un Cliente:");
        lblCliente.setBounds(30, 30, 150, 25);
        contentPane.add(lblCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(200, 30, 250, 25);
        contentPane.add(comboClientes);

        areaAviso = new JTextArea("⚠ Atención: Esta acción es irreversible.");
        areaAviso.setEditable(false);
        areaAviso.setBounds(30, 70, 420, 50);
        contentPane.add(areaAviso);

        btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBounds(80, 150, 150, 30);
        contentPane.add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(260, 150, 150, 30);
        contentPane.add(btnVolver);

        // --- Eventos ---
        btnEliminar.addActionListener(e -> {
            if (listener != null) {
                String idCliente = (String) comboClientes.getSelectedItem();
                if (idCliente != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro de eliminar al cliente " + idCliente + "?\nEsta acción no se puede deshacer.",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (confirm == JOptionPane.YES_OPTION) {
                        listener.EliminarCliente(idCliente);
                        comboClientes.removeItem(idCliente); // actualizar combo
                    }
                }
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
    public void setListener(AppListener l) { this.listener = l; }

    /**
     * Carga el mapa de clientes en la interfaz y llena el comboBox.
     * 
     * @param clientes mapa de clientes disponibles
     */
    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
        comboClientes.removeAllItems();
        for (Cliente c : clientes.values()) {
            comboClientes.addItem(c.getId());
        }
    }
}