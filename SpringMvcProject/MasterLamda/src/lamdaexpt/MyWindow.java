package lamdaexpt;

import javax.swing.*;
import java.awt.*;

public class MyWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Window");
        frame.setSize(400,400);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JButton jButton = new JButton("click on me ");
        frame.add(jButton);
    }


}
