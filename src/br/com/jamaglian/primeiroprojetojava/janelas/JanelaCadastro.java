package br.com.jamaglian.primeiroprojetojava.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.jamaglian.primeiroprojetojava.modelos.Filme;
import br.com.jamaglian.primeiroprojetojava.res.StarRater;
import br.com.jamaglian.primeiroprojetojava.sqliteManager.dbBase;

public class JanelaCadastro extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JButton button_img;
	JTextField titulo;
	JTextArea sinopse;
	JComboBox<String> generos;
	ButtonGroup ondeAssistirGrupo;
	JRadioButton netflix;
	JRadioButton primeVideo;
	JRadioButton pirateBay;
	JCheckBox assistido;
	StarRater estrelas;
	String imagem_enc = null;
	public JanelaCadastro() {
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		add(campoImagem(), BorderLayout.WEST);
        add(campos(), BorderLayout.CENTER);
        add(botoesExtras(), BorderLayout.EAST);
        add(botoesDeAcao(), BorderLayout.SOUTH);

	}
	private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        fileInputStreamReader.close();
        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
    }
	private void clearAll() {
		titulo.setText("");
		sinopse.setText("");
		generos.setSelectedIndex(0);
		ondeAssistirGrupo.clearSelection();
		assistido.setSelected(false);
		estrelas.setSelection(0);
		ImageIcon __buttonIcon = new ImageIcon("src/br/com/jamaglian/primeiroprojetojava/res/uploadimg.png"); // load the image to a imageIcon
		Image __image = __buttonIcon.getImage(); // transform it 
		Image __newimg = __image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		button_img.setIcon(new ImageIcon(__newimg));
		imagem_enc = null;
	}
	private JPanel campoImagem() {
		JPanel campoImagem = new JPanel(new GridLayout(1, 1));
		

		ImageIcon buttonIcon = new ImageIcon("src/br/com/jamaglian/primeiroprojetojava/res/uploadimg.png"); // load the image to a imageIcon
		Image image = buttonIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		button_img = new JButton(new ImageIcon(newimg));
		button_img.addActionListener(acao -> {
			final JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
			int returnVal = fc.showOpenDialog(JanelaCadastro.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            try {
	            	imagem_enc = encodeFileToBase64Binary(file);
					byte[] btDataFile = Base64.getDecoder().decode(imagem_enc);
					BufferedImage image_decoded = ImageIO.read(new ByteArrayInputStream(btDataFile));
					ImageIcon _buttonIcon = new ImageIcon(image_decoded); // load the image to a imageIcon
					Image _image = _buttonIcon.getImage(); // transform it 
					Image _newimg = _image.getScaledInstance(140, 320,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
					_buttonIcon = new ImageIcon(_newimg); // load the image to a imageIcon
					button_img.setIcon(_buttonIcon);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //log.append("Opening: " + file.getName() + "." + newline);
	        }else {
	        	ImageIcon __buttonIcon = new ImageIcon("src/br/com/jamaglian/primeiroprojetojava/res/uploadimg.png"); // load the image to a imageIcon
	    		Image __image = __buttonIcon.getImage(); // transform it 
	    		Image __newimg = __image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    		button_img.setIcon(new ImageIcon(__newimg));
	    		imagem_enc = null;
	        }
        });
		button_img.setContentAreaFilled(false);
		button_img.setPreferredSize(new Dimension(200, 200));
		button_img.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));

		campoImagem.add(button_img);
		return campoImagem;
	}
	
	private JScrollPane campos() {
		JPanel campos = new JPanel(new GridLayout(6, 1));
		campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));
		JLabel tituloTexto = new JLabel("Título");
		titulo = new JTextField();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		titulo.setBorder(BorderFactory.createCompoundBorder(border, 
		      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		titulo.setMaximumSize(new Dimension(450, 5) );
		campos.add(tituloTexto);
		campos.add(titulo);
		
		JLabel sinopseTexto = new JLabel("Sinopse");
		 sinopse = new JTextArea();
		sinopse.setBorder(BorderFactory.createCompoundBorder(border, 
		      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		sinopse.setMaximumSize(new Dimension(450, 86000) );
		campos.add(sinopseTexto);
		campos.add(sinopse);

		JLabel generoTexto = new JLabel("Gênero");
		generos = new JComboBox<>(new String[] {"Ação", "Romance", "Animais"});
		generos.setMaximumSize(new Dimension(450, 5) );
		campos.add(generoTexto);
		campos.add(generos);
		
		JScrollPane campos_s = new JScrollPane( campos );
		campos_s.setBorder(BorderFactory.createEmptyBorder());
		return campos_s;
	}
	
	private JPanel botoesExtras() {
		JPanel outrosBotoes = new JPanel(new GridLayout(7, 1));
		
		ondeAssistirGrupo = new ButtonGroup();
		
		JLabel  ondeAssistirTexto = new JLabel("Onde assistir");
		outrosBotoes.add(ondeAssistirTexto);
		
		netflix = new JRadioButton("Netflix");
		netflix.setActionCommand("Netflix");
		ondeAssistirGrupo.add(netflix);
		outrosBotoes.add(netflix);
		
		primeVideo = new JRadioButton("Prime Video");
		primeVideo.setActionCommand("Prime Video");
		ondeAssistirGrupo.add(primeVideo);
		outrosBotoes.add(primeVideo);
		
		pirateBay = new JRadioButton("Pirate Bay");
		pirateBay.setActionCommand("Pirate Bay");
		ondeAssistirGrupo.add(pirateBay);
		outrosBotoes.add(pirateBay);
		
		assistido = new JCheckBox("Assistido");
		outrosBotoes.add(assistido);
		
		
		estrelas = new StarRater(5);
		outrosBotoes.add(estrelas);
		
		return outrosBotoes;
	}
	
	private JPanel botoesDeAcao() {
		JPanel botoesDeAcao = new JPanel();
		
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(acao -> {
			if((titulo.getText() != null && titulo.getText() != "") 
				&& (sinopse.getText() != null && sinopse.getText() != "") 
				&& (generos.getSelectedItem().toString() != null && generos.getSelectedItem().toString() != "") 
				&& (ondeAssistirGrupo.getSelection() != null)
			){
				Filme oFilme = new Filme();
				oFilme.setImagem(imagem_enc);
				oFilme.setTitulo(titulo.getText());
				oFilme.setSinopse(sinopse.getText());
				oFilme.setGenero(generos.getSelectedItem().toString());
				oFilme.setPlataforma(ondeAssistirGrupo.getSelection().getActionCommand());
				oFilme.setAssistido(assistido.isSelected());
				oFilme.setAvaliacao(Double.valueOf(estrelas.getSelection()));
				System.out.println(oFilme);
				dbBase.inserirFilme(oFilme);
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
					    "Filme adicionado com sucesso!",
					    "Sucesso",
					    JOptionPane.PLAIN_MESSAGE);
				clearAll();
			}else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
					    "Para adicionar o filme preencha todas as informações!\n(Obs: A foto é o unico campo opcional)",
					    "Erro",
					    JOptionPane.ERROR_MESSAGE);
			}
        });
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(acao -> {
			if (JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar ?", "Confirme",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				clearAll();
			}
        });
		botoesDeAcao.add(botaoSalvar);
		botoesDeAcao.add(botaoCancelar);
		
		return botoesDeAcao;
	}
	
}
