import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class HandbookFrame extends FrameCustomizer {
    private JButton CategoryBtn;
    private final JButton BackBtn;

    private final JPanel HB_Panel;
    private final JPanel Blank_Panel;

    public HandbookFrame() {

        this.setTitle("Online Student Handbook");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackBtn = new JButton();
        HB_Panel = new JPanel();
        Blank_Panel = new JPanel();

        HB_Panel.setLayout(new GridLayout(0, 3));

        BackBtn.setText("<<");

        File directoryPath = new File(System.getProperty("user.dir") + "/src/main/java/articles");
        String[] contents = directoryPath.list();
        for(int i = 0; i< Objects.requireNonNull(contents).length; i++) {
            CategoryBtn = new JButton(contents[i]);
            CategoryBtn.setText(contents[i]);
            CategoryBtn.setActionCommand(contents[i]);

            CategoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(((JButton) e.getSource()).getText());
                    try {
                        new Handbook_SubFrame(((JButton) e.getSource()).getText());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });

            HB_Panel.add(CategoryBtn);

        }

        BackBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HandbookFrame.this.setVisible(false);
                HandbookFrame.this.dispose();
                new BaseApp();
            }
        });


        HB_Panel.add(Blank_Panel);
        HB_Panel.add(BackBtn);


        this.setContentPane(HB_Panel);


    }

    @Override
    public void ChangeInterface(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
/* Class */
class Handbook_SubFrame extends FrameCustomizer {
    private final JFrame SubFrame;
    private final JPanel SubFrame_Panel;
    private final JList<String> ArticleList;
    private final DefaultListModel ALmodel = new DefaultListModel();
    private String[] ArticleURLs = {};
    private ArrayList<String> ArticleArrayList = new ArrayList<>(Arrays.asList(ArticleURLs));
    public Handbook_SubFrame(String source) throws IOException {

        // Initialize frame contents
        SubFrame = new JFrame();
        SubFrame.setSize(800,600);
        SubFrame.setLocationRelativeTo(null);
        SubFrame.setResizable(false);

        SubFrame_Panel = new JPanel();
        SubFrame_Panel.setLayout(new BorderLayout());
        ArticleList = new JList<String>(ALmodel);
        SubFrame_Panel.add(ArticleList, BorderLayout.CENTER);

        SubFrame.setContentPane(SubFrame_Panel);

        File ArticleFile = new File(System.getProperty("user.dir") + "/src/main/java/articles/" + source + "/articles.txt");

        // Read URL contents of article text file
        BufferedReader br = new BufferedReader(new FileReader(ArticleFile));
        String line;

        // Variables for storing URL/Webpage Titles


        // Iterate through all lines of the article text file
        while ((line = br.readLine()) != null) {

            // Opens instance of HTMLEditorKit for parsing HTML code
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            parser.parse(new InputStreamReader(new URL(line).openStream()),
                    htmlDoc.getReader(0), true);

            // Grabs webpage title and adds to JList component
            ALmodel.addElement(htmlDoc.getProperty("title"));

            // Add URLs to Articles' ArrayList
            ArticleArrayList.add(line);
            ArticleURLs = ArticleArrayList.toArray(ArticleURLs);
        }

        SubFrame.setVisible(true);



        initializeListeners();

    }

    /**
     Initializes all component event listeners.
     */
    public void initializeListeners() {
        // Listener for double-click mouse event
        ArticleList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    // Stores selected item's index near mouse cursor position
                    int index = ArticleList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object o = ArticleList.getModel().getElementAt(index);

                        // Initialize NavFrame window and loads selected article
                        NavFrame NF = new NavFrame(o.toString(), ArticleArrayList.get(index), "wiki-content");
                        ChangeInterface(NF);

                    }
                }
            }
        });
    }
    @Override
    public void ChangeInterface(JFrame frame) {
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

