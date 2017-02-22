import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
//box red:could add 1 time for being hit, blue add half of the defence  
public class boxe {
	int row,col;
	int w=tankyard.BLOCK_SIZE,h=tankyard.BLOCK_SIZE;
	public int bullet=0,blood=0;
	private static Random r=new Random();
	Color color=Color.red;
	public boxe(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public void reshow()
	{
		this.row=r.nextInt(tankyard.X);
		this.col=r.nextInt(tankyard.Y);
		int rn=r.nextInt(3);
		if(this.color==Color.blue)
			{
				bullet=10+bullet;
				}
		if(this.color==Color.red)
			{	
				blood=30;
			}
		if(rn==1)
			this.color=Color.red;
		if(rn==2)
			this.color=Color.blue;
	}
	
	public boxe()
	{
		this(r.nextInt(tankyard.X),r.nextInt(tankyard.Y));
	}
	
	public Rectangle getRect(){
		return new Rectangle(tankyard.BLOCK_SIZE*col, tankyard.BLOCK_SIZE*row,h,w);
	}
	
	void draw (Graphics g){
		Color c=g.getColor();
		g.setColor(color);
		g.fillRect(tankyard.BLOCK_SIZE*col, tankyard.BLOCK_SIZE*row, w, h);
		g.setColor(c);
	}
}

