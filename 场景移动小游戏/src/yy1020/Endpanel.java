package yy1020;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Endpanel extends JPanel implements gameConfig{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Endpanel() {
		super();
		init();
	}
	
	@SuppressWarnings("serial")
	public void init(){
		this.setBounds(1,1,frameX,frameY);
		this.setLayout(null);
		this.setVisible(false);
		//this.setOpaque(false);//设置面板透明
		
		
		JButton on = new JButton();
        on.setSize(new Dimension(350, 120));
        on.setLocation(280, 300);
        on.setIcon(new ImageIcon("img\\mainmenu.png"));
       // on.mouseUp(new Event(0, 0, 0), 280, 300);
        on.addActionListener(new AbstractAction() { 
			@Override
            public void actionPerformed(ActionEvent e) {
                test.mf.dispose();
            }
        });
        this.add(on);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon bg=new ImageIcon("img\\endbg.png");
		g.drawImage(bg.getImage(), 0, 0, frameX, frameY, null);
		
		/*
			g.setColor(Color.BLUE);
			Font font = new Font("黑体", 600, 25);
			g.setFont(font);
			g.drawString(npc.name+":", 30, 30);
			g.setColor(Color.GREEN);
			g.drawString(npc.talk, 60, 65);*/
		
	}

}
