import java.util.*;
public class StableSort{
		public static void main(String[] args){
			// get input to make a list of woman and their rating
			// if duplicates are found print an error message and keep looping
			Set<String> names = new HashSet<String>();
			Scanner conIn = new Scanner(System.in);
			ArrayList<Man> men = new ArrayList<Man>();
			ArrayList<Man> women = new ArrayList<Man>();
			System.out.println("Name your man. Press Enter. Name your woman");
			int i = 0;
			do {
				String manName = conIn.nextLine();
				if ( manName.equals("-1") && i != 0 )
					break;
				if ( !names.add( manName ) ){
					System.out.println("You have entered the same name. Try again");
					continue;
				}
				men.add(new Man(manName));
				String womanName = conIn.nextLine();
			        if ( !names.add( womanName ) ){
					System.out.println("You have entered the same name. Try again");
					while(true){
						womanName = conIn.nextLine();
						if ( names.add( womanName ) )
							break;
					System.out.println("You have entered the same name. Try again");
					}
				}		
				women.add(new Man(womanName));
				System.out.println("Type -1 and Enter to Quit. Else, name your man. Press Enter. Name your woman");
				i++;
			} while(conIn.hasNextLine());
			for ( Man e : men )
				System.out.println(e.getName());
			System.out.println("**********");
			for ( Man e : women )
				System.out.println(e.getName());
			for ( Man e : men ){
				System.out.print("For " + e.getName());
				for ( Man f : women ){
					System.out.println(" rate " + f.getName());
					int rate = conIn.nextInt();
					e.addToHashMap(f.getName(), rate);
				}
			}
			for ( Man e : women ){
				System.out.print("For " + e.getName());
				for ( Man f : men ){
					System.out.println(" rate " + f.getName());
					int rate = conIn.nextInt();
					e.addToHashMap(f.getName(), rate);
				}
			}	
			ArrayList<ArrayList<Man>> twoTables = Pairer.stableSort( men, women );
			System.out.println(Pairer.sortedTables(twoTables));
		}
	}
/*
 Initially all m∈M and w∈W are free
 While there is a man m who is free and hasn't proposed to every woman
 	Choose such a man m
	Let w be the highest-ranked woman in m's preference list
		to whom m has not yet propsed
	If w is free then
		(m,w) become engaged
	Else w is currently engaged to m'
		If w prefers m' to m then
			m remains free
		Else w prefers m to m'
			(m,w) become engaged
			m' becomes free
		Endif
	Endif
 Endwhile
 Return the set S of engaged pairs
 */
