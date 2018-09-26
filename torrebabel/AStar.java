package torrebabel;

import java.util.ArrayList;
import java.util.Stack;


public class AStar {
    final int COSTO_VERTICAL = 1;
    final int COSTO_HORIZONTAL = 1;
    
    private Nodo meta;
    private ArrayList<Nodo> abiertos = new ArrayList<>();
    private ArrayList<Nodo> cerrados = new ArrayList<>();
    
    public AStar(Estado meta){
        this.meta = new Nodo(meta);
    }

    public Nodo getMeta() {
        return meta;
    }

    public void setMeta(Nodo meta) {
        this.meta = meta;
    }
    
    
    
    //copia los datos de una estructura char[][] a otra char[][]
    private void copiarDatosTorre(char[][] origen, char[][] destino){
        for (int fil = 0; fil < meta.getEstadoTorre().getFilas(); fil++){
           for (int col = 0; col < meta.getEstadoTorre().getColumnas(); col++){
               origen[fil][col] = destino[fil][col];
           }
        }
    }
    
    
    //realiza calculo de H segun la Distancia de Hamming desde estado e hasta meta
    public int calcH(Estado e){
        int H = 0;
        int filas = meta.getEstadoTorre().getFilas();
        int columnas = meta.getEstadoTorre().getColumnas();
        char[][] torreMeta = meta.getEstadoTorre().getTorre();
        char[][] torreNodo = e.getTorre();
        for (int fil = 0; fil < filas; fil++){
            for (int col = 0; col < columnas; col++){
                
                if(torreMeta[fil][col] != torreNodo[fil][col]){
                    H++;
                }
                
            }
        }
        return H;
    }
    
    //devuelve el index nodo con mejor valor F dentro del array abiertos
    private int indexAbiertoMenorF(){
        int retI = (int) (Math.random() * (abiertos.size()-1)) + 1;
        int f = abiertos.get(retI).getValorF();
        for (int i = 0; i < abiertos.size(); i++){
            if (abiertos.get(i).getValorF() < f){
                f = abiertos.get(i).getValorF();
                retI = i;
            }
        }
        return retI;
    }
    
    //verifica que no haya un nodo abierto con el mismo estado. Si existe, 
    //combrueba si el valor g es menor. De ser así, modifica su predecesor
    private void agregarAbierto(Nodo nActual, int costo, Estado nuevo, 
                          tipoMovimiento mov, int filMov, int colMov){
        //verifico que el nodo no esté en cerrados
        for(Nodo n : cerrados){
            if (n.getEstadoTorre().isTorreIgual(nuevo)){
                if(n.getValorG() > costo){
                    n.setPredecesor(nActual);
                    n.setMovmientoPredecesor(mov);
                    n.setFilaMovPredecesor(filMov);
                    n.setColMovPredecesor(colMov);
                }
                return; //encontró uno igual. termina
            }
        }
        //verifico los nodos abiertos
        for (Nodo n : abiertos){
            if (n.getEstadoTorre().isTorreIgual(nuevo)){
                if(n.getValorG() > costo){
                    n.setPredecesor(nActual);
                    n.setMovmientoPredecesor(mov);
                    n.setFilaMovPredecesor(filMov);
                    n.setColMovPredecesor(colMov);
                }
                return; //encontró uno igual. termina
            }
        }
        //si no encontró uno igual, lo inserta
        int valH = calcH(nuevo);
        abiertos.add(new Nodo(costo, valH, nActual, mov, nuevo, filMov, colMov));
    }
    
