package oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class LaunchPage implements ActionListener {
    JFrame frame= new JFrame();
    JButton myButton= new JButton("New Animation");
    LinkedList<Grass> grasses=new LinkedList();
    LinkedList<Animal> animals=new LinkedList();
    IWorldMap map;

    LaunchPage(LinkedList<Animal> animals, LinkedList<Grass> grasses,IWorldMap map){

        this.animals=animals;
        this.grasses=grasses;
        this.map=map;
        myButton.setBounds(100,160,200,40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(myButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==myButton){
                NewWindow myWindow= new NewWindow(animals,grasses,map);
            }

    }


}
