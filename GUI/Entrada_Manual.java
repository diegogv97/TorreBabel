package GUI;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Entrada_Manual extends JDialog {
	JTextArea txtConfInicial, txtConfFinal;
	JButton btnAceptar;
	VentanaJuego parent;
	
	int cantN = 0;
	
	public Entrada_Manual(VentanaJuego parent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.parent = parent;
		
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		setTitle("Entrada manual");
		setBounds(parent.getX()+150, parent.getY()+100, 563, 400);
		
		
		JLabel lblIngreseLasConfiguraciones = new JLabel("Ingrese la configuraci\u00F3n inicial y final de la torre que desea resolver:");
		lblIngreseLasConfiguraciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngreseLasConfiguraciones.setBounds(10, 10, 560, 23);
		getContentPane().add(lblIngreseLasConfiguraciones);
		
		JLabel lblConfinicial = new JLabel("Configuraci\u00F3n Inicial:");
		lblConfinicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfinicial.setBounds(26, 181, 200, 14);
		getContentPane().add(lblConfinicial);
		
		JLabel lblConfFinal = new JLabel("Configuraci\u00F3n Final:");
		lblConfFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfFinal.setBounds(303, 181, 200, 14);
		getContentPane().add(lblConfFinal);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(303, 36, 228, 134);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnEjemplo = new JTextPane();
		txtpnEjemplo.setBounds(10, 10, 193, 113);
		panel.add(txtpnEjemplo);
		txtpnEjemplo.setBackground(SystemColor.control);
		txtpnEjemplo.setEditable(false);
		txtpnEjemplo.setText("Ejemplo:\tnnen\r\n\tbwyg\r\n\tbwyg\r\n\tbwyg\r\n\tbwyg");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(26, 35, 210, 135);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnSmbolosNPared = new JTextPane();
		txtpnSmbolosNPared.setEditable(false);
		txtpnSmbolosNPared.setBackground(SystemColor.control);
		txtpnSmbolosNPared.setBounds(10, 10, 190, 114);
		panel_1.add(txtpnSmbolosNPared);
		txtpnSmbolosNPared.setText("S\u00EDmbolos:\tn= Pared\r\n\te= Muesca vac\u00EDa\r\n\tb= Bolita azul\r\n\tg= Bolita Verde\r\n\tw= Bolita blanca\r\n\ty= Bolita amarilla");
		
		txtConfInicial = new JTextArea(5,4);
		txtConfInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char entrada = e.getKeyChar();
		        // Verificar si la tecla pulsada no es un digito
		        if ((!(evaluarEntrada(entrada)) || (entrada==KeyEvent.VK_DELETE))){
		        	e.consume(); // ignorar el evento de teclado
		        }
			}
		});
		txtConfInicial.setBounds(26, 206, 200, 120);
		getContentPane().add(txtConfInicial);
		
		txtConfFinal = new JTextArea(5,4);
		txtConfFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char entrada = e.getKeyChar();
		        // Verificar si la tecla pulsada no es un digito
		        if ((!(evaluarEntrada(entrada)) || (entrada==KeyEvent.VK_DELETE))){
		        	e.consume(); // ignorar el evento de teclado
		        }
			}
		});
		txtConfFinal.setBounds(303, 206, 200, 120);
		getContentPane().add(txtConfFinal);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formarEntrada();
			}
		});
		btnAceptar.setBounds(199, 337, 130, 23);
		getContentPane().add(btnAceptar);
		
	}
	
	
	private boolean evaluarEntrada(char car){
		if(car == 'n' || car == 'e' || car == 'b' || car == 'g' || car == 'w' || car == 'y'){
	        // Verificar si la tecla pulsada no es un digito
			return true;
		}
		return false;
	}
	
	private void formarEntrada(){
		String txtI = txtConfInicial.getText();
		String txtF = txtConfFinal.getText();
		
		if(!(txtI.isEmpty() || txtF.isEmpty())){
			//parent.textoManual = txtI + "\n\n" + txtF;
			parent.runManualText(txtI + "\n\n" + txtF);
			
			this.dispose();
		}else{
			JOptionPane.showConfirmDialog(this, "Debe completar todos los datos", "Datos incompletos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
}
