
package dao;
import conexion.clsConexion;
import modelos.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class daoCliente {
    
    clsConexion objCon = new clsConexion();                  
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static String SQLException;
    
    public List<cliente> findAll() {
        List<cliente> cli = new ArrayList<>();
        String sql = "select id,nombre,apellido,email,create_at,estado from clientes order by id";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cliente objCli = new cliente();
                objCli.setId(rs.getInt("id"));                
                objCli.setNombre(rs.getString("nombre"));
                objCli.setApellido(rs.getString("apellido"));
                objCli.setEmail(rs.getString("email"));
                objCli.setCreateAt(rs.getString("create_at")); 
                objCli.setEstado(rs.getInt("estado"));
                cli.add(objCli);
            }
            cn.close();
            ps.close();
            rs.close();
            cn = null;
            ps = null;
            rs = null;
            return cli;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en findAll");
            SQLException = ex.getMessage();
        }                
        return null;
    }
    
    public cliente findById(int id) {
        cliente objCli = new cliente();
        String sql = "select id,nombre,apellido,email,create_at,estado from clientes where id = " + id + " order by id";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                objCli.setId(rs.getInt("id"));                
                objCli.setNombre(rs.getString("nombre"));
                objCli.setApellido(rs.getString("apellido"));
                objCli.setEmail(rs.getString("email"));
                objCli.setCreateAt(rs.getString("create_at"));
                objCli.setEstado(rs.getInt("estado"));
            }
            cn.close();
            ps.close();
            rs.close();              
            cn = null;
            ps = null;
            rs = null;
            return objCli;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en findById");
            SQLException = ex.getMessage();
        }                
        return null;
    }
    
    public boolean save(cliente obj) {
        String sql = "insert into clientes (nombre, apellido, email, create_at)"
                   + "values('" + obj.getNombre() + "','" + obj.getApellido() + "','" + obj.getEmail() + "','" + obj.getCreateAt() + "','" + obj.getEstado() + "')";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;           
            return true;
        } catch (SQLException ex) {                 
            System.out.println(ex.getMessage());
            System.out.println("Error en save");
            SQLException = ex.getMessage();
        }
        return false;
    }        
    
    public boolean update(cliente obj) {
        String sql = "update clientes set nombre = '" + obj.getNombre() + "',apellido = '" + obj.getApellido() + "',email = '" + obj.getEmail() + "',create_at = '" + obj.getCreateAt() + "' where id = " + obj.getId() + "";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;           
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en update");
            SQLException = ex.getMessage();
        }
        return false;
    }
    
    public boolean delete(int id) {
        String sql = "delete from clientes where id = " + id + "";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;           
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en delete");
            SQLException = ex.getMessage();
        }
        return false;
    }
    
    public boolean changeStatus(int estado, int id) {
        String sql = "update clientes set estado = " + estado + " where id = " + id + "";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;           
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en changeStatus");
            SQLException = ex.getMessage();
        }
        return false;
    }
    
}
