// Import necessary libraries for Swing and graphics
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D;

// Define the main class for the ShapeDrawer program
public class factorial {
    // Main method where the program starts execution
    public static void main(String[] args) {
        // Create a JFrame (window) for the application
        JFrame frame = new JFrame("Shape Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        frame.setSize(400, 400); // Set window size

        // Define an array of shape choices
        String[] shapes = {"Circle", "Rectangle", "Triangle"};
        // Show a dialog to select a shape
        String selectedShape = (String) JOptionPane.showInputDialog(
                frame, "Choose a shape to draw:", "Shape Selection", JOptionPane.PLAIN_MESSAGE,
                null, shapes, shapes[0]
        );

        //check if shape was selected
        if (selectedShape != null) {
            // get dimensions for the selected shape
            double[] dimensions = getShapeDimensions(frame, selectedShape);

            // Check if valid dimensions were provided
            if (dimensions != null) {
                // Get the color for the shape
                Color shapeColor = getColor(frame);

                // Create a JPanel for drawing the shape
                JPanel shapePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;

                        g2.setColor(shapeColor);

                        // Draw the selected shape based on user input
                        if (selectedShape.equals("Circle")) {
                            double radius = dimensions[0];
                            Ellipse2D.Double circle = new Ellipse2D.Double(100, 100, 2 * radius, 2 * radius);
                            g2.fill(circle);
                        } else if (selectedShape.equals("Rectangle")) {
                            double width = dimensions[0];
                            double height = dimensions[1];
                            Rectangle2D.Double rectangle = new Rectangle2D.Double(100, 100, width, height);
                            g2.fill(rectangle);
                        } else if (selectedShape.equals("Triangle")) {
                            double base = dimensions[0];
                            double height = dimensions[1];
                            Path2D.Double triangle = new Path2D.Double();
                            triangle.moveTo(100, 100);
                            triangle.lineTo(100 + base, 100);
                            triangle.lineTo(100 + base / 2, 100 + height);
                            triangle.closePath();
                            g2.fill(triangle);
                        }
                    }
                };

                // Add the shapePanel to the frame and make it visible
                frame.add(shapePanel);
                frame.setVisible(true);
            }
        }
    }

    // Method to get dimensions for a selected shape
    private static double[] getShapeDimensions(JFrame frame, String shapeName) {
        double[] dimensions = new double[2];
        boolean validInput = false;

        // Keep asking for input until it's valid
        while (!validInput) {
            // Show an input dialog to enter dimensions
            String input = JOptionPane.showInputDialog(
                    frame, "Enter dimensions for the " + shapeName + " (comma-separated, e.g., 50,30):");

            // Check if the user canceled input
            if (input == null) {
                System.exit(0); // Exit the program
            }

            try {
                // Split the input into parts and parse as double values
                String[] parts = input.split(",");
                if (shapeName.equals("Circle") && parts.length == 1) {
                    double radius = Double.parseDouble(parts[0]);
                    // Check if the radius is within a valid range
                    if (radius >= 0 && radius <= 100) {
                        dimensions[0] = radius;
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Radius must be between 0 and 100.");
                    }
                } else if ((shapeName.equals("Rectangle") || shapeName.equals("Triangle")) && parts.length == 2) {
                    double width = Double.parseDouble(parts[0]);
                    double height = Double.parseDouble(parts[1]);
                    //check if dimensions are within the valid range
                    if (width >= 0 && width <= 100 && height >= 0 && height <= 100) {
                        dimensions[0] = width;
                        dimensions[1] = height;
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Dimensions must be between 0 and 100.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input format.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.");
            }
        }

        // return the valid dimensions
        return dimensions;
    }

    // Method to get the color for the shape
    private static Color getColor(JFrame frame) {
        // Define an array of color choices
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};
        // Show a dialog to select a color
        Color selectedColor = (Color) JOptionPane.showInputDialog(
                frame, "Choose a color for the shape:", "Color Selection", JOptionPane.PLAIN_MESSAGE,
                null, colors, colors[0]
        );

        // Check if the user canceled color selection, and return the default color (black)
        if (selectedColor == null) {
            return Color.BLACK;
        }

        // Return the selected color
        return selectedColor;
    }
}
