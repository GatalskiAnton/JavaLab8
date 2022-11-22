import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddPanel extends JFrame {

    private GUI gui;
    private JTextField recordBookNumberField;
    private JTextField surnameField;
    private JTextField courseField;
    private JTextField groupField;
    private JButton addButton;


    public AddPanel(String title, GUI gui) throws HeadlessException {
        super(title);
        this.gui = gui;

        createButton();
        addCentralPanel();
    }

    private void createButton() {
        addButton = new JButton("Add");
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Student newStudent;
                try {
                    newStudent = new Student(Integer.parseInt(recordBookNumberField.getText()), surnameField.getText(), Integer.parseInt(courseField.getText()), Integer.parseInt(groupField.getText()));
                } catch (NumberFormatException err) {
                    return;
                }

                ArrayList<Student> newStudents = gui.getStudents();
                newStudents.add(newStudent);
                gui.setStudents(newStudents);
                AddPanel.this.dispose();
            }
        };
        addButton.addMouseListener(adapter);

    }

    private void addCentralPanel() {
        recordBookNumberField = new JTextField();
        surnameField = new JTextField();
        courseField = new JTextField();
        groupField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Surname: "));
        panel.add(surnameField);
        panel.add(new JLabel("Record book number: "));
        panel.add(recordBookNumberField);
        panel.add(new JLabel("Course"));
        panel.add(courseField);
        panel.add(new JLabel("Group"));
        panel.add(groupField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
