package util;

import bancoDeDados.Conexao;
import maquinas.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ApresentarDados {
    Usuario usuario = new Usuario();
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    CPU cpu = new CPU();
    CPUSpec cpuSpec = new CPUSpec();
    HDSpec hdSpec = new HDSpec();
    RAM ram = new RAM();
    USB usb = new USB();

    Processos processos = new Processos();

    public void pegarDadosTabela() {
        con.query("SELECT * FROM Cpu", new BeanPropertyRowMapper<>(CPU.class));
        con.query("SELECT * FROM CpuSpec", new BeanPropertyRowMapper<>(CPUSpec.class));
        con.query("SELECT * FROM RAM", new BeanPropertyRowMapper<>(RAM.class));

        System.out.println(String.format("""
                %s%s
                %s
                %s
                %s
                """,  this.cpuSpec, this.cpu, this.ram, this.processos,this.usb));
    }
}
