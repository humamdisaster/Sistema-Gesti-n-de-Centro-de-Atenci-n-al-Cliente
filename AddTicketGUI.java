import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddTicketGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JComboBox<String> comboBox;
	private JTextField textName;
	private JTextField textMail;
	private JButton btnVolver;
	private JButton btnAdd;
	private JTextArea textDesc;
	
	private AppListener listener;
	
	/**
	 * Create the frame.
	 */
	public AddTicketGUI() {	
		setTitle("Sistema Gestión de Tickets");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(23, 60, 125, 22);
		contentPane.add(comboBox);
		
		JLabel lblTitutlo = new JLabel("Agregar Ticket Nuevo");
		lblTitutlo.setBounds(104, 11, 125, 14);
		contentPane.add(lblTitutlo);
		
		textName = new JTextField();
		textName.setBounds(173, 61, 135, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textMail = new JTextField();
		textMail.setBounds(23, 107, 285, 20);
		contentPane.add(textMail);
		textMail.setColumns(10);
		
		textDesc = new JTextArea();
		textDesc.setLineWrap(true);
		textDesc.setBounds(23, 176, 301, 129);
		contentPane.add(textDesc);
		
		btnVolver = new JButton("<- Atras");
		btnVolver.setBounds(45, 327, 103, 23);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);
		
		btnAdd = new JButton("Agregar Ticket");
		btnAdd.setBounds(188, 327, 120, 23);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		JLabel lblidCliente = new JLabel("ID Cliente");
		lblidCliente.setBounds(23, 43, 125, 14);
		contentPane.add(lblidCliente);
		
		JLabel lblName = new JLabel("Nombre ");
		lblName.setBounds(173, 43, 86, 14);
		contentPane.add(lblName);
		
		JLabel lblMail = new JLabel("Correo");
		lblMail.setBounds(23, 93, 46, 14);
		contentPane.add(lblMail);
		
		JLabel lblDesc = new JLabel("Descripción Ticket");
		lblDesc.setBounds(23, 151, 125, 14);
		contentPane.add(lblDesc);
		
		comboBox.addItem("Elija un cliente");
		//comboBox.addItem("Nuevo Cliente");
		
		comboBox.addActionListener(this);

	}
	
	public void setListener(AppListener l) {this.listener = l;}
	
	public boolean inComboBox(String id) {
	    if (id == null) return false;
	    for (int i = 0; i < comboBox.getItemCount(); i++) {
	        if (id.equals(comboBox.getItemAt(i))) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void agregarCombo(String n) {comboBox.addItem(n);}
	
	public void leerClientes() {
		listener.llenarComboClientes();
	}
	
	public void llenarCliente(String name, String mail) {
		textName.setText(name);
		textMail.setText(mail);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == comboBox) {
	        if ("Elija un cliente".equals(comboBox.getSelectedItem())) {
	            comboBox.removeItem("Elija un cliente");
	        }
	        if ("Nuevo Cliente".equals(comboBox.getSelectedItem())) {
	            textName.setText("");
	            textMail.setText("");
	        }
	        listener.rellenarCliente((String) comboBox.getSelectedItem());
	    }
	    
	    if (e.getSource() == btnAdd) {
	        String clienteSeleccionado = (String) comboBox.getSelectedItem();
	        
	        // Validaciones
	        if (clienteSeleccionado == null || clienteSeleccionado.equals("Elija un cliente") || 
	            clienteSeleccionado.equals("Nuevo Cliente")) {
	            javax.swing.JOptionPane.showMessageDialog(this, 
	                "Por favor seleccione un cliente válido", "Error", 
	                javax.swing.JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        String descripcion = textDesc.getText().trim();
	        if (descripcion.isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, 
	                "La descripción no puede estar vacía", "Error", 
	                javax.swing.JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        // CORRECCIÓN CRÍTICA: Pasar null para que el sistema genere el ID automáticamente
	        listener.NuevoTicket(clienteSeleccionado, null, descripcion);
	        
	        // Limpiar campos después de agregar
	        textDesc.setText("");
	        javax.swing.JOptionPane.showMessageDialog(this, 
	            "Ticket agregado correctamente", "Éxito", 
	            javax.swing.JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    if (e.getSource() == btnVolver) {
	        this.setVisible(false);
	        listener.AbrirMenuPrincipal();
	    }
	}
}
