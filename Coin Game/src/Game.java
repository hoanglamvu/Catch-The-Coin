import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 2018559482955979820L;
	public static int width = 640, height = 480;
	private String title = "Catch the Coin $$$";
	
	private Thread gameThread;
	private boolean isRunning = false;
	
	Handler handler = new Handler();
	
	public int score = 0;
	public int clicks = 0;
	
	Random r = new Random();
	
	public Game() {
		new Window(width, height, title, this);
		start();
		
		this.addMouseListener(new MouseInput(handler, this));
		
		handler.addObject(new Clown(50+r.nextInt(width-100), 50+r.nextInt(height-100), ID.Clown));
		
	
	}

	private synchronized void start(){
		if(isRunning){
			return;
		}
		
		gameThread = new Thread(this);
		gameThread.start();
		isRunning = true;
	}
	
	private synchronized void stop(){
		if(!isRunning)
			return;
	
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(isRunning)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();		
	}
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
	
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, width, height);
		
		handler.render(g);
		
		g.setColor(Color.black);
		g.drawString("Coins Earned: " + score, 20, 25);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		new Game();
	}
}
