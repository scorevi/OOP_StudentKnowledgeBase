import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.internal.rpc.LoadFinished;
import com.teamdev.jxbrowser.navigation.internal.rpc.LoadProgressChanged;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


public class NavFrame extends JFrame {

    private JButton BackButton = new JButton();
    /**
     * ConverterFrameconstructor.
     * E.g.
     * title: Celsius -  Fahrenheit Converter
     * inputText: Celsius
     * outputText: Fahrenheit
     *
     * @param title - Title of the window.
     * @param PageURL - URL of the page to render.
     */


    public NavFrame(String title, String PageURL, String PageElement) {

        this.setTitle(title);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel NavPanel = new JPanel();
        NavPanel.setLayout(new BorderLayout());

        JTextPane NavPane = new JTextPane();
        JScrollPane NavScrollPane = new JScrollPane(NavPane);

        JProgressBar LoadingBar = new JProgressBar();

        BackButton.setText("Back");

        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser SpecificBrowser = engine.newBrowser();
        BrowserView view = BrowserView.newInstance(SpecificBrowser);

        NavPanel.add(BackButton, BorderLayout.EAST);
        NavPanel.add(view, BorderLayout.CENTER);
        NavPanel.add(LoadingBar,BorderLayout.NORTH);
        this.setContentPane(NavPanel);


        Browser browser = engine.newBrowser();


        browser.navigation().loadUrl(PageURL);


        NavScrollPane.setEnabled(false);
        NavPane.setEditable(false);
        LoadingBar.setStringPainted(true);

        browser.navigation().on(LoadProgressChanged.class, event -> {
            LoadingBar.setValue((int)(event.getProgress()* 100));
        });

        Font font = new Font("Calibri Light", Font.BOLD, 16);
        NavPane.setFont(font);



        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance

            browser.navigation().on(LoadFinished.class, event -> {
                browser.mainFrame().ifPresent(webframe -> webframe.document().ifPresent(document -> {
                    document.documentElement().ifPresent(documentElement ->
                            documentElement.findElementByClassName(PageElement).ifPresent(element -> {
                                System.out.println(element.innerText());
                                NavScrollPane.setEnabled(true);
                                NavPane.setText(element.innerText());

                                String html = element.innerHtml();
                                String base64Html = Base64.getEncoder().encodeToString(html.getBytes(UTF_8));
                                String dataUrl = "data:text/html;charset=utf-8;base64," + base64Html;
                                SpecificBrowser.navigation().loadUrl(dataUrl);

                                LoadingBar.setStringPainted(false);


                            }));

                }));
                NavPanel.updateUI();
            });


        });

        BackButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                setVisible(false);
                BaseApp.frame.setVisible(true);
            }
        });

    }
}

