package wordpoo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel label ;
	
	public main(){
		super("DOC TO DIC");
		button = new JButton("Browse");
		button.setBounds(300, 200, 100, 40);
		label = new JLabel("Choisi Fichier Doc :");
		label.setBounds(280, 10, 670, 250);
		add(button);
		add(label);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter docx = new FileNameExtensionFilter("MS Word file(.docx)", "docx");
				FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word file(.doc)", "doc");
				file.addChoosableFileFilter(docx);
				file.addChoosableFileFilter(doc);
				
				int result = file.showSaveDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION){
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					String folder = selectedFile.getParent();
					try {
						todoc(path,folder);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else if(result == JFileChooser.CANCEL_OPTION){
					System.out.println("No File Select ");
				}
			}
			
		});
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700,400);
		setVisible(true);
	}
	
	public void todoc(String path,String Folder)throws IOException{
		wordfile docword = new wordfile(path);
		Range range = docword.getDoc().getRange();
		String Root = null;
		String[] TabElement = new String[9];
		int linenumber = 0;
		
		// Create Dictionary File **
			
			Random rand = new Random();
			int  n = rand.nextInt(5000) + 1;
			@SuppressWarnings("resource")
			
		
			File filedic = new File(Folder+"\\Dictionary-"+n+".dic");
			
			if(!filedic.exists()){
				filedic.createNewFile();
			}
			
			FileWriter fl = new FileWriter(filedic);
			BufferedWriter bf = new BufferedWriter(fl);
			wordfile.createHeader(bf);
			
			String Patt[] = new String[range.numParagraphs()];
			String Rootlist[] = new String[range.numParagraphs()/10];
			
			// write Value in Dictionary File 
		Patternlist patternlist = new Patternlist(range.numParagraphs()/10);

		for(int i =0 ; i<range.numParagraphs() ; i++){
		
			String value = ExtraireLine.getLineValue(range.getParagraph(i));
			if(value == "end"){ 
				// end of line 
				String verb = null;
				String pattern = null;
				
				for(int j =0;j<9;j++){
					if(TabElement[j] != null){
						pattern = Pattern.CleanWord(Pattern.CleanPlus(TabElement[j]));
						
					}
					
						verb = CreatVerb.creatVerb(Root, pattern, j);
						if(verb != null)
							if(j==1){
								if(patternlist.patternid(TabElement[1]) == -1){
									patternlist.setinfo(TabElement[1]);
								}
								
								bf.write(Root+","+Correction.alif(verb)+",V+Root="+Root+"+Pattern="+pattern+"+Flex="+TabElement[j]);
								for(int y=0;y<range.numParagraphs();y++){// insert All pattren in Table
									if(Patt[y] == null){
									
										Patt[y] = pattern;
										break;
									}else if(Patt[y].matches(pattern)){
										break;
									}
								}
								bf.newLine();
								
							}else if(j == 3 ){
								int id = patternlist.patternid(TabElement[1]);
								bf.write(Root+","+verb+",N+MSDA+"+"id="+id+"+Root="+Root+"+Pattern="+Pattern.CleanWord(Pattern.CleanPlus(TabElement[j+1]))+"+Flex=Flex_Masder");
								for(int y=0;y<range.numParagraphs();y++){// insert All pattren in Table
									 if(Patt[y] == null){
										Patt[y] = Pattern.CleanWord(Pattern.CleanPlus(TabElement[j+1]));
										break;
									}else if(Patt[y].matches(Pattern.CleanWord(Pattern.CleanPlus(TabElement[j+1])))){
										break;
									}
								}
								bf.newLine();
							}else if(j>4){
								if(TabElement[j].indexOf('+') == -1 && TabElement[j].length()>2){
									if(j==5){
										bf.write(Root+","+Correction.alif(verb)+",N+MSDH+Root="+Root+"+Pattern="+pattern+"+Flex=Flex_Masder");	
									}else if(j==6){
										bf.write(Root+","+Correction.alif(verb)+",N+MSDM+Root="+Root+"+Pattern="+pattern+"+Flex=Flex_Masder");											
									}else if(j==7){
										bf.write(Root+","+Correction.alif(verb)+",N+MSDMM+Root="+Root+"+Pattern="+pattern+"+Flex=Flex_Masder");											
									}else if(j==8){
										bf.write(Root+","+Correction.alif(verb)+",N+MSDS+Root="+Root+"+Pattern="+pattern+"+Flex=Flex_Masder");											
									}
									for(int y=0;y<range.numParagraphs();y++){// insert All pattren in Table
										if(Patt[y] == null){
											Patt[y] = pattern;
											break;
										}else if(Patt[y].matches(pattern)){
											break;
										} 
									}
									bf.newLine();
								}else if(TabElement[j].indexOf('+') != -1 && (pattern.matches("ة") || pattern.matches("يّة") )){
									if(j==5){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDH+Root="+Root+"+Pattern="+Pattern.patterncomp(TabElement[4], pattern)+"+Flex=Flex_Masder");
									}else if(j==6){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDM+Root="+Root+"+Pattern="+Pattern.patterncomp(TabElement[4], pattern)+"+Flex=Flex_Masder");
									}else if(j==7){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDMM+Root="+Root+"+Pattern="+Pattern.patterncomp(TabElement[4], pattern)+"+Flex=Flex_Masder");
									}else if(j==8){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDS+Root="+Root+"+Pattern="+Pattern.patterncomp(TabElement[4], pattern)+"+Flex=Flex_Masder");
									}
									for(int y=0;y<range.numParagraphs();y++){// insert All pattren in Table
										if(Patt[y] == null){
											Patt[y] = Pattern.patterncomp(TabElement[4], pattern);
											break;
										}else if(Patt[y].matches(Pattern.patterncomp(TabElement[4], pattern))){
											break;
										} 
									}
									bf.newLine();
									
								}else if(TabElement[j].indexOf('+') != -1){
									if(j==5){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDH+Root="+Root+"+Pattern="+TabElement[4]+"+Flex=Flex_Masder");
									}else if(j==6){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDM+Root="+Root+"+Pattern="+TabElement[4]+"+Flex=Flex_Masder");
									}else if(j==7){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDMM+Root="+Root+"+Pattern="+TabElement[4]+"+Flex=Flex_Masder");
									}else if(j==8){
										bf.write(Root+","+Correction.alif(CreatVerb.creatVerb(TabElement[j],TabElement[3]))+",N+MSDs+Root="+Root+"+Pattern="+TabElement[4]+"+Flex=Flex_Masder");
									}
									for(int y=0;y<range.numParagraphs();y++){// insert All pattren in Table
										if(Patt[y] == null){
											Patt[y] = TabElement[4];
											break;
										}else if(Patt[y].matches(TabElement[4])){
											break;
										} 
									}
									bf.newLine();
									
								}
							}
				}

			}else if(value == null){   // case with null value
				
				if(ExtraireLine.getrownumber() == 2){
					TabElement[ExtraireLine.getrownumber()-2] = Root;
					linenumber++;
				}
				
			}else{   //case with value 
				if(ExtraireLine.getrownumber() == 2){
					Root = value;
					linenumber++;
					for(int y=0;y<range.numParagraphs()/10;y++){
						if(Rootlist[y] == null){
							Rootlist[y] = value;
							break;
						}
					}
				}
				TabElement[ExtraireLine.getrownumber()-2] = value;
			}
			
		}
		bf.close();
	    System.out.println("Dictionary-"+n+".dic written successully");
        XWPFDocument docu= new XWPFDocument();
		FileOutputStream  filePattern = new FileOutputStream (Folder+"\\Pattern-"+n+".docx");
		XWPFParagraph para = docu.createParagraph();
	    
		
	    //create table
	    XWPFTable table = docu.createTable();
	    
	    //create first row
	    XWPFTableRow tableRowOne = table.getRow(0);
	    tableRowOne.getCell(0).setText("ID");
	    XWPFParagraph title1 = tableRowOne.getCell(0).getParagraphArray(0);
    	title1.setAlignment(ParagraphAlignment.CENTER);


	    tableRowOne.addNewTableCell().setText("Valeur");
	    XWPFParagraph title2 = tableRowOne.getCell(1).getParagraphArray(0);
    	title2.setAlignment(ParagraphAlignment.CENTER);


	    //create DATA row
	    int numberOfPattern = patternlist.nuberOfPattern();
	    XWPFTableRow tablePatern[] = new XWPFTableRow[numberOfPattern];
	    
	    for(int i=0;i<numberOfPattern-1;i++){
	    	//if(patternlist.getpattern(i) == null)
	    	//	break;
	    	tablePatern[i] = table.createRow();
	    	tablePatern[i].getCell(0).setText((Integer.toString(patternlist.getpatternnum(i))));
	    	tablePatern[i].getCell(1).setText((patternlist.getpattern(i)));
	    	XWPFParagraph text1 = tablePatern[i].getCell(0).getParagraphArray(0);
	    	text1.setAlignment(ParagraphAlignment.CENTER);

	    	XWPFParagraph text2 = tablePatern[i].getCell(1).getParagraphArray(0);
	    	text2.setAlignment(ParagraphAlignment.CENTER);

	    }
	    for(int x = 0;x < table.getNumberOfRows(); x++){
	          XWPFTableRow row = table.getRow(x);
	          int numberOfCell = row.getTableCells().size();
	          for(int y = 0; y < numberOfCell ; y++){
	              XWPFTableCell cell = row.getCell(y);

	              cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
	              
	          } 
	        }
	    
	    File pattern_root = new File(Folder+"\\Pattern_root-"+n+".txt");
		
		if(!pattern_root.exists()){
			pattern_root.createNewFile();
		}
		
		FileWriter fl1 = new FileWriter(pattern_root);
		BufferedWriter bf1 = new BufferedWriter(fl1);
		bf1.write("Pattern = ");
		for(int i=0;i<range.numParagraphs();i++){
			if(Patt[i] == null)
				break;
			bf1.write(Patt[i]+" | ");
		}
		bf1.newLine();
		bf1.write("Root = ");
		for(int i=0;i<range.numParagraphs()/10;i++){
			if(Rootlist[i] == null)
				break;
			bf1.write(Rootlist[i]+" | ");
		}
		bf1.close();
	   /*
	    * * 
	    XWPFTableRow tableRowTwo = table.createRow();
	    tableRowTwo.getCell(0).setText("col one, row two");
	    tableRowTwo.getCell(1).setText("col two, row two");
	    tableRowTwo.getCell(2).setText("col three, row two");
	    //create third row
	    XWPFTableRow tableRowThree = table.createRow();
	    tableRowThree.getCell(0).setText("col one, row three");
	    tableRowThree.getCell(1).setText("col two, row three");
	    tableRowThree.getCell(2).setText("col three, row three");
		*/
	    //Write second Text after the table (by creating a new paragraph)
	    

	    docu.write(filePattern);
	    filePattern.close();
	    System.out.println("Pattern-"+n+".docx written successully");
	    System.out.println("Done");
	    /*	for(int i=0;i<patternlist.getsize();i++){
			if(patternlist.getpattern(i) == null)
				break;
			bf2.write("Pattern="+patternlist.getpattern(i)+"+id="+patternlist.getpatternnum(i));
			bf2.newLine();
		}
		bf2.close();*/
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main(); 
		
		
	}

}
