/**
 * This class models a patient record node in a binary search tree.
 *
 */
public class PatientRecordNode {
  private PatientRecord data; // data field which represents a patient within this PatientNode.
  private PatientRecordNode rightChild; // reference to the right child of this PatientNode.
  private PatientRecordNode leftChild; // reference to the left child of this PatientNode.

  /**
   * Creates a new PatientNode with given data and null for both left and right children
   * 
   * @param data reference to the data of this PatientNode
   */
  public PatientRecordNode(PatientRecord data) {
    this.data = data;
  }

  /**
   * Gets the right child of this PatientNode
   * 
   * @return the rightChild of this PatientNode
   */
  public PatientRecordNode getRightChild() {
    return rightChild;
  }

  /**
   * Sets the right child of this PatientNode
   * 
   * @param rightChild the rightChild to set
   */
  public void setRightChild(PatientRecordNode rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Gets the left child of this PatientNode
   * 
   * @return the leftChild of this PatientNode
   */
  public PatientRecordNode getLeftChild() {
    return leftChild;
  }

  /**
   * Sets the left child of this PatientNode
   * 
   * @param leftChild the leftChild to set
   */
  public void setLeftChild(PatientRecordNode leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Returns the patient record of this PatientNode
   * 
   * @return the data of this binary node.
   */
  public PatientRecord getPatientRecord() {
    return data;
  }



}
