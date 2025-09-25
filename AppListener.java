
public interface AppListener {
	
	// Menú Principal
	void AbrirVerClientes();
	void AbrirVerTickets();
	void AbrirAddTicket();
	void AbrirEditarTicket();
	void AbrirEliminarTicket();
	void AbrirFiltrEstado();
	void AbrirFiltTiempo();

	// Ventana Agregar Tickets
	void NuevoTicket(String idCliente, String idTicket, String desc);
	void VolverMenu();
	void llenarComboClientes();
	void rellenarCliente(String id);
}
