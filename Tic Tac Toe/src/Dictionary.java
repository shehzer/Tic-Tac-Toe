/**
 * #251036612
 * @author Shehzer Naumani
 *
 */
public class Dictionary implements DictionaryADT {

    private Node[] table;
    private int tableSize;
    private int elements;
    

    public Dictionary(int tableSize) {
        this.tableSize = tableSize;
        table = new Node[this.tableSize];
    }

    /**
     * Generates a hash key
     * @param config config to generate hashKeyFor
     * @return hash key
     */
	private int hashFunction(String config,int stringLength) {
		
		int constant = 10;
		
		// value of the first character of the string has been casted as an integer then Stored in a variable named value.
		int value= (int) config.charAt(0);

		// go through loop and calculate the hash code 
		//Determines the index of where the value is placed
		for (int i = 1; i < stringLength - 2; i++) {
			value = (value * constant + (int) config.charAt(i)) % this.tableSize;
		}

		// Returns the hash code to determine where in the table the node needs to be placed.
		return value;
	}

    /**
     * Inserts a given record in to the dictonary
     * @param record to insert
     * @return 1 if produces a collision, else 0
     * @throws Dictionary Thrown when config already in the dictionary
     */
    public int insert (Record record) throws DictionaryException {

    	// Determines the index in the nodeArray where the record configuration goes. 
    	//uses two arguments, the string configuration of record and the length of the string.
		int index = hashFunction(record.getConfig(),record.getConfig().length());

		// Declaring new Nodes named current and newNode
		Node current;
		Node newNode;
		
		// If the first element at the index of nodeArray is null then that index is empty 
		if (table[index] == null) {
			
			// Will create a new node containing the record passed in the parameter, 
			// with a pointer of null because its the first node
			newNode = new Node(record, null);
			
			// This will set the newNode to the first element at that index of nodeArray.
			table[index] = newNode;
			
			// Because there was no collision we will return 0.
			return 0;
		}
		
		//  index contains other Configuration objects,
		//So this else statement will run, this means a collision has occurred.
		else {
			
			// This will make the current node equal to the first element at table[index].
			current = table[index];
			
			//If the current nodes string configuration is not equal to the string configuration of record in the parameter then this if statement will run.
			if(!(current.getRecord().getConfig().equals(record.getConfig())) ) {
				
				// While the next node of current is not null then continue to traverse the linked list
				while(current.getNext()!=null) {
					
					// Will set the current to the next node
					current = current.getNext();
				}
				
				// Creates a new node containing the record passed in the parameter, with a pointer of null, (last node)
				newNode = new Node(record, null);
				
				// Sets the current nodes next node to the new node.
				current.setNext(newNode);
				
				// Returns 1 because there was a collision that occurred.
				return 1;
			}
			
			// This will throw an exception if the current Configuration object's string exists in the dictionary already and will send the Configuration record as an argument.
			else {
				throw new DictionaryException();
			}
		}

	}
	

    /**
     * Removes the record with the given hashkey config from the dictionary
     * @param config Config to remove
     * @throws DictionaryException If key is not found in the table
     */
    public void remove(String config) throws DictionaryException {
    		//hashkey which passes a string configuration and the length of the configuration to the hashfunction
        int hashKey = hashFunction(config, config.length());
        	//current node is equal to the node with a specified indiex
        Node currentNode = table[hashKey];
        //if this node is null throw an exception - config is not in the dictionary
        if (currentNode == null) {
        	throw new DictionaryException();
        }
        //while its not null get the configuration of the record and see if it equals the configuration in question
        while (currentNode != null) {
            if (currentNode.getRecord().getConfig().equals(config)) {
            	//remove it 
                table[hashKey] = currentNode.getNext();
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
    }

    /**
     * Returns a string stored in the dictionary for the given config
     * @param config Config to get from table
     * @return score
     */
    public int get(String config) {
    	//determines index in table where conifguration is
        int hashKey = hashFunction(config,config.length());
        //current nde is the 'head'
        Node currentNode = table[hashKey];
//traverse linked list and return the score
        while (currentNode != null) {
            if (currentNode.getRecord().getConfig().equals(config)) {
                return currentNode.getRecord().getScore();

            } else {
                currentNode = currentNode.getNext();
            }
        }
//if we find the string then return -1
        return -1;
    }

    /**
     * Returns the number of configs in the table
     * @return elements - number of records
     */
    public int numElements() {
        return elements;
    }


}
