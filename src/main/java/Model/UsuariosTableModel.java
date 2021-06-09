/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.ProdutoDAO;
import DAO.UsuariosDAO;
import Objetos.Produto;
import Objetos.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fillipe
 */
public class UsuariosTableModel extends AbstractTableModel {

    private List<Usuarios> dados = new ArrayList<>();
    private String[] colunas = {"Nome", "Login", "Senha"};

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getLogin();
            case 2:
                return dados.get(linha).getSenha();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNome((String) valor);
                break;
            case 1:
                dados.get(linha).setLogin(((String) valor));
                break;
            case 2:
                dados.get(linha).setSenha(((String) valor));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }

    public Usuarios pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        UsuariosDAO udao = new UsuariosDAO();

        for (Usuarios u : udao.read()) {
            this.addLinha(u);
        }
        this.fireTableDataChanged();
    }

    private void addLinha(Usuarios u) {
        this.dados.add(u);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
