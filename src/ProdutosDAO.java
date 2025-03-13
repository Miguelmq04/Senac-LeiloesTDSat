/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    conectaDAO conecta = new conectaDAO();
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        try{
            int status;
            
            conn = conecta.connectDB();
            
            prep = conn.prepareStatement("insert into produtos (nome, valor, status) values (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            
            return status;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar " + e.getMessage());
            return e.getErrorCode();
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

