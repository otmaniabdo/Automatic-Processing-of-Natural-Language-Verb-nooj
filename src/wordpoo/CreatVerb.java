package wordpoo;

public class CreatVerb {
	

	static public String creatVerb(String root , String verb , int indice){
		char[] Verb = verb.toCharArray();
		StringBuilder newVerb = new StringBuilder(); ;
			
			if(indice == 1 || indice > 4){
				if(root.length() == 3){

				for(int i =0 ; i<verb.length();i++){
				
					if(Verb[i] == 'ف'){
						 Verb[i] = root.charAt(0);
					 }else if(Verb[i] == 'ع'){
						 Verb[i] = root.charAt(1);
					 }else if(Verb[i] == 'ل'){
						 Verb[i] = root.charAt(2);
					 }
				
				}
				
			}else if (root .length() == 4){
				boolean char4 = false;
				for(int i =0 ; i<verb.length();i++){
					
					if(Verb[i] == 'ف'){
						 Verb[i] = root.charAt(0);
					 }else if(Verb[i] == 'ع'){
						 Verb[i] = root.charAt(1);
					 }else if(Verb[i] == 'ل' && !char4){
						 Verb[i] = root.charAt(2);
						 char4 = true;
					 }else if (Verb[i] == 'ل' && char4){
						 Verb[i] = root.charAt(3);
						 char4 = false;
					 }
				
				}
			}
			for(int i=0;i<verb.length();i++){
				newVerb.append(Verb[i]);
			}
			return newVerb.toString();
			
			
		}else if (indice == 3){
			return verb.substring(0,verb.length()-2);
		}
		return null;
		
	}
	
	static public String creatVerb(String pattern, String root){
		//StringBuilder newVerb = new StringBuilder(); 
		//char[] Verb = new char[root.length()-2+pattern.length()];
		//Verb = root.substring(0,root.length()-2).toCharArray();
		if (pattern.length() < 5){
		/*	for(int i=0;i<root.length()-2;i++){
				newVerb.append(Verb[i]);
			}
			for(int i=0;i<pattern.length();i++){
				newVerb.append(pattern[i]);
			}
		*/	
			return (root.substring(0, root.length()-2)+Pattern.CleanPlus(pattern)).replaceAll(" ", "");
		}else{
			return root+" "+Pattern.CleanPlus(pattern);
		}
		
	}
	//static public String creatPattern(String Pattern1 ,String Pattern2){
	//	return Pattern1+Pattern.CleanPlus(Pattern2);
	//}
}
