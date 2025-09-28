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
import javax.swing.JOptionPane;

/**
 * La clase {@code AddTicketGUI} representa la ventana gráfica 
 * para la creación de un nuevo ticket dentro del sistema de gestión.
 * 
 * <p>Permite al usuario seleccionar un cliente existente o agregar
 * un nuevo cliente, ingresar nombre, correo electrónico y una 
 * descripción para el ticket. Además, incluye botones para regresar
 * al menú anterior o confirmar la creación del ticket.</p>
 * 
 * <p>Implementa la interfaz {@link ActionListener} para manejar
 * las acciones de los botones y el comboBox.</p>
 */
public class AddTicketGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/** Panel principal de la interfaz. */
	private JPanel contentPane;

	/** Lista desplegable para seleccionar cliente o crear uno nuevo. */
	private JComboBox<String> comboBox;
	
	/** Campo de texto para ingresar el nombre del cliente. */
	private JTextField textName;
	
	/** Campo de texto para ingresar el correo del cliente. */
	private JTextField textMail;
	
	/** Botón para volver al menú anterior. */
	private JButton btnVolver;
	
	/** Botón para agregar un nuevo ticket. */
	private JButton btnAdd;
	
	/** Área de texto para ingresar la descripción del ticket. */
	private JTextArea textDesc;
	
	/** Referencia al listener de la aplicación para comunicación externa. */
	private AppListener listener;
	
	/**
     * Constructor de la ventana {@code AddTicketGUI}.
     * 
     * <p>Inicializa la interfaz con los componentes necesarios para 
     * crear un nuevo ticket: selección de cliente, ingreso de nombre, 
     * correo y descripción.</p>
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
		
		comboBox.addItem("Nuevo Cliente");
		
		comboBox.addActionListener(this);

	}
	
	/**
     * Asigna el {@link AppListener} que recibirá los eventos de esta vista.
     * 
     * @param l el listener de la aplicación
     */
	public void setListener(AppListener l) {this.listener = l;}
	
	/**
     * Verifica si un identificador de cliente ya existe en el comboBox.
     *
     * @param id identificador de cliente a buscar
     * @return {@code true} si el identificador está en la lista, 
     *         {@code false} en caso contrario
     */
	public boolean inComboBox(String id) {
	    if (id == null) return false;
	    for (int i = 0; i < comboBox.getItemCount(); i++) {
	        if (id.equals(comboBox.getItemAt(i))) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Agrega un nuevo elemento al comboBox de clientes.
	 *
	 * @param n el nombre o identificador del cliente a agregar
	 */
	public void agregarCombo(String n) {comboBox.addItem(n);}
	
	/**
	 * Solicita al listener que lea los clientes existentes
	 * y los agregue al comboBox.
	 */
	public void leerClientes() {
		listener.llenarComboClientes();
	}
	
	/**
	 * Llena los campos de nombre y correo con los datos de un cliente existente.
	 *
	 * @param name nombre del cliente
	 * @param mail correo electrónico del cliente
	 */
	public void llenarCliente(String name, String mail) {
		textName.setText(name);
		textMail.setText(mail);
	}
	
	/**
	 * Restablece la interfaz a su estado inicial:
	 * <ul>
	 *   <li>Selecciona "Elija un cliente" en el comboBox.</li>
	 *   <li>Limpia los campos de nombre, correo y descripción.</li>
	 * </ul>
	 */
	public void resetGUI() {
		if (!inComboBox("Elija un cliente")) {
	        comboBox.insertItemAt("Elija un cliente", 0);
	    }
		comboBox.setSelectedItem("Elija un cliente"); // opción por defecto
        textName.setText("");
        textMail.setText("");
        textDesc.setText("");
    }
	
	/**
	 * Maneja los eventos de acción generados por los botones y el comboBox.
	 * 
	 * <p>Dependiendo del componente que genere el evento, realiza diferentes acciones:</p>
	 * <ul>
	 *   <li>comboBox: limpia los campos si es "Nuevo Cliente" o rellena con datos existentes.</li>
	 *   <li>btnAdd: valida los campos, lanza excepciones si hay errores y crea tickets o clientes según corresponda.</li>
	 *   <li>btnVolver: cierra la ventana actual y abre el menú principal.</li>
	 * </ul>
	 *
	 * <p>Excepciones manejadas dentro del método:</p>
	 * <ul>
	 *   <li>{@link DescripcionLargaException} si la descripción está vacía o supera 50 caracteres.</li>
	 *   <li>{@link CorreoInvalidoException} si el correo está vacío o ya existe.</li>
	 *   <li>{@link NombreInvalidoException} si el nombre está vacío.</li>
	 * </ul>
	 *
	 * @param e evento de acción generado por un componente de la interfaz
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == comboBox) {
		    if (!"Elija un cliente".equals(comboBox.getSelectedItem())) {
		        comboBox.removeItem("Elija un cliente");
		    }
		    
		    if ("Nuevo Cliente".equals(comboBox.getSelectedItem())) {
		        textName.setText("");
		        textMail.setText("");
		    } else {
		        // Cliente existente → rellenar campos
		        listener.rellenarCliente((String) comboBox.getSelectedItem());
		    }
		}
		
		if (e.getSource() == btnAdd) {
		    String seleccionado = (String) comboBox.getSelectedItem();
		    String descripcion = textDesc.getText();
		    String nombre = textName.getText().trim();
		    String correo = textMail.getText().trim();

		    try {
		        // Validaciones de la descripción
		        if (descripcion.isEmpty()) {
		            throw new DescripcionLargaException("La descripción no puede estar vacía.");
		        }
		        if (descripcion.length() > 50) {
		            throw new DescripcionLargaException("La descripción no puede superar los 50 caracteres.");
		        }
		        if (correo.isEmpty()) {
		        	throw new CorreoInvalidoException("El correo no puede estar vacío");
		        }
		        
		        if (nombre.isEmpty()) {
		        	throw new NombreInvalidoException("El nombre no puede estar vacío");
		        }
		        

		        if ("Nuevo Cliente".equals(seleccionado)) {
		            // Crear cliente y obtener ID
		        	if (listener.correoYaExiste(textMail.getText())) {
		        		throw new CorreoInvalidoException("El correo ya está registrado");
		        	}
		            String nuevoId = listener.NuevoCliente(textName.getText(), textMail.getText(), true);
		            // Crear ticket con ID recién creado
		            listener.NuevoTicket(nuevoId, "nTicket", descripcion);

		            JOptionPane.showMessageDialog(
		                this,
		                "Has agregado al cliente " + nuevoId + " un nuevo Ticket",
		                "Cliente y Ticket agregado",
		                JOptionPane.INFORMATION_MESSAGE
		            );

		            this.setVisible(false);
		            listener.AbrirMenuPrincipal();

		        } else if (!"Elija un cliente".equals(seleccionado)) {
		            // Cliente existente
		            listener.NuevoTicket(seleccionado, "nTicket", descripcion);

		            JOptionPane.showMessageDialog(
		                this,
		                "Se ha agregado un nuevo ticket al cliente " + seleccionado,
		                "Ticket agregado",
		                JOptionPane.INFORMATION_MESSAGE
		            );

		            this.setVisible(false);
		            listener.AbrirMenuPrincipal();
		        }

		    } catch (DescripcionLargaException | CorreoInvalidoException | NombreInvalidoException ex) {
		        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
		
		if (e.getSource() == btnVolver) {
		    this.setVisible(false);
		    listener.AbrirMenuPrincipal();
		}
	}
}
