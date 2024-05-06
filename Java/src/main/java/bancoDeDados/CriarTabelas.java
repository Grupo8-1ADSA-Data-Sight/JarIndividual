package bancoDeDados;

import org.springframework.jdbc.core.JdbcTemplate;

public class CriarTabelas {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    public void criarTabelaBanco(){
        con.execute("DROP TABLE IF EXISTS CPUSpec");
        con.execute("DROP TABLE IF EXISTS CPU");
        con.execute("DROP TABLE IF EXISTS HDSpec");
        con.execute("DROP TABLE IF EXISTS HD");
        con.execute("DROP TABLE IF EXISTS RAMSpec");
        con.execute("DROP TABLE IF EXISTS RAM");

        con.execute("""
                CREATE TABLE CPU(
                	idCPU INT PRIMARY KEY AUTO_INCREMENT,
                	uso DOUBLE NOT NULL,
                	DataHoraLeitura datetime NOT NULL,
                	fkMaquina INT NOT NULL,
                	CONSTRAINT fkMaquinaCPU FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                )""");

        con.execute("""
                
                CREATE TABLE CPUSpec(
                	idCPUSpec INT PRIMARY KEY AUTO_INCREMENT,
                	fabricante varchar(45)  not null,
                	nome varchar(45) not null,
                	identificador varchar(200) NOT NULL,
                	qtdNucleo int NOT NULL,
                	frequenciaGHz DOUBLE  NOT NULL,
                	fkCPU INT NOT NULL,
                	CONSTRAINT fkCPUSpecCPU FOREIGN KEY (fkCPU) REFERENCES CPU(idCPU)
                )""");

        con.execute("""        
                CREATE TABLE HD(
                	idHD INT PRIMARY KEY AUTO_INCREMENT,
                	DataHoraLeitura datetime NOT NULL,
                	fkMaquina INT NOT NULL,
                	CONSTRAINT fkMaquinaHD FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                )""");

        con.execute("""
                CREATE TABLE HDSpec(
                	idHDSpec INT PRIMARY KEY AUTO_INCREMENT,
                	modelo varchar(45) not null,
                	tamanho DOUBLE NOT NULL,
                	Leitura Double not null,
                	fkHD INT NOT NULL,
                	CONSTRAINT fkHDSpecHD FOREIGN KEY (fkHD) REFERENCES HD(idHD)
                )""");

        con.execute("""                
                CREATE TABLE RAM(
                	idRAM INT PRIMARY KEY AUTO_INCREMENT,
                	EmUso DOUBLE NOT NULL,
                	Total DOUBLE NOT NULL,
                	Disponivel DOUBLE NOT NULL,
                	DataHoraLeitura datetime NOT NULL,
                	fkMaquina INT NOT NULL,
                	CONSTRAINT fk_idMaquinaRAM FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                )""");
        System.out.println("Dados encontrados com sucesso!!");
    }
}
