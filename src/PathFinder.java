package project;
import java.util.*;

public class PathFinder
{
    private Queue q;
    private Stack s;
    private HashMap parents;
    private boolean[][] mark;
    PathFinder()
    {
        s = new Stack();
        q = (Queue) new ArrayList <Vector2D>();
        parents = new HashMap <Vector2D,Vector2D>();
        mark = new boolean[Map.map.length][Map.map[0].length];
    }
    public Vector2D nextMove(Enemy e)
    {
        q.add(e.place);
        parents.put(e.place, null);
        while(!q.isEmpty())
        {
            Vector2D u = (Vector2D)q.remove();
            mark[u.y][u.x] = true;
            if(u.x - 2 > 0 && Map.map[u.y][u.x - 1] == 1 && Map.map[u.y][u.x - 2] == 1 && mark[u.y][u.x] == false)
            {
                Vector2D v = new Vector2D();
                v.set(u.x - 2, u.y);
                q.add(v);
                parents.put(v, u);
            }
            else if(u.x + 2 < Map.map[0].length && Map.map[u.y][u.x + 1] == 1 && Map.map[u.y][u.x + 2] == 1 && mark[u.y][u.x] == false)
            {
                Vector2D v = new Vector2D();
                v.set(u.x + 2, u.y);
                q.add(v);
                parents.put(v, u);
            }
            else if(u.y - 2 > 0 && Map.map[u.y - 1][u.x] == 1 && Map.map[u.y - 2][u.x] == 1 && mark[u.y][u.x] == false)
            {
                Vector2D v = new Vector2D();
                v.set(u.x, u.y - 2);
                q.add(v);
                parents.put(v, u);
            }
            else if(u.y + 2 < Map.map.length && Map.map[u.y + 1][u.x] == 1 && Map.map[u.y + 2][u.x] == 1 && mark[u.y][u.x] == false)
            {
                Vector2D v = new Vector2D();
                v.set(u.x, u.y + 2);
                q.add(v);
                parents.put(v, u);
            }
        }
        Vector2D u = new Vector2D(e.target);
        while(u.x != e.place.x && u.y != e.place.y)
        {
            s.push(u);
            u = (Vector2D) parents.get(u);
        }
        return (Vector2D) s.pop();
    }
}