
package torrebabel;

public class TorreBabel {
	private char torreI[][] = new char[5][4];
	private char torreF[][] = new char[5][4];
	private Estado eInicial;
	private Estado eFinal;

	public TorreBabel(char[][] torreI, char[][] torreF) {
		this.torreI = torreI;
		this.torreF = torreF;
		eInicial = new Estado(5,4, torreI);
        eInicial.printTorre();
        eFinal = new Estado(5,4, torreF);
        eFinal.printTorre();
	}
	
	public void jugar(){
		
        AStar aEstrella = new AStar(eFinal);
        aEstrella.calcularCamino(eInicial);
        aEstrella.printCamino(aEstrella.getMeta());
	}

	public char[][] getTorreI() {
		return torreI;
	}

	public void setTorreI(char[][] torreI) {
		this.torreI = torreI;
	}

	public char[][] getTorreF() {
		return torreF;
	}

	public void setTorreF(char[][] torreF) {
		this.torreF = torreF;
	}

	public Estado geteInicial() {
		return eInicial;
	}

	public void seteInicial(Estado eInicial) {
		this.eInicial = eInicial;
	}

	public Estado geteFinal() {
		return eFinal;
	}

	public void seteFinal(Estado eFinal) {
		this.eFinal = eFinal;
	}
	
	
    /*public static void main(String[] args) {
        char torre[][] = new char[5][4];
        
        torre[0][0] = 'n';
        torre[0][1] = 'a';
        torre[0][2] = 'n';
        torre[0][3] = 'n';
       
        torre[1][0] = 'e';
        torre[1][1] = 'a';
        torre[1][2] = 'a';
        torre[1][3] = 'v';
       
        torre[2][0] = 'y';
        torre[2][1] = 'v';
        torre[2][2] = 'v';
        torre[2][3] = 'a';
        
        torre[3][0] = 'y';
        torre[3][1] = 'v';
        torre[3][2] = 'w';
        torre[3][3] = 'y';
        
        torre[4][0] = 'w';
        torre[4][1] = 'y';
        torre[4][2] = 'w';
        torre[4][3] = 'w';
        
        char torre2[][] = new char[5][4];
        
        
        torre2[0][0] = 'n';
        torre2[0][1] = 'e';
        torre2[0][2] = 'n';
        torre2[0][3] = 'n';
       
        torre2[1][0] = 'a';
        torre2[1][1] = 'a';
        torre2[1][2] = 'a';
        torre2[1][3] = 'a';
       
        torre2[2][0] = 'v';
        torre2[2][1] = 'v';
        torre2[2][2] = 'v';
        torre2[2][3] = 'v';
        
        torre2[3][0] = 'y';
        torre2[3][1] = 'y';
        torre2[3][2] = 'y';
        torre2[3][3] = 'y';
        
        torre2[4][0] = 'w';
        torre2[4][1] = 'w';
        torre2[4][2] = 'w';
        torre2[4][3] = 'w';

        Estado eInicial = new Estado(5,4, torre);
        eInicial.printTorre();
        Estado eFinal = new Estado(5,4, torre2);
        eFinal.printTorre();
        AStar aEstrella = new AStar(eFinal);
        aEstrella.calcularCamino(eInicial);
        aEstrella.printCamino(aEstrella.getMeta());
    }*/
    
}

