import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BaseApp extends FrameCustomizer {
    private JPanel mainPanel;
    private JButton academicCalendarButton;
    private JButton handbookButton;
    private JButton FAQ_Button;
    private JFrame frame;
    public BaseApp() {

        initialize();

    }

    public void initialize() {
        frame = new JFrame("Student Knowledge Base");

        // Academic Calendar button handler
        academicCalendarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                NavFrame nf = new NavFrame("Academic Calendar", "https://confluence.ciit.edu.ph:8443/display/CS/Academic+Calendar", "columnLayout two-equal");
                ChangeInterface(nf);
                frame.setVisible(false);

            }
        });

        // Handbook button handler

        handbookButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HandbookFrame hf = new HandbookFrame();
                ChangeInterface(hf);
                frame.setVisible(false);
            }
        });

        // Application's Documentation FAQ button handler

        FAQ_Button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        frame.setLayout(new GridBagLayout());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



    public static void main(String[] args) {

        // Mandatory evaluation license key for JxBrowser, DO NOT REMOVE! (04.18.2022 - 05.18.2022)
        System.setProperty("jxbrowser.license.key", "1BNDHFSC1G2FM398LUD9P59APVTGX3OOMX268QX3POT8B36H7AN1W6TJ4EOXUNWX4UPS8T");
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new BaseApp();
            }
        });


    }


    @Override
    public void ChangeInterface(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }


}
