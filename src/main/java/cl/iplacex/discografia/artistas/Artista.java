package cl.iplacex.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Objects;

@Document(collection = "artistas")
public class Artista {

    @Id
    private String id;
    private String nombre;
    private List<String> estilos;
    private int anioFundacion;
    private boolean estaActivo;

    public Artista() {
    }

    public Artista(String id, String nombre, List<String> estilos, int anioFundacion, boolean estaActivo) {
        this.id = id;
        this.nombre = nombre;
        this.estilos = estilos;
        this.anioFundacion = anioFundacion;
        this.estaActivo = estaActivo;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<String> estilos) {
        this.estilos = estilos;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artista)) return false;
        Artista artista = (Artista) o;
        return anioFundacion == artista.anioFundacion &&
                estaActivo == artista.estaActivo &&
                Objects.equals(id, artista.id) &&
                Objects.equals(nombre, artista.nombre) &&
                Objects.equals(estilos, artista.estilos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, estilos, anioFundacion, estaActivo);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estilos=" + estilos +
                ", anioFundacion=" + anioFundacion +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
