
public class Controlador implements AppListener{
	
	// GUI
	private MenuGUI menuPrincipal;
	private AddTicketGUI menuTickets;
	
	// Sistema
	private SistemaAtencion sistema;
	
	
	public Controlador(MenuGUI menu, AddTicketGUI menuTickets, SistemaAtencion Sistema) {
		this.menuPrincipal = menu;
		this.menuTickets = menuTickets;
		this.sistema = Sistema;
		
		// Conectar vistas al listener
		menuPrincipal.setListener(this);
		menuTickets.setListener(this);
	}
	
	
	@Override
	public void AbrirVerClientes() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AbrirVerTickets() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AbrirAddTicket() {
		// TODO Auto-generated method stub
		menuPrincipal.setVisible(false);
		menuTickets.setVisible(true);
	}
	
	
	@Override
	public void AbrirEditarTicket() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AbrirEliminarTicket() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AbrirFiltrEstado() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AbrirFiltTiempo() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void llenarComboClientes() {
		// TODO Auto-generated method stub
		for (Cliente cliente : sistema.getClientes().values()) {
			if (menuTickets.inComboBox(cliente.getId()))
				continue;
    		menuTickets.agregarCombo(cliente.getId());
    	}
	}
	
	
	@Override
	public void rellenarCliente(String id) {
		// TODO Auto-generated method stub
		for (Cliente cliente: sistema.getClientes().values()) {
			if (cliente.getId() == id)
				menuTickets.llenarCliente(cliente.getNombre(), cliente.getEmail());
		}
	}
	
	
	@Override
	public void NuevoTicket(String idCliente, String idTicket, String desc) {
		// TODO Auto-generated method stub
		sistema.agregarTicket(idCliente, desc);
		menuTickets.setVisible(false);
		menuPrincipal.setVisible(true);
	}
	
	/*
	@Override
	public void VolverMenu() {
		// TODO Auto-generated method stub
	}
	*/
	@Override
	public void generarReporteYCerrar() {
	    sistema.generarReporte("reporte.csv");
	    System.exit(0);
	}
}
