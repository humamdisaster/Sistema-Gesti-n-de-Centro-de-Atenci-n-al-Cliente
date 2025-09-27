import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Controlador que implementa la interfaz {@link AppListener}.
 * Actúa como intermediario entre la interfaz gráfica de usuario (GUI)
 * y la lógica del sistema ({@link SistemaAtencion}).
 * 
 * Se encarga de:
 * - Gestionar la navegación entre ventanas GUI.
 * - Delegar operaciones al sistema central.
 * - Validar y actualizar información de clientes y tickets.
 */

public class Controlador implements AppListener {

    // --- Interfaces Gráficas (Visibles) ---
    /** Ventana principal del menú */
	public MenuGUI menuPrincipal; 
    
	/** Ventana para agregar tickets */
	private AddTicketGUI menuTickets; 
    
	/** Ventana para visualizar clientes */
	private VerClientesGUI verClientesGUI; 
    
	/** Ventana para visualizar tickets */
	private VerTicketsGUI verTicketsGUI; 
    
	/** Ventana para editar tickets */
	private EditarTicketGUI editarTicketGUI; 
    
	/** Ventana para eliminar tickets */
	private EliminarTicketGUI eliminarTicketGUI; 
    
	/** Ventana para buscar por tickets */
	private BuscarTicketGUI buscarTicketGUI; 
    
	/** Ventana para eliminar clientes. */
	private EliminarClienteGUI eliminarClienteGUI; 

	// --- Sistema ---
    /** Lógica central del sistema de atención al cliente */
    private SistemaAtencion sistema;

    /**
     * Constructor principal del controlador.
     * Inicializa las ventanas y conecta el listener.
     *
     * @param menu ventana principal
     * @param menuTickets ventana para agregar tickets
     * @param sistema sistema de atención de clientes
     */
    public Controlador(MenuGUI menu, AddTicketGUI menuTickets, SistemaAtencion sistema) {
        this.menuPrincipal = menu;
        this.menuTickets = menuTickets;
        this.sistema = sistema;

        // Inicializar nuevas ventanas
        verClientesGUI = new VerClientesGUI();
        verClientesGUI.setListener(this);
        
        verTicketsGUI = new VerTicketsGUI();
        verTicketsGUI.setListener(this);
        
        editarTicketGUI = new EditarTicketGUI();
        editarTicketGUI.setListener(this);
        
        eliminarTicketGUI = new EliminarTicketGUI();
        eliminarTicketGUI.setListener(this);
        
        buscarTicketGUI = new BuscarTicketGUI();
        buscarTicketGUI.setListener(this);
        
        // Conectar vistas al listener
        menuPrincipal.setListener(this);
        menuTickets.setListener(this);
    }

    // --- Menú Principal ---
    
    /** Abre la ventana del menú principal. */
    @Override
    public void AbrirMenuPrincipal() {
        menuPrincipal.setVisible(true);
    }
    
    /** Abre la ventana que muestra los clientes registrados. */
    @Override
    public void AbrirVerClientes() {
        // Mostrar clientes en consola y mantener la GUI visible
    	mostrarClientesGUI();
    }
    
