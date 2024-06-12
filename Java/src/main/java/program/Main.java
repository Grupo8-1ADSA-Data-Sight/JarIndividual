package program;

import bancoDeDados.Conexao;
import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;
import util.ApresentarDados;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Looca looca = new Looca();
        ApresentarDados apresentarDados = new ApresentarDados();
        apresentarDados.iniciarDadosPrograma();
    }
}
