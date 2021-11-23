import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Despachador {
    private ArrayList<Proceso> listaProcesos = new ArrayList<>(); // lista que contiene los procesos
    private Queue<Proceso> colaProcesos = new LinkedList<Proceso>(); // cola que contiene los procesos
    private int cambioContexto;
    private int quantum;
    private int bloqueo;
    private ArrayList<Microprocesador> listaMicros = new ArrayList<Microprocesador>();

    public Despachador(ArrayList<Proceso> listaProcesos, int nMicros, int cambioContexto, int quantum, int bloqueo) {
        this.listaProcesos = listaProcesos;
        this.cambioContexto = cambioContexto;
        this.quantum = quantum;
        this.bloqueo = bloqueo;

        for (int i = 0; i < nMicros; i++) {
            Microprocesador m = new Microprocesador(i + 1, cambioContexto, quantum, bloqueo);
            listaMicros.add(m);
        }

    }

    public void iniciarDespacho() {

        // Partiendo de una lista de procesos ordenada por su tiempo de entrada, se
        // insertan en la cola y se despachan. La variable tiempo inicio detecta cuando
        // se pasa al siguiente tiempo de entrada. Cada uno de los procesos es agregado
        // a la cola cuando hay un cambio de tiempo, los despacha y se colocan los
        // saltos o tiempo del micro vacío.

        int tiempoInicio = 0;
        for (int i = 0; i < listaProcesos.size(); i++) {
            // Recorre todos los procesos de la lista de procesos
            if (listaProcesos.get(i).getTiempoEntrada() != tiempoInicio) {
                this.despachar();
                this.detectarSaltos(listaProcesos.get(i).getTiempoEntrada());
            }
            colaProcesos.add(listaProcesos.get(i)); // Se agrega el proceso a la cola.
        }

        // Se despacha el proceso que queda en la cola al finalizar el ciclo.
        this.despachar();
        this.eliminarSalto();
    }

    public void despachar() {
        // Toma en cuenta el tiempo de microprocesadores.
        while (colaProcesos.size() != 0) {

            /*
             * De todos los microprocesadores selecciona el de menor tiempo y cuida la
             * prioridad del ID. El de menor tiempo se asigna para procesar el primer
             * proceso en la cola.
             */
            this.seleccionarMicro().ejecutarProceso(colaProcesos.remove());
        }

    }

    public void detectarSaltos(int nuevoInicio) {
        for (Microprocesador m : listaMicros) {
            if (m.getDuracion() < nuevoInicio) {
                m.esperar(nuevoInicio);
            }
        }
    }

    public void eliminarSalto() {
        for (Microprocesador micro : listaMicros) {
            Proceso ultimo = micro.getCompletados().get(micro.getCompletados().size() - 1);
            if (ultimo.getNombre() == "Salto") {
                micro.setDuracion(micro.getDuracion() - (ultimo).getTe());
                micro.getCompletados().remove(micro.getCompletados().size() - 1);
            }
        }
    }

    public Microprocesador seleccionarMicro() {
        Microprocesador min = Collections.min(listaMicros);
        return min;
    }

    public void imprimirTablas() {
        GUI it = new GUI(listaMicros);
        it.crearGUI(listaMicros);
    }

    public ArrayList<Proceso> getListaProcesos() {
        return this.listaProcesos;
    }

    public void setListaProcesos(ArrayList<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public Queue<Proceso> getColaProcesos() {
        return this.colaProcesos;
    }

    public void setColaProcesos(Queue<Proceso> colaProcesos) {
        this.colaProcesos = colaProcesos;
    }

    public int getCambioContexto() {
        return this.cambioContexto;
    }

    public void setCambioContexto(int cambioContexto) {
        this.cambioContexto = cambioContexto;
    }

    public int getQuantum() {
        return this.quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getBloqueo() {
        return this.bloqueo;
    }

    public void setBloqueo(int bloqueo) {
        this.bloqueo = bloqueo;
    }

    public ArrayList<Microprocesador> getListaMicros() {
        return this.listaMicros;
    }

    public void setListaMicros(ArrayList<Microprocesador> listaMicros) {
        this.listaMicros = listaMicros;
    }

}