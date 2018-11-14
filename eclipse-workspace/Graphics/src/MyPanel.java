import javax.swing.JPanel;
import java.awt.Graphics;

public class MyPanel extends JPanel
{
    protected int x;
    protected int y;
    
    public MyPanel()
    {
        super();
        x = 10;
        y = 100;
    }
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

}
