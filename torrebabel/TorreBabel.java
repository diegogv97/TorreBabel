
package torrebabel;

public class TorreBabel {

    public static void main(String[] args) {
        char torre[][] = new char[5][4];
        torre[0][1] = 'n';
        torre[0][0] = 'e';
        torre[0][2] = 'n';
        torre[0][3] = 'n';
       
        torre[1][0] = 'w';
        torre[1][1] = 'w';
        torre[1][2] = 'w';
        torre[1][3] = 'w';
        
        torre[2][0] = 'b';
        torre[2][1] = 'b';
        torre[2][2] = 'b';
        torre[2][3] = 'b';
        
        torre[3][0] = 'g';
        torre[3][1] = 'g';
        torre[3][2] = 'g';
        torre[3][3] = 'g';
        
        torre[4][0] = 'y';
        torre[4][1] = 'y';
        torre[4][2] = 'y';
        torre[4][3] = 'y';
        
        Estado eInicial = new Estado(5,4);
        eInicial.setTorre(torre);
        AStar aEstrella = new AStar(eInicial);
        eInicial.printTorre();
        eInicial.bajarBola(1, 3);
        eInicial.printTorre();
        
    }
    
}
