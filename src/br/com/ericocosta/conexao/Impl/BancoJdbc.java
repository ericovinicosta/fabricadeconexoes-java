/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ericocosta.conexao.Impl;

import br.com.ericocosta.Conexao.FabricaConexao;
import br.com.ericocosta.conexao.Repositorio.Crud_Repositorio;
import br.com.ericocosta.dados_banco.Dado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import javafx.scene.control.Alert;

/**
 *
 * @author erico
 */
public class BancoJdbc implements Crud_Repositorio<Dado> {

    @Override
    public void Incluir(Dado Componente) throws SQLException, IOException {

        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            PreparedStatement comando = conexaoBanco.prepareStatement("INSERT INTO teste (descricao) VALUES (?)");
            comando.setString(1, Componente.getMensagem());
//            comando.setInt(2, Componente.getIdade());
//            comando.setString(3, Componente.getTelefone());
            comando.execute();
        }
    }

    @Override
    public void Alterar(Dado Componente) throws SQLException, IOException {
        try(Connection conexaoBanco = FabricaConexao.criarConexao()){
            PreparedStatement comando = conexaoBanco.prepareStatement("UPDATE teste AS a SET a.descricao = ? WHERE a.id = ?");
            comando.setString(1, Componente.getMensagem());
//            comando.setInt(2, Componente.getIdade());
//            comando.setString(3, Componente.getTelefone());
            comando.setInt(2, Componente.getId());
            comando.execute();
        }
    }

    @Override
    public void Excluir(Dado Componente) throws SQLException, IOException {
        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            PreparedStatement comando = conexaoBanco.prepareStatement("DELETE FROM teste AS a WHERE a.id = ?");
            comando.setInt(1, Componente.getId());
            comando.execute();
        }
    }

    @Override
    public List<Dado> Selecionar() throws SQLException, IOException {

        /*Connection conexaoBanco = null;*/
        List<Dado> listaDados = new ArrayList<>();

        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            /*conexaoBanco = FabricaConexao.criarConexao();*/
            Statement solicitaSQL = conexaoBanco.createStatement();
            ResultSet respostaSQL = solicitaSQL.executeQuery("SELECT * FROM teste"); //verificar tabela
            while (respostaSQL.next()) {
                Dado dado = new Dado();
                dado.setId(respostaSQL.getInt("id"));
                dado.setMensagem(respostaSQL.getString("descricao"));
//                dado.setIdade(respostaSQL.getInt("idade"));
//                dado.setTelefone(respostaSQL.getString("telefone"));
                listaDados.add(dado);
            }
        }
        /*finally {
        if(conexaoBanco != null)
        conexaoBanco.close();
        }*/

        return listaDados;
    }

}
