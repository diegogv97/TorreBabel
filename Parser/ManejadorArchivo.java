package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.Date;

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
	
	public static boolean escribirArchivo(String ruta, String contenido){
		try{
			FileOutputStream fos = new FileOutputStream(ruta);
			
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	
	        String[] content = contenido.split("\n");
	        //Escribimos en el archivo con el metodo write
	        for(int cont = 0; cont < content.length; cont++){
	        	bw.write(content[cont]);
	        	bw.newLine();
	        }
	        //Cerramos la conexion
	        bw.close();
	        return true;
		}catch(Exception e){
			//System.out.println("Error al crear archivo de salida");
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public static String definirNombreArchivo(String nruta, String ruta){
		if(!ruta.isEmpty()){
			String[] partesRuta = ruta.split("\\\\");
			
			int ind = partesRuta.length - 1;
			String[] nom = partesRuta[ind].split("\\.");
			String nombre = nom[0];
			partesRuta[ind] = nombre + "[SOLUCIONADO].txt";
			
			ruta = "";
			for(int i = 0; i <= ind; i++){
				ruta += partesRuta[i];
				if(i != ind){
					ruta += "\\";
				}
			}
		}else{
			Date d = new Date();
			
			String nombre = "TorreBabel[SOLUCIONADO]" + d.getDay() + "-" + d.getMonth() + "-" + d.getYear() +
					"_" + d.getHours() + ":" + d.getMinutes() + ".txt";
			ruta = nruta + "\\" + nombre;
		}
		
		
		return ruta;
	}
}
