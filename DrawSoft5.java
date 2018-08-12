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
import java.io.File;

class MyJFrame extends JFrame implements ActionListener{
  MyJPanel myJPanel;
  java.util.List<MyJPanel> myJPanels = new ArrayList<MyJPanel>();
  ButtonPanel buttonpanel;
  JFileChooser fileChooser;
  JColorChooser colorchooser = new JColorChooser();
  JTabbedPane tabbedpane = new JTabbedPane();
  int tabcounter;

  private void addMenuItem(JMenu targetMenu, String itemName, String actionName, ActionListener listener){
    JMenuItem menuItem = new JMenuItem(itemName);
    menuItem.setActionCommand(actionName);
    menuItem.addActionListener(listener);
    targetMenu.add(menuItem);
  }

  private void initMenu(){
    tabcounter = 0;
    JMenuBar menubar=new JMenuBar();
    fileChooser = new JFileChooser();
    JMenu menuFile = new JMenu("File");
    this.addMenuItem(menuFile,"Open","Open",this);
    this.addMenuItem(menuFile,"Save","Save",this);
    menubar.add(menuFile);
    JMenu menuPen = new JMenu("Pen");
    this.addMenuItem(menuPen, "Color", "Color", this);
    JMenu menuWidth = new JMenu("Width");
    this.addMenuItem(menuWidth, "width1", "width1", this);
    this.addMenuItem(menuWidth, "width5", "width5", this);
    this.addMenuItem(menuWidth, "width10", "width10", this);
    this.addMenuItem(menuWidth, "width20", "width20", this);
    menuPen.add(menuWidth);
    menubar.add(menuPen);
    this.setJMenuBar(menubar);
  }

  MyJFrame(){
    myJPanel = new MyJPanel();
    buttonpanel = new ButtonPanel();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 800);
    getContentPane().add(buttonpanel,BorderLayout.LINE_START);
    getContentPane().add(myJPanel.label,BorderLayout.PAGE_END);
    getContentPane().add(myJPanel);
    initMenu();
    setVisible(true);
    myJPanel.createBuffer(myJPanel.getWidth(), myJPanel.getHeight());
  }

  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Open")){
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        myJPanel.openFile(fileChooser.getSelectedFile());
      }
    }
    if(e.getActionCommand().equals("Save")){
      int returnVal2 = fileChooser.showSaveDialog(this);
      if (returnVal2 == JFileChooser.APPROVE_OPTION) {
        myJPanel.saveFile(fileChooser.getSelectedFile());
      }
    }
    if(e.getActionCommand().equals("Color")){
      Color color = colorchooser.showDialog(this, "select color", Color.BLACK);
      if(color != null){
        myJPanel.setPenColor(color);
      }
    }
    if(e.getActionCommand().equals("width1")){
      myJPanel.setPenSize(1.0f);
    }
    if(e.getActionCommand().equals("width5")){
      myJPanel.setPenSize(5.0f);
    }
    if(e.getActionCommand().equals("width10")){
      myJPanel.setPenSize(10.0f);
    }
    if(e.getActionCommand().equals("width20")){
      myJPanel.setPenSize(20.0f);
    }
    if(e.getActionCommand().equals("New")){
    }
  }
}


public class DrawSoft5{
  public static void main(String[] args) {
    MyJFrame myJFrame = new MyJFrame();
  }
}
