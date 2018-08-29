package project;
public abstract class Objects
{
    Vector2D place;
    Objects()
    {
        
    }
    Objects(Vector2D v)
    {
        place = new Vector2D(v);
    }
}
class Stone extends Objects
{
    Stone(Vector2D v)
    {
        super(v);
    }
    static void Throw(Vector2D v)
    {
        
    }
}
class Gun extends Objects
{
    Gun(Vector2D v)
    {
         super(v);
    }
    static void shoot(Vector2D v)
    {
        Hitman.isArmed = false;
        if(v.x > Hitman.place.x)
        {
            Vector2D p = new Vector2D(v);
            while(p.x < Map.map[0].length)
            {
                p.x += 2;
                for(Enemy e : Map.enemies)
                {
                    if(e.place.x == p.x && e.place.y == p.y)
                    {
                        Map.enemies.remove(e);
                    }
                }
            }
        }
        if(v.x < Hitman.place.x)
        {
            Vector2D p = new Vector2D(v);
            while(p.x >= 0)
            {
                p.x -= 2;
                for(Enemy e : Map.enemies)
                {
                    if(e.place.x == p.x && e.place.y == p.y)
                    {
                        Map.enemies.remove(e);
                    }
                }
            }
        }
        else if(v.y < Hitman.place.y)
        {
            Vector2D p = new Vector2D(v);
            while(p.y < Map.map.length)
            {
                p.y += 2;
                for(Enemy e : Map.enemies)
                {
                    if(e.place.x == p.x && e.place.y == p.y)
                    {
                        Map.enemies.remove(e);
                    }
                }
            }
        }
        else if(v.y > Hitman.place.y)
        {
            Vector2D p = new Vector2D(v);
            while(p.y >= 0)
            {
                p.y -= 2;
                for(Enemy e : Map.enemies)
                {
                    if(e.place.x == p.x && e.place.y == p.y)
                    {
                        Map.enemies.remove(e);
                    }
                }
            }
        }
        
    }
}
class Vase extends Objects
{
    Vase(Vector2D v)
    {
        super(v);
    }
}