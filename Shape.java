
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D;


public class Shape {

    private static double[] getShapeDimensions(JFrame frame, String shapeName) {
        double[] dimensions = new double[2];
        boolean validInput = false;


        while (!validInput) {

            String input = JOptionPane.showInputDialog(
                    frame, "Enter dimensions for the " + shapeName + " (must be in the range of 0-100):");


            if (input == null) {
                System.exit(0); // Exit the program
            }

            try {

                String[] parts = input.split(",");
                if (shapeName.equals("Circle") && parts.length == 1) {
                    double radius = Double.parseDouble(parts[0]);

                    if (radius >= 0 && radius <= 100) {
                        dimensions[0] = radius;
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Radius must be between 0 and 100.");
                    }
                } else if ((shapeName.equals("Rectangle") || shapeName.equals("Triangle")) && parts.length == 2) {
                    double width = Double.parseDouble(parts[0]);
                    double height = Double.parseDouble(parts[1]);

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

        /
        return dimensions;
    }


    private static double calculateCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }


    private static double calculateCirclePerimeter(double radius) {
        return 2 * Math.PI * radius;
    }


    private static double calculateRectangleArea(double width, double height) {
        return width * height;
    }


    private static double calculateRectanglePerimeter(double width, double height) {
        return 2 * (width + height);
    }


    private static double calculateTriangleArea(double base, double height) {
        return 0.5 * base * height;
    }


    private static double calculateTrianglePerimeter(double base, double height) {
        /
        return base + 2 * Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
    }


    private static Color getColor(JFrame frame) {

        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};

        Color selectedColor = (Color) JOptionPane.showInputDialog(
                frame, "Choose a color for the shape:", "Color Selection", JOptionPane.PLAIN_MESSAGE,
                null, colors, colors[0]
        );


        if (selectedColor == null) {
            return Color.BLACK;
        }


        return selectedColor;
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Shape Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        frame.setSize(400, 400); // Set window size


        String[] shapes = {"Circle", "Rectangle", "Triangle"};

        String selectedShape = (String) JOptionPane.showInputDialog(
                frame, "Choose a shape to draw:", "Shape Selection", JOptionPane.PLAIN_MESSAGE,
                null, shapes, shapes[0]
        );


        if (selectedShape != null) {

            double[] dimensions = getShapeDimensions(frame, selectedShape);


            if (dimensions != null) {

                Color shapeColor = getColor(frame);


                JPanel shapePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;

                        g2.setColor(shapeColor);


                        if (selectedShape.equals("Circle")) {
                            double radius = dimensions[0];
                            Ellipse2D.Double circle = new Ellipse2D.Double(100, 100, 2 * radius, 2 * radius);
                            g2.fill(circle);


                            double area = calculateCircleArea(radius);
                            double perimeter = calculateCirclePerimeter(radius);
                            JOptionPane.showMessageDialog(frame, "Circle Area: " + area + "\nCircle Perimeter: " + perimeter);
                        } else if (selectedShape.equals("Rectangle")) {
                            double width = dimensions[0];
                            double height = dimensions[1];
                            Rectangle2D.Double rectangle = new Rectangle2D.Double(100, 100, width, height);
                            g2.fill(rectangle);


                            double area = calculateRectangleArea(width, height);
                            double perimeter = calculateRectanglePerimeter(width, height);
                            JOptionPane.showMessageDialog(frame, "Rectangle Area: " + area + "\nRectangle Perimeter: " + perimeter);
                        } else if (selectedShape.equals("Triangle")) {
                            double base = dimensions[0];
                            double triangleHeight = dimensions[1];
                            Path2D.Double triangle = new Path2D.Double();
                            triangle.moveTo(100, 100);
                            triangle.lineTo(100 + base, 100);
                            triangle.lineTo(100 + base / 2, 100 + triangleHeight);
                            triangle.closePath();
                            g2.fill(triangle);


                            double area = calculateTriangleArea(base, triangleHeight);
                            double perimeter = calculateTrianglePerimeter(base, triangleHeight);
                            JOptionPane.showMessageDialog(frame, "Triangle Area: " + area + "\nTriangle Perimeter: " + perimeter);
                        }
                    }
                };

                
                frame.add(shapePanel);
                frame.setVisible(true);
            }
        }
    }
}
