package bancoDeDados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import maquinas.CPULeitura;
import maquinas.CPU;
import maquinas.HD;
//import maquinas.RAM;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InserirDadosNaTabela {
    Conexao conexao = new Conexao();
    Looca looca = new Looca();
    JdbcTemplate con = conexao.getConexaoDoBanco();

    // Objetos das maquinas
    CPULeitura cpuLeitura = new CPULeitura();
    CPU cpu = new CPU();
    HD hd = new HD();
//    RAM ram = new RAM();

    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

    //Obtendo lista de discos a partir do getter
    List<Disco> discos = grupoDeDiscos.getDiscos();

    public void inserindoDadosNaTabela(){
        //Inserindo no banco de dados dados da CPU, puxando os dados pela API - looca
        con.update("INSERT INTO CPU(fabricante, nome, identificador, nucleosFisicos,nucleosLogicos,fkMaquina) values (?, ?, ?, ?,?,1)", cpu.getFabricante(), cpu.getNome(), cpu.getIdentificador(), cpu.getNumeroCpusFisicas(),cpu.getNumeroCpusLogicas());

        //Inserindo no banco de dados dados da CPULeitura, puxando os dados pela API - looca
        con.update("INSERT INTO CPULeitura(uso, DataHoraLeitura, fkCPU) values (?, now(), 1)", cpuLeitura.getUsoCPU());
//        //Inserindo no banco de dados dados da HD, puxando os dados pela API - looca
//        con.update("INSERT INTO HD (DataHoraLeitura, fkMaquina) values (now(), 1)");
//
//        //Inserindo no banco de dados dados da HDSpec, puxando os dados pela API - looca
//        con.update("INSERT INTO HDSpec (Modelo, Tamanho, Leitura, fkHD) values (?, ?, ?, 1)", hdSpec.getModelo(), hdSpec.getTamanho(), hdSpec.getLeitura());

        //Inserindo no banco de dados dados da RAM, puxando os dados pela API - looca
//        con.update("INSERT INTO RAM (EmUso, Total, Disponivel,DataHoraLeitura, fkMaquina) values (?, ?, ?, now(), 12)", ram.getEmUso(), ram.getTotal(), ram.getDisponivel());
    }
}
