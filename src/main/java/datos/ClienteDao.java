package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDao {
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, "
            + "email, telefono FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, "
            + "email, telefono FROM cliente WHERE id_cliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, "
            + "email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, "
            + "apellido = ?, email = ?, telefono = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    public static List<Cliente> listar() {
        // declaramos variables de conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                clientes.add(new Cliente(idCliente, nombre, apellido, email, telefono));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // ceramos variables de conexion
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    public static Cliente encontrar(Cliente cliente) {
        // declaramos variables de conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            // 1 es numero del parametro
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            // nos posicionamos en el primer registro devuelto
            rs.absolute(1);

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // ceramos variables de conexion
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
    public static void insertar(Cliente cliente) {
        // declaramos variables de conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            // pasamos los parametros por el numero de la consulta
            // en la consulta son 4 parametros
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            
            // devuelve cuanto registros se han modificado 
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // ceramos variables de conexion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //System.out.println("Registros modificados: " + rows);
    }
    
    public static void actualizar(Cliente cliente) {
        // declaramos variables de conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            // pasamos los parametros por el numero de la consulta
            // en la consulta son 5 parametros
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setInt(5, cliente.getIdCliente());
            
            // devuelve cuanto registros se han modificado 
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // ceramos variables de conexion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //System.out.println("Registros modificados: " + rows);
    }
    
    public static void eliminar(Cliente cliente) {
        // declaramos variables de conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            // pasamos los parametros por el numero de la consulta
            stmt.setDouble(1, cliente.getIdCliente());
            // devuelve cuanto registros se han modificado 
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // ceramos variables de conexion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //System.out.println("Registros modificados: " + rows);
    }
}
