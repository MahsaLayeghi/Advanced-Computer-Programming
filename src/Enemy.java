package project;

public abstract class Enemy
{
    Vector2D target;
    Vector2D place;
    String direction;
    PathFinder follow;
    boolean isAlive;
    boolean isAttracted;
    abstract void move();
    void moveToward(Vector2D v)
    {
        if(place.x == target.x && place.y == target.y)
            isAttracted = false;
        if(isAttracted)
        {
            this.follow.nextMove(this);
        }
        else
            move();
    }
    Enemy()
    {
        follow = new PathFinder();
        target = new Vector2D();
        isAttracted = false;
        isAlive = false;
        place = new Vector2D();
    }
    Enemy(Vector2D v)
    {
        isAttracted = false;
        isAlive = true;
        place = new Vector2D(v);
        target = new Vector2D();
    }
}
class StaticEnemy extends Enemy
{
    StaticEnemy(Vector2D v)
    {
        super(v);
    }
    @Override
    public void move()
    {
        if(direction.equalsIgnoreCase("up"))
        {
            if(Hitman.place.y == place.y + 2 && Hitman.place.x == place.x && Map.map[place.y + 1][place.x] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else if(direction.equalsIgnoreCase("down"))
        {
            if(Hitman.place.y == place.y - 2 && Hitman.place.x == place.x && Map.map[place.y - 1][place.x] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else if(direction.equalsIgnoreCase("right"))
        {
            if(Hitman.place.x == place.x + 2 && Hitman.place.y == place.y && Map.map[place.y][place.x + 1] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else
        {
            if(Hitman.place.x == place.x - 2 && Hitman.place.y == place.y && Map.map[place.y][place.x - 1] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        if(isAttracted)
        {
            moveToward(target);
        }
    }
}
class DynamicEnemy extends Enemy
{
    DynamicEnemy(Vector2D v)
    {
        super(v);
    }
    @Override
    public void move()
    {
        if(isAttracted)
        {
            moveToward(target);
            if(Hitman.place.x == place.x && Hitman.place.y == place.y)
                Map.finished = true;
            return;
        }
        if(direction.equalsIgnoreCase("down"))
        {
            Vector2D dist = new Vector2D(place);
            dist.set(place.x, place.y + 2);
            if(validate(dist))
                place.y += 2;
            else
                direction = "up";
            dist.set(place.x, place.y + 2);
            if(!validate(dist))
                direction = "up";
        }
        else if(direction.equalsIgnoreCase("up"))
        {
            Vector2D dist = new Vector2D(place);
            dist.set(place.x, place.y - 2);
            if(validate(dist))
                place.y -= 2;
            else
                direction = "down";
            dist.set(place.x, place.y - 2);
            if(!validate(dist))
                direction = "down";
        }
        else if(direction.equalsIgnoreCase("right"))
        {
            Vector2D dist = new Vector2D(place);
            dist.set(place.x + 2, place.y);
            if(validate(dist))
                place.x += 2;
            else
                direction = "left";
            dist.set(place.x + 2, place.y);
            if(!validate(dist))
                direction = "left";
        }
        else
        {
            Vector2D dist = new Vector2D(place);
            dist.set(place.x - 2, place.y);
            if(validate(dist))
                place.x -= 2;
            else
                direction = "right";
            dist.set(place.x - 2, place.y);
            if(!validate(dist))
                direction = "right";
        }
        if(Hitman.place.x == place.x && Hitman.place.y == place.y)
            Map.finished = true;
    }
    public boolean validate(Vector2D v)
    {
        if(this.place.x > v.x)
        {
            if(v.y >= 0 && this.place.y == v.y && this.place.x - 2 == v.x && Map.map[v.y][v.x] == 1 && Map.map[v.y][v.x + 1] == 1)
                return true;
        }
        else if(this.place.x < v.x)
        {
            if(v.x <= Map.c - 1 && this.place.y == v.y && this.place.x + 2 == v.x && Map.map[v.y][v.x] == 1 && Map.map[v.y][v.x - 1] == 1)
                return true;
        }
        else if(this.place.y > v.y)
        {
            if(v.y >= 0 && this.place.y - 2 == v.y && this.place.x == v.x && Map.map[v.y][v.x] == 1 && Map.map[v.y + 1][v.x] == 1)
                return true;
        }
        else if(this.place.y < v.y)
        {
            if(this.place.y + 2 == v.y && this.place.x == v.x && v.y > Map.r && Map.map[v.y][v.x] == 1 && Map.map[v.y - 1][v.x] == 1)
                return true;
        }
        return false;
    }
}
class SpinicEnemy extends Enemy
{
    SpinicEnemy(Vector2D v)
    {
        super(v);
    }
    @Override
    public void move()
    {
        if(direction.equalsIgnoreCase("up"))
        {
            if(Hitman.place.y == place.y + 2 && Hitman.place.x == place.x && Map.map[place.y + 1][place.x] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else if(direction.equalsIgnoreCase("down"))
        {
            if(Hitman.place.y == place.y - 2 && Hitman.place.x == place.x && Map.map[place.y - 1][place.x] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else if(direction.equalsIgnoreCase("right"))
        {
            if(Hitman.place.x == place.x + 2 && Hitman.place.y == place.y && Map.map[place.y][place.x + 1] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        else
        {
            if(Hitman.place.x == place.x - 2 && Hitman.place.y == place.y && Map.map[place.y][place.x - 1] == 1)
            {
                place.x = Hitman.place.x;
                place.y = Hitman.place.y;
                Map.finished = true;
                return;
            }
        }
        if(isAttracted)
        {
            moveToward(target);
            if(Hitman.place.x == place.x && Hitman.place.y == place.y)
                Map.finished = true;
            return;
        }
        if(direction.equalsIgnoreCase("up"))
            direction = "down";
        else if(direction.equalsIgnoreCase("down"))
            direction = "up";
        else if(direction.equalsIgnoreCase("right"))
            direction = "left";
        else
            direction = "right";
    }
}