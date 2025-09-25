
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
	void llenarComboClientes();
	void rellenarCliente(String id);
	void generarReporteYCerrar();
}
