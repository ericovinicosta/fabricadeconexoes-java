
package br.com.ericocosta.conexao.Impl;   

import br.com.ericocosta.conexao.Impl.DadoBanco;
import br.com.ericocosta.conexao.Repositorio.Crud_Repositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DadoBanco implements Crud_Repositorio<DadoBanco>{

    private static List<DadoBanco> dados = new ArrayList<DadoBanco>();
    
    @Override
    public void Incluir(DadoBanco Componente) {
        dados.add(Componente);
    }

    @Override
    public void Alterar(DadoBanco Componente) {
        Optional<DadoBanco> original = dados.stream().filter(contato -> contato.getNome().equals(Componente.getNome())).findFirst();
        if (original.isPresent())
        {
            original.get().setTelefone(Componente.getTelefone());
            original.get().setIdade(Componente.getIdade());
        }
    }

    @Override
    public void Excluir(DadoBanco Componente) {
        dados.remove(Componente);
    }

    @Override
    public List<DadoBanco> Selecionar() {
        return dados;
    }
   
    
}
