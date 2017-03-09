import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by sarah on 3/6/17. wasn't this supposed to be Rodrigo
 */

    public class CheckPoint implements Drawable {

        int x;
        int y;

        public CheckPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(Graphics g)
        {
            g.setColor(Color.yellow);
            g.fillOval(x-7,y-7,7,7);
            g.setColor(Color.black);

        }

        public int getX(){ return x; }
        public int getY(){ return y; }
    }
