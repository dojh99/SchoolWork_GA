
import java.util.*;

public class Gene extends GeneTemplate<Integer>{
	static private int maxSize =50;
	static private int maxValue =50;
	static private int minValue =0;

	static public Random rand= new Random();

	static public int n_pointCross =5; //1 ~ maxSize-1
	
	ArrayList<Integer> crossPoint = new ArrayList<Integer>();

	// boolean mod_strict = false;


	@Override
	public boolean mutate(double permil){
		// TODO: Implement this method
		boolean mutated = (Math.random()*1000)<permil;

		if (mutated){
			int point = rand.nextInt(maxSize);
			int mutatednum = (int) rand.nextInt(maxValue-minValue+1)+minValue;
			this.set(point,mutatednum);
			//this.add(point,mutatednum);
		}

		return mutated;
	}

	@Override
	public GeneTemplate<Integer> crossOver(GeneTemplate<Integer> others){
		//Random rand= new Random();
		Gene crossed= new Gene();
		ArrayList<Integer> pointTemp = new ArrayList<Integer>(n_pointCross);

		int i=0;

		for (i=0;i<n_pointCross;i++){
			Integer point = new Integer(rand.nextInt(maxSize-1)+1);

			while (pointTemp.contains(point)){
				point = new Integer(rand.nextInt(maxSize-1)+1);
			}
			pointTemp.add(point);
		}
		Collections.sort(pointTemp);
		//System.out.println(pointTemp.toString());

		boolean toggle=true;
		int k=0;
		i=0;

		for (i=0;i<n_pointCross;i++){
			
			while (k<pointTemp.get(i)){
				Integer data = toggle? this.get(k) :others.get(k);
				crossed.add(data);
				k++;
			}
			toggle=!toggle;
		}
		
		while (k<maxSize){
			Integer data = toggle? this.get(k) :others.get(k);
			crossed.add(data);
			k++;
		}
		
		crossed.crossPoint= pointTemp;

		return crossed;
	}

	@Override
	public void scoring(){
		scoring_test1();
		
	}

	@Override
	public void autoInit(){
		int i;
		final int k = maxSize;

		for (i=0;i<k;i++){
			int n = rand.nextInt(maxValue-minValue+1)+minValue;
			this.add(i,n);
		}
	}

	private void scoring_test1(){
		int score=0;
		int k=1;
		for(Integer i : this){
			if(i.intValue()==k)
				score++;
			k++;
		}
		this.fitness= score;
		
	}

	private void scoring_test2(){

	}

	public void init(int mxSize,int mxValue,int mnValue){
		this.maxSize = mxSize;
		this.maxValue = mxValue;
		this. minValue = mnValue;
		//this.mod_strict = mod_strict;
	}

	@Override
	public boolean add(Integer object){
		// TODO: Implement this method
		if (this.size()<maxSize)
		    return super.add(object);

		return false;
	}

	@Override
	public String toString(){
		// TODO: Implement this method
		return "Fitness : " + fitness +" Values : "+ super.toString() + "  lenth : "+this.size() +" CPOINT :" + crossPoint.toString();
	
	}
	
	



}