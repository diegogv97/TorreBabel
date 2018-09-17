package torrebabel;

import java.util.Arrays;
import java.util.Collections;

public class Estado {
    private char torre[][];
    private int filas;
    private int columnas;
    private int iColMuescaVacia;
    private int iFilMuestaVacia;
    
    
    public Estado(int filas, int columnas, char[][] torre){
        this.filas = filas;
        this.columnas = columnas;
        this.torre = torre;
        actualizarMuescaVacia();
    }

    public int getiColMuescaVacia() {
        return iColMuescaVacia;
    }

    public int getiFilMuestaVacia() {
        return iFilMuestaVacia;
    }

    public char[][] getTorre() {
        return torre;
    }

    public void setTorre(char[][] torre) {
        this.torre = torre;
        actualizarMuescaVacia();
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
        
    public void printTorre(){
        for(int fil = 0; fil < filas; fil++){
            for(int col = 0; col< columnas; col++){
                System.out.print(torre[fil][col]+ "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public String torreToString(){
    	String torreS = "";
    	
    	for(int fil = 0; fil < filas; fil++){
            for(int col = 0; col < columnas; col++){
            	torreS += torre[fil][col] + "\t";
            }
            torreS += "\n";
        }
    	torreS += "\n";
        
        return torreS;
    }
  
    public void rotarFilaDer(int indexFila){
        char temp[] = new char[columnas];
        for(int i = 0; i < columnas-1; i++){
            temp[i+1] = torre[indexFila][i];
        }
        temp[0] = torre[indexFila][columnas-1];
        torre[indexFila] = temp;
        actualizarMuescaVacia();
    }
    
    
    public void rotarFilaIzq(int indexFila){
        char temp[] = new char[columnas];
        for (int i = 0; i<columnas; i++)
            temp[i] = 'e';
        for (int i = columnas-1; i > 0; i--){
            temp[i-1] = torre[indexFila][i];
        }
        temp[columnas-1] = torre[indexFila][0];
        torre[indexFila] = temp;
        actualizarMuescaVacia();
    }
    

    public void bajarBola(int iFil, int iCol){
        char temp = torre[iFil+1][iCol];
        torre[iFil+1][iCol] = torre[iFil][iCol];
        torre[iFil][iCol] = temp;
        actualizarMuescaVacia();
    }
    
    public void subirBola(int iFil, int iCol){
        char temp = torre[iFil-1][iCol];
        torre[iFil-1][iCol] = torre[iFil][iCol];
        torre[iFil][iCol] = temp;
        actualizarMuescaVacia();
    }
    
    
    private void actualizarMuescaVacia(){
        for (int fil = 0; fil < filas; fil++){
            for (int col = 0; col < columnas; col++){
                
                if(torre[fil][col] == 'e'){
                    iColMuescaVacia = col;
                    iFilMuestaVacia = fil;
                    break;
                }
                
            }
        }
    }
    
     public boolean isTorreIgual(Estado e){
        for (int fil = 0; fil < this.filas; fil++){
            for (int col = 0; col < this.columnas; col++){
                
                if(e.getTorre()[fil][col] != this.torre[fil][col]){
                    return false;
                }
                
            }
        }
        return true;
    }
     
     
}
