// This Program is create by Rupesh Verma 
// Github Profile - RupeshVerma28
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class IDCardGenerator extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JTextField deptField;
    private JTextField emailField;
    private JButton generateButton;
    private JButton uploadButton;
    private JPanel panel;
    private File imageFile;

    public IDCardGenerator() {
        setTitle("ID Card Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Department:"));
        deptField = new JTextField();
        panel.add(deptField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        uploadButton = new JButton("Upload Photo");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImageFile();
            }
        });

        panel.add(uploadButton);
        panel.add(new JLabel()); 

        generateButton = new JButton("Generate ID Card");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateIDCard();
            }
        });

        panel.add(generateButton);

        add(panel);
    }

    private void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            imageFile = fileChooser.getSelectedFile();
        }
    }

    private void generateIDCard() {
        String name = nameField.getText();
        String id = idField.getText();
        String department = deptField.getText();
        String email = emailField.getText();

        // Create an ID card image
        BufferedImage idCard = new BufferedImage(350, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = idCard.createGraphics();

        // background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 350, 200);

        //  border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 349, 199);

        // Add user details
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("ID CARD", 120, 30);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Name: " + name, 150, 70);
        g.drawString("ID: " + id, 150, 100);
        g.drawString("Dept: " + department, 150, 130);
        g.drawString("Email: " + email, 150, 160);

       // Allow user to upload their passport size picture
        if (imageFile != null) {
            try {
                BufferedImage userImage = ImageIO.read(imageFile);
                // Resize the image to fit on the ID card
                BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(userImage, 0, 0, 100, 100, null);
                g2d.dispose();
                g.drawImage(resizedImage, 20, 70, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        g.dispose();

        // Save the image file in PNG format in the program folder
        try {
            ImageIO.write(idCard, "png", new File("ID_Card.png"));
            JOptionPane.showMessageDialog(this, "ID Card generated successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating ID Card.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IDCardGenerator().setVisible(true);
            }
        });
    }
}

