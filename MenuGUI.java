import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana principal del Sistema de Gestión de Centros de Atención al Cliente.
 * Permite navegar entre las funcionalidades: ver, agregar, editar, eliminar y filtrar
 * tickets y clientes, así como salir del sistema.
 */
public class MenuGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel panelPrincipal;  // Panel principal de la ventana

    // Botones del menú
    private JButton btnVerTickets, btnVerClientes, btnAgregarTicket, btnEditarTicket, btnEliminarTicket,
                    btnSalir, btnBuscarTicket, btnBuscarCliente, btnEliminarCliente, btnFiltrarEstado;

    private AppListener listener;   // Listener que conecta la GUI con SistemaAtencion

    /**
     * Constructor que inicializa la ventana del menú principal.
     */
    public MenuGUI() {
        setTitle("Sistema de Gestión de Centros de Atención al Cliente");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (listener != null) {
                    listener.generarReporteYCerrar();
                }
            }
        });
        setBounds(100, 100, 450, 550);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        // Título
        JLabel labelTitulo = new JLabel("Sistema de Gestión de Centros de Atención al Cliente");
        labelTitulo.setBounds(85, 11, 300, 14);
        panelPrincipal.add(labelTitulo);

        // Botón y etiqueta: Ver Clientes
        JLabel labelVerClientes = new JLabel("Ver Lista Clientes");
        labelVerClientes.setBounds(30, 90, 170, 23);
        panelPrincipal.add(labelVerClientes);

        btnVerClientes = new JButton("Ver Clientes");
        btnVerClientes.setBounds(200, 90, 110, 25);
        btnVerClientes.addActionListener(this);
        panelPrincipal.add(btnVerClientes);

        // Botón y etiqueta: Ver Tickets
        JLabel labelVerTickets = new JLabel("Ver Lista Tickets");
        labelVerTickets.setBounds(30, 130, 170, 23);
        panelPrincipal.add(labelVerTickets);

        btnVerTickets = new JButton("Ver Tickets");
        btnVerTickets.setBounds(200, 130, 110, 25);
        btnVerTickets.addActionListener(this);
        panelPrincipal.add(btnVerTickets);

        // Botón y etiqueta: Añadir Ticket
        JLabel labelAgregarTicket = new JLabel("Añadir Nuevo Ticket");
        labelAgregarTicket.setBounds(30, 170, 170, 23);
        panelPrincipal.add(labelAgregarTicket);

        btnAgregarTicket = new JButton("Añadir Ticket");
        btnAgregarTicket.setBounds(200, 170, 110, 25);
        btnAgregarTicket.addActionListener(this);
        panelPrincipal.add(btnAgregarTicket);

        // Botón y etiqueta: Editar Ticket
        JLabel labelEditarTicket = new JLabel("Editar un Ticket");
        labelEditarTicket.setBounds(30, 210, 170, 23);
        panelPrincipal.add(labelEditarTicket);

        btnEditarTicket = new JButton("Editar Ticket");
        btnEditarTicket.setBounds(200, 210, 110, 25);
        btnEditarTicket.addActionListener(this);
        panelPrincipal.add(btnEditarTicket);

        // Botón y etiqueta: Eliminar Ticket
        JLabel labelEliminarTicket = new JLabel("Eliminar un Ticket");
        labelEliminarTicket.setBounds(30, 250, 170, 23);
        panelPrincipal.add(labelEliminarTicket);

        btnEliminarTicket = new JButton("Eliminar Ticket");
        btnEliminarTicket.setBounds(200, 250, 140, 25);
        btnEliminarTicket.addActionListener(this);
        panelPrincipal.add(btnEliminarTicket);

        // Botón y etiqueta: Buscar Ticket
        JLabel labelBuscarTicket = new JLabel("Buscar un Ticket");
        labelBuscarTicket.setBounds(30, 290, 170, 23);
        panelPrincipal.add(labelBuscarTicket);

        btnBuscarTicket = new JButton("Buscar Ticket");
        btnBuscarTicket.setBounds(200, 290, 140, 25);
        btnBuscarTicket.addActionListener(this);
        panelPrincipal.add(btnBuscarTicket);

        // Botón y etiqueta: Buscar Cliente
        JLabel labelBuscarCliente = new JLabel("Buscar un Cliente");
        labelBuscarCliente.setBounds(30, 330, 170, 23);
        panelPrincipal.add(labelBuscarCliente);

        btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.setBounds(200, 330, 140, 25);
        btnBuscarCliente.addActionListener(this);
        panelPrincipal.add(btnBuscarCliente);

        // Botón y etiqueta: Eliminar Cliente
        JLabel labelEliminarCliente = new JLabel("Eliminar un Cliente");
        labelEliminarCliente.setBounds(30, 370, 170, 23);
        panelPrincipal.add(labelEliminarCliente);

        btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(200, 370, 140, 25);
        btnEliminarCliente.addActionListener(this);
        panelPrincipal.add(btnEliminarCliente);

        // Botón y etiqueta: Filtrar Tickets por Estado
        JLabel labelFiltrarEstado = new JLabel("Filtrar tickets por estado");
        labelFiltrarEstado.setBounds(30, 410, 170, 23);
        panelPrincipal.add(labelFiltrarEstado);

        btnFiltrarEstado = new JButton("Filtrar");
        btnFiltrarEstado.setBounds(200, 410, 110, 25);
        btnFiltrarEstado.addActionListener(e -> listener.AbrirFiltrarEstado());
        panelPrincipal.add(btnFiltrarEstado);

        // Botón y etiqueta: Salir
        JLabel labelSalir = new JLabel("Salir del Programa");
        labelSalir.setBounds(30, 450, 170, 23);
        panelPrincipal.add(labelSalir);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 450, 110, 25);
        btnSalir.addActionListener(this);
        panelPrincipal.add(btnSalir);
    }

    /** Asigna el AppListener que conecta la GUI con SistemaAtencion. */
    public void setListener(AppListener listener) {
        this.listener = listener;
    }

    /** Maneja los eventos de los botones del menú principal. */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener == null) return;

        if (e.getSource() == btnVerClientes) listener.AbrirVerClientes();
        if (e.getSource() == btnVerTickets) listener.AbrirVerTickets();
        if (e.getSource() == btnAgregarTicket) listener.AbrirAddTicket();
        if (e.getSource() == btnEditarTicket) listener.AbrirEditarTicket();
        if (e.getSource() == btnEliminarTicket) listener.AbrirEliminarTicket();
        if (e.getSource() == btnBuscarTicket) listener.AbrirBuscarTicket();
        if (e.getSource() == btnBuscarCliente) listener.AbrirBuscarCliente();
        if (e.getSource() == btnEliminarCliente) listener.AbrirEliminarCliente();
        if (e.getSource() == btnSalir) listener.generarReporteYCerrar();
    }
}