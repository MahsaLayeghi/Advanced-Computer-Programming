package project;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.*;

/**
 * Created by free on 06/21/2015.
 */
public class Project extends JFrame
{
    public Project()
    {
        draw();
    }

    private void draw()
    {
        add(new Board());
        addMouseListener((MouseListener) new Board());
        setTitle("Map");
        pack();
        setSize(new Dimension(2 * Map.c * 50 , 2 * Map.r * 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) throws IOException
    {
        Map map = new Map("SaveMap.txt");
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Project p = new Project();
                p.setVisible(true);
            }
        });
        while(Map.finished == false)
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    JFrame frame = new JFrame();
                    Board board = new Board();
                    frame.addMouseListener(board);
                    frame.setContentPane(board);
                }
            });
            Board b = new Board();
            //Hitman.Move(b.getClickLoc());
        }
    }
}
