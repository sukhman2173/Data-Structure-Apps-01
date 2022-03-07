//Sukhman Singh 18041216

package Question1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Human implements Runnable
{
    protected double x, y;
    protected double dx, dy;
    protected double size;
    protected boolean isAlive;
    public static int world_width, world_height;
    protected double max_speed;
    protected int sightDistance;
    protected Random rand;
    protected List<Human> others;
    protected int count;
    protected double d;
    protected double vx;
    protected double vy;
    
    public Human(List<Human> others, double x, double y)
    {
        rand = new Random();
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        size = 25;
        isAlive = true;
        world_width = (int)x*2;
        world_height = (int)y*2;
        max_speed = 2;
        
        while(dx == 0)
        {
            dx = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
        }
       
        while(dy == 0)
        {
            dy = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
        }
        
        sightDistance = (int)((x+y)/2);
        this.others = Collections.synchronizedList(new ArrayList<>());
        this.others = others;
        
        count = 200;
        d = 0;
        vx = 0;
        vy = 0;
    }
    
    @Override
    public void run()
    {
        while(isAlive)
        {
            move();
            try 
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex) 
            {   
            }
        }
    }
    
    public void move()
    {   
        count++;
        for(Human h: others)
        {
            if(h instanceof Zombie)
            {
                d = Math.pow((h.getX() - this.getX()), 2) + Math.pow((h.getY() - this.getY()), 2);
                d = Math.sqrt(d);
                
                if(d <= sightDistance/2)
                {
                    vx = ((this.getX() - h.getX())/d) * (max_speed);
                    vy = ((this.getY() - h.getY())/d) * (max_speed);

                    dx = vx;
                    dy = vy;
                }
            }
            else
            {
                if(count >= 200)
                {
                    dx = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
                    dy = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
                    count = 0;
                }
            }
        }
        
        if(x <= 0)
        {
            dx *= -1;
        }
        
        if(y <= 0)
        {
            dy *= -1;
        }
        
        if(x+size >= world_width)
        {
            dx *= -1;
        }
        
        if(y+size >= world_height)
        {
            dy *= -1;
        }
        
        x += dx;
        y += dy;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
    
    public double getSize()
    {
        return this.size;
    }
    
    synchronized public void kill()
    {
       isAlive = false; 
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillOval((int)x, (int)y, (int)size, (int)size);
    }
}
