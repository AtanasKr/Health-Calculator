import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel panel;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField waist;
    private JTextField weight;
    private JTextField height;
    private JTextField hip;
    private JTextField neck;
    private JTextField age;
    private JButton calculate;
    private JTextArea sResult;
    boolean male;
    boolean validate=false;
    ButtonGroup bgroup = new ButtonGroup();

    public GUI(){
        add(panel);
        setTitle("Health Calculator");
        setSize(800,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgroup.add(maleRadioButton);
        bgroup.add(femaleRadioButton);
        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                male = true;
                validate =true;
            }
        });
        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                male=false;
                validate = true;
            }
        });
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate==false){
                    sResult.setText("Error");
                }
                else {
                    String cWaist = waist.getText();
                    int useWaist = Integer.parseInt(cWaist);
                    String cHeight = height.getText();
                    int useHeight = Integer.parseInt(cHeight);
                    String cWeight = weight.getText();
                    int useWeight = Integer.parseInt(cWeight);
                    String cHip = hip.getText();
                    int useHip = Integer.parseInt(cHip);
                    String cNeck = neck.getText();
                    int useNeck = Integer.parseInt(cNeck);
                    String cAge = age.getText();
                    int useAge = Integer.parseInt(cAge);
                    if(useHeight<80||useHeight>300||useWeight<35||useWeight>300||useNeck<15||useNeck>60){
                        sResult.setText("Error");
                    }
                    else {
                        sResult.setText(Result(useWaist, useHeight, useWeight, useHip, useNeck, useAge));
                    }
                }

            }
        });
    }
    public String Result(int getWaist,int getHeight,int getWeight, int getHip, int getNeck,int getAge){
        GUI check = new GUI();
        double bodyFat,fatMass,leanMass;
        String condition=null;
        String finalResult=null;
        if(male==true){
        bodyFat=495/(1.0324 - 0.19077*Math.log10(getWaist-getNeck) + 0.15456*Math.log10(getHeight))-450;
        fatMass=(bodyFat/100)*getWeight;
        leanMass = getWeight-fatMass;
        }
        else {
            bodyFat=495/(1.29579 - 0.35004*Math.log10(getWaist+getHip-getNeck) + 0.22100*Math.log10(getHeight))-450  ;
            fatMass=(bodyFat/100)*getWeight;
            leanMass = getWeight-fatMass;

        }
        if(male==true){
            if(bodyFat<14){
                condition="athlete";
            }
            if(bodyFat<18){
                condition="fitness";
            }
            if(bodyFat<25){
                condition="average";
            }
            else{
                condition="obese";
            }
        }

        if(male==false){
            if(bodyFat<21){
                condition="athlete";
            }
            if(bodyFat<25){
                condition="fitness";
            }
            if(bodyFat<32){
                condition="average";
            }
            else{
                condition="obese";
            }
        }
        finalResult = "Body Fat Percentage="+ bodyFat+" Lean Fat="+leanMass+" Fat Mass="+fatMass+" Condition="+condition;
        return finalResult;
    }
}
