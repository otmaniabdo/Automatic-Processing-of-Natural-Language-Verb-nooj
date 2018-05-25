package wordpoo;

public class Patternlist {
	private String[] pattern;
	private  int[] pattnum;
	private int size;
	public Patternlist (int size){
		pattern=new String[size];
		pattnum=new int[size];
		this.size = size;
	}
	public void setinfo(String patt){
		for(int i=0;i<size;i++){
			if(pattern[i] == null){
			pattern[i]  = patt;
			pattnum[i] = i+1;
			break;
			}
		}
		
	}
	/*public void setinfo(String patt1,String patt2){
		for(int i=0;i<size;i++){
			if(pattern[i] == null){
			pattern[i]  = patt1;
			pattnum[i] = i+1;
			pattern[i+1]  = patt2;
			pattnum[i+1] = i+2;
			break;
			}
		}
		
	}*/	
	public String getpattern(int indice){
		return pattern[indice];
	}
	public int getpatternnum(int indice){
		return pattnum[indice];
	}
	public int getsize(){
		return size;
	}
	public int patternid(String patternToFind){
		for(int i=0;i<size;i++){
			if(pattern[i] != null)	
				if(pattern[i].matches(patternToFind))
				return i+1;
			}
		return -1;
	}
	public int nuberOfPattern(){
		for(int i=0;i<size;i++){
			if(pattern[i] == null){	
				return i;
			}else{
				i++;
			}
		}
		return size;

	}
	
}

