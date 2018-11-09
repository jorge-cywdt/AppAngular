/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.daoCliente;
import modelos.cliente;
import static dao.daoCliente.SQLException;
import weas.clsValidacion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jorge Baez
 */
public class controladorCliente extends HttpServlet {

    daoCliente objDaoCli = new daoCliente();
    List<String> flash = new ArrayList<>();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet controladorCliente</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet controladorCliente at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("action");
        
        if (action == null) {
            findAll(request, response);
            return; // No ejecuta el siguiente método, como un else
        }
        
        switch(action) {                        
            case "listarCliente":
                findAll(request, response);
            break;
            
            case "listarClienteAjax":
                findAllWithAjax(request, response);
            break;
            
            case "formCliente":
                crear(request, response);
            break;
            
            case "saveCliente":
                save(request, response);
            break;
            
            case "buscarCliente":
                findById(request, response);
            break;
            
            case "updateCliente":
                update(request, response);
            break;
            
            case "eliminarCliente":
                delete(request, response);
            break;
            
            case "cambiarEstadoCliente":
                changeStatus(request, response);
            break;
            
            case "verCliente":
                findById2(request, response);
            break;
            
            default:
                findAll(request, response);
            break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    private void findAll(HttpServletRequest request, HttpServletResponse response) {        
        try {                        
//            request.setAttribute("cliente", objDaoCli.findAll());
            request.getRequestDispatcher("listarCliente.jsp").forward(request, response); // Redirecciones en el servidor
//            response.sendRedirect("ruta"); // Redirecciones en el cliente
        } catch (ServletException ex) { // request.getRequestDispatcher
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { // request.getRequestDispatcher // response.sendRedirect
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void findAllWithAjax(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<cliente> cli = objDaoCli.findAll();

            JSONArray listJson = new JSONArray();
            for (cliente objCli:cli) {
                JSONArray item = new JSONArray();

                item.put("<a href=\"javascript:verCliente(" + objCli.getId() + ")\">" + objCli.getId() + "</a>");
                item.put(objCli.getNombre());
                item.put(objCli.getApellido());
                item.put(objCli.getEmail());
                item.put(objCli.getCreateAt());
                item.put("<a class=\"btn btn-sm btn-primary\" href=\"controladorCliente?action=buscarCliente&id=" + objCli.getId() + "\">editar</a>");

                String eliminar = objCli.getEstado() == 1 ?
                "<a class=\"btn btn-sm btn-dark\" href=\"javascript:confirm('" + objCli.getId() + "')\" onclick=\"return confirm('Estas seguro que quieres eliminar?');\">desactivar</a>" :
                "<a class=\"btn btn-sm btn-primary\" href=\"javascript:confirm('" + objCli.getId() + "')\" onclick=\"return confirm('Estas seguro que quieres eliminar?');\">activar</a>";
                item.put(eliminar);

                String estado = objCli.getEstado() == 1 ?
                "<h6><span class=\"badge badge-outline-primary\">Activado</span></h6>" :
                "<h6><span class=\"badge badge-outline-dark\">Desactivado</span></h6>";
                item.put(estado);

                listJson.put(item);
            }
            
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("draw", 1);
            json.put("recordsTotal", cli.size());
            json.put("recordsFiltered", cli.size());
            json.put("data", listJson);
            out.print(json); 
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("formCliente.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    private void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String createAt = request.getParameter("fecRegistro");

            cliente objCli = new cliente();
            objCli.setNombre(nombre);
            objCli.setApellido(apellido);
            objCli.setEmail(email);
            objCli.setCreateAt(createAt);

            flash.clear();
            if (clsValidacion.isEmpty(objCli.getNombre())) {
                flash.add("El nombre del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getApellido())) {
                flash.add("El apellido del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getEmail())) {
                flash.add("El email del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getCreateAt())) {
                flash.add("La fecha de registro no puede ser nula");
            }

            if (flash.size() > 0) {           
                request.setAttribute("flash", flash);
                request.setAttribute("cliente", objCli);
                request.getRequestDispatcher("controladorCliente?action=formCliente").forward(request, response);
            } else {
                if (objDaoCli.save(objCli) == true) {                  
                    request.getSession().setAttribute("mensajeExito", "Cliente creado con éxito");
                    response.sendRedirect("controladorCliente?action=listarCliente");
                } else {
                    request.getSession().setAttribute("mensajeError", "No se pudo crear cliente");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("controladorCliente?action=formCliente");
                }
            }
        } catch (ServletException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));                       
            
            cliente objCli = new cliente();
            objCli = objDaoCli.findById(id);
            
            if (objCli.getNombre() == null) {
                request.getSession().setAttribute("mensajeError", "El ID del cliente no existe en la BD");
                response.sendRedirect("controladorCliente?action=listarCliente");               
            } else {                
                request.setAttribute("cliente", objCli);
                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);                
            }            
        } catch (ServletException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String createAt = request.getParameter("fecRegistro");                    
            
            cliente objCli = new cliente();
            objCli.setId(id);
            objCli.setNombre(nombre);
            objCli.setApellido(apellido);
            objCli.setEmail(email);
            objCli.setCreateAt(createAt);

            flash.clear();
            if (clsValidacion.isEmpty(objCli.getNombre())) {
                flash.add("El nombre del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getApellido())) {
                flash.add("El apellido del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getEmail())) {
                flash.add("El email del cliente es requerido");
            }
            if (clsValidacion.isEmpty(objCli.getCreateAt())) {
                flash.add("La fecha de registro no puede ser nula");
            }

            if (flash.size() > 0) {           
                request.setAttribute("flash", flash);
                request.setAttribute("cliente", objCli);
                request.getRequestDispatcher("controladorCliente?action=buscarCliente&id="+id+"").forward(request, response);
            } else {
                if (objDaoCli.update(objCli) == true) {                  
                    request.getSession().setAttribute("mensajeExito", "Cliente editado con éxito");
                    response.sendRedirect("controladorCliente?action=listarCliente");
                } else {
                    request.getSession().setAttribute("mensajeError", "No se pudo editar cliente");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("controladorCliente?action=buscarCliente&id="+id+"");
                }
            }
        } catch (ServletException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            cliente objCli = new cliente();
            objCli = objDaoCli.findById(id);

            if (objCli.getNombre() == null) {
                request.getSession().setAttribute("mensajeError", "El ID del cliente no existe en la BD");
                response.sendRedirect("controladorCliente?action=listarCliente");
            } else {
                if (objDaoCli.delete(id) == true) {                                  
                    request.getSession().setAttribute("mensajeExito", "Cliente eliminado con éxito");
                    response.sendRedirect("controladorCliente?action=listarCliente");
                } else {
                    request.getSession().setAttribute("mensajeError", "No se pudo eliminar cliente");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("controladorCliente?action=listarCliente");
                }
            }            
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void changeStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            cliente objCli = new cliente();
            objCli = objDaoCli.findById(id);
            int estado = (objCli.getEstado() == 0) ? 1 : 0;
            String mensaje = (objCli.getEstado() == 0) ? "Cliente " + objCli.getNombre() + " activado con éxito" : "Cliente " + objCli.getNombre() + " desactivado con éxito";

            if (objCli.getNombre() == null) {
                request.getSession().setAttribute("mensajeError", "El ID del cliente no existe en la BD");
                response.sendRedirect("controladorCliente?action=listarCliente");               
            } else {
                if (objDaoCli.changeStatus(estado, id) == true) {
                    request.getSession().setAttribute("mensajeExito", mensaje);
                    response.sendRedirect("controladorCliente?action=listarCliente");
                } else {
                    request.getSession().setAttribute("mensajeError", "No se pudo eliminar cliente");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("controladorCliente?action=listarCliente");
                }
            }            
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void findById2(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));           
        
            cliente objCli = new cliente();
            objCli = objDaoCli.findById(id);
                                    
            if (objCli.getNombre() == null) {
                request.getSession().setAttribute("mensajeError", "El ID del cliente no existe en la BD");
                response.sendRedirect("controladorCliente?action=listarCliente");               
            } else {                
                PrintWriter out = response.getWriter();            
                JSONObject json = new JSONObject();
                json.put("id", objCli.getId());
                json.put("nombre", objCli.getNombre());
                json.put("apellido", objCli.getApellido());                
                json.put("email", objCli.getEmail());
                json.put("createAt", objCli.getCreateAt());
                json.put("estado", objCli.getEstado());
                json.put("string", objCli.toString());
                out.print(json);
            }
        } catch (IOException ex) {
            Logger.getLogger(controladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
