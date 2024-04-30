package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import oshi.software.os.OSProcess;

import java.util.ArrayList;
import java.util.List;

public class CPUSpec extends Processador {
    Looca looca = new Looca();
    Processador processador = new Processador();
    Temperatura temperatura = new Temperatura();

    private String fabricante;
    private String nome;
    private String identificador;
    private Integer qtdNucleo;
    private Long frequenciaGHz;
    private Double temperaturaCPU;
//    private List<Processo> processoCPU = new ArrayList<>();

    public CPUSpec() {
        this.fabricante = processador.getFabricante();
        this.nome = processador.getNome();
        this.identificador = processador.getIdentificador();
        this.qtdNucleo = processador.getNumeroCpusFisicas() + processador.getNumeroCpusLogicas();
        this.frequenciaGHz = processador.getFrequencia();
        this.temperaturaCPU = temperatura.getTemperatura();

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

    public Double getTemperaturaCPU() {
        return temperaturaCPU;
    }

    public void setTemperaturaCPU(Double temperaturaCPU) {
        this.temperaturaCPU = temperaturaCPU;
    }

//    public ProcessoGrupo getProcesso() {
//        return processo;
//    }
//
//    public void setProcesso(ProcessoGrupo processo) {
//        this.processo = processo;
//    }

//    public List<Processo> getProcessoCPU() {
//        return processoCPU;
//    }
//
//    public void setProcessoCPU(List<Processo> processoCPU) {
//        this.processoCPU = processoCPU;
//    }

    @Override
    public String toString() {
        return String.format("""
                Dados da CPU:
                Fabricante: %s
                Nome: %s            
                Identificador: %s
                Quantidade de núcleos: %s
                frequencia(GHz): %s
                temperatura: %s       
                """,fabricante, nome, identificador, qtdNucleo, frequenciaGHz, temperaturaCPU);
    }
}
