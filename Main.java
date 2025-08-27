// 1. CLASE PRINCIPAL (1ra colección)
public class CentroAtencion {
    private int id;
    private String nombre;
    private ArrayList<Cliente> clientes; // 1ra colección
    private HashMap<Integer, Cliente> clientesMap; // Mapa JCF (SIA1.7)
    
    // Constructores
    public CentroAtencion() {}
    public CentroAtencion(int id, String nombre) {}
    
    // Getters y setters (SIA1.3)
    public ArrayList<Cliente> getClientes() {}
    public void setClientes(ArrayList<Cliente> clientes) {}
    
    // Métodos sobrecargados (SIA1.6)
    public void agregarCliente(Cliente cliente) {}
    public void agregarCliente(String nombre, String email) {}
}

// 2. CLASE CON COLECCIÓN ANIDADA (2da colección)
public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private ArrayList<Caso> casos; // 2da colección anidada (SIA1.5)
    
    // Getters y setters
    public ArrayList<Caso> getCasos() {}
    public void setCasos(ArrayList<Caso> casos) {}
}

// 3. CLASE PARA LA COLECCIÓN ANIDADA
public class Caso {
    private int id;
    private String descripcion;
    private String estado;
    
    // Getters y setters
    public String getEstado() {}
    public void setEstado(String estado) {}
}

// 4. GESTOR CON SOBRECARGA (SIA1.6)
public class GestorClientes {
    // Métodos sobrecargados
    public void procesarCliente(Cliente cliente) {}
    public void procesarCliente(String nombre, String email) {}
}

// 5. EXCEPCIONES PERSONALIZADAS (SIA2.9)
public class ClienteException extends Exception {
    public ClienteException(String mensaje) {}
}

public class CasoException extends Exception {
    public CasoException(String mensaje) {}
}