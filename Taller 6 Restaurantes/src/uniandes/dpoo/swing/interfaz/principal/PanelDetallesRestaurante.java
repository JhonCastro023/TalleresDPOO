package uniandes.dpoo.swing.interfaz.principal;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelDetallesRestaurante extends JPanel
{
    private JLabel labNombre;
    private JLabel labCalificacionTexto;
    private JLabel labCalificacionIcono;
    private JLabel labVisitadoTexto;
    private JCheckBox chkVisitado;

    public PanelDetallesRestaurante()
    {
        setLayout(new GridLayout(3, 1, 5, 5));

        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labNombre = new JLabel("Nombre: ");
        panelNombre.add(labNombre);
        add(panelNombre);

        JPanel panelCalificacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labCalificacionTexto = new JLabel("Calificación: ");
        labCalificacionIcono = new JLabel();
        panelCalificacion.add(labCalificacionTexto);
        panelCalificacion.add(labCalificacionIcono);
        add(panelCalificacion);
        
        JPanel panelVisitado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labVisitadoTexto = new JLabel("Visitado: ");
        chkVisitado = new JCheckBox();
        chkVisitado.setEnabled(false);
        panelVisitado.add(labVisitadoTexto);
        panelVisitado.add(chkVisitado);
        add(panelVisitado);
    }

    private void actualizarRestaurante(String nombre, int calificacion, boolean visitado)
    {
        labNombre.setText("Nombre: " + nombre);
        labCalificacionIcono.setIcon(buscarIconoCalificacion(calificacion));
        chkVisitado.setSelected(visitado);
    }

    public void actualizarRestaurante(Restaurante r)
    {
        if (r != null) {
            this.actualizarRestaurante(r.getNombre(), r.getCalificacion(), r.isVisitado());
        } else {
            labNombre.setText("Nombre: ");
            labCalificacionIcono.setIcon(null);
            chkVisitado.setSelected(false);
        }
    }

    private ImageIcon buscarIconoCalificacion(int calificacion)
    {
        String imagen = "./imagenes/stars" + calificacion + ".png";
        return new ImageIcon(imagen);
    }
}
