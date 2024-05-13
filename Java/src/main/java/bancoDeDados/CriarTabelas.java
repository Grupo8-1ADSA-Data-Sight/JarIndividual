package bancoDeDados;

import org.springframework.jdbc.core.JdbcTemplate;

public class CriarTabelas {

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    public void criarTabelaBanco(){
        con.execute("DROP TABLE IF EXISTS CPULeitura");
        con.execute("DROP TABLE IF EXISTS CPU");
        con.execute("DROP TABLE IF EXISTS RAMleitura");
        con.execute("DROP TABLE IF EXISTS RedeLeitura");

        con.execute("""
                CREATE TABLE CPU(
                	idCPU INT PRIMARY KEY auto_increment,
                	fabricante VARCHAR(60),
                	nome VARCHAR(45),
                	identificador VARCHAR(200),
                	frequenciaGHz DOUBLE,
                	fkMaquina CHAR(7),
                	FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                );""");

        con.execute("""
                CREATE TABLE CPULeitura(
                	idCPULeitura INT PRIMARY KEY AUTO_INCREMENT,
                	uso DOUBLE,
                	dataHoraLeitura DATETIME,
                    fkCPU INT,
                	CONSTRAINT fkCPULeituraCPU FOREIGN KEY (fkCPU) REFERENCES CPU(idCPU)
                );""");

        con.execute("""                
                CREATE TABLE RAMLeitura(
	                idRAMLeitura INT PRIMARY KEY AUTO_INCREMENT,
                    emUso DOUBLE,
	                disponivel DOUBLE,
	                dataHoraLeitura DATETIME,
	                fkRAM INT NOT NULL,
	                CONSTRAINT fkRAMLeituraRAM FOREIGN KEY (fkRAM) REFERENCES RAM(idRAM)
                );""");

        con.execute("""
                CREATE TABLE RedeLeitura(
	                idRedeLeitura INT PRIMARY KEY AUTO_INCREMENT,
	                taxaUpload DOUBLE NOT NULL,
	                taxaDownload DOUBLE NOT NULL,
	                dataHoraLeitura DATETIME NOT NULL,
	                fkrede INT NOT NULL,
	                CONSTRAINT fkRedeLeituraREDE FOREIGN KEY (fkrede) REFERENCES Rede(idPlacaRede)
                );""");
        System.out.println("Dados encontrados com sucesso!!");
    }
}
