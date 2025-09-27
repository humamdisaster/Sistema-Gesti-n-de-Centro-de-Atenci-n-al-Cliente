import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class BuscarClienteGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> comboClientes;
    private JTextArea areaInfo;
    private JButton btnVolver;
    private AppListener listener;
    private Map<String, Cliente> clientes;

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

    public void setListener(AppListener l) {
        this.listener = l;
    }

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