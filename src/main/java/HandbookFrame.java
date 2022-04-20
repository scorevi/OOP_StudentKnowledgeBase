import com.sun.javafx.css.Rule;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.awt.*;

public class HandbookFrame extends JFrame {
    private JButton EnrollmentBtn = new JButton();
    private JButton GradesBtn = new JButton();
    private JButton RulesBtn = new JButton();
    private JButton OrganizationsBtn = new JButton();
    private JButton CoursesBtn = new JButton();
    private JButton PaymentsBtn = new JButton();

    public HandbookFrame() {

        this.setTitle("Online Student Handbook");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel HB_Panel = new JPanel();
        JPanel Blank_Panel = new JPanel();

        HB_Panel.setLayout(new GridLayout(0, 3));


        EnrollmentBtn.setText("ENROLLMENT");

        HB_Panel.add(EnrollmentBtn);
        HB_Panel.add(GradesBtn);
        HB_Panel.add(RulesBtn);
        HB_Panel.add(OrganizationsBtn);
        HB_Panel.add(CoursesBtn);
        HB_Panel.add(PaymentsBtn);


        this.setContentPane(HB_Panel);
    }
}
