package project;

public class Hitman
{
    static Vector2D place;
    static boolean isHidden;
    static boolean isArmed;
    static boolean isAlive;
    static boolean hasStone;
    static boolean moveDone;
    Hitman(Vector2D v)
    {
        place = new Vector2D(v);
        isHidden = false;
        isArmed = false;
        hasStone = false;
        moveDone = false;
    }
    static void Move(Vector2D v)
    {
        System.out.println(place.x + "\t" + place.y);
        moveDone = false;
        if(moveDone == false)
        {
            if(validateMove(v) == true)
            {
                System.out.println("validated");
                if(hasStone == true)
                {
                    for(Enemy e : Map.enemies)
                    {
                        if(e.place.x == v.x + 2 || e.place.x == v.x - 2 || e.place.y == v.y - 2 || e.place.y == v.y + 2)
                        {
                            e.isAttracted = true;
                            e.target.set(v.x, v.y);
                        }
                        hasStone = false;
                    }
                }
                else if(isArmed == true)
                {
                    Gun.shoot(v);
                }
                else
                {
                    moveDone = true;
                    place.x = v.x;
                    place.y = v.y;
                }
                System.out.println(Hitman.place.x + " h " + Hitman.place.y);
                for(Enemy e : Map.enemies)
                {
                    if(e.place.x == place.x && e.place.y == place.y)
                        Map.enemies.remove(e);
                }
                for(Stone s : Map.stones)
                {
                    if(s.place.x == place.x && s.place.y == place.y)
                    {
                        Map.stones.remove(s);
                        hasStone = true;
                    }
                }
                for(Gun g : Map.guns)
                {
                    if(g.place.x == place.x && g.place.y == place.y)
                    {
                        isArmed = true;
                        Map.guns.remove(g);
                    }
                }
                boolean flag = false;
                for(Vase va : Map.vases)
                {
                    if(va.place.x == place.x && va.place.y == place.y)
                    {
                        isHidden = true;
                        flag = true;
                    }
                }
                if(!flag)
                    isHidden = false;
                for(Enemy e : Map.enemies)
                    e.move();
                System.out.println("H  " + place.x + "  " + place.y);
            }
            else
                System.out.println("Not valid");
        }
    }
    static boolean validateMove(Vector2D v)
    {
        if(hasStone == true)
        {
            if(v.x <= Map.map[0].length - 1 && v.x >= 0 && v.y >= 0 && v.y <= Map.map.length - 1)
            {
                 if((Math.abs(v.x - place.x) == 2 && v.y == place.y) || (Math.abs(v.y - place.y) == 2 && place.x == v.x))
                 {
                     if(Map.map[v.y][v.x] == 1)
                         return true;
                 }
            }
            return false;
        }
        else if(isArmed == true)
        {
            if(v.x <= Map.map[0].length - 1 && v.x >= 0 && v.y >= 0 && v.y <= Map.map.length - 1)
            {
                 if((Math.abs(v.x - place.x) == 2 && v.y == place.y) || (Math.abs(v.y - place.y) == 2 && place.x == v.x))
                 {
                     return true;
                 }
            }
            return false;
        }
        else
        {
            if(v.x <= Map.map[0].length - 1 && v.x >= 0 && v.y >= 0 && v.y <= Map.map.length - 1)
            {
                 if((Math.abs(v.x - place.x) == 2 && v.y == place.y) || (Math.abs(v.y - place.y) == 2 && place.x == v.x))
                 {
                     if(Map.map[v.y][v.x] == 1 && Map.map[(v.y + place.y) / 2][(v.x + place.x) / 2] == 1)
                         return true;
                 }
            }
            return false;
        }
    }
}