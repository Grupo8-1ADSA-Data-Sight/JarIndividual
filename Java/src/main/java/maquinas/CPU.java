package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CPU {
    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    private Double usoCPU;

    public CPU() {

    }

    public void fetchHardwareData() {
        Double cpuUsage = processador.getUso();
        printProgressBar(cpuUsage);
    }

    private void printProgressBar(Double cpuUsage) {
        int progress = (int) Math.round(cpuUsage);
        String progressBar = "#".repeat(progress) + "-".repeat(100 - progress);
        System.out.println("CPU Usage: [" + progressBar + "] " + progress + "%");
    }

    public void startMonitoring() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::fetchHardwareData, 0, 5, TimeUnit.SECONDS);
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
        Uso da Cpu: %.2f
        """, usoCPU);
    }
}