package web;

import datos.ClienteDao;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "editar":
                    editarCliente(request, response);
                    break;
                case "eliminar": 
                    eliminarCliente(request, response);
                    break;
                default:
                    accionDefault(request, response);
            }
        } else {
            accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        //System.out.println("action = " + action);
        if (action != null) {
            switch (action) {
                case "insertar":
                    incertarCliente(request, response);
                    break;
                case "modificar":
                    modificarCliente(request, response);
                    break;
                default:
                    accionDefault(request, response);
            }
        } else {
            accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> clientes = ClienteDao.listar();// metodo lista devuelve lista de tipo cliente
        //System.out.println("clientes = " + clientes);
        HttpSession session = request.getSession();
        session.setAttribute("clientes", clientes);;
        session.setAttribute("clientesTotal", clientes.size());
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    private void incertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // recuperamos los parametros del formulario agregarCliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        // Creamos el objeto cliente y incertamoss en la BBDD
        ClienteDao.insertar(new Cliente(nombre, apellido, email, telefono));
        // Redirigimos a accion por default
        accionDefault(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperamos idCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = ClienteDao.encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        ClienteDao.actualizar(new Cliente(idCliente, nombre, apellido, email, telefono));
        accionDefault(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        ClienteDao.eliminar(new Cliente(idCliente));
        accionDefault(request, response);
    }
}
