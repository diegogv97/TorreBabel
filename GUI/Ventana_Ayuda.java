package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.TextArea;
import java.awt.Font;
import java.awt.Color;

public class Ventana_Ayuda extends JDialog {
	String instUso = "INSTRUCCIONES DE USO\n\n"
			+ "1. Defina la entrada de datos:\n"
			+ "Diríjase al menú \"Inicio\", luego al sub menú \"Entradas\" y elija si desea\n"
			+ "brindar los datos de entrada cargando un archivo previamente elaborado\n"
			+ "o si desea hacerlo manualmente.\n"
			+ "Si la entrada en manual, se despliega un cuadro de diálogo con dos cajas\n"
			+ "de texto donde tendrá que ingresar la configuración inicial y final para\n"
			+ "resolver la torre.\n\n"
			+ "2. Resolver el problema:\n"
			+ "Si los datos de entrda son correctos el botón \"Resolver torre\" queda\n"
			+ "habilitado.\nAl accionar dicho botón, el programa podría tardar algunos\n"
			+ "segundos encontrando la ruta de solución.\n\n"
			+ "3. Observar solución:\nUna vez encontrada la solución, se muestra gráficamente\n"
			+ "el camino para llegar a ella y también se muestra en formato de texto.\n"
			+ "Además se habilitan los botones para navegar por los estados que\n"
			+ "conforman la solución.\n\n"
			+ "4. Exportar solución:\n"
			+ "Si se desea exportar la solución de un problema a un archivo de texto, se\n"
			+ "selecciona el botón \"Exportar solución\" que aparece bajo la caja de texto\n"
			+ "con la solución de la torre.\n\n"
			+ "5. Crear nueva solución:\n"
			+ "Si se desea limpiar la pantalla y realizar una nueva ejecución, diríjase al\n"
			+ "menú \"Inicio\" y seleccione la opción \"Nuevo\".";
	
	String formEntr = "FORMATOS DE ENTRADA\n\n"
			+ " - Simbología:\n"
			+ "\tn= Pared\r\n"
			+ "\te= Muesca vac\u00EDa\r\n"
			+ "\tb= Bolita azul\r\n"
			+ "\tg= Bolita Verde\r\n"
			+ "\tw= Bolita blanca\r\n"
			+ "\ty= Bolita amarilla\n\n"
			
			+ ">> Desde archivo:\n"
			+ "\tExtensión requerida: texto (.txt)\n"
			+ "\tFormato:\n"
			+ "\t- De la línea 1 a la 5 corresponden a los datos de la configuración\n"
			+ "\tinicial de la Torre.\n"
			+ "\t- De la línea 7 a la 11 corresponden a los datos de la configuración\n"
			+ "\tmeta de la Torre.\n"
			+ "\t- Se deja la línea 6 en blanco.\n"
			+ "\n\t> Ejemplo del contenido del archivo:\n"
			+ "\t(1)ngnn\n"
			+ "\t(2)bgwy\n"
			+ "\t(3)bgwy\n"
			+ "\t(4)bgwy\n"
			+ "\t(5)bewy\n"
			+ "\t(6)\n"
			+ "\t(7)nenn\n"
			+ "\t(8)bgwy\n"
			+ "\t(9)bgwy\n"
			+ "\t(10)bgwy\n"
			+ "\t(11)bgwy\n"
			+ "\t- Nota: A la izqierda, entre paréntesis, se indica el número de línea\n"
			+ "\t  del archivo.\n\n"
			+ ">> Manual:\n"
			+ "\tSe presentan dos cajas de texto. La primera (a la izquierda),\n"
			+ "corresponde a la configuración inicial, por lo tanto, la segunda (a la derecha)\n"
			+ "corresponde a la configuración meta de la Torre.\n"
			+ "\n\t> Ejemplo del contenido correcto para las cajas de texto:\n"
			+ "\tngnn\n"
			+ "\tbgwy\n"
			+ "\tbgwy\n"
			+ "\tbgwy\n"
			+ "\tbewy";
	
	String expSol = "EXPORTAR SOLUCIÓN\n\n"
			+ "Si desea exportar una solución generada, debe seguir los siguientes\n"
			+ "pasos:\n\n"
			+ " 1) Hacer clic en el botón \"Exportar solución\".\n"
			+ " 2) Elegir el directorio donde desea almacenar la solución.\n\n"
			+ " - Nota:\n"
			+ "      Si los datos de entrada fueron brindados desde un archivo, el\n"
			+ "      archivo creado con la solución se almacenará con el siguiente nombre:\n"
			+ "      <Nombre del archivo de entrada>[SOLUCIONADO].txt\n\n"
			+ "      Si los datos de entrada fueron brindados manualmente, el archivo\n"
			+ "      creado con la solución se almacenará con el siguiente nombre:\n"
			+ "      TorreBabel[SOLUCIONADO]<fecha>_<hora>.txt\n\n";
	
	
	public Ventana_Ayuda(JFrame parent, int texto_opc) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Ayuda");
		getContentPane().setLayout(null);
		setBounds(parent.getX() + 150, parent.getY() + 100, 450, 350);
		
		TextArea txtTextoAyuda = new TextArea();
		txtTextoAyuda.setBackground(Color.WHITE);
		txtTextoAyuda.setEditable(false);
		txtTextoAyuda.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTextoAyuda.setBounds(0, 0, 444, 321);
		getContentPane().add(txtTextoAyuda);
		
		switch(texto_opc){
		case 0:
			txtTextoAyuda.setText(instUso);
			setTitle("Ayuda - Instrucciones de Uso");
			break;
		case 1:
			txtTextoAyuda.setText(formEntr);
			setTitle("Ayuda - Formatos de Entrada");
			break;
		case 2:
			txtTextoAyuda.setText(expSol);
			setTitle("Ayuda - Exportar solución");
			break;
		}
	}
}
