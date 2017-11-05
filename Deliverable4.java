import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class Deliverable4 {
	
	public static void main(String[] args){
		HashMap<String, String> eleMap;
		List<String> namesToTest;
		StringBuilder elementsString = new StringBuilder();
		StringBuilder prefixString = new StringBuilder();
		String _file = new String(args[0]);//read the filename from the command line
		if(args.length != 1){
			System.out.println("Please enter only one argument, the file to read.");
			System.exit(0);
		}
		eleMap = hashIt("elements.txt");//always read the elements from this .txt file, as its formatted
		namesToTest = readFile(_file);
		
		int temp = 0;
		while(temp < namesToTest.size()){
			String currentName = namesToTest.get(temp);
			boolean success = buildName(currentName, eleMap, elementsString, prefixString);
			temp++;
		}
	}
	
	public static boolean buildName(String name, HashMap<String, String> elements, StringBuilder eleString, StringBuilder preString){
		eleString.delete(0, eleString.length());
		preString.delete(0, preString.length());
		String _name = name;
		String theElement = null; //theElement = the name of the element that is matched to our substring
		int low = 0; //low = the lowest index of the name that forms the substring we're trying to match
		int high = 2; //high = the highest index of the name that forms the substring we're trying to match
		while(low < _name.length()){
			String currentSearch = _name.substring(low, high); //forms a substring from the name from low to high exclusive
			System.out.println("Current search:" +currentSearch);
		
			while(theElement == null){ //matches the substring to an element name
				theElement = elements.get(currentSearch);
				System.out.println("The Element: "+theElement);
				if(theElement == null){ //if the current substring has no element name, decrement the size of the substring and look again
					if(low == high){
						System.out.println("Could not create name \"" + _name + "\" out of elements.");
						return false;
					}
					high--;
					currentSearch = _name.substring(low, high);
				}
			}
		
			preString.append(currentSearch);
			eleString.append(theElement);
			System.out.println("prestring: "+preString);
			System.out.println("eleString: "+eleString);
			
			low = high; //advances the lowest index to the lowest index that hasn't been matched yet
			high = low + 2; //advances the highes index to the lowest index + 2 to make a 2 character substring (largest element prefixes are 2 characters long)
			theElement = null;
		}
		return true;
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
				line = line.replaceAll("\\x22+", "");//removes all " characters from the line read in
				line = line.replaceAll("\\x2c", "");
				line = line.toLowerCase();//standardizes it as lower case
				String parts[] = line.split("\\:");//splits the line into 2 strings separated by the ":" character
				
				map.put(parts[0], parts[1]);//puts the element prefix as the key, and element name as the value into the hashmap
			}
			br.close();
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
				line = line.replaceAll("\\W+", "");//removes all special characters from the line read in
				line = line.toLowerCase();//standardizes it as lower case
				theFile.add(line);
			}
		}catch(FileNotFoundException ex){
			System.out.println("Unable to find the file: " + _filename);
		}catch(IOException ex){
			System.out.println("Unable to read the file: "+ _filename);
		}
		
		return theFile;
	}
}
