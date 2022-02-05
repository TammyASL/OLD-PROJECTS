import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Char {
	Random RNG = new Random();
	int HP;
	int X;
	int Y;
	int hy;
	int wx;
	public Char(int health,int x,int y){
		this.HP=health;
		this.X=x;
		this.Y=y;
		this.hy=(y+1)*2;
		this.wx=(x+1)*2;
	}
	public Char(Char c){
		this.HP=c.HP;
		this.X=c.X;
		this.Y=c.Y;
		this.hy=c.hy;
		this.wx=c.wx;
	}
	public int damage(){
		return 1;
	}
	public void move(int x,int y){
		int die=RNG.nextInt(HP+1);
		if(die<=(HP+1)/2){
		if(X<x){
			X=X+1;
		}
		if(X>x){
			X=X-1;
		}
		}else{
		if(Y<y){
			Y=Y+1;
		}
		if(Y>y){
			Y=Y-1;
		}
		}

		
	}
	
}
	
