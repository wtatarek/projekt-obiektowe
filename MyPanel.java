package oop;



import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    final int PANEL_WIDTH = 1100;
    final int PANEL_HEIGHT = 700;
    Timer timer;
    LinkedList<Grass> grasses = new LinkedList();
    LinkedList<Animal> animals = new LinkedList();

    //int startstop = 0;
    IWorldMap map;
    Global g=new Global();
    JFrame frame;
    Animal a=null;
    String maxGene="";
    MyPanel(LinkedList<Animal> animals, LinkedList<Grass> grasses, IWorldMap map,JFrame frame) {
        this.animals = animals;
        this.grasses = grasses;
        this.map = map;
        this.frame=frame;
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        timer = new Timer(2, this);
        timer.start();
        this.maxGene= animals.get(0).genesToString();
    }




    public String dominatingGenes(){
        // bo tu jest funkcja add
        // trzymanie na bierzaco

       /* int maxcounter=0;
        String max="";
        for(Animal a: animals){
            String s=a.genesToString();
            int counter=0;
            for(Animal b: animals)
            {
                if(b.genesToString().equals(s)){

                    counter++;
                    System.out.print(counter);
                }
            }
            if(counter>=maxcounter){
                max=s;
                maxcounter=counter;
            }
        }*/
        int count=0;
        String s="";
        for(Animal a:animals){
            if(a.ile_zwierzat_ma_geny_co_ja>=count){
                count=a.ile_zwierzat_ma_geny_co_ja;
                s=a.genesToString();


            }
        }
        return s;
    }

    public String averageEnergyLevel(){
        float allEnergy=0;
        for(Animal a:animals){
            allEnergy+=a.getEnergy();
        }
        if(animals.size()!=0){
            return String.valueOf(allEnergy/animals.size());
        }
        return "there is no animals";
    }

    public String averageNumberOfChildren(){
        int x=0;
        for(Animal a: animals){
            x+=a.numberOfChildren;
        }
        if(animals.size()!=0){
            return String.valueOf(x/animals.size());
        }
        return "0";
    }
    public void updateAnimal(Animal a){
        this.a=a;
    }

    public String lifeExpectancy(){
        int numberOfDeadAnimals=map.getNumberOfDeadAnimals();
        int ageOfDeadAnimals=map.getAgeOfDeadAnimals();
        if(numberOfDeadAnimals!=0){
            return String.valueOf(ageOfDeadAnimals/numberOfDeadAnimals);
        }
        return "no dead animals";

    }
    /*public void paintAnimalAtributs(Animal a,Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawString( " geny zwierzęcia: ",710,270);}*/



    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(5));
        g2D.setPaint(new Color(0x008000));
        for (int i = 0; i <= 700; i += 10) {
            for (int j = 0; j <= 700; j += 10) {
                //System.out.println(j+" "+i+" "+(j+10)+" "+(i+10));
                g2D.fillOval(j, i, 10, 10);
            }
        }
        for (Animal a : animals) {
            float energy = a.getEnergy();
          /*  System.out.print(" elo pozycje");
            System.out.println(10 * a.getPosition().getX() + " " + 10 * a.getPosition().getY() +"\n");
            System.out.print(" koniec pozycji  \n");*/
            if (energy <= 1) {
                g2D.setPaint(new Color(0xFF9999));
            }
            if (energy > 1 && energy < 2) {
                g2D.setPaint(new Color(0xFF3333));
            }
            if (energy >= 2 && energy < 3) {
                g2D.setPaint(new Color(0xCC0000));
            }
            if (energy >= 4) {
                g2D.setPaint(new Color(0x660000));
            }
            Vector2d v = a.getPosition();
            g2D.fillOval(10 * v.getX(), 10 * v.getY(), 10, 10);

        }
        for (Grass t : grasses) {
            Vector2d v = t.getPosition();
            g2D.setPaint(new Color(0x00FF80));
            g2D.fillOval(10 * v.getX(), 10 * v.getY(), 10, 10);
        }
        g2D.setPaint(Color.black);
        //g2D.fillOval(710,0,100,100);
        g2D.drawString("number of animals:",710,10);
        g2D.drawString(String.valueOf(animals.size()),710,20);
        g2D.drawString("number of plants:",710,40);
        g2D.drawString(String.valueOf(grasses.size()),710,50);
        g2D.drawString("dominating genes:",710,70);
        String dominatingGenes = ""+dominatingGenes();
       /* for(int i=0;i<dominatingGenes.size();i++){
            g2D.drawString(String.valueOf(dominatingGenes.get(i)),710+10*i,80);
        }*/
        g2D.drawString(dominatingGenes,710,80);
        g2D.drawString("average energy level for living animals:",710,100);
        g2D.drawString(averageEnergyLevel(),710,110);
        g2D.drawString("average number of children for living animals:",710,130);
        g2D.drawString(averageNumberOfChildren(),710,140);
        g2D.drawString("life expectancy for dead animals:",710,160);
        g2D.drawString(lifeExpectancy(),710,170);
        g2D.drawString("to run press 'a' ",710,190);
        g2D.drawString("to stop press 'w' ",710,210);
        g2D.drawString("jesli chcesz info o zwierzaku  ",710,230);

        g2D.drawString("zatrzymaj animcję i kliknij na nie: ",710,250);
        if(a!=null){
            g2D.setPaint(Color.red);
            g2D.drawString( "geny zwierzęcia: ",710,270);

            int[] geny=a.getGenes();
            String genys="";
            for(int i=0;i<32;i++) {
                genys = genys+Integer.toString(geny[i]);
            }
            g2D.drawString( genys,710,290);
            g2D.drawString( "ilosc dzieci: ",710,310);
            g2D.drawString(String.valueOf(a.numberOfChildren),
                    710,
                    330);
            g2D.drawString( "zmarły? ",710,350);
            if(a.getEnergy()<=0) g2D.drawString( "TAK ",710,370);
            else g2D.drawString( "NIE ",710,370);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(g.startstop==1){
            MoveDirection[] directions = map.skretIPrzemieszczenie();
            map.run(directions,map);
            this.grasses = ((GrassField) map).getGrasses();
            this.animals = ((GrassField) map).animals;
            repaint();
        }
        if(g.startstop==0){
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                g.setStartstop(1);
                break;
            case 'w':
                g.setStartstop(0);
                break;
            case 'k':

                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*switch(e.getLocationOnScreen())*/
      /*  Point pt=new Point(e.getLocationOnScreen());
        for (Animal a : animals){
            Vector2d v =a.getPosition();
            /*Vector2d v2 = new Vector2d(pt.x,pt.y);*/
        /*    if((pt.x>=v.getX()) && (pt.x<=v.getX()+10) && (pt.y>=v.getY()) && (pt.y<=v.getY()+10)){
                int[] geny=a.getGenes();
                System.out.print(pt.toString());
                break;
            }
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
