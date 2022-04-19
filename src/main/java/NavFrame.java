import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;

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


    public NavFrame(String title, String PageURL) {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 479, 179);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel NavPanel = new JPanel();
        NavPanel.setLayout(new GridLayout(2, 2, 5, 10));

        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();
        browser.navigation().loadUrl(PageURL);


    }
}
