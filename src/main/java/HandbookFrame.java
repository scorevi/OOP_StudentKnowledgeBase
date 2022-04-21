import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
public class HandbookFrame extends JFrame {
    private JButton CategoryBtn;
    private JButton BackBtn = new JButton();

    private JPanel HB_Panel = new JPanel();
    private JPanel Blank_Panel = new JPanel();

    private JLabel FunctionTitle = new JLabel("Select a topic to explore or use the search bar to find a specific information.");
    public HandbookFrame() {

        this.setTitle("Online Student Handbook");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HB_Panel.setLayout(new GridLayout(0, 3));

        BackBtn.setText("<<");

        File directoryPath = new File(System.getProperty("user.dir") + "/src/main/java/articles");
        //List of all files and directories
        String contents[] = directoryPath.list();
        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
            System.out.println(contents[i]);
            CategoryBtn = new JButton(contents[i]);
            CategoryBtn.setText(contents[i]);
            HB_Panel.add(CategoryBtn);

        }



        HB_Panel.add(Blank_Panel);
        HB_Panel.add(BackBtn);


        this.setContentPane(HB_Panel);

        CategoryBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }


}

