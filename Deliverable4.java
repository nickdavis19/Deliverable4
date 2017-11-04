import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class Deliverable4 {
	
	public static void main(String[] args){
		HashMap<String, String> eleMap;
		List<String> namesToTest;
		String _file = new String(args[0]);//read the filename from the command line
		if(args[1].isEmpty()){
			//if there are no more arguments other than the first one, it's ok to continue
		}else{
			System.out.println("Please enter only one argument, the file to read.");
			System.exit(0);
		}
		eleMap = hashIt("elements.txt");//always read the elements from this .txt file, as its formatted
		namesToTest = readFile(_file);
		
	}
	
	//Returns a hashMap of the formatted element file passed to it
	//Parameters: 
	//filename: a formatted .txt file that has the element prefixes and element names
	//Returns:
	//a HashMap<String, String> that has the element prefix as the key and element name as the value
	public static HashMap<String, String> hashIt(String filename){
		HashMap<String, String> map = new HashMap<String, String>(119, 1); //makes a hashMap of capacity 119 and load factor 1. There are 118 elements so the map is large enough, load factor is 1 so it will not dynamically resize until there are 119 elements in the map
		String _filename = filename;
		String line = null;
		
		try{
			FileReader fr = new FileReader(_filename);
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				line = line.replaceAll("\\s+", "");//removes whitespace from the line read in
				line = line.replaceAll("\\0x22", "");//removes all " from the line read in
				line = line.toLowerCase();//standardizes it as lower case
				String parts[] = line.split("\\:");//splits the line into 2 strings separated by the ":" character
				
				map.put(parts[0], parts[1]);//puts the element prefix as the key, and element name as the value into the hashmap
				br.close();
			}
		}catch(FileNotFoundException ex){
			System.out.println("Unable to find the file: " + _filename);
		}catch(IOException ex){
			System.out.println("Unable to read the file: "+ _filename);
		}
		return map;
	}
	
	//Returns a List of all the lines in the file
	//Parameters: 
	//filename: the name of the file to be read
	//Returns:
	//a List<String> of all the lines of the input file
	public static List<String> readFile(String filename){
		List<String> theFile = new ArrayList<String>();
		String _filename = filename;
		String line = null;
		
		try{
			FileReader fr = new FileReader(_filename);
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				line = line.replaceAll("\\s+", "");//removes whitespace from the line
				line = line.toLowerCase();//standardizes it as lower case
			}
		}catch(FileNotFoundException ex){
			System.out.println("Unable to find the file: " + _filename);
		}catch(IOException ex){
			System.out.println("Unable to read the file: "+ _filename);
		}
		
		return theFile;
	}
}
