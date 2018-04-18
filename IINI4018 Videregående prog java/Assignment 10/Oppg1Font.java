/*
	Øving 10
	18.04.2018
	Anders Kvanvig
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TekstVindu extends JFrame {
    private JLabel eksempelTekst = new JLabel("Dette er en eksempeltekst for skrifttypene");
    private Font[] fontListe;

    public TekstVindu(String tittel, Font[] fontListe) {
        this.setVisible(true);  //Lagt her ettersom det ikke kunne kjøres fra statisk kontekst
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(eksempelTekst);
        this.fontListe = fontListe;
        Knappelytter lytter = new Knappelytter();

        //Genererer antall knapper basert på fonter gitt
        for (Font font : fontListe) {
            JButton knapp = new JButton(font.getFontName());
            knapp.addActionListener(lytter);
            add(knapp);
        }
        pack();
    }

    //Bruker en intern klasse for å plukke opp ActionEvents, dette gir tilgang til private variabler i klassen over.
    private class Knappelytter implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton valgtKnapp = (JButton) hendelse.getSource(); //Returnerer et object, dette kan castes til typen vi vet det vil være.
            String fontNavn = valgtKnapp.getText();

            //Finner fonten i fontlista, og justerer JLabel-objektet sin font (arvet fra JComponent-klassen)
            for (Font font : fontListe) {
                if (font.getFontName().equals(fontNavn)) {
                    eksempelTekst.setFont(font);
                }
            }
        }
    }
}

class FontEndring {
    //Flere fonter kan enkelt legges til ved å legge flere i Arrayen
    private static Font[] fonter = {new Font("SansSerif", Font.PLAIN, 11),
                                    new Font("Monospaced", Font.PLAIN, 11),
                                    new Font("Dialog", Font.PLAIN, 11),
                                    new Font("Serif", Font.PLAIN, 11),
                                    new Font("Arial", Font.PLAIN, 11)};

    public static void main(String[] args) {
        TekstVindu vinduet = new TekstVindu("FontEndring", fonter);
    }
}
