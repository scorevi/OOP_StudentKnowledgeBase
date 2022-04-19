import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaseApp extends JFrame {
    private JPanel mainPanel;
    private JPanel mainContent;
    private JTextField searchTextField;
    private JButton academicCalendarButton;
    private JButton handbookButton;
    private JButton resourcesButton;
    private JButton FAQ_Button;
    private JLabel searchLabel;
    private static JFrame frame = new JFrame("CIIT Student Knowledge Base");
    public void ChangeInterface() {
        frame.setVisible(false);
    }
    public BaseApp() {
        academicCalendarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ChangeInterface();
                NavFrame nf = new NavFrame("Academic Calendar", "https://confluence.ciit.edu.ph:8443/display/CS/Academic+Calendar", "columnLayout two-equal");
                nf.setVisible(true);
                nf.setLayout(new GridBagLayout());
                nf.setSize(600,400);
                nf.pack();
            }
        });
        handbookButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        FAQ_Button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        resourcesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }

    public static void main(String[] args) {
        System.setProperty("jxbrowser.license.key", "1BNDHFSC1G2FM398LUD9P59APVTGX3OOMX268QX3POT8B36H7AN1W6TJ4EOXUNWX4UPS8T");


        frame.setLayout(new GridBagLayout());
        frame.setContentPane(new BaseApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setResizable(false);






    }
}
