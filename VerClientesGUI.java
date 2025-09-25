import javax.swing.*;
import java.util.List;

public class VerClientesGUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnVolver;
    private AppListener listener;

    public VerClientesGUI() {
        setTitle("Lista de Clientes");
        setResizable(false);
        setBounds(100, 100, 400, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(10, 10, 360, 300);
        contentPane.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(140, 320, 100, 30);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            setVisible(false);
            listener.AbrirMenuPrincipal();
        });
    }

    public void setListener(AppListener l) { this.listener = l; }

    public void mostrarClientes(List<Cliente> clientes) {
        textArea.setText("");
        for (Cliente c : clientes) {
            textArea.append(c.info(true) + "\n");
        }
    }
}