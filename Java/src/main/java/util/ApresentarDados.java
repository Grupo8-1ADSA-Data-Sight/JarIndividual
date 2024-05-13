package util;

import bancoDeDados.Conexao;
import maquinas.CPU;
import maquinas.CPUSpec;
import maquinas.Ram;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ApresentarDados {
    Usuario usuario = new Usuario();
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    CPU cpu = new CPU();
    CPUSpec cpuSpec = new CPUSpec();
    Ram ram = new Ram();


    public void pegarDadosTabela() {
        con.query("SELECT * FROM Cpu", new BeanPropertyRowMapper<>(CPU.class));
        con.query("SELECT * FROM CpuSpec", new BeanPropertyRowMapper<>(CPUSpec.class));
        con.query("SELECT * FROM RAM", new BeanPropertyRowMapper<>(Ram.class));

        System.out.println(String.format("""
                Especificações do CPU: %s
                Dados do CPU: %s
                Dados da RAM: %s
                """,  this.cpuSpec, this.cpu, this.ram));
    }
}
