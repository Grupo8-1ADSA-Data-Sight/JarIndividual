package bancoDeDados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import maquinas.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InserirDadosNaTabela {
    Conexao conexao = new Conexao();
    Looca looca = new Looca();
    JdbcTemplate con = conexao.getConexaoDoBanco();

    // Objetos das maquinas
    CPU Cpu = new CPU();
    CPUSpec CPUSpec = new CPUSpec();
    Ram ram = new Ram();

    Rede rede = new Rede();

    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

    //Obtendo lista de discos a partir do getter

    public void inserindoDadosNaTabela(){
        //Inserindo no banco de dados, dados da CPU, puxando os dados pela API - looca.
        con.update("INSERT INTO CPULeitura (uso, DataHoraLeitura, fkCpu) values (?, now(), 1)", Cpu.getUsoCPU());

        //Inserindo no banco de dados, dados da CPUSpec, puxando os dados pela API - looca.
        con.update("INSERT INTO CPU (fabricante, nome, identificador, frequenciaGHz, fkMaquina values (?, ?, ?, ?, 1)", CPUSpec.getFabricante(), CPUSpec.getNome(), CPUSpec.getIdentificador(), CPUSpec.getFrequenciaGHz());

        //Inserindo no banco de dados, dados da RAM, puxando os dados pela API - looca.
        con.update("INSERT INTO RAM (EmUso, Total, Disponivel, DataHoraLeitura, fkMaquina) values (?, ?, ?, now(), 1)", ram.getEmUso(), ram.getTotal(), ram.getDisponivel());

        // Inserindo no banco de dados, dados da Rede, puxando os dados pela API - looca.
        con.update("INSERT INTO RedeLeitura (taxaUpload, taxaDownload, dataHoraLeitura, fkMaquina) values (?, ?, now(), 1)", rede.getBytesEnviados(), rede.getBytesRecebidos());
    }
}
