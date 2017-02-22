import java.awt.Frame;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tankyard extends Frame{
	public static final int X=50,Y=50,BLOCK_SIZE=10;
	Boolean flag=true;
	Image offScreenImage=null;
	boxe b=new boxe();
	
	List<bullet> bullets=new ArrayList<bullet>();//test the bullet,we should add the fonction into the tank.draw(); 
	
	Tank player=new Tank(0,this);
	
	//bullet ebullet=new bullet(e1.x,e1.y,e1.dir,this);
	
	List<explode> explodes=new ArrayList<explode>();
	
	List<Tank> tanks=new ArrayList<Tank>();
	public void launch ()
	{	this.setTitle("THE TANK WORLD! 1.0   By Shuopeng WANG");
		this.setLocation(200,200);//将图像出现在（200，200）的位置上
		this.setSize(X*BLOCK_SIZE, Y*BLOCK_SIZE);//规定窗口大小
		this.setBackground(Color.GRAY);
		this.addWindowListener(new WindowAdapter()//增加窗口关闭功能
				{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}});
		
		for(int i=0;i<10;i++)
		{
			tanks.add(new Tank(1,this));
		}
		
		this.setVisible(true);//令窗口可视
		this.addKeyListener(new keymonitor());//监听键盘事件
		new Thread(new PaintThread()).start();//在窗口里启动线程
	}
	@Override
	public void update(Graphics g) {
		if(offScreenImage==null)
			offScreenImage=this.createImage( X*BLOCK_SIZE, Y*BLOCK_SIZE);
		Graphics goff=offScreenImage.getGraphics();
		paint(goff);
		g.drawImage(offScreenImage,0,0,null);
	}
	public void paint(Graphics g) {
		Color c=g.getColor();//前景色，默认为黑
		g.setColor(Color.GRAY);
		g.fillRect(0,0,X*BLOCK_SIZE, Y*BLOCK_SIZE);//填充自左上角（0，0）至（w、h,是矩形区域的宽和高）的矩阵区域
		g.setColor(Color.blue);
		g.drawString("the number of bullets:"+(20-bullets.size()+b.bullet), 3*BLOCK_SIZE, 5*BLOCK_SIZE);
		g.drawString("your blood:"+(player.blood+b.blood), 3*BLOCK_SIZE, 10*BLOCK_SIZE);
		g.drawString("enemies sizes:"+(tanks.size()), 3*BLOCK_SIZE, 15*BLOCK_SIZE);
		g.setColor(c);
		b.draw(g);
		//test the bullet,we should add the fonction into the tank.draw(); 
		player.draw(g);
		//e1.draw1(g);
		for(int i=0;i<tanks.size();i++)
		{
			Tank t=tanks.get(i);
			t.hitwithtanks(tanks);
			t.draw1(g);
		}
		for(int i=1;i<bullets.size();i++)
			{	
				
				bullet bl=bullets.get(i);
				bl.hittanks(tanks);
				
				bl.hit(player);
				
				bl.draw(g);
				
				
			}
		for(int i=1;i<explodes.size();i++)
		{
			explode e=explodes.get(i);
			e.draw(g);
		}
		player.hit(b);
		
		//ebullet.draw(g);
		//ebullet.hit(player);
	}
	
	
	
	private class PaintThread implements Runnable
	{

		@Override
		public void run() {
			while(flag){
			repaint();//recrit paint,to make the picture look like moving 
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
				}
			}
		}
		
	}
	public class keymonitor extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e) {
			player.keypressed(e);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		new tankyard().launch();
	}

}
