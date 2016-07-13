package test;

public class MyPerm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "abc";
		String prefix = "";
		//permute("","abc");
		for(int i=0; i < s.length(); ++i){
			prefix = "" + s.charAt(i);
			s = s.substring(0,i) + s.substring(i+1);
			System.out.println("prefix = " + prefix + " s = "+ s);
			permute(prefix,s);
		}
	}

	public static void permute(String prefix,String s){
		System.out.println("permute prefix = " + prefix + " s = "+ s);

		if(s.length()== 1) {
			System.out.println(prefix + s);
		} else {
			for(int i=0; i < s.length(); ++i) {
				prefix = prefix + s.charAt(i);
				s = s.substring(0,i) + s.substring(i+1,s.length());
				permute(prefix,s);
			}
		}
		
		
	}
	
}
