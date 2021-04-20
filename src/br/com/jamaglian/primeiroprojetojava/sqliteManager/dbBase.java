package br.com.jamaglian.primeiroprojetojava.sqliteManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jamaglian.primeiroprojetojava.defines;
import br.com.jamaglian.primeiroprojetojava.modelos.Filme;

public class dbBase {
	static String url;
	public dbBase() {
		File f = new File(System.getProperty("java.class.path"));
    	File dir = f.getAbsoluteFile().getParentFile();
    	String path = dir.toString();
        url = "jdbc:sqlite:" + path.split(":")[0] + File.separator + defines.DB_NAME.getValor().toString();
		criarArquivoDB(defines.DB_NAME.getValor().toString());
	}
    public static void criarArquivoDB(String nome) {
        try (Connection conn = DriverManager.getConnection(url);
        		Statement stmt = conn.createStatement()) {
        	if(conn != null) {
        	 String sql = "CREATE TABLE IF NOT EXISTS "+ defines.TB_FILMES.getValor() +" (\n"
                     + defines.TB_FILMES_ID.getValor() + " integer PRIMARY KEY,\n"
                     + defines.TB_FILMES_TITULO.getValor() + " VARCHAR(255) NOT NULL,\n"
                     + defines.TB_FILMES_SINOPSE.getValor() + " TEXT NOT NULL,\n"
                     + defines.TB_FILMES_GENERO.getValor() + " VARCHAR(25) NOT NULL,\n"
                     + defines.TB_FILMES_PLATAFORMA.getValor() + " VARCHAR(25) NOT NULL,\n"
                     + defines.TB_FILMES_ASSISTIU.getValor() + " BOOLEAN NOT NULL,\n"
                     + defines.TB_FILMES_AVALIACAO.getValor() + " DECIMAL(10,2) NOT NULL,\n"
                     + defines.TB_FILMES_IMAGEM.getValor() + " text\n"
                     + ");";
        	 stmt.execute(sql);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void inserirFilme(Filme oFilme) {
    	String sql;
    	if(oFilme.getImagem() != null) {
	    	 sql = "INSERT INTO "+ defines.TB_FILMES.getValor() +" ("
	   			 + defines.TB_FILMES_TITULO.getValor() + ","
	             + defines.TB_FILMES_SINOPSE.getValor() + ","
	             + defines.TB_FILMES_GENERO.getValor() + ","
	             + defines.TB_FILMES_PLATAFORMA.getValor() + ","
	             + defines.TB_FILMES_ASSISTIU.getValor() + ","
	             + defines.TB_FILMES_AVALIACAO.getValor() + ","
	             + defines.TB_FILMES_IMAGEM.getValor()
	             + ") VALUES (?,?,?,?,?,?,?)";
    	}else {
    		sql = "INSERT INTO "+ defines.TB_FILMES.getValor() +" ("
   	   			 + defines.TB_FILMES_TITULO.getValor() + ","
   	             + defines.TB_FILMES_SINOPSE.getValor() + ","
   	             + defines.TB_FILMES_GENERO.getValor() + ","
   	             + defines.TB_FILMES_PLATAFORMA.getValor() + ","
   	             + defines.TB_FILMES_ASSISTIU.getValor() + ","
   	             + defines.TB_FILMES_AVALIACAO.getValor()
   	             + ") VALUES (?,?,?,?,?,?)";
    	}
        try (Connection conn = DriverManager.getConnection(url);
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        	if(conn != null) {
        	//INSERT INTO filmes (titulo, sinopse, genero, plataforma, assistiu, avaliacao, imagem) VALUES ('Teste', 'djhfhfjaskda', 'batata', 'doce', 0, 0,'dsadhaidhhdiadhaidhiahdiahdiahdiadh')
        	 stmt.setString(1, oFilme.getTitulo());
        	 stmt.setString(2, oFilme.getSinopse());
        	 stmt.setString(3, oFilme.getGenero());
        	 stmt.setString(4, oFilme.getPlataforma());
        	 stmt.setBoolean(5, oFilme.getAssistido());
        	 stmt.setDouble(6, oFilme.getAvaliacao());
        	 if(oFilme.getImagem() != null) {
        		 stmt.setString(7, oFilme.getImagem()); 
        	 }
        	 stmt.execute();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Filme> pegarFilmes() {
    	List<Filme> filmes = new ArrayList<>();
    	String sql = "SELECT * FROM " + defines.TB_FILMES.getValor();
    	try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
			 Filme oFilme = new Filme();
			 while (rs.next()) {
		 		 oFilme.setImagem(rs.getString(defines.TB_FILMES_IMAGEM.getValor().toString()));
				 oFilme.setTitulo(rs.getString(defines.TB_FILMES_TITULO.getValor().toString()));
				 oFilme.setSinopse(rs.getString(defines.TB_FILMES_SINOPSE.getValor().toString()));
				 oFilme.setGenero(rs.getString(defines.TB_FILMES_GENERO.getValor().toString()));
				 oFilme.setPlataforma(rs.getString(defines.TB_FILMES_PLATAFORMA.getValor().toString()));
				 oFilme.setAssistido(rs.getBoolean(defines.TB_FILMES_ASSISTIU.getValor().toString()));
				 oFilme.setAvaliacao(rs.getDouble(defines.TB_FILMES_AVALIACAO.getValor().toString()));
				 filmes.add(oFilme);
				 oFilme = new Filme();
			  }
    	   	  return filmes;
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return filmes;	
    }
}
