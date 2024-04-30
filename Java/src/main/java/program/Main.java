package program;

import bancoDeDados.CriarTabelas;
import bancoDeDados.InserirDadosNaTabela;
import util.ApresentarDados;
import util.Usuario;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriarTabelas criarTabelas = new CriarTabelas();
        InserirDadosNaTabela inserindo = new InserirDadosNaTabela();
        ApresentarDados apresentarDados = new ApresentarDados();
        Usuario usuario = new Usuario();
        Boolean estaLogado = false;

        while (!estaLogado) {
            System.out.println("""
                      _____        __     _______     __        _____    _____    _____    _    _   _______
                     |  __ \\      /  \\   |__   __|   /  \\      / ____|  |_   _|  / ____|  | |  | | |__   __|
                     | |  | |    / /\\ \\     | |     / /\\ \\    | |         | |   | |  __   | |__| |    | |
                     | |  | |   / /__\\ \\    | |    / /__\\ \\    \\____ \\    | |   | | |_ |  |  __  |    | |
                     | |__| |  /  ____  \\   | |   /  ____  \\   _____) |  _| |_  | |__| |  | |  | |    | |
                     |_____/  /_/      \\_\\  |_|  /_/      \\_\\ |______/  |_____|  \\_____|  |_|  |_|    |_|                
                    """);

            System.out.println("Realizar Login");
            System.out.print("Email:");
            String email = sc.nextLine();
            System.out.print("Senha:");
            String senha = sc.nextLine();


            if (usuario.verificarUsuario(email, senha).equals(true)) {
                System.out.println("Acesso permitido");
                criarTabelas.criarTabelaBanco();
//            while (usuario.verificarUsuario(email, senha).equals(true)){
                inserindo.inserindoDadosNaTabela();
                apresentarDados.pegarDadosTabela();
                estaLogado = true;
//            }
            } else {
                System.out.println("Acesso negado, email ou senha invalidos, tente novamente. \n");
            }
        }
    }
}
