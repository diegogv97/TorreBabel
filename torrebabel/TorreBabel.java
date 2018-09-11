
package torrebabel;

public class TorreBabel {

    public static void main(String[] args) {
        char torre[][] = new char[5][4];
        
        
        torre[0][0] = 'n';
        torre[0][1] = 's';
        torre[0][2] = 'n';
        torre[0][3] = 'n';
       
        torre[1][0] = 'a';
        torre[1][1] = 'e';
        torre[1][2] = 'd';
        torre[1][3] = 'f';
       
        torre[2][0] = 'q';
        torre[2][1] = 'w';
        torre[2][2] = '3';
        torre[2][3] = 'r';
        
        torre[3][0] = 'z';
        torre[3][1] = 'x';
        torre[3][2] = 'c';
        torre[3][3] = 'v';
        
        torre[4][0] = 'j';
        torre[4][1] = 'k';
        torre[4][2] = 'l';
        torre[4][3] = 'ñ';
        
        char torre2[][] = new char[5][4];
        
        
        torre2[0][0] = 'n';
        torre2[0][1] = 'e';
        torre2[0][2] = 'n';
        torre2[0][3] = 'n';
       
        torre2[1][0] = 'a';
        torre2[1][1] = 's';
        torre2[1][2] = 'd';
        torre2[1][3] = 'f';
       
        torre2[2][0] = 'q';
        torre2[2][1] = 'w';
        torre2[2][2] = '3';
        torre2[2][3] = 'r';
        
        torre2[3][0] = 'c';
        torre2[3][1] = 'v';
        torre2[3][2] = 'z';
        torre2[3][3] = 'x';
        
        torre2[4][0] = 'j';
        torre2[4][1] = 'k';
        torre2[4][2] = 'l';
        torre2[4][3] = 'ñ';

        Estado eInicial = new Estado(5,4, torre);
        eInicial.printTorre();
        Estado eFinal = new Estado(5,4, torre2);
        eFinal.printTorre();
        AStar aEstrella = new AStar(eFinal);
        aEstrella.calcularCamino(eInicial);
        
    }
    
}

/*
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

*/