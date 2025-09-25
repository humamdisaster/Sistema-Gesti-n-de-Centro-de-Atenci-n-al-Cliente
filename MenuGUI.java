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
	
	private JButton btnTickets, btnClientes, btnTicketAdd, btnSalir;
	
	private AppListener listener;

	/**
	 * Create the frame.
	 */
	public MenuGUI() {
		setTitle("Sistema Gestión de Tickets");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sistema Gestión de Tickets");
		lblTitulo.setBounds(85, 11, 175, 14);
		contentPane.add(lblTitulo);
		
		btnTickets = new JButton("Ver Tickets");
		btnTickets.setBounds(200, 130, 110, 25);
		btnTickets.addActionListener(this);
		contentPane.add(btnTickets);
		
		btnTicketAdd = new JButton("Añadir Ticket");
		btnTicketAdd.setBounds(200, 170, 110, 25);
		btnTicketAdd.addActionListener(this);
		contentPane.add(btnTicketAdd);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(200, 210, 110, 25);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		btnClientes = new JButton("Ver Clientes");
		btnClientes.setBounds(200, 90, 110, 25);
		btnClientes.addActionListener(this);
		contentPane.add(btnClientes);
		
		JLabel lblClientes = new JLabel("Ver Lista Clientes");
		lblClientes.setBounds(30, 90, 170, 23);
		contentPane.add(lblClientes);
		
		JLabel lblTickets = new JLabel("Ver Lista Tickets");
		lblTickets.setBounds(30, 130, 170, 23);
		contentPane.add(lblTickets);
		
		JLabel lblTicketAdd = new JLabel("Añadir Nuevo Ticket");
		lblTicketAdd.setBounds(30, 170, 170, 23);
		contentPane.add(lblTicketAdd);
		
		JLabel lblSalir = new JLabel("Salir del Programa");
		lblSalir.setBounds(30, 210, 170, 23);
		contentPane.add(lblSalir);

	}
	
	public void setListener(AppListener l) {this.listener = l;}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClientes) {listener.AbrirVerClientes();}
		if (e.getSource() == btnTickets) {listener.AbrirVerTickets();}
		if (e.getSource() == btnTicketAdd) {listener.AbrirAddTicket();}
		if (e.getSource() == btnSalir) {
			System.out.println("Saliendo del Programa...");
			System.exit(0);
		}
	}
	
}
