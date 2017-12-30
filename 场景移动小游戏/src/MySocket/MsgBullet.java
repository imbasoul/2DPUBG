package MySocket;

public class MsgBullet {
	private String kind;
    private int port;
    private int x;
    private int y;
    private int direction; //1ÉÏ£¬2ÏÂ£¬3×ó£¬4ÓÒ
    private int id; //×Óµ¯±àºÅ
    
    public MsgBullet(){
    	this.kind = "2";
    }
    
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
