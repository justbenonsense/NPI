package Database;

public class Evento {
    private String nombre;
    private String fecha;
    private String hora;
    private int aforo;
    private String grado;
    private String ubicacion;
    private String idioma;

    public Evento(String n, String date, String h, int a, String g, String ubi, String idio){
        nombre = n;
        fecha = date;
        hora = h;
        aforo = a;
        grado = g;
        ubicacion = ubi;
        idioma = idio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String horario) {
        this.hora = horario;
    }

    public int getAforo() { return aforo; }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}