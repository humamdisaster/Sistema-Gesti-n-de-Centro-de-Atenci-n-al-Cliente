import javax.swing.*;
import java.awt.*;

/**
 * Ventana para filtrar y mostrar tickets según su estado.
 * Permite seleccionar un estado ("Pendiente" o "Resuelto") y muestra
 * los tickets correspondientes de todos los clientes registrados en SistemaAtencion.
 */
public class FiltrarEstadoGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<String> comboEstado;       // Combo para elegir estado
    private JTextArea areaResultados;            // Área de texto para mostrar tickets filtrados
    private AppListener listener;                // Listener que conecta la GUI con SistemaAtencion
    private SistemaAtencion sistema;             // Referencia al sistema de atención con todos los clientes y tickets

    /**
     * Constructor que inicializa la ventana de filtrado de tickets.
     * 
     * @param sistema Referencia al SistemaAtencion que contiene clientes y tickets
     */
    public FiltrarEstadoGUI(SistemaAtencion sistema) {
        this.sistema = sistema;

        setTitle("Filtrar Tickets por Estado");
        setResizable(false);
        setBounds(100, 100, 500, 520); // altura aumentada para botón Atrás
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        setContentPane(panelPrincipal);

        // Label de selección de estado
        JLabel labelSeleccionEstado = new JLabel("Seleccione Estado:");
        labelSeleccionEstado.setBounds(30, 20, 150, 25);
        panelPrincipal.add(labelSeleccionEstado);

        // Combo para elegir estado
        comboEstado = new JComboBox<>(new String[]{"Pendiente", "Resuelto"});
        comboEstado.setBounds(180, 20, 120, 25);
        panelPrincipal.add(comboEstado);

        // Área de resultados con scroll
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);

        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBounds(30, 60, 430, 380);
        panelPrincipal.add(scrollResultados);

        // Botón Atrás
        JButton botonAtras = new JButton("Atrás");
        botonAtras.setBounds(200, 450, 100, 30);
        panelPrincipal.add(botonAtras);

        // Acción del botón Atrás
        botonAtras.addActionListener(e -> {
            setVisible(false); // cierra la ventana actual
            if (listener != null) {
                listener.AbrirMenuPrincipal(); // vuelve al menú principal
            }
        });

        // Evento al cambiar selección en el combo de estado
        comboEstado.addActionListener(e -> mostrarTicketsFiltrados());
    }

    /**
     * Asigna el AppListener que conecta la GUI con SistemaAtencion.
     * 
     * @param listener Listener que maneja eventos y operaciones sobre SistemaAtencion
     */
    public void setListener(AppListener listener) {
        this.listener = listener;
    }

    /**
     * Muestra en el área de resultados todos los tickets de todos los clientes
     * que coincidan con el estado seleccionado en comboEstado.
     * Obtiene los clientes y tickets directamente desde SistemaAtencion.
     */
    private void mostrarTicketsFiltrados() {
        String estadoSeleccionado = (String) comboEstado.getSelectedItem();
        StringBuilder resultados = new StringBuilder();

        // Recorre todos los clientes del SistemaAtencion
        for (Cliente cliente : sistema.getClientes().values()) {
            // Recorre los tickets de cada cliente
            for (Ticket ticket : cliente.getTickets()) {
                if (ticket.getEstado().equalsIgnoreCase(estadoSeleccionado)) {
                    resultados.append("Cliente: ").append(cliente.getNombre())
                              .append(" (").append(cliente.getId()).append(")\n")
                              .append("Ticket: ").append(ticket.getId())
                              .append(" - ").append(ticket.getDescripcion()).append("\n")
                              .append("Estado: ").append(ticket.getEstado())
                              .append(", Tiempo: ").append(ticket.getTiempoRespuesta())
                              .append("h, Satisfacción: ").append(ticket.getSatisfaccion())
                              .append("\n---------------------\n");
                }
            }
        }

        areaResultados.setText(resultados.toString());
    }
}