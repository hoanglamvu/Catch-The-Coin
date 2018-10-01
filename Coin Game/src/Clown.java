import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Clown extends GameObject{
	
	Random r = new Random();
	
	private BufferedImage image;
	
	public Clown(int x, int y, ID id) {
		super(x, y, id);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			image = loader.loadImage("/Coin.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		velX = 1;
		velY = 1;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(x >= Game.width-32 || x <= 0) velX *= -1;
		if(y >= Game.height-64 || y <= 0) velY *= -1;
	}

	@Override
	public void render(Graphics g){
		//g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		//g.fillRect(x, y, 32, 32);
		g.drawImage(image, x, y, null);
	}	
	
}

