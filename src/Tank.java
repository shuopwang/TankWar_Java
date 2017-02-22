import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Tank {
	int w=tankyard.BLOCK_SIZE,h=tankyard.BLOCK_SIZE;
	private static Random r=new Random();
	int x,y;
	Dir dir;
	private Dir pt=Dir.D;
	tankyard ty;
	int type;
	private blood bl=new blood();
	int blood, flag=0;
	public static Random rd=new Random();
	private int step =rd.nextInt(30)+30;
	public Tank(int i,tankyard ty)//i=0 player,i=1 enemy;
	{	
		if(i==0)
			{this.x=20;
			this.y=20;
			this.dir=Dir.S;
			this.ty=ty;
			this.type=0;
			this.blood=100;}
		if(i==1)
			{this.x=r.nextInt(tankyard.X);
			this.y=r.nextInt(tankyard.Y);
			this.ty=ty;
			this.type=1;
				if(this.x<2)
						this.dir=Dir.R;
					else if(this.y<2)
						this.dir=Dir.D;
					else	if(this.x>(tankyard.X-1))
						this.dir=Dir.L;
					else if(this.y>(tankyard.Y-1))
						this.dir=Dir.U;
					else 
						this.dir=Dir.D;
					}
	}
	public void draw (Graphics g)
	{	if(this.blood>=0)
		{
		Color c=g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y, w, h);//画出一个正方形的node
		g.setColor(c);
		bl.draw(g);
		switch(this.pt){
		case L:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE , y*tankyard.BLOCK_SIZE+h/2);break;
		case U:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+(w/2) , y*tankyard.BLOCK_SIZE);break;
		case R:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+w , y*tankyard.BLOCK_SIZE+h/2);break;
		case D:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+w/2 , y*tankyard.BLOCK_SIZE+h);break;
		}
		if(this.dir!=Dir.S)
			this.pt=this.dir;
		move();
		}
	}
	public void draw1 (Graphics g)
	{	if(flag==0)
		{Color c=g.getColor();
		g.setColor(Color.green);
		g.fillRect(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y, w, h);//画出一个正方形的node
		g.setColor(c);
		switch(this.pt){
		case L:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE , y*tankyard.BLOCK_SIZE+h/2);break;
		case U:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+(w/2) , y*tankyard.BLOCK_SIZE);break;
		case R:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+w , y*tankyard.BLOCK_SIZE+h/2);break;
		case D:
			g.drawLine(x*tankyard.BLOCK_SIZE+w/2, y*tankyard.BLOCK_SIZE+h/2,x*tankyard.BLOCK_SIZE+w/2 , y*tankyard.BLOCK_SIZE+h);break;
		}
		if(this.dir!=Dir.S)
			this.pt=this.dir;
		move1();
		
		}
		if(flag==1)
			
		{ty.tanks.remove(this);}
	}
	private void move1() {
		switch(this.dir){
		case L:
			if(this.x >2)
			this.x=this.x-1;
			else
				this.dir=Dir.R;break;
		case U:
			if(this.y>3)
			this.y=this.y-1;
			else
				this.dir=Dir.D;break;
		case R:
			if(this.x<tankyard.X)
			this.x=this.x+1;
			else
			this.dir=Dir.L;	
				break;
		case D:
			if(this.y<tankyard.Y)
				this.y=this.y+1;
			else
				this.dir=Dir.U;	
			break;
		}
		if(this.x<0)
			this.x=0;
		if(this.y<2)
			this.y=2;
		step--;
		if(step==0)
			{
			Dir[] dirs=Dir.values();
			int rn=rd.nextInt(dirs.length);
			step=r.nextInt(30)+20;
			this.dir=dirs[rn];}
		if(r.nextInt(20)+10>25)
		this.shoot();
		
	}
	private void move() {
		switch(this.dir){
		case L:
			this.x=this.x-1;break;
		case U:
			this.y=this.y-1;break;
		case R:
			this.x=this.x+1;break;
		case D:
			this.y=this.y+1;break;
		}
		if(this.x<0)
			this.x=0;
		if(this.y<2)
			this.y=2;
		if(this.x>tankyard.X-2)
			this.x=tankyard.X-2;
		if(this.y>tankyard.Y-2)
			this.y=tankyard.Y-2;
	}
	public void keypressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			this.dir=Dir.L;break;
		case KeyEvent.VK_UP:
			this.dir=Dir.U;break;
		case KeyEvent.VK_RIGHT:
			this.dir=Dir.R;break;
		case KeyEvent.VK_DOWN:
			this.dir=Dir.D;break;
		case KeyEvent.VK_A:
			superbullet();break;
		case KeyEvent.VK_1:
			{this.blood=100;this.flag=0;this.x=20;this.y=20;this.dir=Dir.S;break;}
		case KeyEvent.VK_CONTROL:
			if(flag==0)
			shoot();//基本思想是把子弹和坦克当成两个部分来考虑，每个对象都应该在yard中出现
			//只要将子弹变量在yard设为空值之后，在这边调用形成一个值使之成立，避免了在按键触发后的参数变量变化问题
			//in the yard we have created the List of bullets,use the fonction list.add(e) to add the bullet
			//into the bullets
			break;
		}
	}
	public void hit(boxe b)
	{
		if (this.getRect().intersects(b.getRect()))
		{	
			b.reshow();
			if(b.color==Color.red)
			this.blood=this.blood+30;
		if(b.color==Color.blue)
			{for(int i=0;i<10;i++)
				{bullet bullet=new bullet(x,y,type,pt,this.ty);
			ty.bullets.add(bullet);}
			;
			}
		}
		
	}
	public Rectangle getRect(){
		return new Rectangle(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y,h,w);
	}
	
	private bullet shoot()
	{
			
			bullet b=new bullet(x,y,type,pt,this.ty);
			ty.bullets.add(b);
			return b;
		
	}
	private void superbullet()
	{	Dir[] dirs={Dir.L,Dir.U,Dir.R,Dir.D}; 
	
		for(int i=0;i<4;i++)
		{

			bullet b=new bullet(x,y,type,dirs[i],this.ty);
			ty.bullets.add(b);
		}
	}
	public boolean hitwithtanks(List<Tank> tanks)
	{	for(int i=0;i<tanks.size();i++)
		{
			Tank t=tanks.get(i);
			if(this!=t)
			{
				if(this.flag==0&&t.flag==0&&this.getRect().intersects(t.getRect()))
					this.dir=Dir.S;
					return true;
					
			}
			
		}
		return false;
		
	}
	private class blood
	{
		public void draw(Graphics g)
		{
			Color c=g.getColor();
			g.setColor(Color.red);
			g.drawRect(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y-10, w, 5);
			int wid=w*blood/100;
			g.fillRect(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y-10, wid, 5);
			g.setColor(c);
		}
	}
	
}
