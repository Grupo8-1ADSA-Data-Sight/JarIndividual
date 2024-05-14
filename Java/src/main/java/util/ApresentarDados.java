package util;

import bancoDeDados.Conexao;
import maquinas.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ApresentarDados {
    Usuario usuario = new Usuario();
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    CPULeitura cpuLeitura = new CPULeitura();
    CPU cpu = new CPU();
    HD hd = new HD();
//    RAM ram = new RAM();

    USB usb = new USB();

    Processos processos = new Processos();

    public void pegarDadosTabela() {
        con.query("SELECT * FROM Cpu", new BeanPropertyRowMapper<>(CPU.class));
        con.query("SELECT * FROM CpuLeitura", new BeanPropertyRowMapper<>(CPULeitura.class));
        con.query("SELECT * FROM HDLeitura", new BeanPropertyRowMapper<>(HD.class));
//        con.query("SELECT * FROM RAM", new BeanPropertyRowMapper<>(RAM.class));

        System.out.println(String.format("""
                %s
                %s
                %s
                %s
                """,  this.cpu, this.cpuLeitura, this.processos,this.usb));
    }
}
