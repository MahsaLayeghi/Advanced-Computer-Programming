package project;

import javax.swing.*;
import java.io.*;
import java.util.*;

class Vector2D
{
    int x,y;
    Vector2D(Vector2D v)
    {
        x = v.x;
        y = v.y;
    }
    Vector2D()
    {
        x = 0;
        y = 0;
    }
    void add(Vector2D v)
    {
        x += v.x;
        y += v.y;
    }
    void set(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Map extends JFrame
{
    static int r, c;
    static int[][] map;
    static Vector2D goal;
    static boolean finished;
    static LinkedList <Stone> stones;
    static LinkedList <Enemy> enemies;
    static LinkedList <Gun> guns;
    static LinkedList <Vase> vases;
    Map(String fileAddress) throws IOException
    {
        goal = new Vector2D();
        stones = new LinkedList<Stone>();
        enemies = new LinkedList<Enemy>();
        guns = new LinkedList<Gun>();
        vases = new LinkedList<Vase>();
        finished = false;
        File file = new File(fileAddress);
        Scanner mapLoader = new Scanner(file);
        r = mapLoader.nextInt();
        c = mapLoader.nextInt();
        map = new int [2 * r - 1][2 * c - 1];
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
                map[i][j] = mapLoader.nextInt();
        }
        int house;
        while(mapLoader.hasNext())
        {
            house = mapLoader.nextInt();
            Vector2D p = new Vector2D();
            p.set((house / c) * 2, (house % c) * 2);
            String data = mapLoader.next();
            System.out.println(data);
            if(data.toLowerCase().contains("hitman"))
            {
                Hitman h = new Hitman(p);
            }
            else if(data.toLowerCase().contains("static"))
            {
                StaticEnemy s = new StaticEnemy(p);
                if(data.toLowerCase().contains("right"))
                    s.direction = "right";
                else if(data.toLowerCase().contains("left"))
                    s.direction = "left";
                else if(data.toLowerCase().contains("up"))
                    s.direction = "up";
                else if(data.toLowerCase().contains("down"))
                    s.direction = "down";
                enemies.add(s);
            }
            else if(data.toLowerCase().contains("spinic"))
            {
                SpinicEnemy s = new SpinicEnemy(p);
                if(data.toLowerCase().contains("right"))
                    s.direction = "right";
                else if(data.toLowerCase().contains("left"))
                    s.direction = "left";
                else if(data.toLowerCase().contains("up"))
                    s.direction = "up";
                else if(data.toLowerCase().contains("down"))
                    s.direction = "down";
                enemies.add(s);
            }
            else if(data.toLowerCase().contains("dynamic"))
            {
                DynamicEnemy s = new DynamicEnemy(p);
                if(data.toLowerCase().contains("right"))
                    s.direction = "right";
                else if(data.toLowerCase().contains("left"))
                    s.direction = "left";
                else if(data.toLowerCase().contains("up"))
                    s.direction = "up";
                else if(data.toLowerCase().contains("down"))
                    s.direction = "down";
                enemies.add(s);
            }
            else if(data.toLowerCase().contains("vase"))
            {
                vases.add(new Vase(p));
            }
            else if(data.toLowerCase().contains("gun"))
            {
                guns.add(new Gun(p));       
            }
            else if(data.toLowerCase().contains("stone"))
            {
                stones.add(new Stone(p));
            }
            else if(data.toLowerCase().contains("gameend"))
            {
                goal.set(p.x, p.y);
            }


        }
    }
    static void draw()
    {


        
    }
}