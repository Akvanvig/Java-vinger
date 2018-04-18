/*
 * TestTegneramme.java   E.L. 2004-11-02
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Tegneramme extends JFrame {
  private ArrayList<Point> allePunktene = new ArrayList<Point>();

  public Tegneramme(String tittel) {
    super(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 500); // pga tegningen
    Tegning t = new Tegning();
    add(t);
    MusetrykkLytter museLytter = new MusetrykkLytter();
    t.addMouseListener(museLytter);
  }

  private class Tegning extends JPanel {
    public void paintComponent(Graphics vindu) {
      super.paintComponent(vindu);
      if (allePunktene.size() >= 2) { // må ha minst to punkter for å tegne
        Point forrigePunkt = allePunktene.get(0);
        for (int i = 1; i < allePunktene.size(); i++) {
          Point dettePunkt = allePunktene.get(i);
          vindu.drawLine(forrigePunkt.x, forrigePunkt.y,
                          dettePunkt.x, dettePunkt.y);
          forrigePunkt = dettePunkt;
        }
      }
    }
  }

  private class MusetrykkLytter implements MouseListener {
    public void mouseClicked(MouseEvent hendelse) {
      Point punkt = hendelse.getPoint();
      allePunktene.add(punkt);
      System.out.println("Mus trykket i pkt: " + punkt.x + " " + punkt.y);
      repaint();
    }
    public void mousePressed(MouseEvent hendelse) {}
    public void mouseReleased(MouseEvent hendelse) {}
    public void mouseEntered(MouseEvent hendelse) {}
    public void mouseExited(MouseEvent hendelse) {}
  }
}

class TestTegneramme {
  public static void main(String[] args) {
    Tegneramme etVindu = new Tegneramme("Tegneramme");
    etVindu.setVisible(true);
  }
}