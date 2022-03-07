//Sukhman Singh 18041216

package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZombieSimulator extends JPanel implements ActionListener
{
    private JButton addZombieButton;
    private JButton addHumanButton;
    private DrawPanel drawPanel;
    private Timer timer;
    private List<Human> others;
   
    public ZombieSimulator()
    {   
       super(new BorderLayout());
       JPanel southPanel = new JPanel();
       
       addZombieButton = new JButton("Add Zombie");
       addZombieButton.addActionListener(this);
       southPanel.add(addZombieButton);
       
       addHumanButton = new JButton("Add Human");
       addHumanButton.addActionListener(this);
       southPanel.add(addHumanButton);
       
       add(southPanel,BorderLayout.SOUTH);
       
       drawPanel = new DrawPanel();
       add(drawPanel,BorderLayout.CENTER);
       
       
       timer = new Timer(7,this);
       timer.start();
       
       others = Collections.synchronizedList(new ArrayList<>());
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
       Object source = e.getSource();
       
       if(source == addZombieButton)
       {
           Human myZombie = new Zombie(others, drawPanel.getWidth()/2, drawPanel.getHeight()/2);
           others.add(myZombie);
           
           Thread zombieThread = new Thread(myZombie);
           zombieThread.start();
       }
       
       if(source == addHumanButton)
       {
           Human myHuman = new Human(others, drawPanel.getWidth()/2, drawPanel.getHeight()/2);
           others.add(myHuman);
           
           Thread humanThread = new Thread(myHuman);
           humanThread.start();
       }

       if(source == timer)
       {
           drawPanel.repaint();
       }
   }

    private class DrawPanel extends JPanel
    {
       
       public DrawPanel()
       {   super();
           setPreferredSize(new Dimension(500,500));
           setBackground(Color.LIGHT_GRAY);
           
       }
       
       @Override
       public void paintComponent(Graphics g)
       {   
           super.paintComponent(g);
           
            for(int i = 0; i < others.size(); ++i)
            {
                others.get(i).draw(g);
                
                if(others.get(i) instanceof Zombie)
                {
                    others.get(i).sightDistance = drawPanel.getWidth()+drawPanel.getHeight();
                }
                else
                {
                    others.get(i).sightDistance = (drawPanel.getWidth()+drawPanel.getHeight())/4;
                }
            } 
            
            Human.world_width = drawPanel.getWidth();
            Human.world_height = drawPanel.getHeight();
       }
   }
   
   public static void main(String[] args)
   {  JFrame frame = new JFrame("Zombie Simulator");
      // kill all threads when frame closes
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new ZombieSimulator());
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
      // now display something while the main thread is still alive
   }
}
