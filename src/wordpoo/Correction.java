package wordpoo;

public class Correction {
	
	static String alif(String verb){
		char[] correction = verb.toCharArray();
		StringBuilder str = new StringBuilder();
		int indiceAlif = verb.indexOf('أ');
		// Alif in middle line
		if(indiceAlif != -1 && indiceAlif != 0 && indiceAlif != verb.length()-1){
			if(correction[indiceAlif+1] == 'ِ' || correction[indiceAlif-1] == 'ِ' || ( correction[indiceAlif-1] == 'ْ' && correction[indiceAlif-2] == 'ي')  ){
				
				correction[indiceAlif] = 'ئ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				
				return str.toString();
			}else if(correction[indiceAlif+1] == 'ُ' || correction[indiceAlif-1] == 'ُ' ){
				
				correction[indiceAlif] = 'ؤ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}else if((correction[indiceAlif+1] == 'َ' && correction[indiceAlif-1] == 'ا') || (correction[indiceAlif+1] == 'َ' && correction[indiceAlif+2] == 'ا' && correction[indiceAlif+3] == 'ً') || (correction[indiceAlif+1] == 'َ' && correction[indiceAlif+2] == 'ا' && correction[indiceAlif+3] == 'ن')){
				
				correction[indiceAlif] = 'ء';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			} else if(correction[indiceAlif+1] == 'َ' || correction[indiceAlif-1] == 'َ' ){
				
				correction[indiceAlif] = 'أ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}
			// Alif in Start Line
		}else if(indiceAlif == 0 && correction[1] == 'ِ'){
			correction[indiceAlif] = 'إ';
			for(int i =0;i<verb.length();i++)
				str.append(correction[i]);
			return str.toString();
			// Alif in End of Line
		}else if(indiceAlif == verb.length()-1){
			if(correction[indiceAlif-1] == 'ِ' ){
				
				correction[indiceAlif] = 'ئ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}else if(correction[indiceAlif-1] ==  'ُ' ){
				
				correction[indiceAlif] = 'ؤ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}else if(correction[indiceAlif-1] == 'َ' ){
				
				correction[indiceAlif] = 'أ';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}else if(correction[indiceAlif-1] == 'ْ' ){ 
				
				correction[indiceAlif] = 'ء';
				for(int i =0;i<verb.length();i++)
					str.append(correction[i]);
				return str.toString();
				
			}
		}
		return verb;
		
	}
	
	// if we have ي 
}
