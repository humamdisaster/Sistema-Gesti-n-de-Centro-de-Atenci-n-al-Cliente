import javax.swing.*;
import java.util.Map;

public class VerTicketsGUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnVolver;
    private AppListener listener;

    public VerTicketsGUI() {
        setTitle("Lista de Tickets");
        setResizable(false);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(10, 10, 460, 300);
        contentPane.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 320, 100, 30);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            setVisible(false);
            listener.AbrirMenuPrincipal();
        });
    }

    public void setListener(AppListener l) { this.listener = l; }

    public void mostrarTickets(Map<String, Cliente> clientes) {
        textArea.setText("");
        for (Cliente c : clientes.values()) {
            textArea.append("Tickets de " + c.info(true) + "\n");
            for (Ticket t : c.getTickets()) {
                textArea.append(" - " + t.resumen(true) + "\n");
            }
            textArea.append("\n");
        }
    }
}