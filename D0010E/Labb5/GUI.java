import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    /**
     * Constructor for the GUI class which sets up the calculator interface.
     * It initializes the JFrame, sets the layout, and adds the display and buttons to the interface.
     * The buttons are arranged in a 4x4 grid and include digit buttons (0-9), binary operator buttons (+, -, *, /), an equals button, and a cancel button.
     * The display is a JLabel that shows the current value on the calculator's screen, and it is aligned to the right with a specific font and padding.
     * The Situation object is passed as a parameter to the constructor, which allows the buttons to interact with the current state of the calculator and update the display accordingly.
     * @param situation
     */
    public GUI(Situation situation) {

        setTitle("Calculator"); // Sets the title of the JFrame to "Calculator"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures that the application exits when the window is closed
        // Sets the layout of the JFrame to BorderLayout, which allows for arranging components in five regions: north, south, east, west, and center
        setLayout(new BorderLayout());

        //makes the numbers align to the right and sets the font and padding for the display
        // also positions the display at the top of the JFrame (BorderLayout.NORTH)
        situation.display.setHorizontalAlignment(SwingConstants.RIGHT);
        situation.display.setFont(new Font("Arial", Font.BOLD, 24));
        situation.display.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(situation.display, BorderLayout.NORTH);

        // Creates a JPanel with a GridLayout to hold the calculator buttons. The GridLayout is set to have 4 rows and 4 columns with a gap of 5 pixels between the buttons.
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Adding buttons to the panel in the order they should appear
        panel.add(new DigitButton(7, situation));
        panel.add(new DigitButton(8, situation));
        panel.add(new DigitButton(9, situation));
        panel.add(new BinOpButton('/', situation));

        panel.add(new DigitButton(4, situation));
        panel.add(new DigitButton(5, situation));
        panel.add(new DigitButton(6, situation));
        panel.add(new BinOpButton('*', situation));

        panel.add(new DigitButton(1, situation));
        panel.add(new DigitButton(2, situation));
        panel.add(new DigitButton(3, situation));
        panel.add(new BinOpButton('-', situation));

        panel.add(new DigitButton(0, situation));
        panel.add(new EqualsButton(situation));
        panel.add(new CancelButton(situation));
        panel.add(new BinOpButton('+', situation));


        // Adds the panel containing the buttons to the center of the JFrame (BorderLayout.CENTER)
        add(panel, BorderLayout.CENTER);

        // Sets the size of the JFrame to 300 pixels wide and 400 pixels tall, centers the window on the screen, and makes it visible
        setSize(300, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}