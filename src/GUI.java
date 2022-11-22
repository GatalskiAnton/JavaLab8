import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class GUI extends JFrame {

    private AddPanel panel;
    private ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    private JTextArea textArea;
    private JButton openButton;
    private JButton sortButton;
    private JButton addButton;

    public GUI(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createButton();
        createCentralPanel();
        addMouseEvent();
    }

    void createCentralPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea("Info here");

        JPanel centralPanel = new JPanel();
        centralPanel.setBorder(BorderFactory.createTitledBorder("Students"));
        centralPanel.add(textArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(openButton);

        add(centralPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    void createButton() {
        openButton = new JButton("Open");
        sortButton = new JButton("Sort");
        addButton = new JButton("Add");
    }

    void addMouseEvent() {
        MouseAdapter adapter1 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                int value = fileChooser.showDialog(null, "Select");
                if (value == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    students = MyReader.readStudentFromFile(selectedFile);

                    updateTextArea();
                }
            }
        };
        openButton.addMouseListener(adapter1);

        MouseAdapter adapter2 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (students == null)
                    return;

                students = MyCollection.sortByRecordBookNumber(students);

                updateTextArea();
            }
        };
        sortButton.addMouseListener(adapter2);

        MouseAdapter adapter3 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel = new AddPanel("Add", GUI.this);
                panel.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        updateTextArea();
                    }
                });
                panel.setSize(250, 250);
                panel.setVisible(true);
                panel.setLocationRelativeTo(null);
            }
        };
        addButton.addMouseListener(adapter3);
    }

    public void updateTextArea() {
        StringBuilder result = new StringBuilder("");
        for (Student student : students)
            result.append(student.toString());

        textArea.setText(result.toString());
    }

}
