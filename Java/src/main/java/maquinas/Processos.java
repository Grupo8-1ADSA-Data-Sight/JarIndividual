package maquinas;

import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.ArrayList;
import java.util.List;

public class Processos extends ProcessoGrupo {
    private List<Processo> processoCPU = new ArrayList<>();

    public Processos() {
    }

    public Processos(ProcessoGrupo processo, List<Processo> processoCPU) {
        this.processoCPU = processoCPU;
    }

    public List<Processo> getProcessoCPU() {
        return processoCPU;
    }

    public void setProcessoCPU(List<Processo> processoCPU) {
        this.processoCPU = processoCPU;
    }

    @Override
    public String toString() {
        String a = String.format("""
             \n Lista de processos
                Total de processos ativos: %d
                Total de threads: %d
                Processos:
                """, this.getTotalProcessos(), this.getTotalThreads());

        for (int i = 0; i < 10; i++) {
            Processo processo = getProcessos().get(i);
            a += "-".repeat(20);
            a += "\n"+ processo.toString();
            a += "-".repeat(20);
        }
        return a;
    }
}
