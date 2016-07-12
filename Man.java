import java.util.*;
public class Man{
	private boolean free;
	private String name;
	private String fiance;
	private int currentMatch;
	private int proposeCount;
	private int proposeMax;
	private HashMap<String, Integer> hashMap;
	public Man( String name ){
		proposeCount = 0;
		proposeMax = 0;
		this.name = name;
		this.free = true;
		hashMap = new HashMap<String, Integer>();
	}
	public void engagedTo(String fiance){
		this.fiance = fiance;
	}
	public boolean canPropose(){
		return (proposeCount < proposeMax);
	}
	public void setProposeCount(){
		proposeCount++;
	}
	public String getName(){
		return name;
	}
	public void setName(){
		
	}
	public int getCurrentMatch(){
		return currentMatch;
	}
	public void setCurrentMatch(String name){
		
	}
	public HashMap<String,Integer> getHashMap(){
		return hashMap;
	}
	public void addToHashMap(String name, Integer rating){
		proposeMax++;
		hashMap.put(name, rating);
	}
	public int getRating( String name ){
		return hashMap.get(name);
	}
	public boolean getFree(){
		return free;
	}
	public void setFree(boolean free){
		this.free =free;
	}
	public void setFiance(String fiance){
		this.fiance = fiance;
	}
	public String getFiance(){
		return fiance;
	}
}
