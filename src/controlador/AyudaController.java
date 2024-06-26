package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 * @category CONTROLADOR AYUDA
 */
public class AyudaController implements Initializable {

	@FXML
    private TextArea parrafo;
	
	
	/**
	 * 
	 * @param event BOTON PARA CERRAR LA VENTANA
	 */
	@FXML
    void onExit(ActionEvent event) {
		Node nodo = (Node)event.getSource();
	       Stage stage = (Stage)nodo.getScene().getWindow();
	       stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.parrafo.setDisable(true);
	}
	
}
