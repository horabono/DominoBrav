package juego;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import layoutkit.Layout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class DominoBrav extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private enum BravSet {
		TRIANGULOS(new JRadioButton("Triángulos")),
		RECTANGULOS(new JRadioButton("Rectángulos")),
		PENTAGONOS(new JRadioButton("Pentágonos")),
		HEXAGONOS(new JRadioButton("Hexágonos")),
		HEPTAGONOS(new JRadioButton("Heptágonos"));
		
		public final JRadioButton control;
		
		private BravSet(JRadioButton control) {
			this.control = control;
			this.control.addItemListener( new ItemListener() {
		        @Override
		        public void itemStateChanged(ItemEvent e) {
		            JRadioButton rb = (JRadioButton) e.getSource();
		            ButtonModel model = rb.getModel();
		            if(rb == TRIANGULOS.control) {
		                if(model.isSelected()) {
		                	lados = 3;
		                }
		            } else if(rb == RECTANGULOS.control) {
		                if(model.isSelected()) {
		                	lados = 4;
		                }
		            } else if(rb == PENTAGONOS.control) {
		                if(model.isSelected()) {
		                	lados = 5;
		                }
		            } else if(rb == HEXAGONOS.control) {
		                if(model.isSelected()) {
		                	lados = 6;
		                }
		            }else if(rb == HEPTAGONOS.control) {
		                if(model.isSelected()) {
		                	lados = 7;
		                }
		            }
		        }
			});
		}
	}
	
	private final JPanel contentPanel = new JPanel();
	private static int lados;
	private boolean giroLibre;

	public static void main(String[] args) {
		try {
			DominoBrav dialog = new DominoBrav();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screenSize.width - dialog.getWidth()) / 2;
			int y = (screenSize.height - dialog.getHeight()) / 2;
			dialog.setLocation(x, y);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DominoBrav() {
		setResizable(false);
		setTitle("Domin\u00F3 BRAV");
		setBounds(100, 100, 170, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JCheckBox chkGiroLibre = new JCheckBox("Giro libre");
		chkGiroLibre.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				giroLibre = chkGiroLibre.isSelected();
			}
		});
		chkGiroLibre.setBounds(164, 61, 86, 23);
		contentPanel.add(chkGiroLibre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Jugar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
						    	String jugadorDerecha = JOptionPane.showInputDialog("Nombre del jugador a la derecha").trim();
						    	String jugadorIzquierda = JOptionPane.showInputDialog("Nombre del jugador a la izquierda").trim();
						    	if(jugadorDerecha.length() == 0) {
							    	JOptionPane.showMessageDialog(null, "Comienza el jugador a la derecha");
						    	} else {
							    	JOptionPane.showMessageDialog(null, "Comienza " + jugadorDerecha);
						    	}
								try {
									Domino.crearJuego(lados, giroLibre, jugadorDerecha, jugadorIzquierda);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
        ButtonGroup bravType = new ButtonGroup();
		int x = Layout.SINGLE_MARGIN;
		int y = Layout.SINGLE_MARGIN;
		for(BravSet b : BravSet.values()) {
        	b.control.setBounds(x, y, 100, 25);
        	bravType.add(b.control);
        	contentPanel.add(b.control);
        	y = Layout.bottomOf(b.control);
		}
		BravSet.RECTANGULOS.control.setSelected(true);
		
		chkGiroLibre.setLocation(x, y + Layout.SINGLE_MARGIN);
		chkGiroLibre.setSelected(true);
	}

}
