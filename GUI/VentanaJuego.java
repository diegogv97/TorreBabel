package GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaJuego extends JFrame implements ActionListener{
	
	JPanel panelJuego;
	JMenuItem mntmNuevo, mntmCargarArchivo, mntmManual;
	int[] posMVacia = {22, 142, 262, 382};
	
	JButton btnResolver, btnAtras, btnSiguiente;
	
	JLabel lblMuescaVacia, lblAvance;
	JLabel[][] lblBolas = new JLabel[5][4];
	
	ImageIcon imagen = new ImageIcon("src/Imagenes/Fondo.png");
	ImageIcon imgMuescaVacia = new ImageIcon("src/Imagenes/Vacia.png");
	ImageIcon imgBolaB = new ImageIcon("src/Imagenes/BolaB.png");
	ImageIcon imgBolaG = new ImageIcon("src/Imagenes/BolaG.png");
	ImageIcon imgBolaW = new ImageIcon("src/Imagenes/BolaW.png");
	ImageIcon imgBolaY = new ImageIcon("src/Imagenes/BolaY.png");
	ImageIcon bolaB, bolaG, bolaW, bolaY;
	
	public VentanaJuego() {
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(this);
		mnInicio.add(mntmNuevo);
		
		JMenu mnEntradas = new JMenu("Entradas");
		mnInicio.add(mnEntradas);
		 
		mntmManual = new JMenuItem("Manual");
		mntmManual.addActionListener(this);
		
		mntmCargarArchivo = new JMenuItem("Cargar archivo");
		mntmCargarArchivo.addActionListener(this);
		mnEntradas.add(mntmCargarArchivo);
		mnEntradas.add(mntmManual);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		getContentPane().setLayout(null);
		
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 924, 600);
		getContentPane().add(panelJuego);
		panelJuego.setLayout(null);
		
		bolaB = new ImageIcon(imgBolaB.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		bolaG = new ImageIcon(imgBolaG.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		bolaW = new ImageIcon(imgBolaW.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		bolaY = new ImageIcon(imgBolaY.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		
        lblBolas[0][0] = new JLabel("");
		lblBolas[0][0].setBounds(49, 45, 70, 70);
		panelJuego.add(lblBolas[0][0]);
		
		lblBolas[0][1] = new JLabel("");
		lblBolas[0][1].setBounds(169, 45, 70, 70);
		panelJuego.add(lblBolas[0][1]);
		
		lblBolas[0][2] = new JLabel("");
		lblBolas[0][2].setBounds(289, 45, 70, 70);
		panelJuego.add(lblBolas[0][2]);
		
		lblBolas[0][3] = new JLabel("");
		lblBolas[0][3].setBounds(409, 45, 70, 70);
		panelJuego.add(lblBolas[0][3]);
		
		lblBolas[1][0] = new JLabel("");
		lblBolas[1][0].setBounds(49, 152, 70, 70);
		panelJuego.add(lblBolas[1][0]);
		
		lblBolas[1][1] = new JLabel("");
		lblBolas[1][1].setBounds(169, 152, 70, 70);
		panelJuego.add(lblBolas[1][1]);
		
		lblBolas[1][2] = new JLabel("");
		lblBolas[1][2].setBounds(289, 152, 70, 70);
		panelJuego.add(lblBolas[1][2]);
		
		lblBolas[1][3] = new JLabel("");
		lblBolas[1][3].setBounds(409, 152, 70, 70);
		panelJuego.add(lblBolas[1][3]);
		
       
		lblBolas[2][0] = new JLabel("");
		lblBolas[2][0].setBounds(49, 259, 70, 70);
		panelJuego.add(lblBolas[2][0]);
		
		lblBolas[2][1] = new JLabel("");
		lblBolas[2][1].setBounds(169, 259, 70, 70);
		panelJuego.add(lblBolas[2][1]);
		
		lblBolas[2][2] = new JLabel("");
		lblBolas[2][2].setBounds(289, 259, 70, 70);
		panelJuego.add(lblBolas[2][2]);
		
		lblBolas[2][3] = new JLabel("");
		lblBolas[2][3].setBounds(409, 259, 70, 70);
		panelJuego.add(lblBolas[2][3]);
		
		lblBolas[3][0] = new JLabel("");
		lblBolas[3][0].setBounds(49, 366, 70, 70);
		panelJuego.add(lblBolas[3][0]);
		
		lblBolas[3][1] = new JLabel("");
		lblBolas[3][1].setBounds(169, 366, 70, 70);
		panelJuego.add(lblBolas[3][1]);
		
		lblBolas[3][2] = new JLabel("");
		lblBolas[3][2].setBounds(289, 366, 70, 70);
		panelJuego.add(lblBolas[3][2]);
		
		lblBolas[3][3] = new JLabel("");
		lblBolas[3][3].setBounds(409, 366, 70, 70);
		panelJuego.add(lblBolas[3][3]);
		
		lblBolas[4][0] = new JLabel("");
		lblBolas[4][0].setBounds(49, 473, 70, 70);
		panelJuego.add(lblBolas[4][0]);
		
		lblBolas[4][1] = new JLabel("");
		lblBolas[4][1].setBounds(169, 473, 70, 70);
		panelJuego.add(lblBolas[4][1]);
		
		lblBolas[4][2] = new JLabel("");
		lblBolas[4][2].setBounds(289, 473, 70, 70);
		panelJuego.add(lblBolas[4][2]);
		
		lblBolas[4][3] = new JLabel("");
		lblBolas[4][3].setBounds(409, 473, 70, 70);
		panelJuego.add(lblBolas[4][3]);
		
		btnResolver = new JButton("Resolver Torre");
		btnResolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControladorPrincipal cp = ControladorPrincipal.getInstance();
				cp.resolverTorre();
				pintarEstado(cp.getEstadoActual());
				lblAvance.setText(cp.getAvance());
				if(!cp.isFin()){
					btnSiguiente.setEnabled(true);
				}
				btnResolver.setEnabled(false);
			}
		});
		btnResolver.setEnabled(false);
		btnResolver.setBounds(619, 43, 124, 33);
		panelJuego.add(btnResolver);
		
		lblMuescaVacia = new JLabel("");
		lblMuescaVacia.setBounds(382, 23, 124, 112);
		lblMuescaVacia.setIcon(new ImageIcon(imgMuescaVacia.getImage().getScaledInstance(lblMuescaVacia.getWidth(), lblMuescaVacia.getHeight(), Image.SCALE_SMOOTH)));
		panelJuego.add(lblMuescaVacia);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(10, 11, 509, 568);
		lblFondo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH)));
		panelJuego.add(lblFondo);
		
		btnAtras = new JButton("<<");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mover(-1);
			}
		});
		btnAtras.setEnabled(false);
		btnAtras.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnAtras.setBounds(529, 147, 80, 80);
		panelJuego.add(btnAtras);
		
		btnSiguiente = new JButton(">>");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mover(1);
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnSiguiente.setBounds(753, 147, 80, 80);
		panelJuego.add(btnSiguiente);
		
		lblAvance = new JLabel("0/0");
		lblAvance.setEnabled(false);
		lblAvance.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAvance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvance.setBounds(619, 154, 124, 67);
		panelJuego.add(lblAvance);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String abrirExplorador(){
		String archivo = "";
		
		JFileChooser sel = new JFileChooser();
		//Creamos el filtro
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
		//Le indicamos el filtro
		sel.setFileFilter(filtro);
		int opcion = sel.showOpenDialog(getContentPane());
		//lblMensaje.setVisible(false);
		//txtReporte.setText("");
		if(opcion == sel.APPROVE_OPTION){
			archivo = sel.getSelectedFile().getPath();
			//txtRuta.setText(archivo);
			//System.out.println(archivo);
			
		}
		
		return archivo;
	}

	@Override
	public void actionPerformed(ActionEvent item) {
		String mensaje = "";
		String titulo = "";
		if(item.getSource() == mntmNuevo){
			limpiarJuego();
		}else{
			if(item.getSource() == mntmCargarArchivo){
				String ruta = abrirExplorador();
				mensaje = ControladorPrincipal.getInstance().evaluarArchivo(ruta);
				titulo = "Error al cargar el archivo";
			}else if(item.getSource() == mntmManual){
					
			}
			
			if(!mensaje.isEmpty()){
				JOptionPane.showConfirmDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}else{
				
				pintarEstado(ControladorPrincipal.getInstance().getEstadoActual());
				btnResolver.setEnabled(true);
			}
		}
			
		
		
		
	}
	
	private void pintarEstado(char[][] torre){
		for(int fila = 0; fila < 5; fila++){
			for(int col = 0; col < 4; col++){
				if(fila == 0){
					if(torre[fila][col] != 'n'){
						lblMuescaVacia.setBounds(posMVacia[col], 23, 124, 112);
					}
				}
				ImageIcon temp = null;
				if(torre[fila][col] == 'e' || torre[fila][col] == 'n'){
					//lblBolas[fila][col].setVisible(false);
				}else if(torre[fila][col] == 'b'){
					temp = bolaB;
					//lblBolas[fila][col].setVisible(true);
				}else if(torre[fila][col] == 'g'){
					temp = bolaG;
					//lblBolas[fila][col].setVisible(true);
				}else if(torre[fila][col] == 'w'){
					temp = bolaW;
					//lblBolas[fila][col].setVisible(true);
				}else if(torre[fila][col] == 'y'){
					temp = bolaY;
					//lblBolas[fila][col].setVisible(true);
				}
				
				lblBolas[fila][col].setIcon(temp);
			}
		}
	}
	
	private void limpiarJuego(){
		for(int fila = 0; fila < 5; fila++){
			for(int col = 0; col < 4; col++){
				lblBolas[fila][col].setIcon(null);
			}	
		}
		
		btnResolver.setEnabled(false);
		btnAtras.setEnabled(false);
		btnSiguiente.setEnabled(false);
	}
	
	private void mover(int mov){
		ControladorPrincipal cp = ControladorPrincipal.getInstance();
		if(mov == 1){
			pintarEstado(cp.getEstadoSiguiente());
			if(cp.isFin()){
				btnSiguiente.setEnabled(false);
			}
			btnAtras.setEnabled(true);
		}else if(mov == -1){
			pintarEstado(cp.getEstadoAnterior());
			if(cp.isInicio()){
				btnAtras.setEnabled(false);
			}
			btnSiguiente.setEnabled(true);
		}
		
		lblAvance.setText(cp.getAvance());
	}
}