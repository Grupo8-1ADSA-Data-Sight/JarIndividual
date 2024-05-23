package util;

import bancoDeDados.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Usuario {
//    Conexao conexao = new Conexao();
//    JdbcTemplate con = conexao.getConexaoDoBanco();

    private String email;
    private String senha;
    private ApresentarDados apresentarDados;


    public Usuario(){

    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
//    List<Usuario> list = con.query("SELECT * FROM usuario", new BeanPropertyRowMapper<>(Usuario.class));
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).getEmail().equals(email) && list.get(i).getSenha().equals(senha)){
//                return true;
//            }
//        }

    public Boolean verificarUsuario(String email, String senha){
        String emailUsuario = "kauan@gmail.com";
        String senhaUsuario = "123456";

        return emailUsuario.equals(email) && senhaUsuario.equals(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
