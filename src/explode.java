import java.awt.*;
public class explode {
	int x,y;
	private boolean live=true;
	
	int[] dia = {1,3,6,9,13,4,2,1};
	int step=0;
	private tankyard ty;
	
	public explode (int x,int y,tankyard ty)
	{
		this.x=x;
		this.y=y;
		this.ty=ty;
	}
	
	public void draw(Graphics g)
	{
		if(!live)
		{	ty.explodes.remove(this);
			return;
		}if(step==dia.length)
		{
			live=false;
			step=0;
			return;
		}
		Color c= g.getColor();
		g.setColor(Color.orange);
		g.fillOval(x*tankyard.BLOCK_SIZE, y*tankyard.BLOCK_SIZE, dia[step],dia[step]);
		g.setColor(c);
		step++;
		
		
	}
	
	
	
	
}
