package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;

import java.util.List;

public class HDSpec {
    Looca looca = new Looca();
    DiscoGrupo discoGrupo = new DiscoGrupo();

    private String modelo;
    private Long tamanho;
    private Long Leitura;

    public HDSpec(){}
//    public HDSpec() {
//            this.modelo = discoGrupo.getDiscos().get(0).getModelo();
//            this.tamanho = discoGrupo.getTamanhoTotal();
//            this.Leitura = discoGrupo.getDiscos().get(0).getLeituras();
//    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Long getLeitura() {
        return Leitura;
    }

    public void setLeitura(Long leitura) {
        Leitura = leitura;
    }

    @Override
    public String toString() {
        return "Modelo='" + modelo + '\'' +
                ", \n Tamanho='" + (tamanho / 1000) + '\'' +
                ", \n Leitura='" + Leitura;
    }
}
