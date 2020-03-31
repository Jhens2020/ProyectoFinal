package datos;


public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

   public void setDocumento(String dni) {
        this.dni = dni;
    }
}