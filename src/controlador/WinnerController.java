package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import compartirDatos.DatosCompartidosSingleton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Santiago CONTROLADOR VENTANA WINNER
 */
public class WinnerController implements Initializable {

	@FXML
	private Button salir;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		salir.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ev) {
				 Node nodo = (Node)ev.getSource();
			       Stage stage = (Stage)nodo.getScene().getWindow();
			       stage.close();	 
			} 
		});
		
		
	}

}
