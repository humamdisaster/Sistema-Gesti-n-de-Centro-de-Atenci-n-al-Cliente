import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JButton btnTickets, btnClientes, btnTicketAdd, btnEditarTicket, btnEliminarTicket, 
                    btnSalir, btnBuscarTicket, btnBuscarCliente, btnEliminarCliente;
    
    private AppListener listener;
    
    public MenuGUI() {
        setTitle("Sistema Gestión de Tickets");
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
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Sistema Gestión de Tickets");
        lblTitulo.setBounds(85, 11, 175, 14);
        contentPane.add(lblTitulo);
        
        // Ver Lista Clientes
        JLabel lblClientes = new JLabel("Ver Lista Clientes");
        lblClientes.setBounds(30, 90, 170, 23);
        contentPane.add(lblClientes);
        btnClientes = new JButton("Ver Clientes");
        btnClientes.setBounds(200, 90, 110, 25);
        btnClientes.addActionListener(this);
        contentPane.add(btnClientes);

        // Ver Lista Tickets
        JLabel lblTickets = new JLabel("Ver Lista Tickets");
        lblTickets.setBounds(30, 130, 170, 23);
        contentPane.add(lblTickets);
        btnTickets = new JButton("Ver Tickets");
        btnTickets.setBounds(200, 130, 110, 25);
        btnTickets.addActionListener(this);
        contentPane.add(btnTickets);

        // Añadir Nuevo Ticket
        JLabel lblTicketAdd = new JLabel("Añadir Nuevo Ticket");
        lblTicketAdd.setBounds(30, 170, 170, 23);
        contentPane.add(lblTicketAdd);
        
        btnTicketAdd = new JButton("Añadir Ticket");
        btnTicketAdd.setBounds(200, 170, 110, 25);
        btnTicketAdd.addActionListener(this);
        contentPane.add(btnTicketAdd);
        
        // Editar un Ticket
        JLabel lblEditar = new JLabel("Editar un Ticket");
        lblEditar.setBounds(30, 210, 170, 23);
        contentPane.add(lblEditar);
        
        btnEditarTicket = new JButton("Editar Ticket");
        btnEditarTicket.setBounds(200, 210, 110, 25);
        btnEditarTicket.addActionListener(this);
        contentPane.add(btnEditarTicket);

        // Eliminar un Ticket
        JLabel lblEliminarTicket = new JLabel("Eliminar un Ticket");
        lblEliminarTicket.setBounds(30, 250, 170, 23);
        contentPane.add(lblEliminarTicket);
        
        btnEliminarTicket = new JButton("Eliminar Ticket");
        btnEliminarTicket.setBounds(200, 250, 140, 25);
        btnEliminarTicket.addActionListener(this);
        contentPane.add(btnEliminarTicket);
        
        // Buscar un Ticket
        JLabel lblBuscar = new JLabel("Buscar un Ticket");
        lblBuscar.setBounds(30, 290, 170, 23);
        contentPane.add(lblBuscar);

        btnBuscarTicket = new JButton("Buscar Ticket");
        btnBuscarTicket.setBounds(200, 290, 140, 25);
        btnBuscarTicket.addActionListener(this);
        contentPane.add(btnBuscarTicket);
        
        // Buscar un Cliente
        JLabel lblBuscarCliente = new JLabel("Buscar un Cliente");
        lblBuscarCliente.setBounds(30, 330, 170, 23);
        contentPane.add(lblBuscarCliente);

        btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.setBounds(200, 330, 140, 25);
        btnBuscarCliente.addActionListener(this);
        contentPane.add(btnBuscarCliente);

        // Eliminar un Cliente
        JLabel lblEliminarCliente = new JLabel("Eliminar un Cliente");
        lblEliminarCliente.setBounds(30, 370, 170, 23);
        contentPane.add(lblEliminarCliente);

        btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(200, 370, 140, 25);
        btnEliminarCliente.addActionListener(this);
        contentPane.add(btnEliminarCliente);
        
        // Filtrar tickets por estado
        JLabel lblFiltrarEstado = new JLabel("Filtrar tickets por estado");
        lblFiltrarEstado.setBounds(30, 410, 170, 23);
        contentPane.add(lblFiltrarEstado);

        JButton btnFiltrarEstado = new JButton("Filtrar");
        btnFiltrarEstado.setBounds(200, 410, 110, 25);
        btnFiltrarEstado.addActionListener(e -> listener.AbrirFiltrarEstado());
        contentPane.add(btnFiltrarEstado);
		        
        // Salir del Programa
        JLabel lblSalir = new JLabel("Salir del Programa");
        lblSalir.setBounds(30, 450, 170, 23);
        contentPane.add(lblSalir);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 450, 110, 25);
        btnSalir.addActionListener(this);
        contentPane.add(btnSalir);
    }
    
    public void setListener(AppListener l) {this.listener = l;}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClientes) {listener.AbrirVerClientes();}
        if (e.getSource() == btnTickets) {listener.AbrirVerTickets();}
        if (e.getSource() == btnTicketAdd) {listener.AbrirAddTicket();}
        if (e.getSource() == btnEditarTicket) { listener.AbrirEditarTicket(); }
        if (e.getSource() == btnEliminarTicket) {listener.AbrirEliminarTicket(); }
        if (e.getSource() == btnBuscarTicket) { listener.AbrirBuscarTicket(); }
        if (e.getSource() == btnBuscarCliente) { listener.AbrirBuscarCliente(); }
        if (e.getSource() == btnEliminarCliente) { listener.AbrirEliminarCliente(); }
        if (e.getSource() == btnSalir) {
            if (listener != null) {
                listener.generarReporteYCerrar();
            }
        }
    }
}