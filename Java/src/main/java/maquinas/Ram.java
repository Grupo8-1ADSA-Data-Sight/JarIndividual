package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

public class Ram {
    Looca looca = new Looca();
    Memoria memoria = looca.getMemoria();
    private Long emUso;
    private Long total;
    private Long disponivel;

    public Ram(){
        emUso = memoria.getEmUso();
        total = memoria.getTotal();
        disponivel = memoria.getDisponivel();
    }

    public Long getEmUso() {
        return emUso;
    }

    public void setEmUso(Long emUso) {
        this.emUso = emUso;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Long disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Informações da RAM:" +
                "Em uso: " + emUso +
                ", Total: " + total +
                ", Disponível: " + disponivel;
    }
}