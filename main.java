/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KordiKereso;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Radagea
 */
public class main extends JFrame implements ActionListener {
    private JLabel telep, knev, ktel;
    private JButton gomb;
    private JPanel p1,p2,p3;
    private JTextField bemenet = new JTextField(20);
    private JTextField kimenetnev = new JTextField(20);
    private JTextField kimenettel = new JTextField(20);
    private java.util.List<Koordinatorok> asd = new ArrayList<>();
    
    
    
    // public Koordinatorok asd = new Koordinatorok("Teszt Elek","Debrecen","+36 30 7845346");
    
    
    public main(String title, List<Koordinatorok> li) throws HeadlessException {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asd = li;
        
        this.telep = new JLabel("Település: ");
        this.knev = new JLabel("Ügyeletes neve: ");
        this.ktel = new JLabel("Ügyeletes telszáma: ");
        
        this.gomb = new JButton("Keresés");
        this.gomb.addActionListener(this);
        
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        this.p3 = new JPanel();
        
        GridLayout alap = new GridLayout(4,2);
        GridLayout kereso = new GridLayout(1,2);
        GridLayout gombl = new GridLayout(1,1);
        GridLayout kimenet = new GridLayout(2,2);
        this.setLayout(alap);
        p1.setLayout(kereso);
        p2.setLayout(gombl);
        p3.setLayout(kimenet);
        
        p1.add(telep);
        p1.add(bemenet);
        
        p2.add(gomb);
        
        p3.add(knev);
        p3.add(kimenetnev);
        p3.add(ktel);
        p3.add(kimenettel);
        
        this.add(p1);
        this.add(p2);
        this.add(p3);
        
        
        pack();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean talalt = false;
        for (int i = 0; i < asd.size(); i++) {
            if (bemenet.getText().equals(asd.get(i).getTelepülés())) {
            System.out.println("ok");
            kimenetnev.setText(asd.get(i).getNév());
            kimenettel.setText(asd.get(i).getTel());
            talalt = true;
            } 
        }
        if (talalt == false) {
            kimenetnev.setText("Nincs ilyen körzet ");
            kimenettel.setText("Nincs ilyen körzet ");
        }
    }
}
