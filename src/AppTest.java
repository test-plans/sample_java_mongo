package test;

import junit.framework.*;
import main.App;

public class AppTest extends TestCase {

  protected void setUp() {
    // put common setup code in here
    try {
      App app = new App();
      app.insertData("Seattle", "WA");
    } catch (Exception e) {
      System.out.println("Error setting up database for test");
    } 
  }
   
  protected void tearDown() {
    // put common cleanup code in here
  }

  public void testGetDataFromBoth() {
    String[] result = {""};
    try {
      App app = new App();
      result = app.getData("Seattle", "WA");
      System.out.println("testGetDataFromBoth: " + result[0]);
    } catch (Exception e) {
      System.out.println("Error testing database");
    } finally {
      assertEquals("Seattle, WA", result[0]);
    }
  } 

  public void testGetDataFromCity() {
    String[] result = {""};
    try {
      App app = new App();
      result = app.getData("Seattle", null);
      System.out.println("testGetDataFromCity: " + result[0]);
    } catch (Exception e) {
      System.out.println("Error testing database");
    } finally {
      assertEquals("Seattle, WA", result[0]);
    }
  } 

  public void testGetDataFromState() {
    String[] result = {""};
    try {
      App app = new App();
      result = app.getData(null, "WA");
      System.out.println("testGetDataFromState: " + result[0]);
    } catch (Exception e) {
      System.out.println("Error testing database");
    } finally {
      assertEquals("Seattle, WA", result[0]);
    }
  } 
}

