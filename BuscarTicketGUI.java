import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class BuscarTicketGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private JComboBox<String> comboTickets;
    private JTextArea areaInfo;
    private JButton btnVolver;
    private AppListener listener;
    private Map<String, Cliente> clientes;
    
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
    
    public void setListener(AppListener l) {
        this.listener = l;
    }
    
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