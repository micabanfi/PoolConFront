package Views.Table.Solicitudes;

import root.User.Rating;
import root.User.User;

public class Solicitudes {
    private User user;
    private String nombre;
    private String apellido;
    private String telefono;
    private Puntaje puntaje;

    public Solicitudes(User user){
        this.user = user;
        this.nombre = user.getName();
        this.apellido = user.getSurname();
        this.telefono = user.getPhone();
        this.puntaje = new Puntaje(user.getRating());
    }

    public User getUser() {
        return user;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }
}
