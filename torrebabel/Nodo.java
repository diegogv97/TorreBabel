package torrebabel;

public class Nodo {
    private int valorH;
    private int valorG;
    private int valorF;
    private Nodo predecesor;
    private tipoMovimiento movmientoPredecesor;
    private int filaMovPredecesor;
    private int colMovPredecesor;
    private Estado estadoTorre;

    public Nodo(int valorG,int valorH, Nodo predecesor,  tipoMovimiento movmientoPredecesor,  
            Estado estadoTorre, int filaMovPredecesor, int colMovPredecesor) {
        this.valorG = valorG;
        this.valorH = valorH;
        this.valorF = valorG + valorH;
        this.predecesor = predecesor;
        this.movmientoPredecesor = movmientoPredecesor;
        this.filaMovPredecesor = filaMovPredecesor;
        this.colMovPredecesor = colMovPredecesor;
        this.estadoTorre = estadoTorre;
    }
    
    public Nodo(Estado estadoTorre) {
        this.valorG = 0;
        this.predecesor = null;
        this.movmientoPredecesor = null;
        this.estadoTorre = estadoTorre;
    }

    public int getValorH() {
        return valorH;
    }

    public void setValorH(int valorH) {
        this.valorH = valorH;
        this.valorF = this.valorG + this.valorH;
    }

    public int getValorG() {
        return valorG;
    }

    public void setValorG(int valorG) {
        this.valorG = valorG;
        this.valorF = this.valorG + this.valorH;
    }

    public int getValorF() {
        return valorF;
    }

    public Nodo getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Nodo predecesor) {
        this.predecesor = predecesor;
    }

    public Estado getEstadoTorre() {
        return estadoTorre;
    }

    public void setEstadoTorre(Estado estadoTorre) {
        this.estadoTorre = estadoTorre;
    }

    public tipoMovimiento getMovmientoPredecesor() {
        return movmientoPredecesor;
    }

    public void setMovmientoPredecesor(tipoMovimiento movmientoPredecesor) {
        this.movmientoPredecesor = movmientoPredecesor;
    }

    public int getFilaMovPredecesor() {
        return filaMovPredecesor;
    }

    public void setFilaMovPredecesor(int filaMovPredecesor) {
        this.filaMovPredecesor = filaMovPredecesor;
    }

    public int getColMovPredecesor() {
        return colMovPredecesor;
    }

    public void setColMovPredecesor(int colMovPredecesor) {
        this.colMovPredecesor = colMovPredecesor;
    }

    
    
}
