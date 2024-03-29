package visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import modelo.Campo;
import modelo.CampoEvento;
import modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
	this.campo = campo;
	setBackground(BG_PADRAO);
	setOpaque(true);
	setBorder(BorderFactory.createBevelBorder(0));
	
	addMouseListener(this);
	campo.registrarObservador(this);
	}
	
	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplosao();
			break;
		default:
			aplicarEstiloPadrao();
		}
	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
		
	}

	private void aplicarEstiloExplosao() {
		setBackground(BG_EXPLODIR);
		setBackground(Color.RED);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setBackground(Color.BLUE);
		setText("M");
		
	}

	private void aplicarEstiloAbrir() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		switch (campo.minasNaVizinhanca()) {
		case 1: 
			setForeground(TEXTO_VERDE);
			break;
			case 2:
				setForeground(Color.BLUE);
				break;
			case 3:
				setForeground(Color.YELLOW);
				break;
			case 4:
			case 5:
			case 6:
				setForeground(Color.RED);
				break;
		default:
			setForeground(Color.PINK);
			
		}
		String valor = !campo.vizinhancasSegura() ? campo.minasNaVizinhanca() + "" :"";
		setText(valor);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();
		}else {
			campo.alternarMarcacao();;
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
