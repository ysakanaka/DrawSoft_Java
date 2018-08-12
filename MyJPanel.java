import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;

class MyJPanel extends JPanel implements MouseListener, MouseMotionListener{
  JLabel label;
  BufferedImage bufferImage = null;
  static Graphics2D bufferGraphics = null;
  static Color currentColor = Color.BLACK;
  static Float currentWidth = 20.0f;
  static Color prevColor;
  static Float prevWidth;
  int newx, newy, lastx=0, lasty=0;
  static int flag = 1;
  static final int PEN_MODE = 1;
  static final int ERASER_MODE = 2;
  static final int RECT_MODE = 3;
  static final int OVAL_MODE = 4;
  static final int RAINBOW_MODE = 5;
  float i, j, h;

  MyJPanel(){
    addMouseListener(this);
    addMouseMotionListener(this);
    setBackground(new Color(255, 255, 255));
    label = new JLabel();
  }

  public void createBuffer(int width, int height){
    bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    bufferGraphics = bufferImage.createGraphics();
    bufferGraphics.setBackground(new Color(255, 255, 255));
    bufferGraphics.clearRect(0, 0, width, height);
  }

  public void openFile(File file1){
    BufferedImage pictureImage;
    int panewidth, paneheight, imgwidth, imgheight;
    panewidth = this.getWidth();
    paneheight = this.getHeight();

    try{
      pictureImage = ImageIO.read(file1);
    } catch(Exception e){
      System.out.println("Error: open file =" +file1.getName());
      return;
    }
    imgwidth = pictureImage.getWidth();
    imgheight = pictureImage.getHeight();
    this.createBuffer(panewidth, imgheight * (panewidth / imgwidth));
    bufferGraphics.drawImage(pictureImage, 0, 0, panewidth, imgheight * (panewidth / imgwidth), this);
    repaint();
  }

  public void saveFile(File file2) {
    try {
      ImageIO.write(bufferImage, "jpeg", file2);
    } catch (Exception e) {
      System.out.println("Error: writing file =" + file2.getName());
      return;
    }
  }



  public void setPenColor(Color newColor){
    currentColor = newColor;
  }

  public void setPenSize(Float newWidth){
    currentWidth = newWidth;
  }

  public void stockPenInfo(){
    prevColor = currentColor;
    prevWidth = currentWidth;
  }

  public void resetPenColor(){
    currentColor = prevColor;
  }

  public void resetPenSize(){
    currentWidth = prevWidth;
  }

  public void setFlag(int number){
    flag = number;
  }

  public void drawPenLine(int x1, int y1, int x2, int y2){
    bufferGraphics.setColor(currentColor);
    bufferGraphics.setStroke(new BasicStroke(currentWidth,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.drawLine(x1, y1, x2, y2);
    repaint();
  }

  public void drawStringLine(int x1, int y1, int x2, int y2){
    bufferGraphics.setColor(currentColor);
    bufferGraphics.setStroke(new BasicStroke(currentWidth,  BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.drawLine(x1, y1, x2, y2);
    repaint();
  }

  public void drawRect(int x, int y){
    bufferGraphics.setColor(currentColor);
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.fillRect(x, y, 50, 50);
    repaint();
  }

  public void drawCircle(int x, int y){
    bufferGraphics.setColor(currentColor);
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.fillOval(x, y, 50, 50);
    repaint();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    if(null != bufferImage){
      g2d.drawImage(bufferImage, 0, 0, this);
    }
  }

  @Override public void mouseClicked(MouseEvent e){
    System.out.println(e.getX() + "," + e.getY());
    label.setText("(" + e.getX()+ "," + e.getY() + ")");
    System.out.println(flag);
    switch (flag) {
      case 3:
      drawRect(e.getX(), e.getY());
      break;

      case 4:
      drawCircle(e.getX(), e.getY());
      break;
    }


  }

  @Override public void mouseDragged(MouseEvent e){
    switch (flag) {
      case 1:
      newx = e.getX();
      newy = e.getY();
      drawPenLine(lastx, lasty, newx, newy);
      lastx = newx;
      lasty = newy;
      break;

      case 2:
      newx = e.getX();
      newy = e.getY();
      drawPenLine(lastx, lasty, newx, newy);
      lastx = newx;
      lasty = newy;
      break;

      case 3:
      break;

      case 4:
      break;

      case 5:
      newx = e.getX();
      newy = e.getY();
      drawStringLine(lastx, lasty, newx, newy);
      lastx = newx;
      lasty = newy;
      break;





    }
  }

  @Override public void mouseMoved(MouseEvent e){}
  @Override public void mouseEntered(MouseEvent e){}
  @Override public void mouseExited(MouseEvent e){}
  @Override public void mousePressed(MouseEvent e){
    lastx = e.getX();
    lasty = e.getY();
  }
  @Override public void mouseReleased(MouseEvent e){}
}
