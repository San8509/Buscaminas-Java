package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * @author R.Santiago Velazquez 1ro DAW
 * @version 1.0 Star Wars
 * 
 * Juego Buscaminas VERSION PROPIA Star Wars; 
 *  NOTA1 !!
 *  -antes de abrir conectate a la Base de Datos para que el login sea efectivo
 *  
 *  Desarrollo.
 *  -Al abrir el juego saldra una ventana emergente para loguearse antes de comenzar asi quedara registrado el jugador
 *  para que gane o pierda se le asinen los puntos 
 *  -tiene 3 niveles de dificultad y uno para resetear y continuar jugando si asi lo desea el jugador
 *  -El boton AYUDA mostrara una ventana de ayuda explicando el sistema del juego
 *  -El boton SCORE mostrara una venta en la cual puedes consultar tu putuacion pasandole u nombre de jugador
 *  
 *  Nota: No hay otro igual en internet, me base en Star Wars para darle un rollo diferente,
 *  para llegar hasta donde esta busque mucha informacion en internet, tutoriales en Youtuve, GitHub, StackOverFlow, 
 *  chatgpt en dudas puntuales y otra gran ayuda es la documentacion en clase que nos has dado.
 *  no es un gran juego pq estoy aprendiendo, pero espero y sea de tu agrado 
 *  
 *   NOTA2: trate de modularizarlo todo lo que pude 
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/Juego.fxml"));
			Scene scene = new Scene(root,420,620);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("RSV_BUSCAMINAS_JAVAFX");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		/**
		 * decidi poner el login aqui para que me lo muestre antes del juego asi el jugador podra 
		 * loguearse y se guarde su nombre en la clase singelton asi gane o pierda se guardaran directamete los datos en la BD 
		 */
		try {
       	 Stage stage = new Stage();
       	 AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));
			 Scene scene = new Scene(root, 450, 250);
			 scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			 stage.setScene(scene);
			 stage.setTitle("LOGIN");
			 stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
