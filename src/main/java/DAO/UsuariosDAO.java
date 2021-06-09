/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BD.Conexao;
import Objetos.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fillipe
 */
public class UsuariosDAO {
 
    public List<Usuarios> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuarios> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
    public void create(Usuarios u) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO tbl_usuarios (nome, login, senha) VALUES (?, ?, ?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Usuarios u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(
    "UPDATE tbl_usuarios SET nome = ?, login = ?, senha = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());            
            stmt.setInt(4, u.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void remover(Usuarios u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(
                 "DELETE FROM tbl_usuarios WHERE id = ?");                              
            stmt.setInt(1, u.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao Remover"  + e);
        } finally{
            Conexao.closeConnection(con, stmt);
        }
    }
}
