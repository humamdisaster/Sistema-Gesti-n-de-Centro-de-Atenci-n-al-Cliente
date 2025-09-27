import javax.swing.*;
import java.util.Map;

public class EliminarClienteGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> comboClientes;
    private JButton btnEliminar, btnVolver;
    private JTextArea areaAviso;
    private AppListener listener;
    private Map<String, Cliente> clientes;

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

    public void setListener(AppListener l) { this.listener = l; }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
        comboClientes.removeAllItems();
        for (Cliente c : clientes.values()) {
            comboClientes.addItem(c.getId());
        }
    }
}