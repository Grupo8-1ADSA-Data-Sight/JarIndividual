package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.temperatura.Temperatura;


public class CPUSpec extends Processador {
    Looca looca = new Looca();
    Processador processador = new Processador();
    Temperatura temperatura = new Temperatura();
    private String fabricante;
    private String nome;
    private String identificador;
    private Integer qtdNucleo;
    private Long frequenciaGHz;

    public CPUSpec() {
        this.fabricante = processador.getFabricante();
        this.nome = processador.getNome();
        this.identificador = processador.getIdentificador();
        this.qtdNucleo = processador.getNumeroCpusFisicas() + processador.getNumeroCpusLogicas();
        this.frequenciaGHz = processador.getFrequencia();
    }


    @Override
    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getQtdNucleo() {
        return qtdNucleo;
    }

    public void setQtdNucleo(Integer qtdNucleo) {
        this.qtdNucleo = qtdNucleo;
    }

    public Long getFrequenciaGHz() {
        return frequenciaGHz;
    }

    public void setFrequenciaGHz(Long frequenciaGHz) {
        this.frequenciaGHz = frequenciaGHz;
    }


    @Override
    public String toString() {
        return String.format("""
                Dados da CPU:
                Fabricante: %s
                Nome: %s            
                Identificador: %s
                Quantidade de núcleos: %s
                frequencia(GHz): %s       
                """,fabricante, nome, identificador, qtdNucleo, frequenciaGHz);
    }
}
