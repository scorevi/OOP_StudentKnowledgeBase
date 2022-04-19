import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.internal.rpc.LoadFinished;

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
        academicCalendarButton.setVisible(false);
        handbookButton.setVisible(false);
        resourcesButton.setVisible(false);
        FAQ_Button.setVisible(false);
        searchLabel.setVisible(false);
        searchTextField.setVisible(false);
        frame.setVisible(false);
    }
    public BaseApp() {
        academicCalendarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ChangeInterface();

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
