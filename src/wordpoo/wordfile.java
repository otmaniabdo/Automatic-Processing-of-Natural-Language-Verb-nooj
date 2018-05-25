package wordpoo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class wordfile{
	
    private InputStream fis ; 
    private POIFSFileSystem fs; 
    private HWPFDocument doc ; 
    
    public wordfile(String filename) throws IOException{
    	
    	fis = new FileInputStream(filename);
        fs  = new POIFSFileSystem(fis);
        doc = new HWPFDocument(fs);
    	
    }
    public HWPFDocument getDoc(){
    	
    	return doc;
    }
    
    public static void createHeader(BufferedWriter bf) throws IOException{
    	bf.write("# NooJ V5");
    	bf.newLine();
    	bf.write("# Dictionary");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    	bf.write("# Language is: ar");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    	bf.write("# Alphabetical order is not required.");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    	bf.write("# Use inflectional & derivational paradigms' description files (.nof), e.g.:");
    	bf.newLine();
    	bf.write("# Special Command: #use paradigms.nof");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    	bf.write("# Special Features: +NW (non-word) +FXC (frozen expression component) +UNAMB (unambiguous lexical entry)");
    	bf.newLine();
    	bf.write("#                   +FLX= (inflectional paradigm) +DRV= (derivational paradigm)");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    	bf.write("# Special Characters: \'\\\' \'\"\' \' \' \',\' \'+\' \'-\' \'#\' ");
    	bf.newLine();
    	bf.write("#");
    	bf.newLine();
    }

}
