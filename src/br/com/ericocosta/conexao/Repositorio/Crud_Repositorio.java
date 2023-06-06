/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ericocosta.conexao.Repositorio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author erico
 * @param <T>
 */
public interface Crud_Repositorio<T> {
    
    public void Incluir(T Componente) throws SQLException, IOException ;
    
    public void Alterar(T Componente) throws SQLException, IOException;
    
    public void Excluir(T Componente) throws SQLException, IOException;
    
    public List<T> Selecionar() throws SQLException, IOException;
    
}