    /** Muestra la lista de clientes en la GUI correspondiente. */
    @Override
    public void mostrarClientesGUI() {
        verClientesGUI.mostrarClientes(new ArrayList<>(sistema.getClientes().values()));
        verClientesGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    /** Abre la ventana que muestra los tickets registrados. */
    @Override
    public void AbrirVerTickets() {
        // Mostrar tickets en consola y mantener la GUI visible
    	mostrarTicketsGUI();
    }
    
    /** Muestra los tickets de todos los clientes en la GUI correspondiente. */
    @Override
    public void mostrarTicketsGUI() {
        verTicketsGUI.mostrarTickets(sistema.getClientes());
        verTicketsGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    /** Abre la ventana para agregar un nuevo ticket. */
    @Override
    public void AbrirAddTicket() {
        menuPrincipal.setVisible(false);
        menuTickets.resetGUI();
        menuTickets.setVisible(true);
    }
    
    /** Abre la ventana para editar un ticket existente. */
    @Override
    public void AbrirEditarTicket() {
        editarTicketGUI.setClientes(sistema.getClientes());
        editarTicketGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    /**
     * Verifica si un correo ya está registrado en el sistema.
     *
     * @param correo dirección de correo a verificar
     * @return true si el correo ya existe, false en caso contrario
     */
    @Override
    public boolean correoYaExiste(String correo) {
        return sistema.correoYaExiste(correo);
    }
    
    /**
     * Edita un ticket existente con nueva información.
     *
     * @param idCliente identificador del cliente
     * @param idTicket identificador del ticket
     * @param nuevaDescripcion nueva descripción del ticket
     * @param nuevoEstado nuevo estado (Pendiente, Resuelto, etc.)
     * @param nuevaSatisfaccion nivel de satisfacción (1 a 5)
     * @param horasResolucion tiempo de resolución en horas
     */
    @Override
    public void editarTicketGUI(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevaSatisfaccion, double horasResolucion) {
    	Ticket t = sistema.buscarTicket(idTicket);
        if (t != null) {
            t.setDescripcion(nuevaDescripcion);
            t.setEstado(nuevoEstado);
            t.setSatisfaccion(nuevaSatisfaccion);
            t.setTiempoRespuesta(horasResolucion);

            Cliente cliente = sistema.getClientes().get(idCliente);
            if (cliente != null) {
                cliente.registrarEdicion(t, nuevaDescripcion, nuevoEstado, nuevaSatisfaccion);
            }

            System.out.println("Ticket actualizado desde GUI.");
        }
    }
    
    /** Abre la ventana para eliminar un ticket. */
    @Override
    public void AbrirEliminarTicket() {
        eliminarTicketGUI.limpiarComboClientes();
        for (Cliente cliente : sistema.getClientes().values()) {
            eliminarTicketGUI.llenarComboClientes(cliente.getId());
        }
        menuPrincipal.setVisible(false);
        eliminarTicketGUI.setVisible(true);
    }
    
    /**
     * Elimina un ticket de un cliente específico.
     *
     * @param idCliente identificador único del cliente
     * @param idTicket identificador único del ticket a eliminar
     */
    @Override
    public void EliminarTicket(String idCliente, String idTicket) {
        sistema.eliminarTicket(idCliente, idTicket);
        JOptionPane.showMessageDialog(null, "Ticket eliminado correctamente");
        eliminarTicketGUI.setVisible(false);
        menuPrincipal.setVisible(true);
    }
    
    /** Abre la ventana para buscar un ticket existente. */
    @Override
    public void AbrirBuscarTicket() {
        buscarTicketGUI.setClientes(sistema.getClientes());
        buscarTicketGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    /** Abre la ventana para buscar un cliente por su identificador. */
    @Override
    public void AbrirBuscarCliente() {
        BuscarClienteGUI gui = new BuscarClienteGUI();
        gui.setListener(this);
        gui.setClientes(sistema.getClientes());
        gui.setVisible(true);
    }
    

 // --- Eliminar Cliente ---
    /**
     * Abre la ventana de eliminación de clientes.
     * 
     * Inicializa la interfaz {@link EliminarClienteGUI} si aún no existe,
     * carga los clientes activos en el combo y muestra la ventana ocultando
     * el menú principal.
     */
    @Override
    public void AbrirEliminarCliente() {
        // Inicializar solo una vez
        if (eliminarClienteGUI == null) {
            eliminarClienteGUI = new EliminarClienteGUI();
            eliminarClienteGUI.setListener(this);
        }
        
        // Llenar combo con clientes activos
        eliminarClienteGUI.setClientes(sistema.getClientes());
        
        // Mostrar ventana y ocultar menú principal
        menuPrincipal.setVisible(false);
        eliminarClienteGUI.setVisible(true);
    }
    
    /**
     * Elimina un cliente del sistema y actualiza la interfaz correspondiente.
     *
     * @param idCliente identificador único del cliente a eliminar
     */
    @Override
    public void EliminarCliente(String idCliente) {
        // Eliminar cliente del sistema (queda registrado en historial dentro de SistemaAtencion)
        sistema.eliminarCliente(idCliente);
        
        // Mensaje de confirmación
        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
        
        // Actualizar combo en la ventana de eliminación
        if (eliminarClienteGUI != null) {
            eliminarClienteGUI.setClientes(sistema.getClientes());
        }
        
        // Volver al menú principal
        menuPrincipal.setVisible(true);
    }
    
    /** Abre la ventana para filtrar tickets por estado. */
    @Override
    public void AbrirFiltrarEstado() {
        FiltrarEstadoGUI gui = new FiltrarEstadoGUI(sistema);
        gui.setVisible(true);
    }
    
    /**
     * Abre la ventana para filtrar tickets por tiempo de resolución.
     * 
     * (Método no implementado a la interfaz).
     */
    @Override
    public void AbrirFiltTiempo() {
    	// TODO Auto-generated method stub
    }
    
    // --- AddTicketGUI ---
    /**
     * Llena el combo de clientes en la vista de agregar ticket,
     * evitando duplicados.
     */
    @Override
    public void llenarComboClientes() {
        for (Cliente cliente : sistema.getClientes().values()) {
            if (menuTickets.inComboBox(cliente.getId()))
                continue;
            menuTickets.agregarCombo(cliente.getId());
        }
    }
    
    /**
     * Rellena los campos de nombre y correo de un cliente
     * en la vista de agregar ticket.
     *
     * @param id identificador único del cliente
     */
    @Override
    public void rellenarCliente(String id) {
        for (Cliente cliente : sistema.getClientes().values()) {
            if (cliente.getId().equals(id)) {
                menuTickets.llenarCliente(cliente.getNombre(), cliente.getEmail());
            }
        }
    }
    
    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param nombre nombre del cliente
     * @param correo correo electrónico del cliente
     * @param retornarId si es {@code true}, devuelve el ID del cliente creado
     * @return identificador único del nuevo cliente
     */
    @Override
    public String NuevoCliente(String nombre, String correo, boolean retornarId) {
        // Llamar a la sobrecarga de SistemaAtencion
        String id = sistema.agregarCliente(nombre, correo, retornarId); // usa la sobrecarga
        return id;
    }
    
    /**
     * Llena el combo de tickets pertenecientes a un cliente en
     * la vista de eliminar ticket.
     *
     * @param idCliente identificador único del cliente
     */
    @Override
    public void rellenarTicketsCliente(String idCliente) {
        eliminarTicketGUI.limpiarComboTickets();
        Cliente cliente = sistema.getClientes().get(idCliente);
        if (cliente != null) {
            for (Ticket t : cliente.getTickets()) {
                eliminarTicketGUI.llenarComboTickets(t.getId() + " - " + t.getDescripcion());
            }
        }
    }
    
    /**
     * Crea un nuevo ticket para un cliente y actualiza la interfaz,
     * cerrando la ventana de creación de tickets y mostrando el menú principal.
     *
     * @param idCliente identificador único del cliente
     * @param idTicket identificador del ticket (no usado en la creación)
     * @param desc descripción del ticket
     */
    @Override
    public void NuevoTicket(String idCliente, String idTicket, String desc) {
        sistema.agregarTicket(idCliente, desc);
        menuTickets.setVisible(false);
        menuPrincipal.setVisible(true);
    }

    /**
     * Genera el reporte final de clientes y tickets, guarda los cambios
     * en archivos CSV y cierra la aplicación.
     */
    @Override
    public void generarReporteYCerrar() {
        sistema.guardarCambios("clientes.csv");
        sistema.generarReporte("reporte.csv");
        System.exit(0);
    }
}