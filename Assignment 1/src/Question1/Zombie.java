//Sukhman Singh 18041216

package Question1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Zombie extends Human
{
    private Human closest;
    
    public Zombie(List<Human> others, double x, double y)
    {
        super(others, x, y);
        max_speed = max_speed/2;
        
        while(dx == 0)
        {
            dx = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
        }
       
        while(dy == 0)
        {
            dy = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
        }
        
        sightDistance = (int)(x+y)*2;
        closest = null;
    }
    
    @Override
    public void move()
    {
        count++;
        for(Human h: others)
        {
            if(h instanceof Zombie)
            {
                if(count >= 200)
                {
                    dx = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
                    dy = (-max_speed + (rand.nextDouble() * (max_speed + max_speed)));
                    count = 0;
                }
            }
            else
            {
                if(closest == null)
                {
                    closest = h;
                }

                d = Math.pow((closest.getX() - this.getX()), 2) + Math.pow((closest.getY() - this.getY()), 2);
                d = Math.sqrt(d);

                double newD = Math.pow((h.getX() - this.getX()), 2) + Math.pow((h.getY() - this.getY()), 2);
                newD = Math.sqrt(newD);

                if(newD < d)
                {
                    closest = h;
                    d = newD;
                }

                vx = ((this.getX() - closest.getX())/d) * (max_speed);
                vy = ((this.getY() - closest.getY())/d) * (max_speed);
            }
        }
        
        synchronized(this)
        {
            if(closest != null)
            {
                if(d <= closest.getSize())
                {
                    double turnedX = closest.getX();
                    double turnedY = closest.getY();

                    if(closest.getX() <= 3)
                    {
                        turnedX += 3;
                    }
                    else if(closest.getX()+closest.getSize() >= world_width-3)
                    {
                        turnedX -= 3;
                    }

                    if(closest.getY() <= 3)
                    {
                        turnedY += 3;
                    }
                    else if(closest.getY()+closest.getSize() >= world_height-3)
                    {
                        turnedY -= 3;
                    }

                    closest.kill();
                    others.remove(closest);

                    Human turned = new Zombie(others, turnedX, turnedY);
                    others.add(turned);
                    Thread turnedThread = new Thread(turned);
                    turnedThread.start();
                }            
                else
                {
                    dx = (vx*-1);
                    dy = (vy*-1);
                }
                closest = null;
            }
        }
        
        if(x <= 0)
        {
            x += 3;
            dx *= -1;
        }
        
        if(y <= 0)
        {
            y += 3;
            dy *= -1;
        }
        
        if(x+size >= world_width)
        {
            x -= 3;
            dx *= -1;
        }
        
        if(y+size >= world_height)
        {
            y -= 3;
            dy *= -1;
        }
        
        x += dx;
        y += dy;
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, (int)size, (int)size);
    }
}
