package de.laurens.tree;


/**
 * Klasse TreeDemo
 *
 * Roland Stiebel
 * version 1.0
 */

//Import
import basis.*;
import java.awt.*;
import java.util.ArrayList;


public class TreeDemo extends Fenster implements KnopfLauscher, TextFeldLauscher
{

    //Deklaration
    private Knopf ende, knopf2;
    private BeschriftungsFeld label1;

    private BeschriftungsFeld searchTitle;
    private TextFeld searchInput;
    private BeschriftungsFeld searchOutput;

    private BeschriftungsFeld inputTitle, inputIDTitle, inputOutput;

    private TextFeld inputIDInput;

    private BeschriftungsFeld inputValueTitle;
    private TextFeld inputValueInput;






    private TextFeld input;

    private ReiterFeld reiterFeld;
    private Leinwand leinwand;
    private Stift stift;

    private GrundFlaeche grundFlaeche;

    private Tree tree;
    private Content content;

    // Konstruktor fuer Objekte der Klasse TischKlasse

    public TreeDemo()
    {
        this.initGui();
    }

    public void initGui()
    {
        this.setzeGroesse(600,500);
        this.setzeTitel("TreeDemo");
        ende = new Knopf("Ende",490,460,100,30);
        ende.setzeKnopfLauscher(this);

        leinwand = new Leinwand(0, 0, 600, 450);
        stift = new Stift();
        stift.maleAuf(leinwand);
        grundFlaeche = new GrundFlaeche(0, 0, 600, 450);


        reiterFeld = new ReiterFeld(0,0, 600, 450, this);
        reiterFeld.fuegeReiterHinzu("Visualisierung", leinwand);
        reiterFeld.fuegeReiterHinzu("Eingabe", grundFlaeche);


        input = new TextFeld(140, 460, 100, 30);
        input.setzeTextFeldLauscher(this);

        searchTitle = new BeschriftungsFeld("Suche", 10, 70, 200, 30);
        searchInput = new TextFeld(10, 100, 100, 30);
        searchInput.setzeTextFeldLauscher(this);
        searchOutput = new BeschriftungsFeld("Search Output",120, 100, 100, 30);

        inputTitle = new BeschriftungsFeld("Hinzufügen", 10, 130, 200, 30);
        inputIDTitle = new BeschriftungsFeld("ID:", 10, 160, 30, 30);
        inputIDInput = new TextFeld(40, 160, 100, 30);
        inputIDInput.setzeTextFeldLauscher(this);
        inputIDInput.setzeTextFeldLauscher(this);

        inputValueTitle = new BeschriftungsFeld("Value:", 150, 160, 100, 30);
        inputValueInput = new TextFeld(200, 160, 100, 30);
        inputValueInput.setzeTextFeldLauscher(this);
        inputOutput = new BeschriftungsFeld("Response",310, 160, 200, 30);

        label1 = new BeschriftungsFeld("TreeDemo",10,10,580,30);

        knopf2 = new Knopf("Build Tree",10,460,120,30);
        knopf2.setzeHintergrundFarbe(Color.red); 
        knopf2.setzeKnopfLauscher(this);

        grundFlaeche.betteEin(label1);
        grundFlaeche.betteEin(searchTitle);
        grundFlaeche.betteEin(searchInput);
        grundFlaeche.betteEin(searchOutput);
        grundFlaeche.betteEin(inputTitle);
        grundFlaeche.betteEin(inputIDTitle);
        grundFlaeche.betteEin(inputIDInput);
        grundFlaeche.betteEin(inputValueTitle);
        grundFlaeche.betteEin(inputValueInput);
        grundFlaeche.betteEin(inputOutput);




        tree = new Tree();
        content=new Content(10,"10");
        tree.insert(content);
        content=new Content(20,"20");
        tree.insert(content);
        content=new Content(5,"5");
        tree.insert(content);
        content=new Content(17,"17sdf");
        tree.insert(content);
        content=new Content(16,"16sdf");
        tree.insert(content);
        content=new Content(19,"19");
        tree.insert(content);
        content = new Content(1, "1");
        tree.insert(content);

        tree.printSmallest();

       /* System.out.println("Ascending print");
        tree.printAsc();
        System.out.println("Descending print");
        tree.printDesc();

        System.out.println("Level of 16: " + tree.getLevel(16));
        System.out.println("Term of id 16: " + tree.search(16));*/
       /* System.out.println("Tree depth: " + tree.depth());
        System.out.println("ROW 2: ");
        tree.printRow(1);*/


    }

    @Override
    public void bearbeiteKnopfDruck(Knopf k)
    {
        if (k ==ende)
        {
            this.gibFrei();
        }
        else if (k ==knopf2)
        {
            leinwand.loescheAlles();
           stift.hoch();
            int lb = leinwand.breite();
            int lh = leinwand.hoehe();
            stift.bewegeAuf(lb/ 2, 0);
            ArrayList<String> drawInst = tree.getTreeDrawInst();
            drawInst.forEach((instruction) -> {
                stift.hoch();

                System.out.println(instruction);
                String[] params = instruction.split(";");

                if(params[0].equals("0")) {
                    //code for drawing content
                    String content = params[1];
                    double x = Double.parseDouble(params[2]);
                    double y = Double.parseDouble(params[3]);

                    stift.bewegeAuf(lb * x, lh * y);
                    stift.schreibe(content);
                } else if (params[0].equals("1")) {
                    //code for drawing line
                    double fromX = Double.parseDouble(params[1]);
                    double fromY = Double.parseDouble(params[2]);
                    double toX = Double.parseDouble(params[3]);
                    double toY = Double.parseDouble(params[4]);

                    stift.bewegeAuf(lb * fromX, lh * fromY);
                    stift.runter();
                    stift.zeichneKreis(15);
                    stift.bewegeAuf(lb * toX, lh * toY);
                    stift.hoch();

                }

            });
        }

    }






    /*TODO: REMOVE BEFORE DEPLOY*/
    public static void main(String[]args){
        new TreeDemo();
    }

    @Override
    public void bearbeiteReturnGedrueckt(TextFeld textFeld) {

        if(textFeld == inputIDInput || textFeld == inputValueInput) {
            if(inputIDInput != null && inputValueInput != null) {
                if(inputIDInput.text().matches("[0-9]+")) {
                    if(tree.search(Integer.parseInt(inputIDInput.text())).id == -1) {
                        tree.insert(new Content(Integer.parseInt(inputIDInput.text()), inputValueInput.text()));
                        inputOutput.setzeText("Hinzugefügt!");
                    } else {
                        inputOutput.setzeText("ID existiert schon!");
                    }
                }
            }
        }

        if(textFeld == searchInput) {
            if(textFeld.text().matches("[0-9]+")) {
                searchOutput.setzeText(tree.search(Integer.parseInt(textFeld.text())).value);
            }
        }


    }

    @Override
    public void bearbeiteTextVeraenderung(TextFeld textFeld) {
        if(textFeld == searchInput) {
            if(!textFeld.text().matches("[0-9]+")) {
                textFeld.setzeSchriftFarbe(Color.RED);
            }else {
                textFeld.setzeSchriftFarbe(Color.BLACK);
            }
        }
    }

    @Override
    public void bearbeiteFokusVerloren(TextFeld textFeld) {

    }
}
