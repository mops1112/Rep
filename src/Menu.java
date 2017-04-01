
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yotsathon
 */
public class Menu extends JPanel implements MouseListener {

    JPanel box;
    String iconPath;
    String text;
    JLabel menuText;
    JLabel icon;
    JPanel content;
    JPanel destination;
    JFrame fr;

    public Menu(String text, String path, JPanel content, JPanel destination) {
        this.content = content;
        this.destination = destination;
        icon = new JLabel();
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        menuText = new JLabel(text);
        menuText.setForeground(Color.black);
        menuText.setFont(new Font("Century Gothic", 0, 12));
        this.setPreferredSize(new Dimension(90, 80));
        this.setBackground(new Color(191, 189, 190));
        this.addMouseListener(this);
        add(icon);
        add(menuText);

        fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setPreferredSize(new Dimension(1024, 600));

    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        this.setBackground(new Color(150, 149, 150));
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        this.setBackground(new Color(191, 189, 190));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        fr.add(this.destination);
        fr.pack();
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
//        content.removeAll();
//
//        content.add(destination);
//        content.repaint();
//        content.revalidate();
//
//        this.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
