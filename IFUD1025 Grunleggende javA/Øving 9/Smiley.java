import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame {
  public Vindu(String tittel) {
    setTitle(tittel);
    setSize(200, 200); //Bredd,Høyde
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Tegning tegningen = new Tegning();
    add(tegningen);
  }
}

class Tegning extends JPanel {
  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);
    setBackground(Color.LIGHT_GRAY);
    tegneflate.drawOval(10,10,150,150);
    tegneflate.fillOval(40,60,10,10);
    tegneflate.fillOval(120,60,10,10);
    tegneflate.drawArc(35,65,100,75,190,160);
  }
}

class Smiley {
  public static void main(String[] args) {
    Vindu smil = new Vindu("Smiley");
    smil.setVisible(true);
  }
}
