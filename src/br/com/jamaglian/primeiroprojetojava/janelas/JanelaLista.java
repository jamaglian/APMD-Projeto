package br.com.jamaglian.primeiroprojetojava.janelas;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import br.com.jamaglian.primeiroprojetojava.database.dao.FilmeDao;
import br.com.jamaglian.primeiroprojetojava.modelos.Filme;
import br.com.jamaglian.primeiroprojetojava.sqliteManager.dbBase;

public class JanelaLista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public JanelaLista() {
		init();
	}
	
	private void init() {
		setLayout(new GridLayout());
        try {
			List<Filme> filmes = dbBase.pegarFilmes();
			
			@SuppressWarnings("serial")
			DefaultTableModel modelo = new DefaultTableModel() {
				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};
			JTable tabela = new JTable(modelo);
			
			configureTableModel(modelo);
			configureTable(tabela);
			if(filmes != null) {
				filmes.forEach(filme -> {
					modelo.addRow(new Object[] {
						filme.getTitulo(),
						filme.getSinopse(),
						filme.getGenero(),
						filme.getPlataforma(),
						filme.getAssistido(),
						filme.getAvaliacao()
					});
				});
			}
			JScrollPane scroll = new JScrollPane(tabela);
			add(scroll);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void configureTableModel(DefaultTableModel modelo) {
		modelo.addColumn("Título");
		modelo.addColumn("Sinopse");
		modelo.addColumn("Gênero");
		modelo.addColumn("Plataforma");
		modelo.addColumn("Assistido");
		modelo.addColumn("Avaliação");
	}
	
	private void configureTable(JTable tabela) {
		tabela.getColumnModel().getColumn(0)
			.setPreferredWidth(45);
		tabela.getColumnModel().getColumn(1)
			.setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2)
			.setPreferredWidth(25);
		tabela.getColumnModel().getColumn(3)
			.setPreferredWidth(25);
		tabela.getColumnModel().getColumn(4)
			.setPreferredWidth(5);
		tabela.getColumnModel().getColumn(5)
		.setPreferredWidth(3);
		
		tabela.setFont(new Font(null, Font.PLAIN, 16));
	}

}
