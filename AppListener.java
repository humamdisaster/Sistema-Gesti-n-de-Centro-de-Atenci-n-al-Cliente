
/**
 * Interfaz {@code AppListener} que define los métodos de comunicación
 * entre las vistas (GUI) y la lógica de negocio de la aplicación.
 * 
 * <p>Se utiliza para manejar eventos de la interfaz y realizar
 * operaciones como abrir ventanas, agregar o editar tickets,
 * gestionar clientes y generar reportes.</p>
 */
public interface AppListener {
	
	// -------------------- Menú Principal --------------------

    /** Abre la ventana principal del sistema. */
	void AbrirMenuPrincipal();
	
	/** Abre la ventana de visualización de clientes. */
	void AbrirVerClientes();
	
	/** Abre la ventana de visualización de tickets. */
	void AbrirVerTickets();
	
	/** Abre la ventana para agregar un nuevo ticket. */
	void AbrirAddTicket();
	
	/** Abre la ventana para editar un ticket existente. */
	void AbrirEditarTicket();
	
	/** Abre la ventana para eliminar un ticket. */
	void AbrirEliminarTicket();
	
	/** Abre la ventana para filtrar tickets por estado. */
	void AbrirFiltrarEstado();
	
	/** Abre la ventana para filtrar tickets por tiempo. */
	void AbrirFiltTiempo();
	
	/** Abre la ventana para buscar un ticket. */
	void AbrirBuscarTicket();
	
	/** Abre la ventana para buscar los datos de un cliente. */
	void AbrirBuscarCliente();
	
	/** Abre la ventana para eliminar un cliente. */
	void AbrirEliminarCliente();
	
	// -------------------- Ventana Mostrar Clientes --------------------
	/** Muestra la GUI de clientes con sus datos actuales. */
	void mostrarClientesGUI();
	
	/** Muestra la GUI de tickets con sus datos actuales. */
	void mostrarTicketsGUI();
	
	// -------------------- Ventana Agregar Tickets --------------------
	/**
     * Crea un nuevo ticket para un cliente existente.
     * 
     * @param idCliente identificador del cliente
     * @param idTicket identificador del ticket
     * @param desc descripción del ticket
     */
	void NuevoTicket(String idCliente, String idTicket, String desc);
	
	/**
     * Crea un nuevo cliente en el sistema.
     * 
     * @param nombre nombre del cliente
     * @param correo correo electrónico del cliente
     * @param retornarId si es {@code true}, retorna el ID generado
     * @return el ID del cliente creado si {@code retornarId} es {@code true}, de lo contrario {@code null}
     */
	String NuevoCliente(String nombre, String correo, boolean retornarId);
	
	/** Llena el comboBox de clientes en la interfaz correspondiente. */
	void llenarComboClientes();
	
	/** Rellena los datos de un cliente dado su ID. 
	 * 
	 * @param id identificador del cliente
	 */
	void rellenarCliente(String id);
	
	/**
     * Verifica si un correo ya existe en el sistema.
     * 
     * @param correo correo a verificar
     * @return {@code true} si el correo ya está registrado, {@code false} en caso contrario
     */
	boolean correoYaExiste(String correo);
	
	// -------------------- Ventana Editar Ticket --------------------
	/**
     * Edita un ticket existente con nuevos valores.
     * 
     * @param idCliente identificador del cliente
     * @param idTicket identificador del ticket
     * @param nuevaDescripcion nueva descripción del ticket
     * @param nuevoEstado nuevo estado del ticket
     * @param nuevaSatisfaccion nuevo nivel de satisfacción (entero)
     * @param horasResolucion tiempo de resolución del ticket
     */
	void editarTicketGUI(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevaSatisfaccion, double horasResolucion);
	
	// -------------------- Ventana Eliminar Ticket --------------------
	/**
	 * Elimina un ticket específico de un cliente.
	 *
	 * @param idCliente identificador del cliente al que pertenece el ticket
	 * @param idTicket identificador del ticket a eliminar
	 */
	void EliminarTicket(String idCliente, String idTicket);
	
	/**
	 * Rellena los tickets de un cliente para visualización o edición.
	 *
	 * @param idCliente identificador del cliente al que pertenece el ticket
	 */
	void rellenarTicketsCliente(String idCliente);
	
	// -------------------- Ventana Eliminar Cliente --------------------
	/**
	 * Elimina un cliente específico del sistema.
	 *
	 * @param idCliente identificador del cliente al que pertenece el ticket
	 */
	void EliminarCliente(String idCliente);
	
	// -------------------- Reporte --------------------
	/** Genera un reporte del estado actual y cierra la aplicación. */
	void generarReporteYCerrar();
}