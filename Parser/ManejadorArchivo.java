package Parser;

import java.io.BufferedReader;
import java.io.FileReader;

public class ManejadorArchivo {

	public static String leerArchivoRuta(String ruta){
		String contenidoArchivo = "";
		boolean primeraLinea = true;
		try{
			//Creamos un archivo FileReader que obtiene lo que tenga el archivo
			FileReader lector = new FileReader(ruta);
	
			//El contenido de lector se guarda en un BufferedReader
			BufferedReader contenido=new BufferedReader(lector);
	
			String texto = "";
			int contLineas = 0;
			
			//Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
			while((texto=contenido.readLine())!=null){
				if(!primeraLinea){
					contenidoArchivo += "\n";
				}
				contenidoArchivo += texto;
				primeraLinea = false;
			}
			//System.out.println(contenidoSector);
		}catch(Exception e){
			System.out.println("Error al leer");
			contenidoArchivo = "ERROR";
		}
		
		return contenidoArchivo;
	}
}
