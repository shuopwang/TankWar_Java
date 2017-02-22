import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
public class bullet {
	int w=tankyard.BLOCK_SIZE,h=tankyard.BLOCK_SIZE;
	int x,y;
	Dir dir;
	int flag=0,type;
	tankyard ty;
	public bullet(int i,int j,int type,Dir d,tankyard ty)
	{
		this.x=i;//it should be modify by the postion of each tank
		this.y=j;
		this.dir=d;
		this.type=type;
		this.ty=ty;
	}
	public void draw (Graphics g)
	{	if(flag==0)
		{
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.fillOval(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y, w/2, h/2);//画出一个正方形的node
		g.setColor(c);
		move();
		}
	if(flag==1)
	{
		ty.bullets.remove(this);
		return;
	}
	}
	public void move()
	{	
		
		switch(this.dir){
		case L:
			this.x=this.x-2;break;
		case U:
			this.y=this.y-2;break;
		case R:
			this.x=this.x+2;break;
		case D:
			this.y=this.y+2;break;
		}
		if(x<0||x>tankyard.X||y<0||y>tankyard.Y)
			flag=1;
	}
	public Rectangle getRect(){
		return new Rectangle(tankyard.BLOCK_SIZE*x, tankyard.BLOCK_SIZE*y,h,w);
	}
	public boolean hit(Tank t)
	{
		if (t.flag==0&&this.flag==0&&this.getRect().intersects(t.getRect())&&this.type!=t.type)
		{	explode e=new explode(this.x,this.y,ty);
			if(t.type==1)
			{	
				t.flag=1;this.flag=1;
				
				ty.explodes.add(e);
				return true;
			}
			if(t.type==0)
				{if(t.blood==0)
					{	t.flag=1;t.dir=Dir.S;
						//new end(0);
						
						}
				t.blood=t.blood-20;this.flag=1;
				return true;
				}
					
		}
		return false;
	}
	
	public boolean hittanks(List<Tank> tanks)
	{	
		for(int i=0;i<tanks.size();i++)
		{
			
			if(hit(tanks.get(i)))
			return true;
		
		}
		
		return false;
	}
	
}
