import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/*
 * Written by Honglan Liu, NetID: sp6682.
 */
public class Calculator2 extends Applet implements ActionListener {
	
	    int i;
	    String operator="=";
	    // Strings for Digit & Operator buttons.
	    private final String[] str = { "7", "8", "9", "/", "4", "5", "6", "*","1",
	           "2", "3", "-", ".", "0", "=", "+" };
	    // Build buttons.
	    Button[] buttons = new Button[str.length];
	    // For cancel or reset.
	    Button reset = new Button("CE");
	    // Build the text field to show the result.
	    TextField display = new TextField("0.0");

	   

	    public Calculator2() {
	    	
	        // Add a panel.
	        Panel panel1 = new Panel(new GridLayout(4, 4));
	        for (i = 0; i < str.length; i++) {
	            buttons[i] = new Button(str[i]);
	            buttons[i].setBackground(Color.LIGHT_GRAY);
	            panel1.add(buttons[i]);
	        }
	        Panel panel2 = new Panel(new BorderLayout());
	        panel2.add("Center", display);
	        panel2.add("East", reset);
	        reset.setBackground(Color.LIGHT_GRAY);
	        this.setLayout(new BorderLayout());
	        this.add("North", panel2);
	        this.add("Center", panel1);
	        
	        // Add action listener for each digit & operator button.
	        for (i = 0; i < str.length; i++)
	            buttons[i].addActionListener(this);
	        // Add listener for "reset" button.
	        reset.addActionListener(this);
	        // Add listener for "display" button.
	        display.addActionListener(this);
	        setVisible(true);
	        
	     }  
	    public void init() {
	    	//this.setSize(300, 300);
	    	this.setBackground(Color.blue);
	    	
	    }
    public void actionPerformed(ActionEvent e) {
    	
        Object target = e.getSource();
        String label = e.getActionCommand();
        
        try {
        	if (target == reset)
                handleReset();
            else if ("0123456789.".indexOf(label) >= 0)
                handleNumber(label);
            else{
                handleOperator(label);
            }
        }
        catch (Exception error) {
        	JOptionPane.showMessageDialog(this,"Illegal expression: " + error); // putting in characters causes error 
        }
     
        
     }
     // Is the first digit pressed?
     boolean isFirstDigit = true;
     //double n;
     /**
      * Number handling.
      * @param key the key of the button.
      */
     public void handleNumber(String key) {
        if (isFirstDigit){
        	//n=Double.parseDouble(key);
        	display.setText(String.valueOf(key));
        }
        else if ((key.equals(".")) && (display.getText().indexOf(".") < 0)){

            display.setText(display.getText() + ".");
        }
        else if (!key.equals("."))
        	display.setText(display.getText()+key);
        	
        isFirstDigit = false;
     }
    
     /**
      * Reset the calculator.
      */
     public void handleReset() {
        display.setText("0.0");
        isFirstDigit = true;
        String operator = "=";
     }
  

     double number1;
     double number2;
     double result;
     String opp;
     /**
      * Handling the operation.
      * @param key pressed operator's key.
      */
     public void handleOperator(String key) {
    	 operator = key;
		 isFirstDigit = true;
    	 if (operator.equals("+")){
    		 number1 = Double.parseDouble(display.getText());
    		 opp="+";
         }
         else if (operator.equals("-")){
             number1 = Double.parseDouble(display.getText());
             opp="-";

         }
         else if (operator.equals("*")){
             number1 = Double.parseDouble(display.getText());
             opp="*";

         }
         else if (operator.equals("/")){
             number1 = Double.parseDouble(display.getText());
             opp="/";

         }
    	 number2 = Double.valueOf(display.getText());
    	 if (operator.equals("=")){
    		 if(opp=="+")
    			 result=number1+number2;
    		 else if(opp=="-")
    			 result=number1-number2;
    		 else if(opp=="*")
    			 result=number1*number2;
    		 else if(opp=="/"){
    			 if(number2!=0){
    				 result=number1/number2;
    			 }
    			 else
    				 result=stringToDouble("The devisor can't be zero!");
    			
    		 }
    		
             display.setText(""+result);
             isFirstDigit = false;
         }
		          
    	
        
     }
     public double stringToDouble(String x){
		 double y= Double.parseDouble(x);
		 return y;
	 }
         
        
    
     public static void main(String[] args) {
        new Calculator2();
     }
}
