package homework;
public class goods {
	
	static String Engine =new String("E");
	static String Truck =new String("T");
	static String Guard =new String("G");
	static String result1 =new String("OK");
	static String result2 =new String("not OK");
	static String legalGoodsTrains (String [] e) {
		if (e[0].equals(Engine)){//check first string is "E"
			if (e[1].equals(Engine)){//check second string is "E"
				for(int i = 2; i<=e.length-2; i++){//check third to e.length-2 string is "T"
					if(e[i].equals(Truck)){
						if(e[e.length-1].equals(Guard))//check last string is "G"
							if(e[e.length-2].equals(Guard))//check last-1 string is "G"
								return result2;
							else 
								return result1;
						else
							return result2;
					}
					 else
						 return result2;
				}
			}
			else if(e[1].equals(Truck)){//check second string is "T"
				for(int i = 1; i<=e.length-2; i++){//check second to e.length-2 string is "T"
					if(e[i].equals(Truck)){
						if(e[e.length-1].equals(Guard))//check last string is "G"
							if(e[e.length-2].equals(Guard))//check last-1 string is "G"
								return result2;
							else 
								return result1;
						else
							return result2;
					}
					
					else
						return result2;
				}

			}
		    else
			    return result2;
		}
	
	    else
		    return result2;
		
		return result2;
		
	}
	public static void print(String [] c){
		System.out.print("{ ");
		for (int i = 0; i< c.length;i++){
			System.out.print(c[i]);
		    System.out.print(" ");
		}
		System.out.println("}");
		
	}
	
	public static void main(String [] args){
		String [] case1 ={"T","T","G","G"};
		System.out.print ("Case1: ");
		print(case1);
		System.out.print ("Result of case1: ");
		System.out.println(legalGoodsTrains(case1));
		
		String [] case2 ={"E","T","T","G"};
		System.out.print ("Case2: ");
		print(case2);
		System.out.print ("Result of case2: ");
		System.out.println(legalGoodsTrains(case2));
		
		String [] case3 ={"E","E","T","T","G"};
		System.out.print ("Case3: ");
		print(case3);
		System.out.print ("Result of case3: ");
		System.out.println(legalGoodsTrains(case3));
		
		String [] case4 ={"E","E","T","G"};
		System.out.print ("Case4: ");
		print(case4);
		System.out.print ("Result of case4: ");
		System.out.println(legalGoodsTrains(case4));
		
		String [] case5 ={"E","T","G"};
		System.out.print ("Case5: ");
		print(case5);
		System.out.print ("Result of case5: ");
		System.out.println(legalGoodsTrains(case5));
		
		String [] case6 ={"T","T"};
		System.out.print ("Case6: ");
		print(case6);
		System.out.print ("Result of case6: ");
		System.out.println(legalGoodsTrains(case6));
		
		String [] case7 ={"E","T","T","G","G"};
		System.out.print ("Case7: ");
		print(case7);
		System.out.print ("Result of case7: ");
		System.out.println(legalGoodsTrains(case7));
	}
}
