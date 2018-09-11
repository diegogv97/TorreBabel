package torrebabel;


import java.util.ArrayList;

public class AStar {
    private Nodo meta;
    private ArrayList<Nodo> abiertos = new ArrayList<>();
    private ArrayList<Nodo> cerrados = new ArrayList<>();
    
    public AStar(Estado meta){
        this.meta = new Nodo(meta);
    }
    
    //copia los datos de una estructura char[][] a otra char[][]
    private void copiarDatosTorre(char[][] origen, char[][] destino){
        for (int fil = 0; fil < meta.getEstadoTorre().getFilas(); fil++){
           for (int col = 0; col < meta.getEstadoTorre().getColumnas(); col++){
               origen[fil][col] = destino[fil][col];
           }
        }
    }
    
    
    //realiza calculo de H segun la Distancia de Hamming desde Nodo n hasta meta
    public void calcH(Nodo n){
        int H = 0;
        int filas = meta.getEstadoTorre().getFilas();
        int columnas = meta.getEstadoTorre().getColumnas();
        char[][] torreMeta = meta.getEstadoTorre().getTorre();
        char[][] torreNodo = n.getEstadoTorre().getTorre();
        for (int fil = 0; fil < filas; fil++){
            for (int col = 0; col < columnas; col++){
                
                if(torreMeta[fil][col] != torreNodo[fil][col]){
                    H++;
                }
                
            }
        }
        n.setValorH(H);
    }
    
    //verifica que no haya un nodo abierto con el mismo estado. Si existe, 
    //combrueba si el valor g es menor. De ser así, modifica su predecesor
    private void agregarAbierto(Nodo nActual, int costo, Estado nuevo, String desc){
        //verifico que el nodo no esté en cerrados
        for(Nodo n : cerrados){
            if (n.getEstadoTorre().isTorreIgual(nuevo)){
                return; //lo encontré cerrado, termino
            }
        }
        //verifico los nodos abiertos
        for (Nodo n : abiertos){
            if (n.getEstadoTorre().isTorreIgual(nuevo)){
                if(n.getValorG() > costo){
                    n.setPredecesor(nActual);
                }
                return; //encontró uno igual. termina
            }
        }
        //si no encontró uno igual, lo inserta
        abiertos.add(new Nodo(4, nActual, desc  , nuevo));
    }
    
    //calcula todos los posibles nodos que se generan a partir de un nodo actual
    private void nodosProximos(Nodo nActual){
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
            desc = "rotar fila " + (fil+1) + " a la derecha";
            agregarAbierto(nActual, 4, nuevo, desc);
            
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.rotarFilaIzq(fil);
            desc = "rotar fila " + (fil+1) + " a la izquierda";
            agregarAbierto(nActual, 4, nuevo, desc);
        }
        
        //movimientos verticales de bolitas por muesca vacia
        temp = new char[filas][columnas]; 
        copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
        Estado nuevo = new Estado(filas, columnas, temp);
        int colVacia = nuevo.getiColMuescaVacia();
        int filVacia = nuevo.getiFilMuestaVacia();
        System.out.println(filVacia);
        if (filVacia == 0){
            nuevo.subirBola(filVacia+1, colVacia);
            desc = "subir bolita de fila " + (filVacia+1) + " columna " + (colVacia+1);
            agregarAbierto(nActual, 2, nuevo, desc);
        }
        else{
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.bajarBola(filVacia-1, colVacia);
            desc = "bajar bolita de fila " + (filVacia) + " columna " + (colVacia+1);
            agregarAbierto(nActual, 2, nuevo, desc);
            
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.subirBola(filVacia+1, colVacia);
            desc = "subir bolita de fila " + (filVacia+1) + " columna " + (colVacia+1);
            agregarAbierto(nActual, 2, nuevo, desc);
        }
        
        for (Nodo n : abiertos){
            System.out.println(n.getDescMovimientoPredecesor());
            n.getEstadoTorre().printTorre();
        }
    }
    
    
    public void calcularCamino(Estado eActual){
        nodosProximos(meta);
    }
}
