package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;

public class RAM {
    Looca looca = new Looca();
    Memoria memoria = looca.getMemoria();

    private Long EmUso;
    private Long Total;
    private Long Disponivel;


    public RAM() {
        EmUso = memoria.getEmUso();
        Total = memoria.getTotal();
        Disponivel = memoria.getDisponivel();
    }

    public Long getEmUso() {
        return EmUso;
    }

    public void setEmUso(Long emUso) {
        EmUso = emUso;
    }

    public Long getTotal() {
        return Total;
    }

    public void setTotal(Long total) {
        Total = total;
    }

    public Long getDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(Long disponivel) {
        Disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Dados da memória RAM: " +
                "\nEm uso: " + Conversor.formatarBytes(getEmUso()) +
                "\nTotal: " + Conversor.formatarBytes(getTotal()) +
                "\nDisponivel: " + Conversor.formatarBytes(getDisponivel());
    }
}
