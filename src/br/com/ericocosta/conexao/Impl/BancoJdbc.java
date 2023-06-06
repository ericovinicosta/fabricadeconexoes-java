/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ericocosta.conexao.Impl;

import br.com.ericocosta.conexao.Repositorio.Crud_Repositorio;
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
public class BancoJdbc implements Crud_Repositorio<DadoBanco> {

    @Override
    public void Incluir(Contato Componente) throws SQLException, IOException {

        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            PreparedStatement comando = conexaoBanco.prepareStatement("INSERT INTO contatos (nome, idade, telefone) VALUES (?, ?, ?)");
            comando.setString(1, Componente.getNome());
            comando.setInt(2, Componente.getIdade());
            comando.setString(3, Componente.getTelefone());
            comando.execute();
        }
    }

    @Override
    public void Alterar(Contato Componente) throws SQLException, IOException {
        try(Connection conexaoBanco = FabricaConexao.criarConexao()){
            PreparedStatement comando = conexaoBanco.prepareStatement("UPDATE contatos AS a SET a.nome = ?, a.idade = ?, a.telefone = ? WHERE a.id = ?");
            comando.setString(1, Componente.getNome());
            comando.setInt(2, Componente.getIdade());
            comando.setString(3, Componente.getTelefone());
            comando.setInt(4, Componente.getId());
            comando.execute();
        }
    }

    @Override
    public void Excluir(Contato Componente) throws SQLException, IOException {
        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            PreparedStatement comando = conexaoBanco.prepareStatement("DELETE FROM contatos WHERE contatos.id = ?");
            comando.setInt(1, Componente.getId());
            comando.execute();
        }
    }

    @Override
    public List<Contato> Selecionar() throws SQLException, IOException {

        /*Connection conexaoBanco = null;*/
        List<Contato> listaContatos = new ArrayList<>();

        try (Connection conexaoBanco = FabricaConexao.criarConexao()) {
            /*conexaoBanco = FabricaConexao.criarConexao();*/
            Statement solicitaSQL = conexaoBanco.createStatement();
            ResultSet respostaSQL = solicitaSQL.executeQuery("SELECT * FROM contatos");
            while (respostaSQL.next()) {
                Contato contato = new Contato();
                contato.setId(respostaSQL.getInt("id"));
                contato.setNome(respostaSQL.getString("nome"));
                contato.setIdade(respostaSQL.getInt("idade"));
                contato.setTelefone(respostaSQL.getString("telefone"));
                listaContatos.add(contato);
            }
        }
        /*finally {
        if(conexaoBanco != null)
        conexaoBanco.close();
        }*/

        return listaContatos;
    }

}
