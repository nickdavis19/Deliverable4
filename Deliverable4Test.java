import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import java.lang.*;
import java.io.*;
import java.util.*;



public class Deliverable4Test {
  PrintStream oldStdErr = System.err;
  PrintStream oldStfOut = System.out;

  @After
  public void cleanUpStreams() {
      System.setOut(oldStfOut);
      System.setErr(oldStdErr);
  }

  // Test the hashIt function correctly hashes a file that is properly formatted
  @Test
	public void hashIt_test1(){

    //create a Deliverable4 object
    Deliverable4 d4 = new Deliverable4();
    //create the hashmap the result should look like
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("ac", "actinium");
    map.put("ag", "silver");
    map.put("al", "aluminum");
    map.put("am", "americium");
    map.put("ar", "argon");
    map.put("as", "arsenic");
    map.put("at", "astatine");



    assertEquals(map, d4.hashIt("elements1.txt"));

	}

  // Test the hashIt function with a non-existant file
  @Test
  public void hashIt_test2(){

    //create a Deliverable4 object
    Deliverable4 d4 = new Deliverable4();

    //force the print output to come to the outContent stream
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    //call the hashIt function with a non-existant file
    d4.hashIt("e.txt");

    assertTrue(outContent.toString().contains("Unable to find the file: e.txt"));

  }

  // Test the hashIt function with an incorrectly formatted file
  @Test
  public void hashIt_test3(){

    //create a Deliverable4 object
    Deliverable4 d4 = new Deliverable4();

    //force the print output to come to the outContent stream
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    //call the hashIt function with a misformatted file (specifically does not have any :'s in it)
    d4.hashIt("el.txt");

    assertTrue(outContent.toString().contains("Unable to hash the file:"));
    // assertEquals(outContent.toString(), "Unable to hash the file: el.txt");

  }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Test the readFile function with a non-existant file
  @Test
  public void readFile_test1(){

    //create a Deliverable4 object
    Deliverable4 d4 = new Deliverable4();

    //force the print output to come to the outContent stream
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    //call the hashIt function with a non-existant file
    d4.readFile("e.txt");
    // assertEquals(outContent.toString(), "Unable to find the file: e.txt");

    assertTrue(outContent.toString().contains("Unable to find the file:"));

  }

  // Test the readFile function with a the test file which is appropriate input
  @Test
  public void readFile_test2(){

    //create a Deliverable4 object
    Deliverable4 d4 = new Deliverable4();

    List<String> theFile = new ArrayList<String>();
    theFile.add("Tsar");

    //call the hashIt function with a misformatted file (specifically does not have any :'s in it)
    assertEquals(theFile, d4.readFile("test.txt"));

  }

  //Test readfile


}
