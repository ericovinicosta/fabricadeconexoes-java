
package br.com.ericocosta.Conexao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {
    
    static public Connection estabeleConexao() throws IOException, SQLException{
        Properties propriedade = new Properties();
        
        InputStream entrada = FabricaConexao.class.getClassLoader().getResourceAsStream("application.properties");
        if (entrada == null){
            throw new FileNotFoundException("Arquivo de configuração não localizado!");
        }
        
        propriedade.load(entrada);
        
        Connection conexao = DriverManager.getConnection(propriedade.getProperty("urlConexao"), 
                propriedade.getProperty("userConexao"), propriedade.getProperty("userSenha"));
        
        return conexao;
    }
    
}
