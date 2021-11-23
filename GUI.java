import javax.swing.BorderFactory;
import javax.swing.table.AbstractTableModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GUI extends JPanel {

    public GUI(ArrayList<Microprocesador> micros) {

        super(new GridLayout(0, 1));

        ArrayList<Microprocesador> microsList = micros;
        ArrayList<Proceso> procesos = new ArrayList<>();

        String[] columnNames = { "PROCESO", "TCC", "TE", "TVC", "TB", "TT", "TI", "TF" };

        for (Microprocesador m : microsList) {

            String microId = Integer.toString(m.getId());

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Microprocesador " + microId, TitledBorder.CENTER, TitledBorder.TOP));
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            procesos = m.getCompletados();
            String proceso;
            int tcc, te, tvc, tb, tt, ti, tf;
            for (Proceso p : procesos) {
                proceso = p.getNombre();
                tcc = p.getTcc();
                if (p.getNombre() != "Salto") {
                    te = p.getTe();
                    tvc = p.getTvc();
                    tb = p.getNumBloq();
                    tt = p.getTiempoTotal();
                    ti = p.getTiempoInicial();
                    tf = p.getTiempoFinal();
                    Object[] tableRow = { proceso, tcc, te, tvc, tb, tt, ti, tf };
                    tableModel.addRow(tableRow);
                } else {
                    tt = p.getTiempoTotal();
                    ti = p.getTiempoInicial();
                    tf = p.getTiempoFinal();
                    Object[] tableRow = { "", "", "", "", "", tt, ti, tf };
                    tableModel.addRow(tableRow);
                }
            }

            final JTable table = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(tableModel);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane);
            add(panel);
        }
    }

    public void crearGUI(ArrayList<Microprocesador> microsListCreate) {
        // Creación de la ventana
        JFrame frame = new JFrame("Despachador");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GUI newContentPane = new GUI(microsListCreate);
        newContentPane.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(newContentPane);
        frame.setContentPane(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

}