import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.frame.Frame;
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

    private final JButton BackButton = new JButton();
    private final JPanel NavPanel;
    private final JProgressBar LoadingBar;

    private final BrowserManager BManager;

    /**
     * NavFrame constructor for rendering specific contents of a website.
     *
     * @param title       Title of the window.
     * @param PageURL     URL of the page to load.
     * @param PageElement Specific element of a page to gather content.
     */

    public NavFrame(String title, String PageURL, String PageElement) {

        this.setTitle(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        NavPanel = new JPanel();
        LoadingBar = new JProgressBar();
        BManager = new BrowserManager();

        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();
        BrowserView view = BrowserView.newInstance(browser);

        NavPanel.setLayout(new BorderLayout());
        BackButton.setText("Back");

        NavPanel.add(BackButton, BorderLayout.EAST);
        NavPanel.add(view, BorderLayout.CENTER);
        NavPanel.add(LoadingBar, BorderLayout.NORTH);
        this.setContentPane(NavPanel);

        browser.navigation().loadUrl(PageURL);
        view.setVisible(false);
        BackButton.setVisible(false);
        LoadingBar.setStringPainted(true);

        BManager.getLoadProgressValue(browser, LoadingBar);

        // Web scraping specific contents of a webpage

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            browser.navigation().on(LoadFinished.class, event -> {
                browser.mainFrame().flatMap(Frame::document).flatMap
                        (document -> document.documentElement().flatMap
                        (documentElement -> documentElement.findElementByClassName(PageElement))).ifPresent(element -> {
                    LoadingBar.setStringPainted(false);
                    browser.navigation().loadUrl(BManager.GetElementContent(element.innerHtml()));
                    view.setVisible(true);
                    BackButton.setVisible(true);
                });
                NavPanel.updateUI();
            });

        });

        // Back Button event handler
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

/**
 * A set of custom functionalities for JxBrowser's browser component
 */
class BrowserManager {

    /**
     * Converts the HTML code of the specified element to Data URI format.
     */
    public String GetElementContent(String HTMLCode) {
        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();

        String base64Html = Base64.getEncoder().encodeToString(HTMLCode.getBytes(UTF_8));
        String dataUrl = "data:text/html;charset=utf-8;base64," + base64Html;
        browser.navigation().loadUrl(dataUrl);

        return dataUrl;

    }

    /**
     * Gets the progress value of the page's loading time in percentage.
     */
    public void getLoadProgressValue(Browser browser, JProgressBar progressBar) {
        browser.navigation().on(LoadProgressChanged.class, event -> {
            progressBar.setValue((int) (event.getProgress() * 100));
        });
    }


}
