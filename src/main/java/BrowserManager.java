import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.internal.rpc.LoadProgressChanged;

import javax.swing.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * A set of custom functionalities for JxBrowser's browser component
 */
public class BrowserManager {

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
