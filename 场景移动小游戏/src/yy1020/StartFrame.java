package yy1020;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Howard on 2017/12/30.
 */

//class MouseListener extends MouseAdapter {
//    public void Mouse
//}
/*
public class StartFrame extends JFrame implements gameConfig {
    JPanel panel;
    JFrame
    public JButton on;
    final int C = 350;
    static int st = 0;
    public StartFrame() {
        super();
        on = new JButton();
        this.setTitle(new String("Main"));
        this.setSize(frameX - C, frameY);
        this.setDefaultCloseOperation(3);
        
        on.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(st == 0) {
                    st = 1;
                    test.start();
                }
            }
        });
        on.setIcon(MenuStartButton);

        this.add(on);



        //this.setIconImage(MenuBackground.getImage());

        //panel = setpanel();
        //panel.add(on);
        // this.add(panel);

        this.setVisible(true);
//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                int eButton = e.getButton();
//                if(eButton != 0 && st == 0) {
//                    test.start();
//                    st = 1;
//                }
//            }
//        });
    }

    public JPanel setpanel() {
        JPanel panel = new MyPanel();
        panel.setBounds(18, 5, panelX - C, panelY);
		//panel.setPreferredSize(new Dimension(panelX, panelY));
        panel.setLayout(null);
        panel.setBackground(Color.green);

        return panel;
    }
    class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //
            Image bg = MenuBackground.getImage();
            g.drawImage(bg, 0, 0, null);


        }
    }
}
*/
public class StartFrame implements gameConfig {
    JFrame jFrame;
    JPanel jPanel;
    public JButton on;
    final int C = 350;
    static int st = 0;

    class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //
            Image bg = MenuBackground.getImage();
            g.drawImage(bg, 0, 0, null);


        }
    }
    public StartFrame() {
        jFrame = new JFrame();
        jFrame.setTitle(new String("Main"));
        jFrame.setSize(frameX - C, frameY);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);
        on = new JButton(MenuStartButton);
        on.setSize(new Dimension(260, 107));
        on.setLocation(190, 300);
        
        on.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st == 0) {
                    st = 1;
                    test.start();
                    jFrame.dispose();
                }
            }
        });
        jPanel = new MyPanel();
        jPanel.setBounds(18, 5, panelX - C, panelY);
		//panel.setPreferredSize(new Dimension(panelX, panelY));
        jPanel.setLayout(null);
        jPanel.setBackground(Color.green);
        jPanel.add(on);

        jFrame.add(jPanel);
        //jFrame.add(on);
        jFrame.setVisible(true);
        jFrame.setIconImage(icon102.getImage());
    }
}
