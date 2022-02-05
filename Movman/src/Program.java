import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
	static InputStreamReader reader = new InputStreamReader(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {
		String winner = null;
		Map MAP= new Map(6,5);
		MAP.firstprint();
		char key=(char)reader.read();
		while(key!='`'){
		MAP.turn(key);
		
		if(MAP.PC.HP<=0){
			winner="MOBs";
			break;
		}
		if(MAP.MOB.HP<=0 && MAP.MOB2.HP<=0){
			winner="Player";
			break;
		}
		key=(char)reader.read();
		}
		String s="\n";
		for(int i=0;i<63;i++){
			s=s+"\n";
		}
		System.out.println(s+winner);
	}

}
