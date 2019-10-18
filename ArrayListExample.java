import java.util.ArrayList;
import java.util.List;

class ArrayListExample{
	
	public static void main(String args[]){
		
		List<String> players=new ArrayList<>();
		
		players.add("player1");
		players.add("player2");
		players.add("player3");
		players.add("player4");
		
		System.out.println("ArrayList works!");
		System.out.println(players);
		
		players.add(2,"player5");
		System.out.println(players);
	}
}