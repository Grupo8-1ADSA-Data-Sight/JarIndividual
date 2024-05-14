package bancoDeDados;

import org.springframework.jdbc.core.JdbcTemplate;

public class CriarTabelas {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    public void criarTabelaBanco(){
        con.execute("DROP TABLE IF EXISTS CPULeitura");
        con.execute("DROP TABLE IF EXISTS CPU");
        con.execute("DROP TABLE IF EXISTS HDLeitura");
        con.execute("DROP TABLE IF EXISTS HD");
        con.execute("DROP TABLE IF EXISTS RAMLeitura");
        con.execute("DROP TABLE IF EXISTS RAM");


        con.execute("""
                CREATE TABLE CPU(
                	idCPU INT PRIMARY KEY auto_increment,                                                                     idCPUMaquina Char(17),
                    fabricante VARCHAR(60),
                    nome VARCHAR(45),
                    identificador VARCHAR(200),
                    frequencia DOUBLE,
                    nucleosFisicos INT,
                    nucleosLogicos INT,
                    fkMaquina INT,
                    CONSTRAINT fkMaquinaCPU FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                )""");

        con.execute("""
                
                CREATE TABLE CPULeitura(
                	idCPULeitura INT PRIMARY KEY AUTO_INCREMENT,
                    uso DOUBLE,
                    dataHoraLeitura DATETIME,
                    fkCPU INT,
                    CONSTRAINT fkCPULeituraCPU FOREIGN KEY (fkCPU) REFERENCES CPU(idCPU)
                )""");

        con.execute("""        
                CREATE TABLE HD(
                	idHD INT PRIMARY KEY AUTO_INCREMENT,
                    nome VARCHAR(50),
                    tamanho DOUBLE,
                    fkMaquina INT,
                    CONSTRAINT fkMaquinaHD FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
                )""");

        con.execute("""
                CREATE TABLE HDLeitura(
                    idHDLeitura INT PRIMARY KEY AUTO_INCREMENT,
	                uso Double,
                    disponivel DOUBLE,
	                dataHoraLeitura DATETIME,
	                fkHD INT,
	                CONSTRAINT fkHDLeituraHD FOREIGN KEY (fkHD) REFERENCES HD(idHD)
                )""");

//        con.execute("""
//                CREATE TABLE RAM(
//                	idRAM INT PRIMARY KEY AUTO_INCREMENT,
//                	EmUso DOUBLE NOT NULL,
//                	Total DOUBLE NOT NULL,
//                	Disponivel DOUBLE NOT NULL,
//                	DataHoraLeitura datetime NOT NULL,
//                	fkMaquina INT NOT NULL,
//                	CONSTRAINT fk_idMaquinaRAM FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)
//                )""");
        System.out.println("Dados encontrados com sucesso!!");
    }
}
