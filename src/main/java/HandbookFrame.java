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

public class HandbookFrame extends FrameCustomizer {
    private JButton CategoryBtn;
    private JButton BackBtn = new JButton();

    private JPanel HB_Panel = new JPanel();
    private JPanel Blank_Panel = new JPanel();

    public HandbookFrame() {

        this.setTitle("Online Student Handbook");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HB_Panel.setLayout(new GridLayout(0, 3));

        BackBtn.setText("<<");

        File directoryPath = new File(System.getProperty("user.dir") + "/src/main/java/articles");
        String contents[] = directoryPath.list();
        for(int i=0; i<contents.length; i++) {
            CategoryBtn = new JButton(contents[i]);
            CategoryBtn.setText(contents[i]);
            CategoryBtn.setActionCommand(contents[i]);

            CategoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(((JButton) e.getSource()).getText());
                    try {
                        Handbook_SubFrame HBSF = new Handbook_SubFrame(((JButton) e.getSource()).getText());

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
                ChangeInterface(BaseApp.frame);
                HandbookFrame.this.setVisible(false);
                HandbookFrame.this.dispose();
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
    private JFrame SubFrame;
    private JPanel SubFrame_Panel;
    private JList<String> ArticleList;
    private DefaultListModel ALmodel = new DefaultListModel();
    public Handbook_SubFrame(String source) throws IOException {

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
        //List of all files and directories

        BufferedReader br = new BufferedReader(new FileReader(ArticleFile));
        String line = null;
        String ArticleURLs[] = {};
        ArrayList<String> ArticleArrayList = new ArrayList<String>(Arrays.asList(ArticleURLs));
        while ((line = br.readLine()) != null) {

            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            parser.parse(new InputStreamReader(new URL(line).openStream()),
                    htmlDoc.getReader(0), true);

            ALmodel.addElement(htmlDoc.getProperty("title"));
            ArticleArrayList.add(line);
            ArticleURLs = ArticleArrayList.toArray(ArticleURLs);
        }

        SubFrame.setVisible(true);

        ArticleList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    int index = ArticleList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object o = ArticleList.getModel().getElementAt(index);
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
    }
}

