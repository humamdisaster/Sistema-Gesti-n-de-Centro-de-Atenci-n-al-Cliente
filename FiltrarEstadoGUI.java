import javax.swing.*;
import java.awt.*;

public class FiltrarEstadoGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboEstado;
    private JTextArea areaResultados;
    private AppListener listener;
    private SistemaAtencion sistema;

    public FiltrarEstadoGUI(SistemaAtencion sistema) {
        this.sistema = sistema;
        setTitle("Filtrar Tickets por Estado");
        setResizable(false);
        setBounds(100, 100, 500, 520); // se aumenta altura para el botón Atrás
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // Label selección de estado
        JLabel lblSeleccion = new JLabel("Seleccione Estado:");
        lblSeleccion.setBounds(30, 20, 150, 25);
        panel.add(lblSeleccion);

        // Combo para elegir estado
        comboEstado = new JComboBox<>(new String[]{"Pendiente", "Resuelto"});
        comboEstado.setBounds(180, 20, 120, 25);
        panel.add(comboEstado);

        // Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(30, 60, 430, 380);
        panel.add(scroll);

        // Botón Atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(200, 450, 100, 30); // centrado horizontalmente
        panel.add(btnAtras);

        btnAtras.addActionListener(e -> {
            setVisible(false); // cierra la ventana actual
            if (listener != null) {
                listener.AbrirMenuPrincipal(); // vuelve al menú principal
            }
        });

        // Evento al cambiar selección
        comboEstado.addActionListener(e -> mostrarTicketsFiltrados());
    }

    // Setter para AppListener
    public void setListener(AppListener listener) {
        this.listener = listener;
    }

    // Mostrar tickets filtrados
    private void mostrarTicketsFiltrados() {
        String estadoSeleccionado = (String) comboEstado.getSelectedItem();
        StringBuilder sb = new StringBuilder();

        for (Cliente cliente : sistema.getClientes().values()) {
            for (Ticket t : cliente.getTickets()) {
                if (t.getEstado().equalsIgnoreCase(estadoSeleccionado)) {
                    sb.append("Cliente: ").append(cliente.getNombre())
                      .append(" (").append(cliente.getId()).append(")\n")
                      .append("Ticket: ").append(t.getId())
                      .append(" - ").append(t.getDescripcion()).append("\n")
                      .append("Estado: ").append(t.getEstado())
                      .append(", Tiempo: ").append(t.getTiempoRespuesta())
                      .append("h, Satisfacción: ").append(t.getSatisfaccion())
                      .append("\n---------------------\n");
                }
            }
        }
        areaResultados.setText(sb.toString());
    }
}