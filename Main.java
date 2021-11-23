import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
    private JTextField microsTF;
    private JTextField quantumTF;
    private JTextField contextoTF;
    private JTextField bloqueoTF;
    private ImageIcon image;
    private JLabel label1;

    private ActionListener EnviarDatos = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //Recuperación de información desde interfaz gráfica.
            try {
                int micros = Integer.parseInt(microsTF.getText());
                int quantum = Integer.parseInt(quantumTF.getText());
                int contexto = Integer.parseInt(contextoTF.getText());
                int bloqueo = Integer.parseInt(bloqueoTF.getText());
                if (micros < 1 || quantum < 1 || contexto < 0 || bloqueo < 0) {
                    throw new Exception("Input number error");
                }

                ArrayList<Proceso> lista = LeerArchivo("procesos.txt");
                
                Despachador x = new Despachador(lista, micros, contexto, quantum, bloqueo);
                
                x.iniciarDespacho(); 
                x.imprimirTablas();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor revisa tu input (1).");
            } catch (Exception e) {
                // crear una alerta de swing de error;
                JOptionPane.showMessageDialog(null, "Por favor revisa tu input (2).");

            }
        }
    };

    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        new Main();
    }

    public static ArrayList<Proceso> LeerArchivo(String nombre) throws FileNotFoundException, IOException { 
                                                                                                            
        ArrayList<Proceso> lista = new ArrayList<>();
        File archivo = new File(nombre);
        FileReader lector = new FileReader(archivo);
        BufferedReader lector2 = new BufferedReader(lector);

        String linea;

        while ((linea = lector2.readLine()) != null) {
            String nombre2 = "";
            int te = 0;
            int tiempoEntrada = 0;
            int numBloq = 0;

            String[] temp = SepararConComa(linea);
            nombre2 = temp[0];
            te = Integer.parseInt(temp[1]);
            tiempoEntrada = Integer.parseInt(temp[2]);
            numBloq = Integer.parseInt(temp[3]);

            Proceso x = new Proceso(nombre2, te, tiempoEntrada, numBloq);
            lista.add(x);

        }
        lector2.close();
        return lista;
    }

    public static String[] SepararConComa(String linea) {
        return linea.split(",");
    }

    private Main() {
        super();
        //Imagen
        image = new ImageIcon(getClass().getResource("champs.jpg"));
        label1 = new JLabel(image);
        add(label1);
        label1.setBounds(320, 60, 300, 300);

        //JPanel
        JButton boton = new JButton("Despachar");
        JLabel titulo = new JLabel("J-CAN Despachador");
        JLabel micros = new JLabel("Microprocesadores");
        JLabel quantum = new JLabel("Tiempo del quantum");
        JLabel contexto = new JLabel("Cambio de contexto");
        JLabel bloqueo = new JLabel("Tiempo de bloqueo");
        microsTF = new JTextField();
        quantumTF = new JTextField();
        contextoTF = new JTextField();
        bloqueoTF = new JTextField();

        // Posición
        titulo.setBounds(40, 25, 300, 60);
        micros.setBounds(40, 100, 150, 40);
        quantum.setBounds(40, 250, 150, 40);
        contexto.setBounds(40, 150, 150, 40);
        bloqueo.setBounds(40, 200, 150, 40);
        microsTF.setBounds(190, 100, 80, 30);
        quantumTF.setBounds(190, 250, 80, 30);
        contextoTF.setBounds(190, 150, 80, 30);
        bloqueoTF.setBounds(190, 200, 80, 30);
        boton.setBounds(100, 310, 80, 40);

        // Fuente
        Font fuente = new Font("Times New Roman", Font.PLAIN, 14);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
        micros.setFont(fuente);
        quantum.setFont(fuente);
        contexto.setFont(fuente);
        bloqueo.setFont(fuente);
        microsTF.setFont(fuente);
        quantumTF.setFont(fuente);
        contextoTF.setFont(fuente);
        bloqueoTF.setFont(fuente);
        boton.setFont(fuente);

        add(titulo);
        add(micros);
        add(quantum);
        add(contexto);
        add(bloqueo);
        add(microsTF);
        add(quantumTF);
        add(contextoTF);
        add(bloqueoTF);
        add(boton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 400);
        setLayout(null);
        setVisible(true);
        

        // Listener botón
        boton.addActionListener(EnviarDatos);

    }

}