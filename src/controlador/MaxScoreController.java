package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import compartirDatos.DatosCompartidosSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelos.ConexionDB;

/**
 * @author Santiago CONTROLADOR VENTANA DE PUNTUACION
 */
public class MaxScoreController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private Label score;
    
    @FXML
    private Button btnConsulta;

    @FXML
    void consultar(ActionEvent event) {
    }

    /**
     * 
     * @param event  BOTON PARA CERRAR LA VENTANA 
     */
    @FXML
    void onExit(ActionEvent event) {
    	Node nodo = (Node)event.getSource();
        Stage stage = (Stage)nodo.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onTextFieldName(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		btnConsulta.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				/**
				 * CONEXION A BD PARA CONSULTAR LA PUTUACION
				 */
				DatosCompartidosSingleton dcs = DatosCompartidosSingleton.getInstancia();
				 ConexionDB dB = new ConexionDB();
				 int sre= dB.consultaScore(name.getText());
				 score.setText(String.valueOf(sre));
			} 
		});
	}

}
