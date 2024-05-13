package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

public class CPUSpec {
    Looca looca = new Looca();

    Processador processador = new Processador();

    Temperatura temperatura = new Temperatura();

    private String fabricante;

    private String nome;

    private String identificador;

    private Integer qtdNucleo;

    private Long frequenciaGHz;

    private Double temperaturaCPU;

    public CPUSpec() {
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Double getTemperaturaCPU() {
        return temperaturaCPU;
    }

    public void setTemperaturaCPU(Double temperaturaCPU) {
        this.temperaturaCPU = temperaturaCPU;
    }

    @Override
    public String toString() {
        return String.format("""
                Dados da CPU:
                Fabricante: %s
                Nome: %s
                Identificador: %s
                Quantidade de Núcleos: %s
                Frequencia(GHz): %s
                Temperatura: %s 
                """, fabricante, nome, identificador, qtdNucleo, frequenciaGHz, temperatura);
    }
}
