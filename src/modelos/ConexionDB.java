package modelos;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Santiago
 * CODIGO DE CLASE
 */
public class ConexionDB {

	private String servidor;
    private String usuario;
    private String password;
    private String driver;
    private Connection con;
	
	/**
	 * contructor
	 */
    public ConexionDB() {
    	this.servidor="jdbc:mysql://localhost:3306/Buscaminas";
    	this.usuario="root";
    	this.password="";
    	this.driver="com.mysql.cj.jdbc.Driver";
    	this.conectar();
    }
    
    
    //getters
    public Connection getConnection() {
        return con;
    }
    public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	
	//setters
	public void setPassword(String password) {
		this.password = password;
	}
    public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}


	/**
	 * metodo que conecta la clase a la BD
	 */
	public void conectar() {	
    	try { 
            Class.forName(this.driver);
            this.con = DriverManager.getConnection(this.servidor, this.usuario, this.password); 
            System.out.println("conectada");
 
        } catch (ClassNotFoundException e) { 
            System.out.println("No se pudo cargar el driver JDBC"); 
            e.printStackTrace();
            
        } catch (SQLException e) {  
            System.out.println("Error al conectarse a la base de datos:");
            e.printStackTrace();
            
        } 
    }
	
	/**
	 * metodo para enviar los datos a la BD
	 * @param name
	 * @param i
	 * @param url
	 * @param usuario
	 * @param password
	 */
    public void envioDatos(String name, int i, String url, String usuario, String password) {
		
		PreparedStatement ps = null;
		Connection conn = null;  
        ResultSet rs = null;
      
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password); 
            System.out.println("conectada");
            
            String query = "INSERT INTO Buscaminas (nombre, score) VALUES ('"+name+"', '"+i+"')";
            
            Statement p = conn.createStatement();
            int num = p.executeUpdate(query);

            if (num > 0) {
                System.out.println("Datos insertados correctamente.");
            } else {
                System.out.println("Error al insertar datos.");
            }

        } catch (ClassNotFoundException e) { 
            System.out.println("No se pudo cargar el driver JDBC"); 
            e.printStackTrace();
            
        } catch (SQLException e) {  
            System.out.println("Error al conectarse a la base de datos:");
            e.printStackTrace();
            
        } finally {  
            try { 
                if (rs != null) { 
                    rs.close(); 
                } 
                if (ps != null) { 
                    ps.close(); 
                } 
                if (conn != null) { 
                    conn.close(); 
                } 
            } catch (SQLException e) { 
                System.out.println(e); 
            } 
        }
		
	}
    
    
    /**
     * metodo para consultar la puntuacion n la BD
     * @param name
     * @return
     */
    public int consultaScore(String name) {
    	int puntuacion=0;
    	PreparedStatement ps = null;
		Connection conn = null;  
        ResultSet rs = null;
      
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(servidor, usuario, password); 
            System.out.println("conectada");
            
            String query = "SELECT b.score FROM Buscaminas b WHERE b.nombre LIKE '"+name+"'";
            
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery(); 

            // Mostrar los datos de las personas accediendo a los campos 
            while (rs.next()) { 
                       puntuacion = rs.getInt("score");           
            } 

        } catch (ClassNotFoundException e) { 
            System.out.println("No se pudo cargar el driver JDBC"); 
            e.printStackTrace();
            
        } catch (SQLException e) {  
            System.out.println("Error al conectarse a la base de datos:");
            e.printStackTrace();
            
        } finally {  
            try { 
                if (rs != null) { 
                    rs.close(); 
                } 
                if (ps != null) { 
                    ps.close(); 
                } 
                if (conn != null) { 
                    conn.close(); 
                } 
            } catch (SQLException e) { 
                System.out.println(e); 
            } 
        }
        return puntuacion;
    }

  
    /**
     * metodo para actualizar la puntuacion del jugador si desea resetear el juego por si ha perdido
     * @param name
     * @param score
     */
    public void actualizarJugador(String name, int score) {
    	PreparedStatement ps = null;
		Connection conn = null;  
        ResultSet rs = null;
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.servidor, this.usuario, this.password); 
            System.out.println("conectada");
            
            String query = "UPDATE buscaminas SET score = "+score+" WHERE nombre LIKE '"+name+"'";
            ps = conn.prepareStatement(query);
           
            int actualizada = ps.executeUpdate(query);
            System.out.println("Filas actualizadas: " + actualizada);

        } catch (ClassNotFoundException e) { 
            System.out.println("No se pudo cargar el driver JDBC"); 
            e.printStackTrace();
            
        } catch (SQLException e) {  
            System.out.println("Error al conectarse a la base de datos:");
            e.printStackTrace();
            
        } finally {  
            try { 
                if (rs != null) { 
                    rs.close(); 
                } 
                if (ps != null) { 
                    ps.close(); 
                } 
                if (conn != null) { 
                    conn.close(); 
                } 
            } catch (SQLException e) { 
                System.out.println(e);
                e.printStackTrace();
            } 
        }
		
    }
}
