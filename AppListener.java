
public interface AppListener {
	
	// Menú Principal
	void AbrirMenuPrincipal();
	void AbrirVerClientes();
	void AbrirVerTickets();
	void AbrirAddTicket();
	void AbrirEditarTicket();
	void AbrirEliminarTicket();
	void AbrirFiltrEstado();
	void AbrirFiltTiempo();
	
	// Ventana Mostrar Clientes
	void mostrarClientesGUI();
	void mostrarTicketsGUI();
	
	// Ventana Agregar Tickets
	void NuevoTicket(String idCliente, String idTicket, String desc);
	void llenarComboClientes();
	void rellenarCliente(String id);
	
	// Ventana Editar Ticket
	void editarTicketGUI(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevaSatisfaccion, int horasResolucion);
	
	// Ventana Eliminar Ticket
	void EliminarTicket(String idCliente, String idTicket);
	void rellenarTicketsCliente(String idCliente);
	
	// Genera un reporte al salir del programa
	void generarReporteYCerrar();
}
