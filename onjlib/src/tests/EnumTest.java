package tests;


enum Color {
	RED,BLACK,GREEN,BLUE;

	/**
	 * get a color enum by its ordinal code;
	 * @param code
	 * @return
	 */
	static Color getByOrdinal(int code)
	{
		Color sc = null;
		Color v[] = Color.values();
		for(Color c:v) {
			if(c.ordinal() == code) {
				sc = c;
				break;
			}
		}
		
		return(sc);
	}
	
	
}


/*
Java 2, v5.0 (Tiger) New Features
by Herbert Schildt
ISBN: 0072258543
Publisher: McGraw-Hill/Osborne, 2004
*/

enum Apple { 
  Jonathan(10), GoldenDel(9), RedDel, Winsap(15), Cortland(8); 
 
  private int price; // price of each apple 
 
  // Constructor 
  Apple(int p) { price = p; } 
 
  // Overloaded constructor 
  Apple() { price = -1; } 
 
  int getPrice() { return price; } 
}



enum PlayerType {
	FreeAgent(1), Draft(2);
	int player;
	PlayerType(int p){ player = p; } 
}



public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Color c = Color.getByOrdinal(0);
		System.out.println(c.toString());
		
		

	}

}
