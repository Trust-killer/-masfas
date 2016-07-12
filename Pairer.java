import java.util.*;
public class Pairer{
	public static ArrayList<ArrayList<Man>> stableSort( ArrayList<Man> men, ArrayList<Man> women ){
		ArrayList<ArrayList<Man>> toReturn = new ArrayList<ArrayList<Man>>();	
		Pairer pairer = new Pairer();
		while(  pairer.nextMan(men) != null){
			Man e = pairer.nextMan(men);
			String womanName = pairer.nextWoman(e);	
			System.out.println("e is " + e.getName() + " womanName is " + womanName );
			Man woman = pairer.getWoman(womanName, women);	
			System.out.println("woman is " + woman.getName());
			if ( woman.getFree() ){
				System.out.println(woman.getName() + " is free");
				woman.setFree(false);
				e.setFree(false);
				e.engagedTo(woman.getName());
				woman.engagedTo(e.getName());
				e.setProposeCount();
			} else {
				if ( woman.getRating(woman.getFiance()) > woman.getRating(e.getName())){
					System.out.println(woman.getName() + " is not free and likes her current partner better ");
					e.setProposeCount();
				} else {
					System.out.println("Woman is not free and is trading her partner " + woman.getFiance() + " for " + e.getName());
					String manName = woman.getFiance();
					woman.engagedTo(e.getName());
					e.engagedTo(woman.getName());
					woman.setFree(false);
					e.setFree(false);
					e.setProposeCount();
					Man toFree = pairer.getMan(manName, men);
					toFree.setFree(true);
				}
			}
		}
		toReturn.add(men);
		toReturn.add(women);
		return toReturn;
	}
	public static String sortedTables( ArrayList<ArrayList<Man>> tables ){
		StringBuilder sB = new StringBuilder("");
		ArrayList<Man> men = tables.get(0);
		ArrayList<Man> women =  tables.get(1);
		sB.append("Table for Men\n");
		sB.append("\t________________________________________\n");			
		for ( Man e : men ){
			sB.append("\t"+"| "+e.getName().substring(0,3));			
		}
		sB.append("\t|\n");
		sB.append("\t________________________________________\n");
		for ( Man e : men ){
			
			sB.append("\t"+"| "+e.getFiance().substring(0,3));
		}
		sB.append("\t|\n");
		sB.append("\t________________________________________\n");
		sB.append("\n\n\nTable for Women\n");
		sB.append("\t________________________________________\n");
		for ( Man e : women ){
			sB.append("\t"+"| "+e.getName().substring(0,3));			
		}
		sB.append("\t|\n");
		sB.append("\t________________________________________\n");
		for ( Man e : women ){
			
			sB.append("\t"+"| "+e.getFiance().substring(0,3));
		}
		sB.append("\n\t________________________________________\n");			
		return sB.toString();
	}
	public Man getMan(String name, ArrayList<Man> men){
		for ( Man e : men ){
			if ( e.getName().equals(name))
				return e;
		}
		return null;	
	}
	public Man getWoman( String name, ArrayList<Man> women ){
		for ( Man e : women ){
			if ( e.getName().equals(name) )
				return e;
		}
		return null;
	}
	public Man nextMan(ArrayList<Man> men){
		for ( Man e : men ){
			if ( e.getFree() && e.canPropose() ){
				return e;
			}
		}
		return null;
	}
	public String nextWoman(Man e){
		HashMap<String,Integer> hashMap = e.getHashMap();
		Set set = hashMap.entrySet();
		Iterator iterator = set.iterator();
		int highest = 0;
		StringBuilder sB = new StringBuilder("");
		while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			int value = (int) entry.getValue();
			String name = (String) entry.getKey();	
			if ( value >= highest ){
				highest = value;
				sB.setLength(0);
				sB.append(name);
			}
		}
		String toReturn = sB.toString();
		hashMap.remove(toReturn);
		return toReturn;
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
}
