
package com;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

public class Calculate_class {

    private int  X,Y;
    private Point pixelPoint=new Point();
    private int pixelX,pixelY;
    private boolean havelife=true;
    private boolean movedbefore=false;
    
    private Point p=new Point();
    private Point old=new Point();
    private int state;
    private String str;
    public Calculate_class(String NameIcon,int startX,int startY,int state,String str) {
        
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
        this.state=state;
        this.str=str;
    }
    public void Set_Soilder_image(String NameIcon ,int startX, int startY,int state){
        
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
        this.state=state;
     }
    public int getstate()
    {
        return state;
    }
    public String getside()
    {
        return str;
    }
   
  public boolean movedbefore()
  {
    return movedbefore;
  }
   
    public boolean returnLife() {
        return  havelife ;
    }
    public int  returnX() {
        return X;
    }
     public void toOld(Point Old) {
        p.x=Old.x;
        p.y=Old.y;
    }
    public void setPoint(Point newPoint) {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
        p.x=X;
        p.y=Y;
       
    }
    public void Set_move_status()
    {
        movedbefore  = true;
    }
    public Point returnOld() {
        return old;
    }
    public void setX(int newX) {
        X=newX;
        p.x=X;
    }
    public void setY(int newY) {
        Y=newY;
        p.y=Y;
    }
    public void setPixels(int newpixelX,int newpixelY) {
        pixelPoint.x=newpixelX;
        pixelPoint.y=newpixelY;
    }
    public int getPixelX() {
        return pixelX;
    }
    public int getPixelY() {
        return pixelY;
    }
    public Point getpixelPoint() {
        return  pixelPoint;
    }
    public int  returnY() {
        return Y;
    }
    public Point returnPostion() {
        return (Point)p.clone();
    }
    public boolean Inthispostion(int x,int y) {
        if(p.x==x&&p.y==y)
            return true;
        return false;
    }
}
