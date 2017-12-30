package yy1020;

public class NPC {
	//NPC的名字
	String name;
	//血量
	int hp;
	//等级
	int level;
	//经验
	int exp;
	//金钱
	int money;
	//是否活着 1活着 0死了
	int islive;
	//对话
	String talk = "我是大傻逼！";
	
	public NPC(String name,int hp,int level,int exp,int money,int islive,String talk) {
		this.name = name;
		this.hp = hp;
		this.level = level;
		this.exp = exp;
		this.money = money;
		this.islive = islive;
		this.talk = talk;
	}
}
