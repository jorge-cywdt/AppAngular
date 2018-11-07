
package modelos;
public class cliente {
    
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String createAt;
    private int estado;

    public cliente() {
    }    

    public cliente(int id, String nombre, String apellido, String email, String createAt, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
        this.estado = estado;
    }          
    
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }    

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }  

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }        
    
}
