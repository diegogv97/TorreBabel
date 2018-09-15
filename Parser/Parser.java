package Parser;

public class Parser {
	private int contN, contE, contB, contG, contW, contY;
	
	public Parser() {
		// TODO Auto-generated constructor stub
		contN = 0;
		contE = 0;
		contB = 0;
		contG = 0;
		contW = 0;
		contY = 0;
	}
	
	
	public String evaluarCompleto(String contenido){
		String mensaje = "";
		String[] lineas = contenido.split("\n");
		
		int contLineaTorre = 0;
		int filaError = -1;
		if(lineas.length == 11){
			for(int i = 0; i < 11; i++){
				if(i != 5){
					mensaje = evaluarLinea(contLineaTorre, lineas[i]);
					contLineaTorre++;
					if(contLineaTorre == 5){
						contLineaTorre = 0;
						contN = 0;
						contE = 0;
						contB = 0;
						contG = 0;
						contW = 0;
						contY = 0;
						
					}
					if(!mensaje.isEmpty()){
						filaError = i+1;
						break;
					}
				}
			}
			if(!mensaje.isEmpty()){
				String encabezado = "Error en la linea " + filaError + ". ";
				if(filaError > 5){
					encabezado += "(Configuracion final)\nError: ";
				}else{
					encabezado += "(Configuracion inicial)\nError: ";
				}
				mensaje = encabezado + mensaje;
			}
		}else{
			mensaje = "El archivo contiene demasiadas lineas";
		}
		
		return mensaje;
	}
	
	public String evaluarLinea(int fila, String contenido){
		String mensaje = "";
		switch(fila){
			case 7:
				fila = 0;
				break;
			case 8:
				fila = 1;
				break;
			case 9:
				fila = 2;
				break;
			case 10:
				fila = 3;
				break;
			case 11:
				fila = 4;
				break;
		}
		
		int error = revisarLinea(fila, contenido);
		
		switch(error){
			case 1:
				return "Longitud de fila incorrecta";
			case 2:
				return "Primera fila contiene mas de 3 topes ('n')";
			case 3:
				return "Primera fila contiene menis de 3 topes ('n')";
			case 4:
				return "Hay mas de una muesca vacia ('e')";
			case 5:
				return "Hay mas de cuatro bolitas azules ('b')"; 
			case 6:
				return "Hay mas de cuatro bolitas verdes ('g')";
			case 7:
				return "Hay mas de cuatro bolitas blancas ('w')";
			case 8:
				return "Hay mas de cuatro bolitas amarillas ('y')";
		}
		
		return mensaje;
	}

	/* TIPOS DE ERRORES:
	 * 0: No hay errores
	 * 1: Longitud de entrada incorrecta
	 * 2: Primera fila contiene mas de 3 n
	 * 3: Primera fila contiene menos de 3 n
	 * 4: Hay mas de 1 e
	 * 5: Hay mas de 4 b
	 * 6: Hay mas de 4 g
	 * 7: Hay mas de 4 w
	 * 8: Hay mas de 4 y
	 */
	public int revisarLinea(int fila, String linea){
		if(linea.length() == 4){
			for(int i = 0; i < 4; i++){
				
				// PARA LA FILA 0
				if(fila == 0){
					if(linea.charAt(i) == 'n'){
						if(!isSimboloCompleto('n', contN)){
							contN++;
						}else{
							return 2;
						}
					}
					
					if(i == 3 && contN < 3){
						return 3;
					}
				}
				
				// PARA CUALQUIER FILA
				if(linea.charAt(i) == 'e'){
					if(!isSimboloCompleto('e', contE)){
						contE++;
					}else{
						return 4;
					}
				}else if(linea.charAt(i) == 'b'){
					if(!isSimboloCompleto('b', contB)){
						contB++;
					}else{
						return 5;
					}
				}else if(linea.charAt(i) == 'g'){
					if(!isSimboloCompleto('g', contG)){
						contG++;
					}else{
						return 6;
					}
				}else if(linea.charAt(i) == 'w'){
					if(!isSimboloCompleto('w', contW)){
						contW++;
					}else{
						return 7;
					}
				}else if(linea.charAt(i) == 'y'){
					if(!isSimboloCompleto('y', contY)){
						contY++;
					}else{
						return 8;
					}
				}
			}
		}else{
			return 1;
		}
		return 0;
	}
	
	private boolean isSimboloCompleto(char tipo, int cantidad){
		if(tipo == 'n'){
			if(cantidad < 3){
				return false;
			}
		}else if(tipo == 'e'){
			if(cantidad < 1){
				return false;
			}
		}else{
			if(cantidad < 4){
				return false;
			}
		}
		return true;
	}
}
