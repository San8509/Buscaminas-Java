package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import compartirDatos.DatosCompartidosSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelos.ConexionDB;
import modelos.Imagen;
import modelos.Score;
import modelos.TableroBuscaminas;


/**
 * @author Santiago
 * @category controlador
 */
public class JuegoController implements Initializable {
	
	  
	    @FXML
	    private GridPane gridPane;
  
	    @FXML
	    private Text score;
	    
	    @FXML
	    private ImageView imageDerecha;

	    @FXML
	    private ImageView imageIzquierda;
	    
	    @FXML
	    TableroBuscaminas tablero;
	    
	    @FXML
	    Button btn;
	    
	    @FXML
	    private ComboBox<String> nivel;
	    
	    @FXML
	    Score puntuacion;
	   
	    @FXML
	    Imagen image = new Imagen();
	   
	    /**
	     * 
	     * @param event  ventana de ayuda
	     */
	    @FXML
	    void ayuda(ActionEvent event) {
	    	try {
	        	 Stage stage = new Stage();
	        	 AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/Ayuda.fxml"));
				 Scene scene = new Scene(root, 450, 450);
				 scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				 stage.setScene(scene);
				 stage.setTitle("AYUDA");
				 stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    /**
	     * 
	     * @param event  ventana para consultar la puntuacion
	     * 	      
	     */
	    @FXML
	    void showPuntuacion(ActionEvent event) {
	    	try {
	        	 Stage stage = new Stage();
	        	 AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/MaxScore.fxml"));
				 Scene scene = new Scene(root, 480, 250);
				 scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				 stage.setTitle("SCORE");
				 stage.setScene(scene);
				 stage.show();	 
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	     
	    /**
	     * atributos del controlador
	     */
	    @FXML
	    private boolean mina=false;
	    private int contador=0;
	    private int vueltas=0;
	     
	    /**
	     * DESARROLLO DEL JUEGO
	     */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {		
			 ObservableList<String> listDificultad = FXCollections.observableArrayList();
			 listDificultad.addAll("Principiante", "Intermedio", "Avanzado", "Clear");
			 this.nivel.setItems(listDificultad);
		
			 /**
			  * @author Santiago   selector del nivel del juego
			  */
			 this.nivel.setOnAction(event ->{
				 //*************************LIMPIAR TABLERO*********************************
				 if(nivel.getSelectionModel().getSelectedItem().toString().equals("Clear")) {
					 clear();
				 }	    
			     //*************************PRINCIPIANTE*********************************
			     if(nivel.getSelectionModel().getSelectedItem().toString().equals("Principiante")) {
			    	 int minas = 8;
			    	 nivelJuego(minas);
			     //***********INTERMEDIO***************************************    
			     }else if(nivel.getSelectionModel().getSelectedItem().toString().equals("Intermedio")) {
			    	int minas=14;
			    	 nivelJuego(minas);  
			     //***********************AVANZADO***************** ***   
			     }else if(nivel.getSelectionModel().getSelectedItem().toString().equals("Avanzado")) {
			    	 int minas=20; 
			    	 nivelJuego(minas);
			     }
			 }); 
		}
		
		
		/**
		 * ***METODO AL QUE SE LE PASA UN NIVEL*****
		 * @param nivel
		 */
		public void nivelJuego(int nivel) {
			 tablero = new TableroBuscaminas(10, 10, nivel); 
			 tablero.imprimirTablero(); 
			
			 gridPane.getChildren().forEach((Node node) -> {   
		         //registro los eventos al presionar un boton en el grid
				 node.setOnMouseClicked(new EventHandler<MouseEvent>() {
					 
		        	@Override
					public void handle(MouseEvent e) {		
						int filas =  (GridPane.getRowIndex(node) == null) ? 0 : GridPane.getRowIndex(node);
				        int columnas =  (GridPane.getColumnIndex(node) == null) ? 0 : GridPane.getColumnIndex(node);  
				        /**
				         * saque los if del bucle y elimine el bucle pq a parte de no tener sentido recorrerlo dos veces
				         * ya que accedo directamente a los indices mediante el evento del boton qu presione y a parte me 
				         * ponia el juego super lento
				         */
							if((tablero.casillas[filas][columnas].isMina())) {
								nodoMina(node, columnas, filas);
								vueltas++;
								mina=true;
								insertarDatos();				
							} else {
							  try {
					     	  //columna anterior
							  if(columnas -2 >= 0 && !tablero.casillas[filas][columnas-1].isMina()) {
								 nodoAnterior(node, columnas, filas);
						         incrementarContador();    
						      } 
							  //columna siguiente
							  if(columnas +2 < tablero.casillas.length && !tablero.casillas[filas][columnas+1].isMina()) {
								nodoPosterior(node, columnas, filas);
								incrementarContador();    
							  }
						      //fila superior
							  if(filas - 2 >=0 && !tablero.casillas[filas-1][columnas].isMina()) {
								nodoSuperior(node, columnas, filas);
								incrementarContador();    
							  }
							  //fila inferior
							  if(filas + 2 < tablero.casillas.length && !tablero.casillas[filas+1][columnas].isMina()) {
							    nodoInferior(node, columnas, filas);
							    incrementarContador();    
							  }		
						//**NODO INDICE**	  
						 nodoIndice(node, columnas, filas);
						 incrementarContador(); 	 		 
						 } catch (Exception e1) {
						 System.out.println(e1);
						 e1.printStackTrace();
						 } 
					}	
				  }
		       });
	        });
	     }
	 	
		/**
		 * //********LIMPIAR TABLERO***********************
		 */
		public void clear() {
			 try {
				gridPane.getChildren().clear();
				this.score.setText("0000");
				contador=0;
				this.image.imageStart(this.imageDerecha, this.imageIzquierda);
				
				gridPane.setStyle("-fx-background-color: black;");
				tablero = new TableroBuscaminas(10, 10, 0); 
				tablero.imprimirTablero();
				   
				 for(int i=0; i < tablero.casillas.length; i++) {
					for(int j=0; j < tablero.casillas[i].length; j++) {
					   Button buton = new Button();
					   buton.setStyle("-fx-background-color: #D2BF7E; -fx-border-color: #797878; -fx-border-width: 0.5px;");
					   buton.setPrefHeight(40);
					   buton.setPrefWidth(40);
					   gridPane.add(buton, j, i);
					   gridPane.setVisible(true);
					   gridPane.setDisable(false);	   
					}
				 }		 	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * CUANDO LA PUNTUACION ALCANCE LOS 8000 PTS GANA 
		 * @param score
		 */
		public void ganador(int score) {
			if(score == 8000) {
				insertarDatos();
				try {
		        	 Stage stage = new Stage();
		        	 AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/winer.fxml"));
					 Scene scene = new Scene(root, 450, 250);
					 scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
					 stage.setTitle("WINER");
					 stage.setScene(scene);
					 stage.show();	 	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 		
		
		/**
		 * ** NODO MINA*****
		 * cambio de imagenes y puesta la explosion en la celda
		 * @param node
		 * @param columnas
		 * @param filas
		 */
		public void nodoMina(Node node, int columnas, int filas ) {
			try {
			gridPane.getChildren().remove(node);
			imageMina(columnas, filas);
			imagenGameOver();
			}catch(Exception ex) {
				System.out.println(ex);
		    }
	    }
	    
		
		/**
		 * //**************IMAGEN EN LOS NODOS********************************************************************
		 * @param node
		 * @param columnas
		 * @param filas
		 * @throws FileNotFoundException
		 * 
		 */
		public void nodoAnterior(Node node, int columnas, int filas) throws FileNotFoundException {//nodo anterior
			gridPane.getChildren().remove(node);
			imageCasillas(columnas-1, filas);
		}
		
		public void nodoPosterior(Node node, int columnas, int filas) throws FileNotFoundException {//nodo posterior
			gridPane.getChildren().remove(node);
			imageCasillas(columnas+1, filas);
		}
		
		public void nodoSuperior(Node node, int columnas, int filas) throws FileNotFoundException {//nodo superior
			gridPane.getChildren().remove(node);
			imageCasillas(columnas, filas-1); 
		}
		
		public void nodoInferior(Node node, int columnas, int filas) throws FileNotFoundException {//nodo inferior
			gridPane.getChildren().remove(node);
			imageCasillas(columnas, filas+1);
		}
		
		public void nodoIndice(Node node, int columnas, int filas) throws FileNotFoundException {//nodo indice
			gridPane.getChildren().remove(node);
			imageCasillas(columnas, filas);
		}
		
	   
		/**
		 * //*******IMAGENES******************************
		 * @throws FileNotFoundException
		 */
		public void imagenGameOver() throws FileNotFoundException {//imagenes cuando pierdes de vader y ovi
			this.image.imagenGameOver(imageDerecha, imageIzquierda);
		}
		
		public void imageMina(int columnas, int filas) throws FileNotFoundException {// imagen de las minas
			gridPane.add(this.image.imageMina(), columnas, filas);  
		    gridPane.setDisable(true);
		}
		
		public void imageCasillas(int columnas, int filas) throws FileNotFoundException {//imagen de las casillas
	        gridPane.add(this.image.imageCasillas(), columnas, filas);
		}
		
		
		/**
		 * **********CONTADOR*******************************	
		 * @return
		 */
		private int incrementarContador() {//cada casilla sin mina contara +50
		   contador+=50;
           score.setText(String.valueOf(contador));
           ganador(contador);  
         return contador;
        }
		 
		 /**
		  * *******INSERTAR DATOS A LA BD********************
		  */
		 public void insertarDatos() {//insertar datos a la BD 
			 if(mina == true && vueltas == 1) {
            	DatosCompartidosSingleton dcs = DatosCompartidosSingleton.getInstancia();
     			ConexionDB dB = new ConexionDB();
     			dB.envioDatos(dcs.getNombre(), contador, dB.getServidor(), dB.getUsuario(), dB.getPassword());
     			
             }else if (mina == true && vueltas > 1) {
            	DatosCompartidosSingleton dcs = DatosCompartidosSingleton.getInstancia();
      			ConexionDB dB = new ConexionDB();
      			System.out.println(dcs.getNombre()+" "+contador);
      			dB.actualizarJugador(dcs.getNombre(), contador);
             }
		 }
		
		 /**
		  *  *********SALIDA DEL JUEGO*********
		  * @param event
		  */
		 @FXML
		    void exit(ActionEvent event) {
	             System.exit(0);
		    }
		 
}

