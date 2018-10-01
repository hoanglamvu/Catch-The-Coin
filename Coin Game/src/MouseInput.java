
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseInput extends MouseAdapter {

	Handler handler;
	Game game;
	
	Random r = new Random();
	
	public MouseInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		game.clicks++;
		
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject object = handler.object.get(i);
			
			if(object.getId() == ID.Clown){
				if(mx >= object.x && mx <= object.x + 32){
					if(my >= object.y && my <= object.y + 32){
						object.velX*=1.2;
						object.velY*=1.2;
						game.score++;
						
					}
				}
			}
		}
		

	}
	
}	


