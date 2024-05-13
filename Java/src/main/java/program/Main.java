package program;

import bancoDeDados.CriarTabelas;
import bancoDeDados.InserirDadosNaTabela;
import util.ApresentarDados;
import util.Usuario;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import maquinas.CPU;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.startMonitoring();
        Scanner sc = new Scanner(System.in);
        CriarTabelas criarTabelas = new CriarTabelas();
        InserirDadosNaTabela inserindo = new InserirDadosNaTabela();
        ApresentarDados apresentarDados = new ApresentarDados();
        Usuario usuario = new Usuario();

        System.out.println("******************************************************************************************");
        System.out.println("""
                      _____        __     _______     __        _____    _____    _____    _    _   _______
                     |  __ \\      /  \\   |__   __|   /  \\      / ____|  |_   _|  / ____|  | |  | | |__   __|
                     | |  | |    / /\\ \\     | |     / /\\ \\    | |         | |   | |  __   | |__| |    | |
                     | |  | |   / /__\\ \\    | |    / /__\\ \\    \\____ \\    | |   | | |_ |  |  __  |    | |
                     | |__| |  /  ____  \\   | |   /  ____  \\   _____) |  _| |_  | |__| |  | |  | |    | |
                     |_____/  /_/      \\_\\  |_|  /_/      \\_\\ |______/  |_____|  \\_____|  |_|  |_|    |_|
                     """);
        System.out.println("******************************************************************************************");

        while (!login(sc, usuario)) {
            System.out.println("Acesso não permitido, email ou senha invalidos.\n");
        }

        criarTabelas.criarTabelaBanco();
        inserindo.inserindoDadosNaTabela();
        apresentarDados.pegarDadosTabela();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(apresentarDados::pegarDadosTabela, 0, 5, TimeUnit.SECONDS);
    }

    private static boolean login(Scanner sc, Usuario usuario) {
        System.out.println("Realize seu login para acessar seus dados da maquina");
        System.out.print("Email:");
        String email = sc.nextLine();
        System.out.print("Senha:");
        String senha = sc.nextLine();

        if (usuario.verificarUsuario(email, senha)) {
            System.out.println("Login efetuado");
            return true;
        }
        return false;
    }
}