import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador implements AppListener {

    // GUI
    public MenuGUI menuPrincipal;
    private AddTicketGUI menuTickets;
    private VerClientesGUI verClientesGUI;
    private VerTicketsGUI verTicketsGUI;
    private EditarTicketGUI editarTicketGUI;
    private EliminarTicketGUI eliminarTicketGUI;

    // Sistema
    private SistemaAtencion sistema;

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
        
        // Conectar vistas al listener
        menuPrincipal.setListener(this);
        menuTickets.setListener(this);
    }

    // --- Menú Principal ---
    
    @Override
    public void AbrirMenuPrincipal() {
        menuPrincipal.setVisible(true);
    }
    
    @Override
    public void AbrirVerClientes() {
        // Mostrar clientes en consola y mantener la GUI visible
    	mostrarClientesGUI();
    }
    
    @Override
    public void mostrarClientesGUI() {
        verClientesGUI.mostrarClientes(new ArrayList<>(sistema.getClientes().values()));
        verClientesGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    @Override
    public void AbrirVerTickets() {
        // Mostrar tickets en consola y mantener la GUI visible
    	mostrarTicketsGUI();
    }
    
    @Override
    public void mostrarTicketsGUI() {
        verTicketsGUI.mostrarTickets(sistema.getClientes());
        verTicketsGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    @Override
    public void AbrirAddTicket() {
        menuPrincipal.setVisible(false);
        menuTickets.setVisible(true);
    }
    
    @Override
    public void AbrirEditarTicket() {
        editarTicketGUI.setClientes(sistema.getClientes());
        editarTicketGUI.setVisible(true);
        menuPrincipal.setVisible(false);
    }
    
    @Override
    public void editarTicketGUI(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevaSatisfaccion) {
        Ticket t = sistema.buscarTicket(idTicket);
        if (t != null) {
            t.setDescripcion(nuevaDescripcion);
            t.setEstado(nuevoEstado);
            t.setSatisfaccion(nuevaSatisfaccion);

            Cliente cliente = sistema.getClientes().get(idCliente);
            if (cliente != null) {
                cliente.registrarEdicion(t, nuevaDescripcion, nuevoEstado, nuevaSatisfaccion);
            }

            System.out.println("Ticket actualizado desde GUI.");
        }
    }
    
    @Override
    public void AbrirEliminarTicket() {
        eliminarTicketGUI.limpiarComboClientes();
        for (Cliente cliente : sistema.getClientes().values()) {
            eliminarTicketGUI.llenarComboClientes(cliente.getId());
        }
        menuPrincipal.setVisible(false);
        eliminarTicketGUI.setVisible(true);
    }
    
    @Override
    public void EliminarTicket(String idCliente, String idTicket) {
        sistema.eliminarTicket(idCliente, idTicket);
        JOptionPane.showMessageDialog(null, "Ticket eliminado correctamente");
        eliminarTicketGUI.setVisible(false);
        menuPrincipal.setVisible(true);
    }
    
    @Override
    public void AbrirFiltrEstado() {
    	// TODO Auto-generated method stub
    }
    
    @Override
    public void AbrirFiltTiempo() {
    	// TODO Auto-generated method stub
    }
    
    // --- AddTicketGUI ---
    @Override
    public void llenarComboClientes() {
        for (Cliente cliente : sistema.getClientes().values()) {
            if (menuTickets.inComboBox(cliente.getId()))
                continue;
            menuTickets.agregarCombo(cliente.getId());
        }
    }
    
    @Override
    public void rellenarCliente(String id) {
        for (Cliente cliente : sistema.getClientes().values()) {
            if (cliente.getId().equals(id)) {
                menuTickets.llenarCliente(cliente.getNombre(), cliente.getEmail());
            }
        }
    }
    
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
    
    @Override
    public void NuevoTicket(String idCliente, String idTicket, String desc) {
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
        sistema.guardarCambios("clientes.csv");
        sistema.generarReporte("reporte.csv");
        System.exit(0);
    }
}