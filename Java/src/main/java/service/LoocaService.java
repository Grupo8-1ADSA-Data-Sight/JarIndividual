package service;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;

public class LoocaService extends Looca {

    private Integer fkMaquina;

    public Boolean getPermissao() {
        return super.getSistema().getPermissao();
    }


    // METODOS:

    // Metodos de listas:
    public String listaArmazenamento() {
        String informacoesArmazenamento = null;

        for (int i = 0; i < getGrupoDeDiscos().getVolumes().size(); i++) {
            informacoesArmazenamento = String.format("""
                                Disco (%d)
                            %s
                            Espaço disponivel do disco = %.1f GiB
                            Espaço total do disco = %.1f GiB
                            """,
                    (i + 1),
                    getGrupoDeDiscos().getVolumes().get(i).getNome(),
                    (getGrupoDeDiscos().getVolumes().get(i).getDisponivel() / 1e+9),
                    (getGrupoDeDiscos().getVolumes().get(i).getTotal() / 1e+9)
            );

        }
        return informacoesArmazenamento;
    }

    public Double totalArmazenamentoDisponivel() {
        Double totalArmazenamentoDisponivel = 0.0;

        for (int i = 0; i < getGrupoDeDiscos().getVolumes().size(); i++) {
            totalArmazenamentoDisponivel += getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
        }
        return totalArmazenamentoDisponivel;
    }

    public Double totalArmazenamento() {
        Double totalArmazenamento = 0.0;

        for (int i = 0; i < getGrupoDeDiscos().getVolumes().size(); i++) {
            totalArmazenamento += getGrupoDeDiscos().getVolumes().get(i).getTotal();
        }
        return totalArmazenamento;
    }

    public String ExibirDispositivosUSB(){
        String textoSaida = "";
        for (int i = 0; i < super.getDispositivosUsbGrupo().getDispositivosUsbConectados().size(); i++) {
            textoSaida += "Dispositivo "+(i+1)+": "+getDispositivosUsbGrupo().getDispositivosUsbConectados().get(i)+" \n";
        }
        return textoSaida;
    }

    // Metodos para apresentar
    public String exibirComponentesEstaticos() {
        StringBuilder sb = new StringBuilder();

        return String.format("""
                                                                                
                                                                                
                                                                      Sistema
                        ------------------------------------------------------------------------------------------------                               
                        Sistema Operacional = %s
                        Fabricante = %s
                        Arquitetura = x%s
                        Permissões = %s
                        fkMaquina = %d                   
                        ------------------------------------------------------------------------------------------------
                                                                                
                                                                       CPU
                        ------------------------------------------------------------------------------------------------                              
                        idCpuMaquina = %s
                        Fabricante = %s
                        Nome = %s
                        Identificador = %s
                        FrequenciaGhz = %d
                        Nucleos Fisicos = %d
                        Nucleos Logicos = %d
                        fkMaquina = %d                        
                        ------------------------------------------------------------------------------------------------
                               
                                                                  Armazenamento
                        ------------------------------------------------------------------------------------------------
                                                
                         %s
                        Espaço Disponivel geral = %.1f GiB
                        Espaço total geral = %.1f GiB
                        fkMaquina = %d 
                        ------------------------------------------------------------------------------------------------
                                   
                                                                       RAM
                        ------------------------------------------------------------------------------------------------
                                                
                        Tamanho= %s
                        fkMaquina = %d 
                        ------------------------------------------------------------------------------------------------
                                                
                                                                       Rede
                        ------------------------------------------------------------------------------------------------
                        hostName= %s 
                        modelo= %s
                        ipv4= %s
                        fkMaquina= %s
                        ------------------------------------------------------------------------------------------------
                                                                        USB
                        Total de dispositivos conectados: %d
                        """+ExibirDispositivosUSB()+"""
                        fkMaquina= %s
                        ------------------------------------------------------------------------------------------------
                        """,
                // Dados sistema
                super.getSistema().getSistemaOperacional(),
                super.getSistema().getFabricante(),
                super.getSistema().getArquitetura(),
                sb.append(("Executando como ")).append((this.getPermissao() ? "root" : "usuário padrão")),
                this.fkMaquina,

                // Dados processador (CPU)

                super.getProcessador().getId(),
                super.getProcessador().getFabricante(),
                super.getProcessador().getNome(),
                super.getProcessador().getIdentificador(),
                super.getProcessador().getFrequencia(),
                super.getProcessador().getNumeroCpusFisicas(),
                super.getProcessador().getNumeroCpusLogicas(),
                this.fkMaquina,

                // Dados armazenamento (HD/SD)

                listaArmazenamento(),
                totalArmazenamentoDisponivel() / 1e+9,
                totalArmazenamento() / 1e+9,
                this.fkMaquina,


                // Dados memória RAM

                Conversor.formatarBytes(getMemoria().getTotal()),
                this.fkMaquina,

                // Dados Rede
                super.getRede().getParametros().getHostName(),
                super.getRede().getGrupoDeInterfaces().getInterfaces().get(1).getNomeExibicao(),
                super.getRede().getGrupoDeInterfaces().getInterfaces().get(1).getEnderecoIpv4(),
                this.fkMaquina,

                // Dados USB
                super.getDispositivosUsbGrupo().getTotalDispositvosUsbConectados(),
                this.fkMaquina
        );
    }

    public String exibirLeituraComponentes() {
        Double porcentagemDeUsoRAM = (double) super.getMemoria().getEmUso() / super.getMemoria().getTotal() * 100;
        Double disponivelEmGibRAM = super.getMemoria().getDisponivel() / 1e+9;

        return String.format("""
                           |--------------------------|
                           |           CPU            |
                           |--------------------------|
                           |    Uso   |   Frequência  |
                           |                          |
                           |   %.1f%%  |    %.1fGhz   |
                           |__________________________|
                           
                           |--------------------------|
                           |      Armazenamento       |
                           |--------------------------|
                           |     Uso   |   Disponível |
                           |                          |
                           |  %.1f%%   |   %.1f GiB   |
                           |__________________________|
                                        
                           |--------------------------|
                           |      Memória Ram         |
                           |--------------------------|
                           |      USO   |  Disponível |
                           |                          |
                           |    %.1f%%   |  %.1f Gib  |
                           |__________________________|
                           
                          
                        %n""",
                super.getProcessador().getUso(),
                super.getProcessador().getFrequencia()/ 1e+9,
                totalArmazenamento() / 1e+9 ,
                totalArmazenamentoDisponivel() / 1e+9,
                porcentagemDeUsoRAM,
                disponivelEmGibRAM

        );

    }
}


