package compartirDatos;


/**
 * CODIGO DE CLASE 1RO DAW PROGRAMACION
 */
public class DatosCompartidosSingleton {
	  private String nombre;
	  private int score;
	  
	  private final static DatosCompartidosSingleton INSTANCIA = new DatosCompartidosSingleton();
	  
	  private DatosCompartidosSingleton() {}
	  
	  public static DatosCompartidosSingleton getInstancia() {
	    return INSTANCIA;
	  }
	  
	  public void setNombre(String nombre) {
	    this.nombre = nombre;
	  }
	  
	  public String getNombre() {
	    return this.nombre;
	  }

	  public int getScore() {
		return score;
	  }

	  public void setScore(int score) {
		this.score = score;
	  }
	  
	  
}
