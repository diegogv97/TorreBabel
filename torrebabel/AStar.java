package torrebabel;


import java.util.ArrayList;

public class AStar {
    private Nodo meta;
    private ArrayList<Nodo> abiertos = new ArrayList<>();
    private ArrayList<Nodo> cerrados = new ArrayList<>();
    
    public AStar(Estado meta){
        this.meta = new Nodo(meta);
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
    
    
    public void calcularCamino(Estado eActual){
        
    }
    
    private void nodosProximos(Nodo nActual){
        Estado eActual = nActual.getEstadoTorre();
        Estado temp = eActual;
        int filas = eActual.getFilas();
        for(int fil = 0; fil < filas-1; fil++){
            
            temp.rotarFilaDer(fil);
            abiertos.add(new Nodo(4, nActual, "rotar fila" + fil + "a la derecha"  , temp));
            temp = eActual;
           
            temp.rotarFilaIzq(fil);
            abiertos.add(new Nodo(4, nActual, "rotar fila" + fil + "a la izquierda", temp));
            temp = eActual;
            
        }
    }
}
