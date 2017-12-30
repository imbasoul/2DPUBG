package yy1020;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Howard on 2017/12/29.
 */
public class SmallMap {//implements Runnable {
//    private int status[][] = new int[300][300];
    private final int a = 3, EPS = 10, BIAS = 19 * 50 - 10 - a * 70;
    public void paint(Graphics g) {
        int x = Player.getI(), y = Player.getJ();
        for(int i = 0; i < 70; ++i) {
            for(int j = 0; j < 70; ++j) {
                if(ReadMapFile.map1[i][j] <= 2) g.setColor(Color.yellow);// green
                else if(ReadMapFile.map1[i][j] == 3) g.setColor(Color.gray);// brown
                if(ReadMapFile.map2[i][j] != 0) g.setColor(Color.green);// blue
                if((i == x && j == y) || (i - 1 == x && j == y) || (i + 1 == x && j == y) ||
                        (i == x && j + 1 == y) || (i == x && j - 1 == y)) g.setColor(Color.red);// white
                g.fill3DRect(EPS + j * a, BIAS - EPS + i * a, a, a, false);
            }
        }
//        g.clearRect();
//        ImageIcon shilaimu=new ImageIcon("Ê·À³Ä·"+num+".png");
//        g.drawImage(shilaimu.getImage(), x- x+Player.px, y-Player.y+Player.py , 50, 50, null);
    }
//    @Override
//    public void run() {
//
//    }
}