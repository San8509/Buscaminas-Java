package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import compartirDatos.DatosCompartidosSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelos.ConexionDB;


/**
 * @author Santiago CONTROLADOR VENTANA LOGIN
 */
public class LoginController implements Initializable {

    @FXML
    private TextField nombre;
    
    @FXML
    private Button botton;
   
    /**
     * 
     * @param event  BOTON PARA CERRAR LA VENTANA
     */
    @FXML
    void salida(ActionEvent event) {
    	   Node nodo = (Node)event.getSource();
	       Stage stage = (Stage)nodo.getScene().getWindow();
	       stage.close();
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		botton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent ev) {
				DatosCompartidosSingleton dcs = DatosCompartidosSingleton.getInstancia();
				dcs.setNombre(nombre.getText());
				nombre.setText(null);
				 nombre.setEditable(false);
				
				 Node nodo = (Node)ev.getSource();
			       Stage stage = (Stage)nodo.getScene().getWindow();
			       stage.close();	 
			} 
		});
		
	}

}
