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
import java.awt.event.KeyEvent;

class ButtonPanel extends JPanel implements ActionListener{
  MyJPanel myJPanel = new MyJPanel();
  ImageIcon coloricon = new ImageIcon("./img/color.png");
  ImageIcon penicon = new ImageIcon("./img/pen.png");
  ImageIcon pensizeicon = new ImageIcon("./img/pensize.png");
  ImageIcon erasericon = new ImageIcon("./img/eraser.png");
  ImageIcon fishicon = new ImageIcon("./img/goldenfish.png");
  ImageIcon recticon = new ImageIcon("./img/rect.png");
  ImageIcon ovalicon = new ImageIcon("./img/oval.png");
  ImageIcon stringpenicon = new ImageIcon("./img/stringpen.png");


  JButton colorbutton = new JButton("color", coloricon);
  JButton pencilbutton = new JButton("  pen", penicon);
  JButton bucketbutton = new JButton("bucket");
  JButton pensizebutton = new JButton("pensize",pensizeicon);
  JButton eraserbutton = new JButton("eraser", erasericon);
  JButton fishbutton = new JButton("stamp", fishicon);
  JButton rectbutton = new JButton("四角", recticon);
  JButton ovalbutton = new JButton("円", ovalicon);
  JButton rainbowbutton = new JButton("もけもけ", stringpenicon);

  ButtonPanel(){
    setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    add(colorbutton);
    add(pencilbutton);
    add(pensizebutton);
    add(eraserbutton);
    add(rectbutton);
    add(ovalbutton);
    add(rainbowbutton);
    colorbutton.addActionListener(this);
    pensizebutton.addActionListener(this);
    eraserbutton.addActionListener(this);
    pencilbutton.addActionListener(this);
    fishbutton.addActionListener(this);
    rectbutton.addActionListener(this);
    ovalbutton.addActionListener(this);
    rainbowbutton.addActionListener(this);
    colorbutton.setMnemonic(KeyEvent.VK_C);
    pensizebutton.setMnemonic(KeyEvent.VK_S);
    eraserbutton.setMnemonic(KeyEvent.VK_E);
    pencilbutton.setMnemonic(KeyEvent.VK_P);
    rectbutton.setMnemonic(KeyEvent.VK_R);
    ovalbutton.setMnemonic(KeyEvent.VK_O);
    rainbowbutton.setMnemonic(KeyEvent.VK_Q);
  }
  public void actionPerformed(ActionEvent event){
    if (event.getSource() == colorbutton){
      JColorChooser colorchooser = new JColorChooser();
      Color color = colorchooser.showDialog(this, "select color", Color.BLACK);
      if(color != null){
        myJPanel.setPenColor(color);
      }
    }
    if (event.getSource() == pensizebutton){
      ImageIcon icon = new ImageIcon("./imag/icon_1.png");
      String selectvalues[] = {"5", "10", "15", "20", "25", "30"};
      Object size = JOptionPane.showInputDialog(this, "ペンの太さを入力してください", "select pen size", JOptionPane.INFORMATION_MESSAGE, icon,  selectvalues, selectvalues[0]);
      if(size != null){
        Float inputsize = Float.parseFloat((String)size);
        myJPanel.setPenSize(inputsize);
      }
    }
    if (event.getSource() == eraserbutton){
      myJPanel.stockPenInfo();
      myJPanel.setPenColor(Color.white);
      myJPanel.setPenSize(20.0f);
      myJPanel.setFlag(2);
    }

    if (event.getSource() == pencilbutton){
      myJPanel.resetPenColor();
      myJPanel.resetPenSize();
      myJPanel.setPenSize(20.0f);
      myJPanel.setFlag(1);
    }
    if (event.getSource() == fishbutton) {
      System.out.println("ok");
    }
    if (event.getSource() == rectbutton) {
      myJPanel.resetPenColor();
      myJPanel.resetPenSize();
      myJPanel.setFlag(3);
    }
    if (event.getSource() == ovalbutton) {
      myJPanel.resetPenColor();
      myJPanel.resetPenSize();
      myJPanel.setFlag(4);
    }
    if (event.getSource() == rainbowbutton) {
      myJPanel.resetPenColor();
      myJPanel.resetPenSize();
      myJPanel.setPenSize(20.0f);
      myJPanel.setFlag(5);
    }
  }
}
