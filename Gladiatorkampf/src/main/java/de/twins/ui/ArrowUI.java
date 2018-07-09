package de.twins.ui;



import java.awt.*;
import java.awt.geom.AffineTransform;

public class ArrowUI extends GameObject{


    private int arrowLength;

    public ArrowUI(int x, int y) {
        super(x, y);
        setVelx(5);
        setVely(-4);
        arrowLength = 10;
    }


    @Override
    public void tick() {
        setX(getX() + getVelx());
        setY(getY() + getVely());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        Graphics2D g1 = (Graphics2D) g;

        double degree = calculateDegree(getVelx(),getVely());
        AffineTransform transform = g1.getTransform();
        g1.rotate(degree,x,y);
        g.drawLine(x ,y,x + arrowLength,y);
        //feathers
        g.drawLine(x,y,x-2,y-2);
        g.drawLine(x,y,x-2,y+2);
        //arrowhead
        g.fillPolygon(new int[]{x + arrowLength,x-3 + arrowLength,x-3 + arrowLength},new int[]{y,y+3,y-3},3);
        g1.setTransform(transform);
    }

    private double calculateDegree(int velx, int vely) {
    // -------------------------- 0
        double velx1 = velx;
        double vely1 = vely;
        return  Math.tan((double)vely/(double)velx);
    }
}
