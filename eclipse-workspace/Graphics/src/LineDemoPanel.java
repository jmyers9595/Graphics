import java.awt.Color;
import java.awt.Graphics;

public class LineDemoPanel extends MyPanel
{
    public LineDemoPanel()
    {
        super();
    }
    
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        
        int startX = 10;
        int startY = 10;
        
        int endX = 280;
        int endY = 280;
        
        g.drawLine(startX, startY, endX, endY);
    }

}
