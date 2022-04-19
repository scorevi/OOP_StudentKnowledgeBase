import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.internal.rpc.LoadFinished;

import javax.swing.*;
import java.awt.*;

public class NavFrame extends BaseApp {

    private JButton BackButton;

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
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setBounds(100, 100, 479, 179);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel NavPanel = new JPanel();
        NavPanel.setLayout(new GridLayout(2, 2, 5, 10));

        JTextPane NavPane = new JTextPane();
        JScrollPane NavScrollPane = new JScrollPane(NavPane);

        NavPanel.add(NavScrollPane);
        NavScrollPane.setLayout(new GridLayout(1,1,3,5));

        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();
        browser.navigation().loadUrl(PageURL);

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            browser.navigation().on(LoadFinished.class, event -> {
                browser.mainFrame().ifPresent(webframe -> webframe.document().ifPresent(document -> {
                    document.documentElement().ifPresent(documentElement ->
                            documentElement.findElementByClassName(PageElement).ifPresent(element -> {
                                System.out.println(element.innerText());
                                NavPane.setText(element.innerText());

                            }));

                }));
                NavPanel.updateUI();
            });

        });

    }
}
