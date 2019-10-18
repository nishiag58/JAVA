import java.util.*;
import java.util.ArrayList;

class Cards{
	
	public static void main(String args[]){
		
		String[] SUIT ={"spade","diamond","heart","club"};
		String[] RANK={ "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		String color;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the number of players");
		//Input the number of players 
		int no_of_players=sc.nextInt();

		int hidden=0;
		int next_card=0;
		int cards=52/no_of_players;

		//unused card check
		if(52%no_of_players==0)
			hidden=0;
		else
			hidden=52%no_of_players;
		
		//create deck 
		int n = SUIT.length * RANK.length;
        String[] deck = new String[n];
        for (int i = 0; i < RANK.length; i++) {
           for (int j = 0; j < SUIT.length; j++) {
			   if(SUIT[j].equals("diamond")||SUIT[j].equals("heart"))
				   color="Red";
			   else
				   color="Black";
			   deck[next_card++] = RANK[i] + " : " + SUIT[j] + " : " +color;
            
		   } 
         }
	
        //random shuffle of deck cards
		for (int i = 0; i < n; i++){
          int r = i + (int) (Math.random()*(n-i));
          String temp = deck[r];
          deck[r] = deck[i];
          deck[i] = temp;  
         } 
		 
		 //division of cards among players
		 List<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		 next_card=0;	
		 for(int i=0;i<no_of_players;i++)
		  {			   
			List<String> player=new ArrayList<String>();
			 for(int x=0;x<cards;x++)
			  {
				 player.add(deck[next_card++]+"\n");

			  }	 
			 players.add(new ArrayList(player));
		 }	 
		 
		 //display
		 for(int i=0;i<no_of_players;i++)
		 {
			 System.out.println("\nplayer "+ (i+1) +" has:\n");
		 	 System.out.println(players.get(i));
		 }
        
        //condition for unused cards		
		if(hidden!=0)
		  {	System.out.println("Unused Cards are:\n");
			for(int i=0;i<hidden;i++)
			{
				System.out.println(deck[next_card++]);
			}
	      }	
    }
}