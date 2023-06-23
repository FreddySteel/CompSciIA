import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JPanel implements ActionListener {
        // canvas for other GUI widgets

        JButton button1;
        JButton button2;

        public GUI(int width, int height) {
            System.out.println("SEQUENCE: GUI constructor");
            this.setPreferredSize(new Dimension(width, height));
            setLayout(null);
            button1 = new JButton("");
            button1.setBounds(250,180, 100, 40);
            button2 = new JButton("");
            button2.setBounds(500,0, 100, 40);
            add(button1);
            button1.addActionListener(this);
            add(button2);
            button2.addActionListener(this);
        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
