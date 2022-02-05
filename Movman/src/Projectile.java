
public class Projectile extends Char {
	char D;
	int XW;
	int YH;
	
	public Projectile(int health, int x, int y, char d) {
		super(health, x, y);
		XW=wx;
		YH=hy;
		D=d;
	}
	public void move(){

		if(HP==0){
			D='X';
		}
		if (D == 'w' && Y>1 || D == 'W' && Y>1){
	    	Y=Y-1;
		}else{
			if(Y<=1 && D == 'w' || Y<=1 && D == 'W'){
				D='"';
			HP=0;
			}
		}
	    if (D == 's' && Y<YH-2 || D == 'S' && Y<YH-2){
			Y=Y+1;
		}else{
			if(Y>=YH-2 && D == 's' || Y>=YH-2 && D == 'S' ){
				D='_';
				HP=0;
				}
		}
	    if (D == 'a' && X+1>2 || D == 'A' && X+1>2){
			X=X-1;
		}else{
			if(X<=1 && D == 'a' || X<=1 && D == 'A'){
				D=';';
				HP=0;
				}
		}
	    if (D == 'd' && X<(XW-2) || D == 'D' && X<(XW-2)){
			X=X+1;
		}else{
			if(X>=(XW-2) && (D == 'd') || X>=(XW-2) && (D == 'D')){
				D=';';
				HP=0;
				}
		}

		
	}



}
