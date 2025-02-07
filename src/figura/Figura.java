package figura;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import brav.Brav;
import brav.Orientable;

public abstract class Figura extends JComponent implements MouseListener {
	private static final long serialVersionUID = 1L;
	protected static final double COS30 = Math.cos(Math.PI/6);

	protected static final BasicStroke FULL_LINE = 
            new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    protected static final BasicStroke DASHED_LINE = 
            new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
    protected static final BasicStroke DOTTED_LINE = 
            new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
    
    public static Point mitad(Point p, Point q) {
    	return new Point((p.x + q.x + 1) / 2, (p.y + q.y + 1) / 2);
    }
    
    protected static int impar(int x) {
    	return (x & 1) == 0? x+1 : x;
    }
    
    public enum Estado {
    	VACIO,
    	INACTIVO,
    	ACTIVO,
    	JUGADO
    }
	
	public final static int ANCHO = 85;
    
    protected static ArrayList<FiguraListener> listeners = new ArrayList<>();
  
    public static void addFiguraListener(FiguraListener listener) {
    	listeners.add(listener);
    }

	protected Brav brav;
	
    protected Estado estado;
    protected boolean base;
	
	protected Polygon poligono;
	protected Polygon[] sectores;
	protected Point centro;
	
    public Polygon[] getSectores() {
		return sectores;
	}
	
    protected Figura() {
		addMouseListener(this);
		setBrav(null);
	}
	
    // Tama�o del rect�ngulo JComponent que circunscribe al poligono
	protected void setTam(int ancho, int alto) {
		setSize(impar(ancho), impar(alto));
	}
    
    public boolean isBase() {
    	return base;
    }

	public void setBrav(Brav brav) {
		this.brav = brav;
		if(brav == null) {
			base = true;
			estado = Estado.VACIO;
		} else {
			base = brav.isBase();
			estado = Estado.INACTIVO;
		}
		repaint();
	}

	public abstract void girar();
	
    public int getLados() {
    	return sectores.length;
    }

	public Polygon getPoligono() {
		return poligono;
	}
    
    public boolean isEmpty() {
    	return estado == Estado.VACIO;
    }

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public abstract Orientable getOrientacion();
	
	public Brav getBrav() {
		return brav;
	}
	
	public void mover(int dx, int dy) {
		Point p = getLocation();
		p.translate(dx, dy);
		setLocation(p);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g.create();
		
		if(estado == Estado.VACIO) {
	        g2.setStroke(DASHED_LINE);
	        g2.setColor(new Color(0, 160, 0));
			g2.fillPolygon(poligono);
	        g2.setColor(Color.BLACK);
//			for(Polygon p : sectores) {
//				g2.drawPolygon(p);
//			}
	        g2.drawPolygon(poligono);
	        g2.fillRect(centro.x-2, centro.y-1, 3, 3);
		} else {
	        g2.setStroke(FULL_LINE);
			int c = brav.ini();
			for(Polygon p : sectores) {
		        g2.setColor(brav.getColor(c).color);
				g2.fillPolygon(p);
		        g2.setColor(Color.BLACK);
				g2.drawPolygon(p);
				c = brav.sig(c);
			}
		}

		g2.dispose();
	}
	
	@Override
	public boolean equals(Object otra) {
		return otra instanceof Figura && brav == ((Figura)otra).brav;
	}
	
	@Override
	public String toString() {
		return brav == null ? "vacía" : brav.toString();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.poligono.contains(e.getX(), e.getY())) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				for(FiguraListener l : listeners) {
					if(l.clic(this)) break;
				}
			} else {
				if(estado == Estado.ACTIVO) {
					girar();
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

}
