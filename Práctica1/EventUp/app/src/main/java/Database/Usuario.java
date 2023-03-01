package Database;

public class Usuario {
    private String login;
    private String password;
    private String nombre;
    private String carrera;
    private int curso;
    private int edad;
    private String foto;

    public Usuario(String l ,String psw ,String n ,String c ,int Curso ,int ed ,String fot){
        login = l;
        password = psw;
        nombre = n;
        carrera = c;
        curso = Curso;
        edad = ed;
        foto = fot;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}