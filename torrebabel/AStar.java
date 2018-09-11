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
        int valH = calcH(nuevo);
        abiertos.add(new Nodo(costo, valH, nActual, desc  , nuevo));
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
            desc = "rotar fila " + (fil+1) + " a la derecha";
            int costo = nActual.getValorG() + 4;
            agregarAbierto(nActual, costo, nuevo, desc);
            
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.rotarFilaIzq(fil);
            desc = "rotar fila " + (fil+1) + " a la izquierda";
            costo = nActual.getValorG() + 4;
            agregarAbierto(nActual, costo, nuevo, desc);
        }
        
        //movimientos verticales de bolitas por muesca vacia
        temp = new char[filas][columnas]; 
        copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
        Estado nuevo = new Estado(filas, columnas, temp);
        int colVacia = nuevo.getiColMuescaVacia();
        int filVacia = nuevo.getiFilMuestaVacia();
        if (filVacia == 0){
            nuevo.subirBola(filVacia+1, colVacia);
            desc = "subir bolita de fila " + (filVacia+1) + " columna " + (colVacia+1);
            int costo = nActual.getValorG() + 2;
            agregarAbierto(nActual, 2, nuevo, desc);
        }
        else if(filVacia == meta.getEstadoTorre().getFilas()-1){
            nuevo.bajarBola(filVacia-1, colVacia);
            desc = "bajar bolita de fila " + (filVacia) + " columna " + (colVacia+1);
            int costo = nActual.getValorG() + 2;
            agregarAbierto(nActual, 2, nuevo, desc);
        }
        else{
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.bajarBola(filVacia-1, colVacia);
            desc = "bajar bolita de fila " + (filVacia) + " columna " + (colVacia+1);
            int costo = nActual.getValorG() + 2;
            
            agregarAbierto(nActual, 2, nuevo, desc);
            
            temp = new char[filas][columnas]; 
            copiarDatosTorre(temp, nActual.getEstadoTorre().getTorre());
            nuevo = new Estado(filas, columnas, temp);
            nuevo.subirBola(filVacia+1, colVacia);
            desc = "subir bolita de fila " + (filVacia+1) + " columna " + (colVacia+1);
            agregarAbierto(nActual, 2, nuevo, desc);
        }
        
        
    }
    
    
    public void calcularCamino(Estado eActual){
        int h = calcH(eActual);
        Nodo nActual = new Nodo(0, h, null, "", eActual);
        int i = 0;
        while (true){
            System.out.println("Iteracion : " +i); 
            i++;
            nodosProximos(nActual);
            int iMenor = indexAbiertoMenorF();
            Nodo menor = abiertos.get(iMenor);
            abiertos.remove(iMenor);
            if (menor.getEstadoTorre().isTorreIgual(meta.getEstadoTorre())){
                printCamino(menor);
                break;
            }
            nActual = menor;
        }
        
    }
    
    public void printCamino(Nodo n){
        while(n != null){
            n.getEstadoTorre().printTorre();
            System.out.println(n.getDescMovimientoPredecesor());
            System.out.println();
            printCamino(n.getPredecesor());
            break;
        }
    }
}
