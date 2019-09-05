package solution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator2 implements ActionListener
{
    
    private JFrame myframe;
    private JTextField infixExpression;
    private JLabel resultLabel;

    /**
     * Getting the myframe.
     * @return JFrame
     */
    public JFrame getFrame()
    {
        return myframe;
    }
    /**
     * Constructor.
     * 
     */
    public Calculator2()
    {
        myframe = new JFrame("Calculator");
        myframe.setVisible(true);
        myframe.setSize(500, 500);
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //creat button panel
        JPanel buttonPanel = new JPanel();
        
        
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setSize(50,100);
        calculateButton.setName("calculateButton");
        calculateButton.addActionListener(this);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setSize(50,100);
        clearButton.setName("clearButton");
        clearButton.addActionListener(this);
        
        //add each button to the button Panel
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        //Add the button panel to the frame
        myframe.add(buttonPanel, BorderLayout.PAGE_END);
        
        //create label panel
        JPanel resultPanel = new JPanel();
        
        //create label
        resultLabel = new JLabel("Result = ");
        resultLabel.setName("resultLabel");
        
        //add label to result label
        resultPanel.add(resultLabel);
        
        //add result panel to frame
        myframe.add(resultPanel, BorderLayout.CENTER);
        
        //create text field panel
        JPanel operandPanel = new JPanel();
        
        //Create one text fields
        infixExpression = new JTextField(15);
        infixExpression.setName("infixExpression");
        
        //add text fields to panel
        operandPanel.add(infixExpression);
        
        //add the text field to the frame
        myframe.add(operandPanel, BorderLayout.PAGE_START);
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
         
        JButton button = (JButton)e.getSource();
        String something = button.getText();
        
        String expression = infixExpression.getText(); 
        
        
        if (something == "Calculate")
        {
            ExpressionEvaluator yay = new ExpressionEvaluator();
            Double check = yay.evaluate(yay.toPostfix(expression));
            if (Double.isNaN(check))
            {
                resultLabel.setText("Error");
            }
            else 
            {
                resultLabel.setText("" + check);
            }
      
        }
        else if(something == "Clear")
        {
            infixExpression.setText("");
        }
        }
        catch(IllegalArgumentException k)
        {
            resultLabel.setText("Error");
        }
      

    }
    public static void main (String [] args)
    {
        Calculator2 calc = new Calculator2();
        
    }
    
}


