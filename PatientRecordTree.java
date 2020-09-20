//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Patient Record System Tree
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
 * This class implements a binary search tree (BST) which stores a set of patient records. The left
 * subtree contains the patient records of all the patients who are older than the patient who's
 * PatientRecord is stored at a parent node. The right subtree contains the patient records of all
 * the patients who are younger than the patient who's PatientRecord is stored at a parent node.
 *
 * @author Matt Thompson
 */
public class PatientRecordTree {
  private PatientRecordNode root; // Root of this binary search tree
  private int size; // Total number of patient records stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PatientRecordTree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.size == 0 && this.root == null) { // If size is zero and root is null
      return true;
    }

    return false;
  }

  /**
   * Returns the number of patient records stored in this BST.
   * 
   * @return the size of this PatientRecordTree
   */
  public int size() {
    return this.size;
  }

  /**
   * Recursive helper method to add a new PatientRecord to a PatientRecordTree rooted at current.
   * 
   * @param current   The "root" of the subtree we are inserting newRecord into.
   * @param newRecord The PatientRecord to be added to a BST rooted at current.
   * @return true if the newRecord was successfully added to this PatientRecordTree, false otherwise
   */
  public static boolean addPatientRecordHelper(PatientRecord newRecord, PatientRecordNode current) {
    if (newRecord.compareTo(current.getPatientRecord()) == 0) { // If newRecord is already in the
                                                                // record
      return false;
    } else if (newRecord.compareTo(current.getPatientRecord()) == 1) { // if newRecord contains a
                                                                       // younger patient
      if (current.getRightChild() != null) { // If not null, keep searching through the tree
        addPatientRecordHelper(newRecord, current.getRightChild());
      } else { // Set it to be the right child of the current node
        current.setRightChild(new PatientRecordNode(newRecord));
      }

      return true;
    } else if (newRecord.compareTo(current.getPatientRecord()) == -1) { // if newRecord contains an
                                                                        // older patient
      if (current.getLeftChild() != null) { // If not null, keep searching thorugh the tree
        addPatientRecordHelper(newRecord, current.getLeftChild());
      } else { // Set it to be the left child of the current node
        current.setLeftChild(new PatientRecordNode(newRecord));
      }

      return true;
    }

    return false;
  }

  /**
   * Adds a new PatientRecord to this PatientRecordTree
   * 
   * @param newRecord a new PatientRecord to add to this BST.
   * @return true if the newRecord was successfully added to this BST, and returns false if there is
   *         a match with this PatientRecord already already stored in this BST.
   */
  public boolean addPatientRecord(PatientRecord newRecord) {
    if (isEmpty()) { // Add newRecord to an empty PatientRecordTree
      this.root = new PatientRecordNode(newRecord);
      this.size++;

      return true;
    } else if (!addPatientRecordHelper(newRecord, this.root)) { // Patient has a match in BST
      return false;
    } else { // Add new patient to BST
      addPatientRecordHelper(newRecord, this.root);
      this.size++;

      return true;
    }
  }


  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PatientRecordTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current PatientRecordNode within this BST.
   * @return a String representation of all the PatientRecords stored in the sub-tree
   *         PatientRecordTree rooted at current in increasing order with respect to the patients
   *         dates of birth. Returns an empty String "" if current is null.
   */
  public static String toStringHelper(PatientRecordNode current) {
    String bst = ""; // String value to be returned

    // If current is not null, it uses in order search and recurs to look for all the patients names
    if (current != null) {
      bst += toStringHelper(current.getLeftChild()); // Gets left side of root (older people)
      bst += (current.getPatientRecord().toString() + "\n"); // Gets current (the root)
      bst += toStringHelper(current.getRightChild()); // Gets right side of root (younger people)
    }

    return bst;
  }

  /**
   * Returns a String representation of all the PatientRecords stored within this BST in the
   * increasing order, separated by a newline "\n". For instance: "Sarah(1/2/1935)" + "\n" +
   * "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n" + "Norah(11/23/1985)" + "\n" +
   * "William(6/4/1998)" + "\n" + "Nancy(9/12/2003)" + "\n" + "Sam(4/20/2019)" + "\n"
   * 
   * @return a String representation of all the PatientRecords stored within this BST sorted in an
   *         increasing order with respect to the dates of birth of the patients (i.e. from the
   *         oldest patient to the youngest patient). Returns an empty string "" if this BST is
   *         empty.
   */
  public String toString() {
    String patientsRecords = ""; // String value to be returned

    // If it is null, return the empty string
    if (this.root == null) {
      return patientsRecords;
    }

    PatientRecordNode current = this.root; // Sets current equal to the root
    patientsRecords += toStringHelper(current); // Appends what toStringHelper() returned

    return patientsRecords;
  }

  /**
   * Search for a patient record (PatientRecord) given the date of birth as lookup key.
   * 
   * @param date a String representation of the date of birth of a patient in the format mm/dd/yyyy
   * @return the PatientRecord of the patient born on date.
   * @throws a NoSuchElementException with a descriptive error message if there is no PatientRecord
   *           found in this BST having the provided date of birth
   */
  public PatientRecord lookup(String date) {
    PatientRecord findRecord = new PatientRecord("", date);
    return this.lookupHelper(findRecord, root);
  }

  /**
   * Recursive helper method to lookup a PatientRecord given a reference PatientRecord with the same
   * date of birth in the subtree rooted at current
   * 
   * @param findRecord a reference to a PatientRecord target we are lookup for a match in the BST
   *                   rooted at current.
   * @param current    "root" of the subtree we are looking for a match to findRecord within it.
   * @return reference to the PatientRecord stored stored in this BST which matches findRecord.
   * @throws NoSuchElementException with a descriptive error message if there is no patient record
   *                                whose date of birth matches date, stored in this BST.
   */
  private PatientRecord lookupHelper(PatientRecord findRecord, PatientRecordNode current) {
    // If current is null
    if (current == null) {
      throw new NoSuchElementException("No search results.");
    }

    // If findRecord matches with current
    if (findRecord.compareTo(current.getPatientRecord()) == 0) {
      return current.getPatientRecord();
    }

    // If findRecord is younger than current
    if (findRecord.compareTo(current.getPatientRecord()) == 1) {
      return lookupHelper(findRecord, current.getRightChild());
    }

    // If findRecord is older than current
    findRecord.compareTo(current.getPatientRecord());

    return lookupHelper(findRecord, current.getLeftChild());
  }

  /**
   * Computes and returns the height of this BST, counting the number of nodes (PatientRecordNodes)
   * from root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return height of the subtree rooted at current, counting the number of PatientRecordNodes
   */
  public static int heightHelper(PatientRecordNode current) {
    if (current == null) { // If empty, returns 0
      return 0;
    } else {
      int leftSide = heightHelper(current.getLeftChild()); // Recursively calls left until finds max
                                                           // depth on left side
      int rightSide = heightHelper(current.getRightChild()); // Recusively calls right until finds
                                                             // max depth on right side

      if (leftSide > rightSide) { // If left is more than right, add one to count root
        return (leftSide + 1);
      } else { // If right is more than left, add one to count root
        return (rightSide + 1);
      }
    }
  }


  /**
   * Returns the PatientRecord of the youngest patient in this BST.
   * 
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  public PatientRecord getRecordOfYoungestPatient() {
    if (this.root == null) { // If BST is empty, return null
      return null;
    }

    PatientRecordNode ptr = this.root; // Pointer of the BST's root

    // While the ptr is not null, keeps calling the right child until the right-most is found.
    while (ptr.getRightChild() != null) {
      PatientRecordNode temp = ptr; // Temp value
      ptr = temp.getRightChild();
    }

    return ptr.getPatientRecord();
  }

  /**
   * Returns the PatientRecord of the oldest patient in this BST.
   * 
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  public PatientRecord getRecordOfOldestPatient() {
    if (this.root == null) { // If BST is empty, return null
      return null;
    }

    PatientRecordNode ptr = this.root; // Pointer of the BST's root

    // While the ptr is not null, keeps calling the left child until the left-most is found.
    while (ptr.getLeftChild() != null) {
      PatientRecordNode temp = ptr; // Temp value
      ptr = temp.getLeftChild();
    }

    return ptr.getPatientRecord();
  }

}
