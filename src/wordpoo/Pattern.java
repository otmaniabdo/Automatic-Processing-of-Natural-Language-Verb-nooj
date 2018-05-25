package wordpoo;

public class Pattern {
	
	static public String CleanWord(String word){
		if(word.indexOf('-') == -1){
			return word;
		}else if(word.indexOf('-') != -1){
			return word.substring(0, word.indexOf('-'));
		}
		return word;
	}
	static public String CleanPlus(String word){
		if(word.indexOf('+') == -1){
			return word;
		}else if(word.indexOf('+') != -1){
			String newword = word.replace('+', ' ');
			String newword2 = newword.replaceAll(" ","");
			return newword2;
		}
		return word;
		
	}
	static public String patterncomp(String pat1,String pat2){
		return pat1+pat2;
	}
	
	
	
}
