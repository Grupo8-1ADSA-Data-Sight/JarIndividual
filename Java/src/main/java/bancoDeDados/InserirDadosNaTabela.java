package bancoDeDados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import org.springframework.jdbc.core.JdbcTemplate;
import util.Componentes;
import util.Log;
import util.Maquina;
import util.Slack;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InserirDadosNaTabela {
    Conexao conexao = new Conexao();
    ConexaoServer conexaoServer = new ConexaoServer();
    Looca looca = new Looca();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    JdbcTemplate conServer = conexaoServer.getConexaoDoBancoServer();
    Maquina maquina = new Maquina();
    Componentes componentes = new Componentes();
    Log logger = new Log();


    public InserirDadosNaTabela() throws IOException {
        logger.createLog();
        logger.writeLog("Construtor InserirDadosNaTabela chamado");
        logger.closeLog();
    }

    private boolean dadosExistem(String sql, Object... params) throws IOException {
        logger.writeLog("Verificando se os dados existem no servidor SQL: "+sql);
        Integer count = conServer.queryForObject(sql, params, Integer.class);
        logger.writeLog("Resultado da verificação: "+(count == null ? "null" : count));

        return count == null || count <= 0;
    }

    public void inserirDadosFixos() throws IOException {
        logger.createLog();
        logger.writeLog("Método inserirDadosFixos iniciado");
        maquina.isLoginMaquina();
        logger.writeLog("Login da máquina verificado");

        //Inserindo no banco de dados da CPU, puxando os dados pela API - looca
        if (dadosExistem("SELECT COUNT(*) FROM cpu join Maquina on fkMaquina = idMaquina WHERE hostName = ?", looca.getRede().getParametros().getHostName())) {
            logger.writeLog("Dados de CPU não existem no servidor, inserindo dados...");
            con.update("INSERT INTO CPU (fabricante, nome, identificador, frequenciaGHz, fkMaquina) values (?, ?, ?, ?, ?)", looca.getProcessador().getFabricante(), looca.getProcessador().getNome(), looca.getProcessador().getIdentificador(), looca.getProcessador().getFrequencia(), maquina.getIdMaquina());
            conServer.update("INSERT INTO CPU (fabricante, nome, identificador, frequenciaGHz, fkMaquina) values (?, ?, ?, ?, ?)", looca.getProcessador().getFabricante(), looca.getProcessador().getNome(), looca.getProcessador().getIdentificador(), looca.getProcessador().getFrequencia(), maquina.getIdMaquina());
        }

        //Inserindo no banco de dados da HD, puxando os dados pela API - looca

        for (Volume volume : looca.getGrupoDeDiscos().getVolumes()) {
            String volumeNome = volume.getNome();
            logger.writeLog("Verificando volume: " + volumeNome);
            if (dadosExistem("SELECT COUNT(*) FROM HD WHERE nome = ? AND fkMaquina = ?", volume.getNome(), maquina.getIdMaquina())) {
                con.update("INSERT INTO HD (nome, tamanho, fkMaquina) values (?, ? , ?)", volume.getNome(), volume.getTotal(), maquina.getIdMaquina());
                conServer.update("INSERT INTO HD (nome, tamanho, fkMaquina) values (?, ? , ?)", volume.getNome(), volume.getTotal(), maquina.getIdMaquina());

            }
        }
        //Inserindo no banco de dados da RAM, puxando os dados pela API - looca
        if (dadosExistem("SELECT COUNT(*) FROM RAM WHERE fkMaquina = ?", maquina.getIdMaquina())) {
            logger.writeLog("Dados de HD não existem no servidor, inserindo dados...");
            con.update("INSERT INTO RAM (armazenamentoTotal, fkMaquina) values (?, ?)", looca.getMemoria().getTotal(), maquina.getIdMaquina());
            conServer.update("INSERT INTO RAM (armazenamentoTotal, fkMaquina) values (?, ?)", looca.getMemoria().getTotal(), maquina.getIdMaquina());
        }

//       if(dadosExistem("SELECT COUNT(*) FROM RAM WHERE fkMaquina = ?", maquina.getIdMaquina())){
//            if(dadosExistem("select armazenamentoTotal from ram where armazenamentoTotal != ?", looca.getMemoria().getTotal())){
//                con.update("INSERT INTO RAM (armazenamentoTotal, fkMaquina) values (?, ?)", looca.getMemoria().getTotal(), maquina.getIdMaquina());
//            }
//        }

    }

    public void inserindoDadosDinamicos() throws IOException, InterruptedException {

        logger.createLog();
        logger.writeLog("Método inserindoDadosDinamicos iniciado");

        //Inserindo no banco de dados da CPULeitura, puxando os dados pela API - looca
        logger.writeLog("Inserindo dados de leitura da CPU");
        con.update("INSERT INTO CPULeitura (uso, tempoAtividade, dataHoraLeitura, fkCPU) values (?, ?, now(), (select max(idcpu) from CPU))", looca.getProcessador().getUso(), looca.getSistema().getTempoDeAtividade());
        conServer.update("INSERT INTO CPULeitura (uso, tempoAtividade, dataHoraLeitura, fkCPU) values (?, ?, GETDATE(), (select max(idcpu) from CPU))", looca.getProcessador().getUso(), looca.getSistema().getTempoDeAtividade());
        //Inserindo no banco de dados da HDLeitura, puxando os dados pela API - looca
        logger.writeLog("Inserindo dados de leitura do HD");
        con.update("INSERT INTO HDLeitura (uso, disponivel, dataHoraLeitura, fkHD) values (?, ?, now(), (select max(idHD) from HD))", componentes.emUsoHD(), looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        conServer.update("INSERT INTO HDLeitura (uso, disponivel, dataHoraLeitura, fkHD) values (?, ?, GETDATE(), (select max(idHD) from HD))", componentes.emUsoHD(), looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());

        //Inserindo no banco de dados da RAMLeitura, puxando os dados pela API - looca
        logger.writeLog("Inserindo dados de leitura da RAM");
        con.update("INSERT INTO RAMLeitura (emUso, disponivel, dataHoraLeitura, fkRam) values (?, ?, now(), (select max(idRAM) from RAM))", looca.getMemoria().getEmUso(), looca.getMemoria().getDisponivel());
        conServer.update("INSERT INTO RAMLeitura (emUso, disponivel, dataHoraLeitura, fkRam) values (?, ?, GETDATE(), (select max(idRAM) from RAM))", looca.getMemoria().getEmUso(), looca.getMemoria().getDisponivel());


        //Inserindo no banco de dados da RedeGrupo, puxando os dados pela API - looca
//        con.update("INSERT INTO Rede (modelo, ipv4, fkMaquina) values (?, ?, ?)", looca.getRede().getParametros().getNomeDeDominio(), looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4(), maquina.getIdMaquina());

        //Inserindo no banco de dados da USB, puxando os dados pela API - looca

            JSONObject json = new JSONObject();
        logger.writeLog("Inserindo dados de leitura dos USBs");

        if (!((con.queryForMap("Select totalConectados from USB order by dataHoraLeitura desc limit 1").containsValue(looca.getDispositivosUsbGrupo().getDispositivosUsb().size())))) {
            json.put("text", """
                     Novo USB conectado ou removido!
                    """);
            Slack.sendMessage(json);
            for (int i = 0; i < looca.getDispositivosUsbGrupo().getDispositivosUsb().size(); i++) {
                con.update("INSERT INTO USB (totalConectados,dispositivo,dataHoraLeitura,fkMaquina)values(?,?,current_timestamp(),?);", looca.getDispositivosUsbGrupo().getDispositivosUsb().size(), looca.getDispositivosUsbGrupo().getDispositivosUsb().get(i).getNome(), maquina.getIdMaquina());
                conServer.update("INSERT INTO USB (totalConectados,dispositivo,dataHoraLeitura,fkMaquina)values(?,?,GETDATE(),?)", looca.getDispositivosUsbGrupo().getDispositivosUsb().size(), looca.getDispositivosUsbGrupo().getDispositivosUsb().get(i).getNome(), maquina.getIdMaquina());
            }
        }

        logger.writeLog("Dados dinâmicos da CPU, Memória RAM, Disco e Rede enviados");
        logger.closeLog();
    }
}