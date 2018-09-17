package GUI;

import java.util.ArrayList;
import java.util.Stack;

import torrebabel.AStar;
import torrebabel.Estado;
import torrebabel.Nodo;
import torrebabel.TorreBabel;
import Parser.ManejadorArchivo;
import Parser.Parser;

public class ControladorPrincipal {
	
	private static final ControladorPrincipal ourInstance = new ControladorPrincipal();
	
	private ManejadorArchivo manejadorArchivo;
	private String rutaArchivoBase, rutaArchivoExportado;
	private Parser parser;
	
	private AStar aEstrella;
	private TorreBabel torreBabel;
	private ArrayList<Estado> listaEstado = new ArrayList<Estado>();
	private int indexLista;
	
	public static ControladorPrincipal getInstance() {
        return ourInstance;
    }
	
	public String evaluarArchivo(String ruta){
		
		rutaArchivoBase = ruta;
		indexLista = -1;
		
		String mensaje = "";
		String contenido = manejadorArchivo.leerArchivoRuta(ruta);
		if(!contenido.equals("ERROR")){
			//Sigue con el parser
			parser = new Parser();
			System.out.println(contenido);
			mensaje = parser.evaluarCompleto(contenido);
			
			if(mensaje.isEmpty()){
				String conUnido = unirContenido(contenido);
				definirConfiguraciones(conUnido);
			}
		}else{
			mensaje = "Error al leer el archivo";
		}
		
		return mensaje;
	}
	
	private String unirContenido(String contenido){
		String contUnido = "";
		String[] cont = contenido.split("\n");
		for(int i = 0; i < 11; i++){
			contUnido += cont[i];
		}
		
		return contUnido;
	}
	
	private void definirConfiguraciones(String contenido){
		char[][] torreI = new char[5][4];
		char[][] torreF = new char[5][4];
		int torre = 0;
		int contLetra = 0;
		while(torre < 2){
			for(int fila = 0; fila < 5; fila++){
				for(int col = 0; col < 4; col++){
					switch(torre){
						case 0:
							torreI[fila][col] = contenido.charAt(contLetra);
							break;
						case 1:
							torreF[fila][col] = contenido.charAt(contLetra);
							break;
					}
					contLetra++;
				}
			}
			torre++;
		}
		
		torreBabel = new TorreBabel(torreI, torreF);
		listaEstado.clear();
		listaEstado.add(torreBabel.geteInicial());
		indexLista = 0;
	}

	public boolean isInicio(){
		if(indexLista == 0){
			return true;
		}
		return false;
	}
	
	public boolean isFin(){
		if(indexLista == listaEstado.size()-1){
			return true;
		}
		return false;
	}
	
	public String getAvance(){
		return (indexLista+1) + "/" + listaEstado.size();
	}
	
	public char[][] getEstadoActual(){
		return listaEstado.get(indexLista).getTorre();
	}
	
	public char[][] getEstadoAnterior(){
		indexLista--;
		return listaEstado.get(indexLista).getTorre();
	}
	
	public char[][] getEstadoSiguiente(){
		indexLista++;
		return listaEstado.get(indexLista).getTorre();
	}
	
	public char[][] getEstadoInicial(){
		indexLista = 0;
		return listaEstado.get(indexLista).getTorre();
	}
	
	public void resolverTorre(){		
		aEstrella = new AStar(torreBabel.geteFinal());
		aEstrella.calcularCamino(torreBabel.geteInicial());
		
		Stack<Estado> pilaEstado = aEstrella.getCaminoEstados(aEstrella.getMeta(), new Stack<Estado>());
		aEstrella.printCamino(aEstrella.getMeta());
		
		listaEstado.clear();
		
		while(!pilaEstado.isEmpty()){
			listaEstado.add(pilaEstado.pop());
		}
	}
	
	public String getCaminoToString(){
		String solucion = "Archivo base:\n" + rutaArchivoBase + "\n\n" + "Configuración inicial:\n" + aEstrella.getCaminoString(aEstrella.getMeta(), "") +
				"------ Configuración final alcanzada ------";
		
		return solucion;
	}
	
	public boolean exportarPartida(){
		rutaArchivoExportado = manejadorArchivo.definirNombreArchivo(rutaArchivoBase);
		return manejadorArchivo.escribirArchivo(rutaArchivoExportado, getCaminoToString());
	}
	
	public String getRutaExportada(){
		return rutaArchivoExportado;
	}
}
