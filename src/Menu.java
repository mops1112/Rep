
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    public Menu(String text, String path, JPanel content, JPanel destination) {
        this.content = content;
        this.destination = destination;
        icon = new JLabel();
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        menuText = new JLabel(text);
        menuText.setForeground(Color.white);
        menuText.setFont(new Font("times new roman", Font.BOLD, 14));
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(new Color(19, 78, 220));
        this.addMouseListener(this);
        add(icon);
        add(menuText);

    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        this.setBackground(new Color(21, 28, 250));
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        this.setBackground(new Color(19, 78, 220));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        content.removeAll();
        
        content.add(destination);
        content.repaint();
        content.revalidate();
        
        this.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

}
