package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class CPULeitura {
    Looca looca = new Looca();
    Processador processador = new Processador();

    private Double usoCPU;

    public CPULeitura() {
        this.usoCPU = processador.getUso();
    }

    public Double getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(Double usoCPU) {
        this.usoCPU = usoCPU;
    }

    @Override
    public String toString() {
        return String.format("""
    Uso da Cpu: %.2f GB
    """,usoCPU) ;
    }
}
