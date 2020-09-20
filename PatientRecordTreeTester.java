//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Patient Record System Tree Tester
// Files: PatientRecordTree.java, PatientRecordTreeTester.java,
// PatientRecordNode.java, PatientRecord.java
// Course: CS 300, Spring 2020
//
// Author: Matt Thompson
// Email: mathompson23@wisc.edu
// Lecturer's Name: Hobbes LeGault
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Sources:
// - https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-
// or-height-of-a-tree/
//
// - https://www.geeksforgeeks.org/print-leftmost-and-rightmost-nodes-of-a-
// binary-tree/
//
// - https://stackoverflow.com/questions/44181981/print-binary-search-tree-
// from-biggest-number-to-smallest-using-java
//
// - https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PatientRecordTree.
 *
 * @author Matt Thompson
 */

public class PatientRecordTreeTester {

  /**
   * Checks the correctness of the implementation of both addPatientRecord() and toString() methods
   * implemented in the PatientRecordTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PatientRecordTree, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one patient record and
   * then check that the addPatientRecord() method call returns true, the tree is not empty, its
   * size is 1, and the .toString() called on the tree returns the expected output. (3) Try adding
   * another patientRecord which is older that the one at the root, (4) Try adding a third patient
   * Record which is younger than the one at the root, (5) Try adding at least two further patient
   * records such that one must be added at the left subtree, and the other at the right subtree.
   * For all the above scenarios, and more, double check each time that size() method returns the
   * expected value, the add method call returns true, and that the .toString() method returns the
   * expected string representation of the contents of the binary search tree in an ascendant order
   * from the oldest patient to the youngest one. (6) Try adding a patient whose date of birth was
   * used as a key for a patient record already stored in the tree. Make sure that the
   * addPatientRecord() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordToStringSize() {
    // Test 1: empty PatientRecordTree
    PatientRecordTree tree = new PatientRecordTree();
    String treeString = "";

    if (tree.size() != 0) { // size of 0
      return false;
    }
    if (!tree.isEmpty()) { // is empty
      return false;
    }
    if (!tree.toString().equals(treeString)) { // empty string
      return false;
    }

    // Test 2: add one patient to record
    PatientRecord patient1 = new PatientRecord("Matt", "07/06/2001");
    if (!tree.addPatientRecord(patient1)) { // if patient was not added
      return false;
    }
    if (tree.isEmpty()) { // if tree is still empty
      return false;
    }
    if (tree.size() != 1) { // if size is not 1
      return false;
    }
    if (!tree.toString().equals("Matt(7/6/2001)\n")) {
      return false;
    }

    // Test 3: two patients on record (older)
    PatientRecord patient2 = new PatientRecord("Dave", "02/06/1969");
    if (!tree.addPatientRecord(patient2)) { // if patient was not added
      return false;
    }
    if (tree.isEmpty()) { // if tree is still empty
      return false;
    }
    if (tree.size() != 2) { // if size is not 2
      return false;
    }
    if (!tree.toString().equals("Dave(2/6/1969)\nMatt(7/6/2001)\n")) {
      return false;
    }

    // Test 4: three patients on record (younger)
    PatientRecord patient3 = new PatientRecord("Amanda", "01/19/2006");
    if (!tree.addPatientRecord(patient3)) { // if patient was not added
      return false;
    }
    if (tree.isEmpty()) { // if tree is still empty
      return false;
    }
    if (tree.size() != 3) { // if size is not 3
      return false;
    }
    if (!tree.toString().equals("Dave(2/6/1969)\nMatt(7/6/2001)\nAmanda(1/19/2006)\n")) {
      return false;
    }

    // Test 5: five patients, a new one on each side
    PatientRecord patient4 = new PatientRecord("Maria", "12/21/1969");
    PatientRecord patient5 = new PatientRecord("Emil", "08/30/2001");
    if (!tree.addPatientRecord(patient4)) { // if patient was not added
      return false;
    }
    if (tree.isEmpty()) { // if tree is still empty
      return false;
    }
    if (tree.size() != 4) { // if size is not 4
      return false;
    }
    if (!tree.toString()
        .equals("Dave(2/6/1969)\nMaria(12/21/1969)\nMatt(7/6/2001)\nAmanda(1/19/2006)\n")) {
      return false;
    }

    if (!tree.addPatientRecord(patient5)) { // if patient was not added
      return false;
    }
    if (tree.isEmpty()) { // if tree is still empty
      return false;
    }
    if (tree.size() != 5) { // if size is not 5
      return false;
    }
    if (!tree.toString().equals(
        "Dave(2/6/1969)\nMaria(12/21/1969)\nMatt(7/6/2001)\nEmil(8/30/2001)\nAmanda(1/19/2006)\n")) {
      return false;
    }

    // Test 6: patient cannot be added
    PatientRecord patient6 = new PatientRecord("Mateo", "07/06/2001");
    if (tree.addPatientRecord(patient6)) { // if patient was not added
      return false;
    }
    if (tree.size() != 5) { // if size does not remain 5
      return false;
    }
    if (!tree.toString().equals(
        "Dave(2/6/1969)\nMaria(12/21/1969)\nMatt(7/6/2001)\nEmil(8/30/2001)\nAmanda(1/19/2006)\n")) {
      return false;
    }

    return true;
  }

  /**
   * This method checks mainly for the correctness of the PatientRecordTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PatientRecordTree. Then, check
   * that calling the lookup() method with any valid date must throw a NoSuchElementException. (2)
   * Consider a PatientRecordTree of height 3 which consists of at least 5 PatientRecordNodes. Then,
   * try to call lookup() method to search for the patient record at the root of the tree, then a
   * patient records at the right and left subtrees at different levels. Make sure that the lookup()
   * method returns the expected output for every method call. (3) Consider calling .lookup() method
   * on a non-empty PatientRecordTree with a date of birth not stored in the tree, and ensure that
   * the method call throws a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordAndLookup() {
    // Test 1: empty lookup
    PatientRecordTree bst = new PatientRecordTree();
    String date = "4/10/2001";
    try {
      bst.lookup(date);

      return false;
    } catch (NoSuchElementException e) {
    }

    // Test 2: tree with 5 people
    PatientRecord patient1 = new PatientRecord("Audrey", "04/10/2001");
    PatientRecord patient2 = new PatientRecord("Matt", "07/06/2001");
    PatientRecord patient3 = new PatientRecord("Maria", "12/21/1969");
    PatientRecord patient4 = new PatientRecord("Dave", "02/06/1969");
    PatientRecord patient5 = new PatientRecord("Sylvie", "06/29/2003");

    bst.addPatientRecord(patient1);
    bst.addPatientRecord(patient2);
    bst.addPatientRecord(patient3);
    bst.addPatientRecord(patient4);
    bst.addPatientRecord(patient5);

    try {
      bst.lookup(date); // looking for patient #1 (root)
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception, failed");

      return false;
    }

    date = "6/29/2003";

    try {
      bst.lookup(date); // looking for patient #5 (right-most)
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception, failed");

      return false;
    }

    date = "2/6/1969";

    try {
      bst.lookup(date); // looking for patient #4 (root)
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception, failed");

      return false;
    }

    date = "7/6/2001";

    try {
      bst.lookup(date); // looking for patient #2 (right of root)
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception, failed");

      return false;
    }

    date = "12/21/1969";

    try {
      bst.lookup(date); // looking for patient #3 (left of root)
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception, failed");

      return false;
    }

    // Test 3: Date is not in record
    date = "7/4/1996";

    try {
      bst.lookup(date); // "looking" for date of non-existing patient

      return false;
    } catch (NoSuchElementException e) {
    }

    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.height() method. This test must consider
   * several scenarios such as, (1) ensures that the height of an empty patient record tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1. (3) ensures that
   * the height of a PatientRecordTree with the following structure for instance, is 4. (*) / \ (*)
   * (*) \ / \ (*) (*) (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // Test 1: Empty tree
    PatientRecordTree bst = new PatientRecordTree();

    if (bst.height() != 0) {
      return false;
    }
    // Test 2: Root is 1
    PatientRecord patient1 = new PatientRecord("Audrey", "04/10/2001");
    bst.addPatientRecord(patient1);

    if (bst.height() != 1) {
      return false;
    }

    // Test 3: Height of 4
    PatientRecord patient2 = new PatientRecord("Matt", "07/06/2001");
    PatientRecord patient3 = new PatientRecord("Dave", "02/06/1969");
    PatientRecord patient4 = new PatientRecord("Maria", "12/21/1969");
    PatientRecord patient5 = new PatientRecord("Crystal", "05/30/2001");
    PatientRecord patient6 = new PatientRecord("Amanda", "01/19/2006");
    PatientRecord patient7 = new PatientRecord("Sylvie", "06/29/2003");

    bst.addPatientRecord(patient2);
    bst.addPatientRecord(patient3);
    bst.addPatientRecord(patient4);
    bst.addPatientRecord(patient5);
    bst.addPatientRecord(patient6);
    bst.addPatientRecord(patient7);

    if (bst.height() != 4) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfYoungestPatient() {
    PatientRecordTree bst = new PatientRecordTree();

    PatientRecord patient1 = new PatientRecord("Audrey", "04/10/2001");
    PatientRecord patient2 = new PatientRecord("Matt", "07/06/2001");
    PatientRecord patient3 = new PatientRecord("Dave", "02/06/1969");
    PatientRecord patient4 = new PatientRecord("Maria", "12/21/1969");
    PatientRecord patient5 = new PatientRecord("Crystal", "05/30/2001");
    PatientRecord patient6 = new PatientRecord("Amanda", "01/19/2006");
    PatientRecord patient7 = new PatientRecord("Sylvie", "06/29/2003");

    bst.addPatientRecord(patient1);
    bst.addPatientRecord(patient2);
    bst.addPatientRecord(patient3);
    bst.addPatientRecord(patient4);
    bst.addPatientRecord(patient5);
    bst.addPatientRecord(patient6);
    bst.addPatientRecord(patient7);

    // Test: Get youngest patient, in this case "Amanda"

    if (bst.getRecordOfYoungestPatient() != patient6) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfOldestPatient() {
    PatientRecordTree bst = new PatientRecordTree();

    PatientRecord patient1 = new PatientRecord("Audrey", "04/10/2001");
    PatientRecord patient2 = new PatientRecord("Matt", "07/06/2001");
    PatientRecord patient3 = new PatientRecord("Dave", "02/06/1969");
    PatientRecord patient4 = new PatientRecord("Maria", "12/21/1969");
    PatientRecord patient5 = new PatientRecord("Crystal", "05/30/2001");
    PatientRecord patient6 = new PatientRecord("Amanda", "01/19/2006");
    PatientRecord patient7 = new PatientRecord("Sylvie", "06/29/2003");

    bst.addPatientRecord(patient1);
    bst.addPatientRecord(patient2);
    bst.addPatientRecord(patient3);
    bst.addPatientRecord(patient4);
    bst.addPatientRecord(patient5);
    bst.addPatientRecord(patient6);
    bst.addPatientRecord(patient7);

    // Test: Get oldest patient, in this case "Dave"

    if (bst.getRecordOfOldestPatient() != patient3) {
      return false;
    }

    return true;

  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // Calls all 5 test methods:
    System.out.println("testAddPatientRecordToStringSize(): " + testAddPatientRecordToStringSize());
    System.out.println("testAddPatientRecordAndLookup(): " + testAddPatientRecordAndLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetRecordOfYoungestPatient(): " + testGetRecordOfYoungestPatient());
    System.out.println("testGetRecordOfOldestPatient(): " + testGetRecordOfOldestPatient() + "\n");

    // Running test code provided:
    PatientRecordTree bst = new PatientRecordTree();
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + " Contents:");
    System.out.println(bst);
    bst.addPatientRecord(new PatientRecord("Norah", "11/23/1985"));
    bst.addPatientRecord(new PatientRecord("George", "5/27/1943"));
    System.out.println("*****************************************************");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + " Contents:");
    System.out.println(bst);
    bst.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));
    System.out.println("*****************************************************");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + " Contents:");
    System.out.println(bst);
    System.out.println("Oldest patient: " + bst.getRecordOfOldestPatient());
    System.out.println("Youngest patient: " + bst.getRecordOfYoungestPatient());
    bst.addPatientRecord(new PatientRecord("William", "6/4/1998"));
    bst.addPatientRecord(new PatientRecord("Sarah", "1/2/1945"));
    System.out.println("*****************************************************");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + " Contents:");
    System.out.println(bst);
    System.out.println("Oldest patient: " + bst.getRecordOfOldestPatient());
    System.out.println("Youngest patient: " + bst.getRecordOfYoungestPatient());
    bst.addPatientRecord(new PatientRecord("Andrew", "4/20/2019"));
    bst.addPatientRecord(new PatientRecord("Tom", "1/2/1935"));
    bst.addPatientRecord(new PatientRecord("Sam", "9/12/2003"));
    bst.addPatientRecord(new PatientRecord("Emily", "2/28/2020"));
    System.out.println("*****************************************************");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + " Contents:");
    System.out.println(bst);
    System.out.println("Oldest patient: " + bst.getRecordOfOldestPatient());
    System.out.println("Youngest patient: " + bst.getRecordOfYoungestPatient());
    System.out.println("*****************************************************");
    try {
      System.out.println("Lookup query: search for the patient who’s born on 9/12/2003.");
      System.out.println("Search Results: " + bst.lookup("9/12/2003"));
      System.out.println("Lookup query: search for the patient who’s born on : 1/10/2000.");
      System.out.println("Search Results: " + bst.lookup("1/10/2000"));
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }
}
