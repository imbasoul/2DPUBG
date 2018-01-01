/*package yy1020;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Dsz implements Runnable{
	public int x;
	public int y;
	public int num;
	public int health;
	public boolean islive;
	public Bullet bullet[]=new Bullet[5];
	public Dsz(int x,int y,int num) {
		this.x=x;
		this.y=y;
		this.num=num;
		health=100;
		islive=true;
	}
	public void paint(Graphics g) {
		ImageIcon shilaimu=new ImageIcon("史莱姆"+num+".png");
		g.drawImage(shilaimu.getImage(), x-Player.x+Player.px-25, y-Player.y+Player.py-25, 50, 50, null);
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				bullet[i].paint(g);
				//System.out.println("123123");
			}
		}
	}
	//更新敌方子弹对我方造成的伤害
	public void check() {
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				if(bullet[i].getI()==Player.getI()&&bullet[i].getJ()-1==Player.getJ()) {
					Player.health.de(1.0);
					bullet[i].isLive=false;
				}
			}
		}
	}
	public int getI(){
		return (y-(50/2))/50;
	}
	//得到角色在数组中的位置J
	public int getJ(){
		return (x-(50/2))/50;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int bnum=0;
		while(true) {
			try {
				Thread.sleep(20);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(health<0) {
				islive=false;
				break;
			}
			if(bullet[bnum]==null||(bullet[bnum]!=null&&bullet[bnum].isLive!=true)) {
				bullet[bnum]=new Bullet(x+25, y, bnum,0);
				Thread thb=new Thread(bullet[bnum]);
				thb.start();
			}
			bnum=(bnum+1)%5;
			check();
			//在这里改变x，y和子弹的值
		}
	}
}
*/
/*package yy1020;

import MySocket.Msg;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Dsz implements Runnable{
	private int port;
    public int x;
	public int y;
	public int num;
	public int health;
	public boolean islive;
	public Bullet bullet[]=new Bullet[5];

	public Dsz(){};
	public Dsz(int x,int y,int num) {
		this.x=x;
		this.y=y;
		this.num=num;
		health=100;
		islive=true;
	}

	public Msg toMsg (){
	    Msg msg = new Msg();
//	    msg.setBullet(this.getBullet());
	    msg.setHealth(this.getHealth());
	    msg.setIslive(this.isIslive());
	    msg.setPort(this.getPort());
	    msg.setX(this.getX());
	    msg.setY(this.getY());
	    return msg;
    }
    public void fromMsg(Msg msg){
	    this.setPort(msg.getPort());
//	    this.setBullet(msg.getBullet());
	    this.setHealth(msg.getHealth());
	    this.setIslive(msg.isIslive());
	    this.setX(msg.getX());
	    this.setY(msg.getY());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public Bullet[] getBullet() {
        return bullet;
    }

    public void setBullet(Bullet[] bullet) {
        this.bullet = bullet;
    }

    public void paint(Graphics g) {
		ImageIcon shilaimu = new ImageIcon("史莱姆"+num+".png");
		if(shilaimu == null) System.out.println("史莱姆fuck");
		g.drawImage(shilaimu.getImage(), x-Player.x+Player.px-25, y-Player.y+Player.py-25, 50, 50, null);
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				bullet[i].paint(g);
			}
		}
	}
	//????з????????????????
	public void check() {
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				if(bullet[i].getI()==Player.getI()&&bullet[i].getJ()-1==Player.getJ()) {
					Player.health.de(1.0);
					bullet[i].isLive=false;
				}
			}
		}
	}
	public int getI(){
		return (y-(50/2))/50;
	}
	//?????????????е?λ??J
	public int getJ(){
		return (x-(50/2))/50;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int bnum=0;
		while(true) {
			try {
				Thread.sleep(20);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(health<0) {
				islive=false;
				break;
			}
			if(bullet[bnum]==null||(bullet[bnum]!=null&&bullet[bnum].isLive!=true)) {
				bullet[bnum]=new Bullet(x+25, y+25, bnum,0);
				Thread thb=new Thread(bullet[bnum]);
				thb.start();
			}
			bnum=(bnum+1)%5;
			check();
			//????????x??y????????
		}
	}
}*/

package yy1020;

import MySocket.MsgCharacter;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Dsz implements Runnable{
	private int port;
    public int x;
	public int y;
	public int num;
	public int health;
	public boolean islive=false;
	public Bullet bullet[]=new Bullet[5];

	public Dsz(){};
	public Dsz(int x,int y,int num) {
		this.x=x;
		this.y=y;
		this.num=num;
		health=100;
		islive=true;
	}

	public MsgCharacter toMsg (){
	    MsgCharacter msg = new MsgCharacter();
	    msg.setHealth(this.getHealth());
	    msg.setIslive(this.isIslive());
	    msg.setPort(this.getPort());
	    msg.setX(this.getX());
	    msg.setY(this.getY());
	    return msg;
    }
	
    public void fromMsg(MsgCharacter msg){
	    this.setPort(msg.getPort());
//	    this.setBullet(msg.getBullet());
	    this.setHealth(msg.getHealth());
	    this.setIslive(msg.isIslive());
	    this.setX(msg.getX());
	    this.setY(msg.getY());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public Bullet[] getBullet() {
        return bullet;
    }

    public void setBullet(Bullet[] bullet) {
        this.bullet = bullet;
    }

    public void paint(Graphics g) {
		ImageIcon shilaimu = new ImageIcon("史莱姆"+0+".png");
		if(shilaimu == null) System.out.println("史莱姆fuck");
		g.drawImage(shilaimu.getImage(), x-Player.x+Player.px-25, y-Player.y+Player.py-25, 50, 50, null);
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				bullet[i].paint(g);
			}
		}
	}
	//????з????????????????
	public void check() {
		for(int i=0;i<=4;i++) {
			if(bullet[i]!=null&&bullet[i].isLive==true) {
				if(bullet[i].getI()==Player.getI()&&bullet[i].getJ()-1==Player.getJ()) {
					Player.health.de(1.0);
					bullet[i].isLive=false;
				}
			}
		}
	}
	public int getI(){
		return (y-(50/2))/50;
	}
	//?????????????е?λ??J
	public int getJ(){
		return (x-(50/2))/50;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int bnum=0;
		while(true) {
			try {
				Thread.sleep(20);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(health<0) {
				islive=false;
				break;
			}
			/*if(bullet[bnum]==null||(bullet[bnum]!=null&&bullet[bnum].isLive!=true)) {
				bullet[bnum]=new Bullet(x+50, y+25, bnum,0);
				Thread thb=new Thread(bullet[bnum]);
				thb.start();
			}
			bnum=(bnum+1)%5;*/
			check();
			//????????x??y????????
		}
	}
}
