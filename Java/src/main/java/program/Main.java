package program;

//import bancoDeDados.CriarTabelas;
//import bancoDeDados.InserirDadosNaTabela;

import com.github.britooo.looca.api.core.Looca;
import service.LoocaService;
//import util.ApresentarDados;
import util.ApresentarDados;
import util.Usuario;


import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        LoocaService service = new LoocaService();
//        CriarTabelas criarTabelas = new CriarTabelas();
//        InserirDadosNaTabela inserindo = new InserirDadosNaTabela();
        ApresentarDados apresentarDados = new ApresentarDados();
//        Usuario usuario = new Usuario();
        apresentarDados.inicioPrograma();


    }
}
