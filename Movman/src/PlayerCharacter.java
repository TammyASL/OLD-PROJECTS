import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PlayerCharacter {
	
	static InputStreamReader reader = new InputStreamReader(System.in); 
	int HP;
	int X;
	int Y;
	int hy;
	int wx;
	int bX;
	int bY;
	char lastchar;
	int reload=0;
	int MA;
	int NR;
	Projectile[] Bullet= new Projectile[MA];
	int ammo;
	public PlayerCharacter(int health,int x,int y,int maxammo){
		this.HP=health;
		this.X=x;
		this.Y=y;
		this.hy=(y+1)*4;
		this.wx=(x+1)*4;
		this.MA=maxammo;
		ammo=MA;
		Bullet= new Projectile[MA+1];
	}
	public void move(char key) throws IOException{
	    if (key == 'w' && Y>1 || key == 'W' && Y>1){
	    	Y=Y-1;
	    	lastchar=key;
		}
	    if (key == 's' && Y<hy-2 || key == 'S' && Y<hy-2){
			Y=Y+1;
			lastchar=key;
		}
	    if (key == 'a' && X>1 || key == 'A' && X>1){
			X=X-1;
			lastchar=key;
		}
	    if (key == 'd' && X<wx-2 || key == 'D' && X<wx-2){
			X=X+1;
			lastchar=key;
		}
	    if(NR==1 && reload == 0){
	    	Reload();
	    }
	    if(reload!=0 && ammo==-1){
	    	reload--;
	    }
	    if(ammo!=-1){
	    if (key == ' ' && NR==0){
			shoot(lastchar);
	    }
			
		}else{
			NR=1;
			
		}
	    
	    
	    
	}
	public void Reload(){
		if(ammo==-1){
			ammo=0;
		}
		if(Bullet[MA-ammo]==null || Bullet[MA-ammo].HP==0){
			 if(ammo<MA){
			ammo++;
			 }
		}
		if(ammo==MA){
			NR=0;
		}
	}
	public void shoot(char c){
			Bullet[MA-ammo]=new Projectile (1,X,Y,c);
			ammo--;
			reload++;

		}
	}

