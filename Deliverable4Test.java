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

    assertEquals(map, d4.hashIt("elements.txt"));

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

    // assertTrue(outContent.toString().contains("Unable to read the file: el.txt"));
    assertEquals(outContent.toString(), "Unable to hash the file: el.txt");

  }


}
