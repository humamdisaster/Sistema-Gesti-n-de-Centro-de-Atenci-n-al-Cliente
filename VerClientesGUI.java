import javax.swing.*;
import java.util.List;

/**
 * Ventana para visualizar todos los clientes registrados en el SistemaAtencion.
 */
public class VerClientesGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal;      // Panel contenedor
    private JTextArea areaClientes;     // Área de texto donde se muestran los clientes
    private JButton btnVolver;          // Botón para volver al menú principal
    private AppListener listener;       // Listener que maneja la interacción con SistemaAtencion

    public VerClientesGUI() {
        // Configuración básica de la ventana
        setTitle("Lista de Clientes");           // Título de la ventana
        setResizable(false);                     // Evita que el usuario cambie el tamaño
        setBounds(100, 100, 400, 400);          // Posición y tamaño inicial de la ventana

        // Panel principal que contendrá todos los componentes
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);          // Layout nulo para colocar los componentes manualmente
        setContentPane(panelPrincipal);          // Se asigna el panel como contenido de la ventana

        // Área de texto para mostrar los clientes
        areaClientes = new JTextArea();
        areaClientes.setEditable(false);         // No se permite editar el contenido
        JScrollPane scrollClientes = new JScrollPane(areaClientes); // Scroll para cuando haya muchos clientes
        scrollClientes.setBounds(10, 10, 360, 300); // Posición y tamaño del scroll dentro del panel
        panelPrincipal.add(scrollClientes);      // Se agrega el scroll (y por ende el área de texto) al panel

        // Botón Volver para regresar al menú principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(140, 320, 100, 30); // Posición y tamaño del botón
        panelPrincipal.add(btnVolver);          // Se agrega el botón al panel

        // Evento que se ejecuta al presionar el botón Volver
        btnVolver.addActionListener(e -> {
            setVisible(false);                   // Oculta esta ventana
            listener.AbrirMenuPrincipal();       // Llama al método del listener para abrir el menú principal
        });
    }
    /** Setter para asignar el listener. 
      * @param listener Objeto que implementa AppListener para manejar eventos.
      */
    public void setListener(AppListener listener) {
        this.listener = listener;
    }

    /** Muestra la lista de clientes en el área de texto. 
    * @param clientes Lista de objetos Cliente a mostrar.
    */
    public void mostrarClientes(List<Cliente> listaClientes) {
        areaClientes.setText("");
        for (Cliente cliente : listaClientes) {
            areaClientes.append(cliente.info(true) + "\n");
        }
    }
}