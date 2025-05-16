package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel
{
    private JLabel labMapa;
    private List<Restaurante> restaurantes;

    public PanelMapaVisualizar()
    {
        this.labMapa = new JLabel(new ImageIcon("./imagenes/mapa.png"));
        labMapa.setBorder(new LineBorder(Color.DARK_GRAY));
        add(labMapa, BorderLayout.CENTER);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if (restaurantes != null && !restaurantes.isEmpty()) {
            Graphics2D g2d = (Graphics2D)g;
            
            g2d.setColor(Color.RED);
            
            for (Restaurante restaurante : restaurantes) {
                int x = restaurante.getX();
                int y = restaurante.getY();
                
                g2d.fillOval(x - 5, y - 5, 10, 10);
                
                g2d.setColor(restaurante.isVisitado() ? Color.BLUE : Color.RED);
                
                g2d.drawString(restaurante.getNombre(), x + 10, y);
                
                g2d.setColor(Color.RED);
            }
        }
    }

    public void actualizarMapa(List<Restaurante> nuevosRestaurantes)
    {
        if(restaurantes != null)
        {
            this.restaurantes.clear();
            this.restaurantes.addAll(nuevosRestaurantes);
        }
        else
        {
            this.restaurantes = nuevosRestaurantes;
        }
        repaint();
    }
}
