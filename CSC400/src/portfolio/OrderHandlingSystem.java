/**
 * File Name: OrderHandlingSystem.java 
 * Date: Aug 23, 2023
 * Author: Richard Fritsche
 * Project Name: portfolio 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package portfolio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author richard
 * 
 */
public class OrderHandlingSystem extends JFrame {

private boolean veritas;
private JPanel contentPane;
private JTextField txtName;
private JTextField textField;
/**
 * Launch the application.
 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				OrderHandlingSystem frame = new OrderHandlingSystem();
				frame.setVisible(true);
			}//end try
			catch (Exception e) {
				e.printStackTrace();
			}//end catch
		}
	});
}//end main method
/**
 * Create the frame.
 */
public OrderHandlingSystem() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	
	JToolBar toolBar = new JToolBar();
	toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
	toolBar.setFloatable(false);
	contentPane.add(toolBar, BorderLayout.NORTH);
	
	JButton btnAddOrder = new JButton("Add Order");
	toolBar.add(btnAddOrder);
	
	JButton btnRemoveOrder = new JButton("Remove Order");
	toolBar.add(btnRemoveOrder);
	
	JButton btnDisplayByName = new JButton("Display by Name");
	toolBar.add(btnDisplayByName);
	
	JButton btnDisplayBy = new JButton("Display by Order Number");
	toolBar.add(btnDisplayBy);
	
	JLayeredPane addPane = new JLayeredPane();
	contentPane.add(addPane, BorderLayout.CENTER);
	addPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
	JLabel lblCustomerLastName = new JLabel("Customer Last Name:");
	addPane.add(lblCustomerLastName);
	
	txtName = new JTextField();
	addPane.add(txtName);
	txtName.setColumns(10);
	
	JLabel lblOrderNumber = new JLabel("Order Number");
	addPane.add(lblOrderNumber);
	
	textField = new JTextField();
	textField.setEditable(false);
	addPane.add(textField);
	textField.setColumns(10);
}//end constructor
}//end class
