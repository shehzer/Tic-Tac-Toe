/**
 * #251036612
 * @author Shehzer Naumani
 *
 */
public class Node {

    private Record record;      
    private Node nextNode;          

    public Node(Record record, Node nextNode){
        this.record = record;
        this.nextNode = null;
    }

    /**
     * Gets the next connected Node
     * @return next connected Node
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * Sets the next connected Node
     */
    public void setNext(Node next){
        this.nextNode = next;
    }

    /**
     * Returns the record in the Node
     * @return Record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Sets the record in the Node
     */
    public void setRecord(Record newRecord) {
        this.record = newRecord;
    }
}
