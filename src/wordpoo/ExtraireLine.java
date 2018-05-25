package wordpoo;

import org.apache.poi.hwpf.usermodel.Paragraph;

public class ExtraireLine {
	static private boolean intable = false;
    static private boolean inrow = false;
	static private int rownumber = 0;
	public ExtraireLine (){
		
	}
	// End Of Constructor
	
	public static String getLineValue(Paragraph paragraph){
		
	
           // System.out.println(i);

            if (paragraph.isInTable()) {
                if (!intable) {
                	//System.out.println("New table creating"+intable);
                    intable = true;
                }
                if (!inrow) {
                	//System.out.println("New row creating"+inrow);
                	rownumber = 0;
                    inrow = true;
                }
                if (paragraph.isTableRowEnd()) {
                    inrow = false;
                    return "end";
                } else {
                	
                	String wordnoqmark = paragraph.text().replaceAll("","");
                    String wordnospace = wordnoqmark.replaceAll(" ","");
                    //System.out.println("New text adding"+par.text());
                	if(wordnospace.isEmpty()){
                		rownumber++;
                		return null;
                	}else{
                		rownumber++;
                		return wordnospace;
                		//System.out.println(" ***** "+rownumber);

                	}
                }
                	
            } else {
            	 if (inrow) {//System.out.println("Closing Row");
                     inrow = false;
                     return "END";
                 }
                 if (intable) {//System.out.println("Closing Table");
                     intable = false;
                     return "END";

                 }
            }
			return null;
            
        }


	static public int getrownumber(){
		return rownumber;
	}
	
}
