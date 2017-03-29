import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


public class Calculator1 extends Applet implements ActionListener {
	
	    int i;
	    String operator="=";
	    String opp;
	    // Strings for Digit & Operator buttons.
	    private final String[] str = { "+", "-","*", "/", "=" };
	    // Build buttons.
	    Button[] buttons = new Button[str.length];
	    // For cancel or reset.
	    Button reset = new Button("CE");
	    // Build the text field to show the result.
	    TextField display1 = new TextField("");
	    TextField display2 = new TextField("");
	    //TextField result1 = new TextField("");
	    Label lab = new Label("");
	   

	    public Calculator1() {
	    	
	        // Add a panel.
	        Panel panel1 = new Panel(new GridLayout(1,1,20,5));
	        panel1.setBackground(Color.blue);
	        for (i = 0; i < str.length; i++) {
	            buttons[i] = new Button(str[i]);
	            buttons[i].setBackground(Color.LIGHT_GRAY);
	            panel1.add("Center",buttons[i]);
	        }
	        Panel panel2 = new Panel(new GridLayout(1,1,20,5));
	        panel2.add("West", display1);
	        panel2.add("Center", display2);
	        //panel2.add("East", result1);
	        panel2.add("East",lab);
	        panel2.add("East", reset);
	        reset.setBackground(Color.LIGHT_GRAY);
	        lab.setBackground(Color.WHITE);
	        
	        Panel lowerPanel = new Panel();
			lowerPanel.add("Center",panel1);
			lowerPanel.setLayout(new GridLayout(1,1,10,10));
			lowerPanel.setBackground(Color.blue);
	        
	        this.setLayout(new BorderLayout());
	        this.add("North", panel2);
	        this.add("South", lowerPanel);
	        
	        
	        // Add action listener for each digit & operator button.
	        for (i = 0; i < str.length; i++)
	            buttons[i].addActionListener(this);
	        //Add listener for "reset" button.
	        reset.addActionListener(this);
	        // Add listener for "display" button.
	        display1.addActionListener(this);
	        display2.addActionListener(this);
	        //result1.addActionListener(this);
	        //lab.addActionListener(this);
	        setVisible(true);
	        
	     }  
	    public void init() {
	    	this.setSize(300, 50);
	    	this.setBackground(Color.blue);
	    	
	    }
    public void actionPerformed(ActionEvent e) {
    	
        Object target = e.getSource();
        String label = e.getActionCommand();
        
        try {
        	if (target == reset)
                handleReset();
            else if ("0123456789.".indexOf(label) >= 0){
                handleNumber(label);
            }
            else
                handleOperator(label);
        }
        catch (Exception error) {
        	JOptionPane.showMessageDialog(this,"Illegal expression: " + error); // putting in characters causes error 
        }
     
        
     }
     // Is the first digit pressed?
     boolean isFirstDigit = true;
     double n;
     /**
      * Number handling.
      * @param key the key of the button.
      */
     public void handleNumber(String key) {
        if (isFirstDigit){
        	//n=Double.parseDouble(key);
        	display1.setText(String.valueOf(key));
        	isFirstDigit = false;
        }
        else if ((key.equals(".")) && (display1.getText().indexOf(".") < 0)){

            display1.setText(display1.getText() + ".");
            isFirstDigit = false;
        }
        else if (!key.equals(".")){
        	display1.setText(display1.getText()+key);
        	isFirstDigit = false;
        }
     }
    
     /**
      * Reset the calculator.
      */
     public void handleReset() {
        lab.setText("");
        isFirstDigit = true;
        //String operator = "=";
     }
  

     double number1;
     double number2;
     double result;
     /**
      * Handling the operation.
      * @param key pressed operator's key.
      */
     public void handleOperator(String key) {
    	 operator = key;
         isFirstDigit = true;
         char ch=operator.charAt(0);
 		 if (Character.isDigit(ch))
 			 display1.setText(display1.getText()+str);
 		 else
    	 if (operator.equals("+")){
    		 number1 = Double.parseDouble(display1.getText());
    		 opp="+";
         }
         else if (operator.equals("-")){
             number1 = Double.parseDouble(display1.getText());
             opp="-";

         }
         else if (operator.equals("*")){
             number1 = Double.parseDouble(display1.getText());
             opp="*";

         }
         else if (operator.equals("/")){
             number1 = Double.parseDouble(display1.getText());
             opp="/";

         }
    	 number2 = Double.valueOf(display2.getText());
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
    		
             lab.setText(""+result);
         }
		 
    	 
         
    	
        
     }
     public double stringToDouble(String x){
		 double y= Double.parseDouble(x);
		 return y;
	 }
		 
    
     public static void main(String[] args) {
        new Calculator1();
     }
}


    
    
