package view;

/**Class which implements the view of the application.*/

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PolynomialView {
	private JFrame mainFrame;
	private JLabel pLabel;
	private JLabel qLabel;
	private JTextField pText;
	private JTextField qText;
	private JButton addButton;
	private JButton subButton;
	private JButton mulButton;
	private JButton divButton;
	private JButton derButton;
	private JButton intButton;
	private JLabel resultLabel;
	private JTextField resultText;
	private JPanel pPanel;
	private JPanel qPanel;
	private JPanel buttonsPanel;
	private JPanel resultPanel;
	

    public PolynomialView() {

        prepareGUI();
    }
    
    /**Contains three panels.
     * 1.User inputs.
     * 2.Buttons.
     * 3.Result.*/
    private void prepareGUI() {
    	mainFrame = new JFrame("Operations on Polynomials");
    	mainFrame.setSize(500, 300);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setLayout(new GridLayout(4, 1));
    	
    	pLabel = new JLabel("P:");
    	pText = new JTextField(30);
    	pPanel = new JPanel();
    	pPanel.add(pLabel);
    	pPanel.add(pText);
    	pPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    	pPanel.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
    	
    	qLabel = new JLabel("Q:");
    	qText = new JTextField(30);
    	qPanel = new JPanel();
    	qPanel.add(qLabel);
    	qPanel.add(qText);
    	
    	addButton = new JButton("+");
    	subButton = new JButton("-");
    	mulButton = new JButton("*");
    	divButton = new JButton("/");
    	derButton = new JButton("Derivate");
    	intButton = new JButton("Integrate");
    	
    	buttonsPanel = new JPanel();
    	buttonsPanel.add(addButton);
    	buttonsPanel.add(subButton);
    	buttonsPanel.add(mulButton);
    	buttonsPanel.add(divButton);
    	buttonsPanel.add(derButton);
    	buttonsPanel.add(intButton);
    	
    	resultLabel = new JLabel("Result:");
    	resultText = new JTextField(30);
    	resultPanel = new JPanel();
    	resultPanel.add(resultLabel);
    	resultPanel.add(resultText);
    	
    	mainFrame.getContentPane().add(pPanel);
    	mainFrame.add(qPanel);
    	mainFrame.add(buttonsPanel);
    	mainFrame.add(resultPanel);
    	mainFrame.setVisible(true);
    	
    }
    
    public void addListeners(ActionListener add, ActionListener sub, ActionListener mul, ActionListener div, ActionListener der, ActionListener integrate) {
    	addButton.addActionListener(add);
    	subButton.addActionListener(sub);
    	mulButton.addActionListener(mul);
    	divButton.addActionListener(div);
    	derButton.addActionListener(der);
    	intButton.addActionListener(integrate);
    }
    
    public String getInputOne() {
		return this.pText.getText();
	}
    
    public String getInputTwo() {
    	return this.qText.getText();
    }
    
    public void setResult(String output) {
    	this.resultText.setText(output);
    }
    
}
