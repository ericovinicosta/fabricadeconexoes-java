
package br.com.ericocosta.conexao.Impl;   

import br.com.ericocosta.conexao.Repositorio.Crud_Repositorio;
import br.com.ericocosta.dados_banco.Dado;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DadoBanco implements Crud_Repositorio<Dado>{

    private static List<Dado> dados = new ArrayList<Dado>();
    
    @Override
    public void Incluir(Dado Componente) {
        dados.add(Componente);
    }

    @Override
    public void Alterar(Dado Componente) {
        Optional<Dado> original = dados.stream().filter(dados -> dados.getId() == Componente.getId()).findFirst();
        if (original.isPresent())
        {
            original.get().setMensagem(Componente.getMensagem());
        
        }
    }

    @Override
    public void Excluir(Dado Componente) {
        dados.remove(Componente);
    }

    @Override
    public List<Dado> Selecionar() {
        return dados;
    }
   
    
}
