package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.PublicKey;

/**
 * Created by free on 06/22/2015.
 */
public class Board extends JPanel implements MouseListener
{
    public Image circle;
    public Image ho;
    public Image ve;
    public Image blank;
    public Image hitman;
    public Image dynamic;
    public Image Static;
    public Image spinic;
    public Image goal;
    private Vector2D v;
    //private MouseEvent event;


    public Board()
    {
        draw();
        v = new Vector2D();
        //event = new MouseEvent(this, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, true);
    }

    private void draw()
    {
        loadImage();
        this.setPreferredSize(new Dimension(2 * Map.c * 50, 2 * Map.r * 50));
    }

    public void loadImage()
    {
        ImageIcon icircle = new ImageIcon("o.png");
        ImageIcon iHo = new ImageIcon("Ho.png");
        ImageIcon iVe = new ImageIcon("Ve.png");
        ImageIcon iblank = new ImageIcon("blank.png");
        ImageIcon ihitman = new ImageIcon("hitman.png");
        ImageIcon idynamic = new ImageIcon("dynamic.png");
        ImageIcon istatic = new ImageIcon("static.png");
        ImageIcon igoal = new ImageIcon("goal.png");
        ImageIcon ispinic = new ImageIcon("spinic.png");


        circle = icircle.getImage();
        ho = iHo.getImage();
        ve = iVe.getImage();
        blank = iblank.getImage();
        hitman = ihitman.getImage();
        dynamic = idynamic.getImage();
        Static = istatic.getImage();
        spinic = ispinic.getImage();
        goal = igoal.getImage();
    }

    @Override

    public void paintComponent(Graphics g)
    {
        for (int i = 0; i < Map.map.length ; i++)
        {
            for (int j = 0; j < Map.map[i].length ; j++)
            {
                if(i % 2 == 0 && j % 2 == 0 && Map.map[i][j] == 1)
                {
                    g.drawImage(circle, j * 50 , i * 50,this);
                }
                else if(i % 2 == 0 && j % 2 == 1 && Map.map[i][j] == 1)
                {
                    g.drawImage(ho , j * 50 , i * 50,this);
                }
                else if(i % 2 == 1 && j % 2 == 0 && Map.map[i][j] == 1)
                {
                    g.drawImage(ve , j * 50 , i * 50,this);
                }
                else
                {
                    g.drawImage(blank, j * 50 , i * 50,this);
                }
            }
        }
        g.drawImage(hitman, Hitman.place.y * 50 ,Hitman.place.x * 50 , this);
        for(int i = 0; i < Map.enemies.size(); i++)
        {
            if(Map.enemies.get(i) instanceof DynamicEnemy)
                g.drawImage(dynamic, Map.enemies.get(i).place.y * 50 , Map.enemies.get(i).place.x * 50 , this);
            else if (Map.enemies.get(i) instanceof StaticEnemy)
                g.drawImage(Static , Map.enemies.get(i).place.y *50 , Map.enemies.get(i).place.x * 50 , this);
            else
                g.drawImage(spinic, Map.enemies.get(i).place.y *50 , Map.enemies.get(i).place.x * 50 ,this);
        }

        g.drawImage(goal , Map.goal.y * 50 ,Map.goal.x * 50 ,this);
    }



    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        //event = e;
        int positionX = e.getLocationOnScreen().x;
        int positionY = e.getLocationOnScreen().y;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width/2;
        int centerY = screenSize.height/2;

        int cornerX = centerX - (Map.c * 50);
        int cornerY = centerY - (Map.r * 50);
        v.y = (positionX - cornerX) / 50;
        v.x = (positionY - cornerY) /50;
        System.out.println(v.x + " " + v.y);
        Hitman.Move(v);
        System.out.println("MouseClicked");
    }

    /*public Vector2D getClickLoc ()
    {
        //mouseClicked(event);
        new Board();
        return v;
    }*/
}