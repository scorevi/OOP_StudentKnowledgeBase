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

                Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
                Browser browser = engine.newBrowser();
                browser.navigation().loadUrl("https://confluence.ciit.edu.ph:8443/display/CS/Academic+Calendar");


                SwingUtilities.invokeLater(() -> {
                    // Creating Swing component for rendering web content
                    // loaded in the given Browser instance
                    browser.navigation().on(LoadFinished.class,event -> {
                        browser.mainFrame().ifPresent(frame -> frame.document().ifPresent(document -> {
                            document.documentElement().ifPresent(documentElement ->
                                    documentElement.findElementByClassName("columnLayout two-equal").ifPresent(element -> {
                                        System.out.println(element.innerText());
                                        JTextPane webPane = new JTextPane();


                                        JScrollPane webScrollPane = new JScrollPane(webPane);

                                        webScrollPane.setSize(600,400);
                                        mainPanel.add(webScrollPane, BorderLayout.WEST);
                                        webPane.setText(element.innerText());

                                    }));

                        }));
                        mainContent.updateUI();
                    });

                });

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
