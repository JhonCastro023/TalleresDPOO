package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel
{
    private JTextField txtNombre;
    private JComboBox<String> cbbCalificacion;
    private JComboBox<String> cbbVisitado;
    public PanelEditarRestaurante()
    {
        setLayout(new GridLayout(3, 2, 5, 5));
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        add(lblNombre);
        add(txtNombre);
        
        JLabel lblCalificacion = new JLabel("Calificación:");
        cbbCalificacion = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        add(lblCalificacion);
        add(cbbCalificacion);
        
        JLabel lblVisitado = new JLabel("Visitado:");
        cbbVisitado = new JComboBox<>(new String[]{"Sí", "No"});
        add(lblVisitado);
        add(cbbVisitado);
    }

    public boolean getVisitado()
    {
        return cbbVisitado.getSelectedItem().equals("Sí");
    }

    public int getCalificacion()
    {
        String calif = (String)cbbCalificacion.getSelectedItem();
        return Integer.parseInt(calif);
    }


    public String getNombre()
    {
        return txtNombre.getText();
    }
}
