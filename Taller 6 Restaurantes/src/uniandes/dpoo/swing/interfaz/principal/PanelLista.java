package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelLista extends JPanel implements ListSelectionListener
{
    private JList<String> listaDeRestaurantes;
    private DefaultListModel<String> dataModel;
    private VentanaPrincipal ventanaPrincipal;
    private List<Restaurante> restaurantes;

    public PanelLista(VentanaPrincipal ventanaPrincipal)
    {
        this.ventanaPrincipal = ventanaPrincipal;
        this.restaurantes = new ArrayList<>();
        
        setBorder(new TitledBorder("Restaurantes"));
        setLayout(new BorderLayout());

        dataModel = new DefaultListModel<>();
        listaDeRestaurantes = new JList<>(dataModel);
        listaDeRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaDeRestaurantes.addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane(listaDeRestaurantes);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll, BorderLayout.CENTER);
    }

    public void actualizarRestaurantes(List<Restaurante> restaurantes)
    {
        this.restaurantes = restaurantes;
        dataModel.clear();
        
        for(Restaurante r : restaurantes)
        {
            dataModel.addElement(r.getNombre());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(!e.getValueIsAdjusting())
        {
            int selectedIndex = listaDeRestaurantes.getSelectedIndex();
            if(selectedIndex >= 0 && selectedIndex < restaurantes.size())
            {
                Restaurante seleccionado = restaurantes.get(selectedIndex);
                ventanaPrincipal.cambiarRestauranteSeleccionado(seleccionado);
            }
        }
    }

    public void seleccionarRestaurante(Restaurante restaurante)
    {
        int index = restaurantes.indexOf(restaurante);
        if(index >= 0)
        {
            listaDeRestaurantes.setSelectedIndex(index);
            listaDeRestaurantes.ensureIndexIsVisible(index);
        }
    }
}
