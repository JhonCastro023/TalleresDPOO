package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener
{
    private static final String VISITADOS = "VISITADOS";
    private static final String TODOS = "TODOS";

    private PanelMapaVisualizar panelMapa;
    private JRadioButton radioTodos;
    private JRadioButton radioVisitados;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa(VentanaPrincipal ventanaPrincipal)
    {
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Mapa de Restaurantes");
        setLayout(new BorderLayout());

        panelMapa = new PanelMapaVisualizar();
        add(panelMapa, BorderLayout.CENTER);

        JPanel panelRadios = new JPanel();
        
        radioTodos = new JRadioButton("Todos los restaurantes", true);
        radioTodos.setActionCommand(TODOS);
        radioTodos.addActionListener(this);
        
        radioVisitados = new JRadioButton("Sólo visitados");
        radioVisitados.setActionCommand(VISITADOS);
        radioVisitados.addActionListener(this);
        
        ButtonGroup grupoRadios = new ButtonGroup();
        grupoRadios.add(radioTodos);
        grupoRadios.add(radioVisitados);
        
        panelRadios.add(radioTodos);
        panelRadios.add(radioVisitados);
        
        add(panelRadios, BorderLayout.SOUTH);

        panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));

        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String comando = e.getActionCommand();
        if(TODOS.equals(comando))
        {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));
        }
        else if(VISITADOS.equals(comando))
        {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(false));
        }
    }
}
