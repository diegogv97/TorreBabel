package torrebabel;

public class Nodo {
    private int valorH;
    private int valorG;
    private int valorF;
    private Nodo predecesor;
    private String descMovimientoPredecesor = "";
    private Estado estadoTorre;

    public Nodo(int valorG, Nodo predecesor, String descMovimientoPrececesor,  
            Estado estadoTorre) {
        this.valorG = valorG;
        this.predecesor = predecesor;
        this.descMovimientoPredecesor = descMovimientoPrececesor;
        this.estadoTorre = estadoTorre;
    }
    
    public Nodo(Estado estadoTorre) {
        this.valorG = 0;
        this.predecesor = null;
        this.descMovimientoPredecesor = "";
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

    public String getDescMovimientoPredecesor() {
        return descMovimientoPredecesor;
    }

    public void setDescMovimientoPredecesor(String descMovimientoPredecesor) {
        this.descMovimientoPredecesor = descMovimientoPredecesor;
    }
    
    
    
}
