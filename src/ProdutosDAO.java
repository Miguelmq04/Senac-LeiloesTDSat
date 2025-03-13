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
import java.util.List;


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
            
            conecta.desconectar();
            
            return status;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar " + e.getMessage());
            return e.getErrorCode();
        }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        String sql = "select * from produtos";
        try{
            conn = conecta.connectDB();
            
            prep = this.conn.prepareStatement(sql);
            
            resultset = prep.executeQuery();
            
            List<ProdutosDTO> listaProdutos = new ArrayList<>();
            while(resultset.next()){
                ProdutosDTO produtosDTO = new ProdutosDTO();
                
                produtosDTO.setId(resultset.getInt("ID"));
                produtosDTO.setNome(resultset.getString("Nome"));
                produtosDTO.setValor(resultset.getInt("Valor"));
                produtosDTO.setStatus(resultset.getString("Status"));
                
                listaProdutos.add(produtosDTO);
            }
            conecta.desconectar();
            
            return listaProdutos;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    
    
        
}

