public class Proceso {

    private String nombre; 
    private int te; 
    private int tcc = 0;
    private int numBloq; 
    private int tiempoEntrada; 
    private int tvc;
    private int tiempoTotal; 
    private int tiempoInicial; 
    private int tiempoFinal; 

    public Proceso(String nombre, int te, int tiempoEntrada, int numBloq) {
        this.nombre = nombre;
        this.te = te;
        this.tiempoEntrada = tiempoEntrada;
        this.numBloq = numBloq;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTe() {
        return this.te;
    }

    public void setTe(int te) {
        this.te = te;
    }

    public int getTcc() {
        return this.tcc;
    }

    public void setTcc(int tcc) {
        this.tcc = tcc;
    }

    public int getNumBloq() {
        return this.numBloq;
    }

    public void setNumBloq(int numBloq) {
        this.numBloq = numBloq;
    }

    public int getTiempoEntrada() {
        return this.tiempoEntrada;
    }

    public void setTiempoEntrada(int tiempoEntrada) {
        this.tiempoEntrada = tiempoEntrada;
    }

    public int getTvc() {
        return this.tvc;
    }

    public void setTvc(int tvc) {
        this.tvc = tvc;
    }

    public int getTiempoTotal() {
        return this.tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public int getTiempoInicial() {
        return this.tiempoInicial;
    }

    public void setTiempoInicial(int tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public int getTiempoFinal() {
        return this.tiempoFinal;
    }

    public void setTiempoFinal(int tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public String toString() {
        return nombre + " " + te + " " + tiempoEntrada;
    }
}