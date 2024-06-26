package modelos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Santiago
 * clase imagen para poner la imagens en en juego
 */
public class Imagen {

	ImageView imageDerecha;
	ImageView imageIzquierda;
	
	
	public Imagen() {
		this.imageDerecha=new ImageView();
		this.imageIzquierda=new ImageView();
	}
	
	
	/**
	 * metodo que al reinciar pone las imagenes que habian en el panel de inico del juego
	 * @param imageDerecha
	 * @param imageIzquierda
	 * @throws FileNotFoundException
	 */
	public void imageStart(ImageView imageDerecha, ImageView imageIzquierda) throws FileNotFoundException {
		 //reinicio
		String clear = "recursos/C3POsolo.jpeg";
	    Path restart =  Paths.get(clear);
		FileInputStream input = new FileInputStream(restart.toString()); 
	    Image img = new Image(input);
	    imageDerecha.setImage(img);
	    //imagen de Vader
	    String error = "recursos/DarthVader1.jpeg";
	    Path bomba =  Paths.get(error);
		FileInputStream input1 = new FileInputStream(bomba.toString()); 
		Image imgChico = new Image(input1);
		imageIzquierda.setImage(imgChico);
	}
	
	/**
	 * metodo que cambia las imagenes del panel de inicio cuando el juador ha perido
	 * @param imageDerecha
	 * @param imageIzquierda
	 * @throws FileNotFoundException
	 */
	public void imagenGameOver(ImageView imageDerecha, ImageView imageIzquierda) throws FileNotFoundException {
		 //Vader
		String vader = "recursos/Vader.jpeg";
	    Path vaderSentado =  Paths.get(vader);
		FileInputStream input = new FileInputStream(vaderSentado.toString()); 
	    Image img = new Image(input);
	    imageDerecha.setImage(img);	
	    //imagen de Ovi
	    String maestro = "recursos/maestro.jpeg";
	    Path ovi =  Paths.get(maestro);
		FileInputStream input1 = new FileInputStream(ovi.toString()); 
		Image jedi = new Image(input1);
		imageIzquierda.setImage(jedi);
	}
	
	/**
	 * metodo que pone la imagen de la mina
	 * @return imageview
	 * @throws FileNotFoundException
	 */
	public ImageView imageMina() throws FileNotFoundException {
		String rutaIcono = "recursos/explosion2.jpeg";
		Path ruta =  Paths.get(rutaIcono);
		FileInputStream inputstream = new FileInputStream(ruta.toString()); 
		Image imagen = new Image(inputstream);
	    ImageView imageView = new ImageView(imagen);
	    imageView.setFitHeight(40); 
		imageView.setFitWidth(40);
		return imageView;
	}
	
	/**
	 * metodo que pone la imagen de la nave en las casillas
	 * @return imageview
	 * @throws FileNotFoundException
	 */
	public ImageView imageCasillas() throws FileNotFoundException {
		String rutaIcono = "recursos/x-wing.jpeg";
		Path ruta =  Paths.get(rutaIcono);
		FileInputStream inputstream = new FileInputStream(ruta.toString()); 
		Image imagen = new Image(inputstream);
       ImageView imageView = new ImageView(imagen);
       imageView.setFitHeight(40); 
       imageView.setFitWidth(40);
      return imageView;
	}
	
	
	
	
}
