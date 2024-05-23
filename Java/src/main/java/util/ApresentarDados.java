package util;

import bancoDeDados.Conexao;
//import maquinas.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import service.LoocaService;

import java.util.List;
import java.util.Scanner;

public class ApresentarDados {
//    Usuario usuario = new Usuario();
    //    Conexao conexao = new Conexao();
//    JdbcTemplate con = conexao.getConexaoDoBanco();
//    CPU cpu = new CPU();
//    CPUSpec cpuSpec = new CPUSpec();
//    HDSpec hdSpec = new HDSpec();
//    RAM ram = new RAM();
//    SystemInfo si = new SystemInfo();
//    RedeGrupo redeGrupo = new RedeGrupo(si);
//
//    Processos processos = new Processos();

//    public void pegarDadosTabela() {
//        List<CPU> cpulist = con.query("SELECT * FROM Cpu", new BeanPropertyRowMapper<>(CPU.class));
//        List<CPUSpec> cpuSpecList = con.query("SELECT * FROM CpuSpec", new BeanPropertyRowMapper<>(CPUSpec.class));
//        List<HDSpec> hdSpecList = con.query("SELECT * FROM HDSpec", new BeanPropertyRowMapper<>(HDSpec.class));
//        List<RAM> ramList = con.query("SELECT * FROM RAM", new BeanPropertyRowMapper<>(RAM.class));
//
//        System.out.println(cpulist);
//        System.out.println();
//        System.out.println(cpuSpecList);
//        System.out.println();
//        System.out.println(hdSpecList);
//        System.out.println();
//        System.out.println(ramList);

//        System.out.printf("""
//                           |--------------------------|
//                           |           CPU            |
//                           |--------------------------|
//                           |    Uso   |  Frequência   |
//                           |                          |
//                           |   %.1f%%  | %dGhz  |
//                           |__________________________|
//
//                           |--------------------------|
//                           |      Armazenamento       |
//                           |--------------------------|
//                           |    Uso   |  Disponível   |
//                           |                          |
//                           |  %.1f%%   |   %.1fGB     |
//                           |__________________________|
//
//                           |--------------------------|
//                           |      Memória Ram         |
//                           |--------------------------|
//                           |    USO   |  Disponível   |
//                           |                          |
//                           |    %.1f%%     |   %.1f     |
//                           |__________________________|
//                        %n""",
//                this.cpu.getUsoCPU(),
//                this.cpuSpec.getFrequenciaGHz(),
//                this.hdSpec.porcentagemDeUso(),
//                this.hdSpec.disponivelEmGB(),
//                this.ram.porcentagemDeUso(),
//                this.ram.disponivelEmGB()
//        );
//    }

    public void inicioPrograma() throws InterruptedException {
        Usuario usuario = new Usuario();
        Boolean estaLogado = false;
        LoocaService loocaService = new LoocaService();


        System.out.println("""
                  _____        __     _______     __        _____    _____    _____    _    _   _______
                 |  __ \\      /  \\   |__   __|   /  \\      / ____|  |_   _|  / ____|  | |  | | |__   __|
                 | |  | |    / /\\ \\     | |     / /\\ \\    | |         | |   | |  __   | |__| |    | |
                 | |  | |   / /__\\ \\    | |    / /__\\ \\    \\____ \\    | |   | | |_ |  |  __  |    | |
                 | |__| |  /  ____  \\   | |   /  ____  \\   _____) |  _| |_  | |__| |  | |  | |    | |
                 |_____/  /_/      \\_\\  |_|  /_/      \\_\\ |______/  |_____|  \\_____|  |_|  |_|    |_|                
                """);
        while (!estaLogado) {

            System.out.println("Realizar Login");
            System.out.print("Email:");
//            String email = sc.nextLine();
            String email = "kauan@gmail.com";
            System.out.print("Senha:");
//            String senha = sc.nextLine();
            String senha = "123456";


            if (usuario.verificarUsuario(email, senha).equals(true)) {
                System.out.println("Acesso permitido");
//                criarTabelas.criarTabelaBanco();
//                inserindo.inserindoDadosNaTabela();
                estaLogado = true;
                while (true) { // Este while não está atualizando os valores em tempo real
//                    apresentarDados.pegarDadosTabela();
                    apresentarDados();
                    Thread.sleep(5000);
                }
            } else {
                System.out.println("Acesso negado, email ou senha invalidos, tente novamente. \n");
            }
    }
    }

    public void apresentarDados() {
        Scanner scanner = new Scanner(System.in);
        LoocaService loocaService = new LoocaService();

        System.out.println("Digite 1 para analisar os componentes das maquinas");
        System.out.println("Digite 2 para analisar as leituras das maquinas");
        int digitoUsuario = scanner.nextInt();

        if (digitoUsuario == 1) {
            System.out.println(loocaService.exibirComponentesEstaticos());
        } else if (digitoUsuario == 2) {
            System.out.println(loocaService.exibirLeituraComponentes());
        } else {
            System.out.println("O dígito é inválido");
        }

    }
}