    //calcula todos los posibles nodos que se generan a partir de un nodo actual
    private void nodosProximos(Nodo nActual){
        cerrados.add(nActual);
        int filas = nActual.getEstadoTorre().getFilas();
        int columnas = nActual.getEstadoTorre().getColumnas();
        char[][] temp;
        String desc;
        //calculo de todos los movimientos rotacionales de filas
        for(int fil = 0; fil < filas; fil++){
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            Estado nuevo = new Estado(filas, columnas, temp);
            nuevo.rotarFilaDer(fil);
            int costo = nActual.getValorG() + COSTO_HORIZONTAL;
            agregarAbierto(nActual, costo, nuevo, tipoMovimiento.ROTAR_DERECHA, (fil+1), 0);
            
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.rotarFilaIzq(fil);
            costo = nActual.getValorG() + COSTO_HORIZONTAL;
            agregarAbierto(nActual, costo, nuevo, tipoMovimiento.ROTAR_IZQUIERDA, (fil+1), 0);
        }
        
        //movimientos verticales de bolitas por muesca vacia
        temp = new char[filas][columnas]; 
        copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
        Estado nuevo = new Estado(filas, columnas, temp);
        int colVacia = nuevo.getiColMuescaVacia();
        int filVacia = nuevo.getiFilMuestaVacia();
        if (filVacia == 0){
            for (int i = 0; i < filas-1; i++){
                nuevo.subirBola(filVacia+1, colVacia);
                int costo = nActual.getValorG() + COSTO_VERTICAL;
                agregarAbierto(nActual, costo, nuevo, tipoMovimiento.SUBIR_BOLITA, (filVacia+2), (colVacia+1));
                temp = new char[filas][columnas]; 
                copiarDatosTorre(temp, nuevo.getTorre());
                nuevo = new Estado(filas, columnas, temp); 
                filVacia++;
            }
            
        }
        else if(filVacia == meta.getEstadoTorre().getFilas()-1){ 
            if(nuevo.getTorre()[filVacia-1][colVacia] != 'n'){  //no intente bajar una pared
                for (; filVacia > 0; filVacia--){
                    nuevo.bajarBola(filVacia-1, colVacia);
                    int costo = nActual.getValorG() + COSTO_VERTICAL;
                    agregarAbierto(nActual, costo, nuevo, tipoMovimiento.BAJAR_BOLITA, (filVacia), (colVacia+1));;
                    temp = new char[filas][columnas]; 
                    copiarDatosTorre(temp, nuevo.getTorre());
                    nuevo = new Estado(filas, columnas, temp); 
                }
            }
        }
        else{
            if(nuevo.getTorre()[filVacia-1][colVacia] != 'n'){  //no intente bajar una pared
                temp = new char[filas][columnas]; 
                copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
                nuevo = new Estado(filas, columnas, temp);    
                for (; filVacia > 0; filVacia--){
                    nuevo.bajarBola(filVacia-1, colVacia);
                    int costo = nActual.getValorG() + COSTO_VERTICAL;
                    agregarAbierto(nActual, costo, nuevo, tipoMovimiento.BAJAR_BOLITA, (filVacia), (colVacia+1));
                    temp = new char[filas][columnas]; 
                    copiarDatosTorre(temp, nuevo.getTorre());
                    nuevo = new Estado(filas, columnas, temp); 
                }
            }
            int costo = nActual.getValorG() + COSTO_VERTICAL;
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            filVacia = nuevo.getiFilMuestaVacia();
            for (; filVacia < filas-1; filVacia++){
                nuevo.subirBola(filVacia+1, colVacia);
                costo = nActual.getValorG() + COSTO_VERTICAL;
                agregarAbierto(nActual, costo, nuevo, tipoMovimiento.SUBIR_BOLITA, (filVacia+2), (colVacia+1));
                temp = new char[filas][columnas]; 
                copiarDatosTorre(temp, nuevo.getTorre());
                nuevo = new Estado(filas, columnas, temp); 
            }
        }
        
        
    }
    
    
    public void calcularCamino(Estado eActual){
        if(!abiertos.isEmpty()){
            abiertos = new ArrayList<>();
            cerrados = new ArrayList<>();
        }
        int h = calcH(eActual);
        Nodo nActual = new Nodo(0, h, null, null, eActual, 0,0);
        if(eActual.isTorreIgual(meta.getEstadoTorre())){
            return;
        }
        while (true){
            nodosProximos(nActual);
            int iMenor = indexAbiertoMenorF();
            Nodo menor = abiertos.get(iMenor);
            abiertos.remove(iMenor);
            if (menor.getEstadoTorre().isTorreIgual(meta.getEstadoTorre())){
                meta = menor;
                break;
            }
            nActual = menor;
        }
    }
    
    public void printCamino(Nodo n){
        while(n != null){
            n.getEstadoTorre().printTorre();
            tipoMovimiento mov =  n.getMovmientoPredecesor();
            System.out.print("Movimiento: " + mov);
            System.out.print("   Fila: " +n.getFilaMovPredecesor());
            if (mov == tipoMovimiento.BAJAR_BOLITA || mov == tipoMovimiento.SUBIR_BOLITA ){
                System.out.print("   Columna: " +n.getColMovPredecesor());
            }
            System.out.println();
            printCamino(n.getPredecesor());
            break;
        }
    }
    
    public Stack<Estado> getCaminoEstados(Nodo n, Stack<Estado> estados){
        while(n != null){
        	estados.push(n.getEstadoTorre());
        	
        	getCaminoEstados(n.getPredecesor(), estados);
        	break;
        }
        
        return estados;
    }
    
    public String getCaminoString(Nodo n, String solucion){
        while(n != null){
        	
            tipoMovimiento mov =  n.getMovmientoPredecesor();
            
            if(mov != null){
            	solucion += getCaminoString(n.getPredecesor(), solucion) + "\n" + solucion;
            	
            	solucion += "Movimiento: " + mov;
                System.out.print("Movimiento: " + mov);
                solucion += "   Fila: " +n.getFilaMovPredecesor();
                System.out.print("   Fila: " +n.getFilaMovPredecesor());
                if (mov == tipoMovimiento.BAJAR_BOLITA || mov == tipoMovimiento.SUBIR_BOLITA ){
                	solucion += "   Columna: " +n.getColMovPredecesor();
                    System.out.print("   Columna: " +n.getColMovPredecesor());
                }
                
                solucion += "\n";
                System.out.println();
            }
            
            solucion += n.getEstadoTorre().torreToString();
            
            return solucion;
            
            
            
        }
        
        return solucion;
    }
}
