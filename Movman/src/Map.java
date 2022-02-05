import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Map {
	char [][] map;
	int wx;
	int hy;
	int mobwx;
	int mobhy;
	int mob2wx;
	int mob2hy;
	PlayerCharacter PC;
	Char MOB;
	Char MOB2;
	Random RNG = new Random();
	int em=1;
	int Ammo=6;
	
	public Map(int hp,int ao){
		this.wx=RNG.nextInt(25)+8;
		this.hy=RNG.nextInt(25)+8;
		mobwx=wx/4-1;
		mobhy=hy/4-1;
		mob2wx=wx*3/4-1;
		mob2hy=hy*3/4-1;
		map=new char[wx][hy];
		PC= new PlayerCharacter(hp, wx/2-1, hy/2-1,ao);
		PC.hy=hy;
		PC.wx=wx;
		
		for(int l=0;l<=wx-1;l++){
			for(int q=0;q<=hy-1;q++){
				map[l][q]='.';
			}
			map[l][0]='=';
			map[l][hy-1]='=';
		if(l==0 || l==wx-1){
		for(int i=1;i<=hy-2;i++){
			map[l][i]='|';
		}
		}
		
		}
		if(wx/4-1>=wx-1||wx/4-1<=0){
			int die=RNG.nextInt(2);
			if(die==0){
			mobwx=wx-1;
			}else{
				mobwx=1;
			}
		}
		if(hy/4-1>=hy-1||hy/4-1<=0){
			int die=RNG.nextInt(2);
			if(die==0){
			mobhy=hy-1;
			}else{
				mobhy=1;
			}
		}
		if(wx*3/4-1>=wx-1||wx*3/4-1<=0){
			int die=RNG.nextInt(2);
			if(die==0){
			mob2wx=wx-1;
			}else{
				mob2wx=1;
			}
		}
		if(hy*3/4-1>=hy-1||hy*3/4-1<=0){
			int die=RNG.nextInt(2);
			if(die==0){
			mob2hy=hy-1;
			}else{
				mob2hy=1;
			}
		}
		MOB=new Char(hp/2, mobwx, mobhy);
		MOB2=new Char(hp/2, mob2wx, mob2hy);
		map[0][0]='/';
		map[wx-1][0]='\\';
		map[wx-1][hy-1]='/';
		map[0][hy-1]='\\';
		map[wx/2-1][hy/2-1]='@';
		map[wx/2][hy/2-1]='d';
		map[wx/2-2][hy/2-1]='a';
		map[wx/2-1][hy/2]='s';
		map[wx/2-1][hy/2-2]='w';
		
	}
	public void turn(char key) throws IOException, InterruptedException{
		MOB.hy=hy;
		MOB.wx=wx;
		MOB2.hy=hy;
		MOB2.wx=wx;
		int die=RNG.nextInt((PC.HP+MOB.HP+MOB2.HP+3)/3)+1;
		if(map[wx/2][hy/2-1]=='d' || map[wx/2-2][hy/2-1]=='a' || map[wx/2-1][hy/2]=='s' || map[wx/2-1][hy/2-2]=='w'){
			map[wx/2][hy/2-1]=' ';
			map[wx/2-2][hy/2-1]=' ';
			map[wx/2-1][hy/2]=' ';
			map[wx/2-1][hy/2-2]=' ';
			map[PC.X][PC.Y]=' ';
			map[MOB.X][MOB.Y]='8';
			map[MOB2.X][MOB2.Y]='8';
			
		}
		for(int i=0;i<=PC.MA;i++){
			if(PC.Bullet.length>0){
		if(PC.Bullet[i]!=null){
			if(PC.Bullet[i].HP==0){
				PC.Bullet[i]=null;
				print();
				TimeUnit.MILLISECONDS.sleep(50);
			}else{
				while(PC.Bullet[i].HP!=0){
					PC.Bullet[i].YH=hy;
					PC.Bullet[i].XW=wx;
					if(PC.Bullet[i].Y==hy)
			map[PC.Bullet[i].X][PC.Bullet[i].Y]=' ';
			PC.Bullet[i].move();
			if(PC.Bullet[i].D=='w' || PC.Bullet[i].D=='W' || PC.Bullet[i].D=='s' || PC.Bullet[i].D=='S'){
			map[PC.Bullet[i].X][PC.Bullet[i].Y]='|';
			}
			if(PC.Bullet[i].D=='a' || PC.Bullet[i].D=='A' || PC.Bullet[i].D=='d' || PC.Bullet[i].D=='D'){
				map[PC.Bullet[i].X][PC.Bullet[i].Y]='-';	
			}
			if(PC.Bullet[i].D=='X' || PC.Bullet[i].D=='"' || PC.Bullet[i].D=='_' || PC.Bullet[i].D==';'){
				map[PC.Bullet[i].X][PC.Bullet[i].Y]=PC.Bullet[i].D;
			}
			print();
			TimeUnit.MILLISECONDS.sleep(50);
			map[PC.Bullet[i].X][PC.Bullet[i].Y]=' ';
			if(PC.Bullet[i].X==MOB.X && PC.Bullet[i].Y==MOB.Y){
				if(MOB.HP>0){
				MOB.HP--;
				}
				if(MOB.HP==0){
					if(MOB2.HP!=0){
					MOB2.HP--;
					}
					MOB=new Char(MOB2);
					MOB.HP=0;
				}
				PC.Bullet[i].HP=0;
				map[MOB.X][MOB.Y]='$';
				print();
				TimeUnit.MILLISECONDS.sleep(50);
				map[MOB.X][MOB.Y]='8';
				Ammo=PC.ammo;
				break;
			}
			if(PC.Bullet[i].X==PC.X && PC.Bullet[i].Y==PC.Y){
				PC.Bullet[i].HP=0;
				map[PC.X][PC.Y]='@';
				print();
				TimeUnit.MILLISECONDS.sleep(50);
				Ammo=PC.ammo;
				break;
			}
			if(PC.Bullet[i].X==MOB.X && PC.Bullet[i].Y==MOB.Y){
				if(MOB.HP>0){
					MOB.HP--;
					map[MOB.X][MOB.Y]='&';
					}
					if(MOB.HP==0){
						if(MOB2.HP!=0){
							MOB2.HP--;
							}
						MOB=new Char(MOB2);
						MOB.HP=0;
					}
				PC.Bullet[i].HP=0;
				map[MOB.X][MOB.Y]='$';
				print();
				TimeUnit.MILLISECONDS.sleep(50);
				map[MOB.X][MOB.Y]='8';
				Ammo=PC.ammo;
				break;
			}
			if(PC.Bullet[i].X==MOB2.X && PC.Bullet[i].Y==MOB2.Y){
				if(MOB2.HP>0){
					MOB2.HP--;
					map[MOB2.X][MOB2.Y]='&';
					}
					if(MOB2.HP==0){
						if(MOB.HP!=0){
							MOB.HP--;
							}
						MOB2=new Char(MOB);
						MOB2.HP=0;
					}
				PC.Bullet[i].HP=0;
				map[MOB2.X][MOB2.Y]='$';
				print();
				TimeUnit.MILLISECONDS.sleep(50);
				map[MOB2.X][MOB2.Y]='8';
				Ammo=PC.ammo;
				break;
			}
			}
		}
		}
		}
		}
		map[PC.X][PC.Y]=' ';
		PC.move(key);
		Ammo=PC.ammo;
		map[PC.X][PC.Y]='@';
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		map[PC.X][PC.Y]=' ';
		if(PC.X==MOB.X && PC.Y==MOB.Y){
			if(MOB.HP>0){
				MOB.HP--;
				}
				if(MOB.HP==0){
					if(MOB2.HP!=0){
						MOB2.HP--;
						}
					MOB=new Char(MOB2);
					MOB.HP=0;
				}
			map[PC.X][PC.Y]='*';
			print();
			TimeUnit.MILLISECONDS.sleep(100);
			map[PC.X][PC.Y]=' ';
		}
		while(PC.X==MOB.X && PC.Y==MOB.Y){
			if(die<=(PC.HP+MOB.HP+2)/2){
			if(PC.X<wx-2){
				PC.X=PC.X+1;
			}else{
			if(PC.X>=2){
				PC.X=PC.X-1;
			}
			}
			}else{
			if(PC.Y<hy-2){
				PC.Y=PC.Y+1;
			}else{
			if(PC.Y>=2){
				PC.Y=PC.Y-1;
			}
			}
			}
		}
		if(PC.X==MOB2.X && PC.Y==MOB2.Y){
			if(MOB2.HP>0){
				MOB2.HP--;
				}
				if(MOB2.HP==0){
					if(MOB.HP!=0){
						MOB.HP--;
						}
					MOB2=new Char(MOB);
					MOB2.HP=0;
				}
			map[PC.X][PC.Y]='*';
			print();
			TimeUnit.MILLISECONDS.sleep(100);
			map[PC.X][PC.Y]=' ';
		}
		while(PC.X==MOB2.X && PC.Y==MOB2.Y){
			if(die<=(PC.HP+MOB2.HP+2)/2){
			if(PC.X<wx-2){
				PC.X=PC.X+1;
			}else{
			if(PC.X>=2){
				PC.X=PC.X-1;
			}
			}
			}else{
			if(PC.Y<hy-2){
				PC.Y=PC.Y+1;
			}else{
			if(PC.Y>=2){
				PC.Y=PC.Y-1;
			}
			}
			}
		}
		map[PC.X][PC.Y]='@';
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		if(em==1){
		map[MOB.X][MOB.Y]=' ';
		if(MOB.HP!=0){
		MOB.move(PC.X, PC.Y);
		}else{
			MOB.X=MOB2.X;
			MOB.Y=MOB2.Y;
		}
		map[MOB.X][MOB.Y]='8';
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		map[MOB.X][MOB.Y]=' ';
		if(PC.X==MOB.X && PC.Y==MOB.Y){
			PC.HP--;
			map[MOB.X][MOB.Y]='#';
			print();
			TimeUnit.MILLISECONDS.sleep(200);
			map[MOB.X][MOB.Y]=' ';
		}
		while(PC.X==MOB.X && PC.Y==MOB.Y){
			if(die<=(PC.HP+MOB.HP+2)/2){
			if(MOB.X<wx-2){
				MOB.X=MOB.X+1;
			}else{
			if(MOB.X>=2){
				MOB.X=MOB.X-1;
			}
			}
			}else{
			if(MOB.Y<hy-2){
				MOB.Y=MOB.Y+1;
			}else{
			if(MOB.Y>=2){
				MOB.Y=MOB.Y-1;
			}
			}
			}
		}
		map[MOB.X][MOB.Y]='8';
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		}
		if(em==-1){
		map[MOB2.X][MOB2.Y]=' ';
		if(MOB2.HP!=0){
		MOB2.move(PC.X, PC.Y);
		}else{
			MOB2.X=MOB.X;
			MOB2.Y=MOB.Y;
		}
		map[MOB2.X][MOB2.Y]='8';
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		map[MOB2.X][MOB2.Y]=' ';
		if(PC.X==MOB2.X && PC.Y==MOB2.Y){
			PC.HP--;
			map[MOB2.X][MOB2.Y]='#';
			print();
			TimeUnit.MILLISECONDS.sleep(200);
			map[MOB2.X][MOB2.Y]=' ';
		}
		while(PC.X==MOB2.X && PC.Y==MOB2.Y){
			if(die<=(PC.HP+MOB2.HP+2)/2){
			if(MOB2.X<wx-2){
				MOB2.X=MOB2.X+1;
			}else{
			if(MOB2.X>=2){
				MOB2.X=MOB2.X-1;
			}
			}
			}else{
			if(MOB2.Y<hy-2){
				MOB2.Y=MOB2.Y+1;
			}else{
			if(MOB.Y>=2){
				MOB2.Y=MOB2.Y-1;
			}
			}
			}
		}
		map[MOB2.X][MOB2.Y]='8';
		if(PC.X==MOB.X && PC.Y==MOB.Y){
			map[MOB.X][MOB.Y]='#';
		}
		if(PC.X==MOB2.X && PC.Y==MOB2.Y){
			map[MOB2.X][MOB2.Y]='#';
		}
		print();
		TimeUnit.MILLISECONDS.sleep(100);
		}
		em=0-em;
		
	}
	
	

		
	public void firstprint(){
		String s="";
		String L="\n";
		for(int l=0;l<32;l++){
			L=L+"\n";
		}
		System.out.println(L);
		for(int i=0;i<=hy-1;i++){
			for(int l=0;l<=wx-1;l++){
				s=s+map[l][i];
			}
			s=s+"\n";
		}
		System.out.println("Space after direction to shoot" + "\n" + s +"PLAYER HP:" + PC.HP +" ENEMY HP:" + MOB.HP + " MOB HP:" + MOB2.HP + "\n" + "Bullets left:" + (PC.ammo+1) + " Reload Left:" + PC.reload);

	}		
	
	public void print(){
		String s="";
		String L="\n";
		for(int l=0;l<62;l++){
			L=L+"\n";
		}
		System.out.println(L);
		for(int i=0;i<=hy-1;i++){
			for(int l=0;l<=wx-1;l++){
				s=s+map[l][i];
			}
			s=s+"\n";
		}
		System.out.println("\n" + s +"PLAYER HP:" + PC.HP +" ENEMY HP:" + MOB.HP + " MOB HP:" + MOB2.HP + "\n" + "Bullets left:" + (PC.ammo+1) + " Reload Left:" + (PC.reload+5-PC.ammo)/3);
	}

}
