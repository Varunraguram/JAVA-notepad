package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class button_notepad  {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotepadFrame();
        });
    }

    static class NotepadFrame extends JFrame {
        private JTextArea textArea;

        public NotepadFrame() {
            super("Varun Notepad");
            textArea = new JTextArea(25, 80);

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");

            JMenuItem openMenuItem = new JMenuItem("Open");
            openMenuItem.setMnemonic(KeyEvent.VK_O);
            openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

            JMenuItem saveMenuItem = new JMenuItem("Save");
            saveMenuItem.setMnemonic(KeyEvent.VK_S);
            saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

            JMenuItem newMenuItem = new JMenuItem("New File");
            newMenuItem.setMnemonic(KeyEvent.VK_N);
            newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

            JMenuItem saveAsMenuItem = new JMenuItem("Save As");
            saveAsMenuItem.setMnemonic(KeyEvent.VK_A);
            saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

            JMenuItem exitMenuItem = new JMenuItem("Exit");
            exitMenuItem.setMnemonic(KeyEvent.VK_E);
            exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

            openMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openFile(textArea, NotepadFrame.this);
                }
            });

            saveMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFile(textArea);
                }
            });

            newMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new NotepadFrame(); // Create a new Notepad instance
                }
            });

            saveAsMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFileAs(textArea, NotepadFrame.this);
                }
            });

            exitMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            fileMenu.add(openMenuItem);
            fileMenu.add(saveMenuItem);
            fileMenu.add(newMenuItem);
            fileMenu.add(saveAsMenuItem);
            fileMenu.addSeparator();
            fileMenu.add(exitMenuItem);

            menuBar.add(fileMenu);
            setJMenuBar(menuBar);

            JPanel buttonPanel = new JPanel();
            JButton openButton = new JButton("Open");
            JButton saveButton = new JButton("Save");
            JButton newButton = new JButton("New File");
            JButton saveAsButton = new JButton("Save As");

            openButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openFile(textArea, NotepadFrame.this);
                }
            });

            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFile(textArea);
                }
            });

            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new NotepadFrame(); // Create a new Notepad instance
                }
            });

            saveAsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFileAs(textArea, NotepadFrame.this);
                }
            });

            buttonPanel.add(openButton);
            buttonPanel.add(saveButton);
            buttonPanel.add(newButton);
            buttonPanel.add(saveAsButton);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }

    private static void openFile(JTextArea textArea, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                String line;
                textArea.setText(""); // Clear existing text
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void saveFile(JTextArea textArea) {
        // Implement your save logic here
        // For simplicity, I'm not repeating the entire save code here
        // You can use the saveFileAs method to specify a file name
    }

    private static void saveFileAs(JTextArea textArea, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                FileWriter fw = new FileWriter(selectedFile);
                fw.write(textArea.getText());
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
