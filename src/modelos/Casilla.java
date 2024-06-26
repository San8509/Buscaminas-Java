package modelos;

/**
 * @author Santiago 
 * CLASE CASILLA 
 */
public class Casilla {

	    private int posFila;
	    private int posColumna;
	    private boolean mina;
	 
	    /**
	     * posicion de la fila y la columna de la casilla 
	     * @param posFila
	     * @param posColumna
	     */
	    public Casilla(int posFila, int posColumna) {
	        this.posFila = posFila;
	        this.posColumna = posColumna;
	    }

	    public int getPosFila() {
	        return this.posFila;
	    }

	    public void setPosFila(int posFila) {
	        this.posFila = posFila;
	    }

	    public int getPosColumna() {
	        return this.posColumna;
	    }

	    public void setPosColumna(int posColumna) {
	        this.posColumna = posColumna;
	    }

	    public boolean isMina() {
	        return this.mina;
	    }

	    public void setMina(boolean mina) {
	        this.mina = mina;
	    }  

}
