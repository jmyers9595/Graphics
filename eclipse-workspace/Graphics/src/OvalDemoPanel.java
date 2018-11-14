import java.awt.Color;
import java.awt.Graphics;

public class OvalDemoPanel extends MyPanel
{
    public OvalDemoPanel()
    {
        super();
    }
    
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        
        int centerX = 40;
        int centerY = 50;
        
        int width = 40;
        int height = 50;
        
        g.drawOval(centerX,centerY,width,height);
        
    }

}
