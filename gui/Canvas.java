package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import world.World;

public final class Canvas extends JPanel {
    
    private int[] camera;
    
    public Canvas() {
        this.camera = new int[2];
        this.addMouseMotionListener(new MouseMotionListener(){

            private int[] last_mouse = new int[2];
            
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - last_mouse[0];
                int dy = e.getY() - last_mouse[1];
                camera[0] -= dx; camera[1] -= dy;
                last_mouse[0] += dx; last_mouse[1] += dy;
                Canvas.refresh();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                last_mouse[0] = e.getX();
                last_mouse[1] = e.getY();
            }
            
        });
    }
    
    public static void refresh() {
        GUI.getCanvas().repaint();
    }
    
    public static int[] getDimensions() { return new int[]{GUI.getCanvas().getWidth(), GUI.getCanvas().getHeight()}; }
    public static int[] getCamera() { return new int[]{(int)GUI.getCanvas().camera[0], (int)GUI.getCanvas().camera[1]}; }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        if (GUI.getCanvas() == null) return; //ensure that Netbeans editor does not throw an exception
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        
        World world = World.getWorld();
        
        if (world == null) { g.drawString("No world loaded!", 15, 25); return; } else { world.draw(g); }

        int[] origin = World.getWorld().getOnscreenCoordinates(0, 0);
        g.setColor(Color.red);
        g.drawLine((int)origin[0], 0, (int)origin[0], getHeight());
        g.drawLine(0, (int)origin[1], getWidth(), (int)origin[1]);
        
        g.setColor(Color.green);
        g.drawLine((int)origin[0] + World.getWorld().width(), 0, (int)origin[0] + World.getWorld().width(), getHeight());
        g.drawLine(0, (int)origin[1] + World.getWorld().height(), getWidth(), (int)origin[1] + World.getWorld().height());
        
    }
    
}
