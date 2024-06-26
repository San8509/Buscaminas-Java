package modelos;

/**
 * TABLERO BUSCAMINAS
 */
public class TableroBuscaminas {

	    public Casilla[][] casillas;
	    int numFilas;
	    int numColumnas;
	    int numMinas;
	     
	    /**
	     * 
	     * @param filas
	     * @param columnas
	     * @param numMinas
	     */
	    public TableroBuscaminas(int filas, int columnas, int numMinas) {
	        this.numFilas = filas;
	        this.numColumnas = columnas;
	        this.numMinas=numMinas;
	        this.inicializarCasillas();
	    }
	    
	    /**
	     * inicializo el atributo matriz de la clase casilla y le genero las minas
	     * y genero las instancias 
	     */
	    public void inicializarCasillas(){
	        casillas=new Casilla[10][10];
	        for (int i = 0; i < casillas.length; i++) {
	            for (int j = 0; j < casillas[i].length; j++) {
	                casillas[i][j]=new Casilla(i, j);//nueva instancia de la casilla
	            }
	        }
	        generarMinas();
	    }
	   
	    /**
	    * metodo para generar las minas aleatoriamente hasta que se cumpla la condicion de la 
	    * cantidad de minas entradas en el constructor
	    */
	    private void generarMinas(){
	        int minasGeneradas=0;
	        while(minasGeneradas!= this.numMinas){
	            int posicionTmpFila=(int)(Math.random()* this.casillas.length);//genero los numeros aleatorios hasta el maximo de la longitud de la matriz 
	            int posicionTmpColumna=(int)(Math.random()* this.casillas[0].length);
	            
	            //compruebo si hay minas en la casilla
	            if (!casillas[posicionTmpFila][posicionTmpColumna].isMina()){
	                casillas[posicionTmpFila][posicionTmpColumna].setMina(true);//introduzco la mina en la casilla
	                minasGeneradas++;
	            }
	        }
	    }
	    
	    public void imprimirTablero() {
	        for (int i = 0; i < this.casillas.length; i++) {
	            for (int j = 0; j < this.casillas[i].length; j++) {
	                System.out.print(this.casillas[i][j].isMina()?"*":"0");
	            }
	            System.out.println("");
	        }
	        System.out.println();
	    }
}
