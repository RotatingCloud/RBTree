/* 
Name: John Kwon
Student ID: 201060515
Course: COMP 282
Ticket: 7902
Assignment: Project 2 - RBTreeMap
*/

public class RBTreeMap<K extends Comparable<K>, V> {

	Node root = null;
	
	private static final boolean RED = true;
    private static final boolean BLACK = false;
    private int size = 0; 
    
    //RBTREE CONSTRUCTOR
    public RBTreeMap() {
        
        root = null;
	}
	
    //RBTREE METHODS
	public V get(K k) throws ClassCastException, NullPointerException {
		
		if(k == null) 
			
			throw new NullPointerException("get: key is null");
		
		K actual_key = (K) k;
		
		try {
			
			return root.nodeGet(actual_key);
		
		} catch (NullPointerException npe) {
			
			return null;
		}
	}

	public V put(K key, V val) {
		
	    System.out.println("put: " + key + "\n");
	    
		if(root == null) {
		
		    root = add(key, val, null);
		    balance(root);
		    
		} else {
		
		    System.out.println("put: root detected \n");

		    Node currentNode = root;
		    
		    while(currentNode != null) {
		        
		        int cmp = key.compareTo(currentNode.key);
                
		        if(cmp < 0) {
                
		            if(currentNode.left == null) {
	               
		                System.out.println("put: null node. adding node to left \n");
		                
		                currentNode.left = add(key, val, currentNode);
                        
		                System.out.println("current.left: " + currentNode.left.key + 
                                            "; current.left.parent: " + currentNode.left.parent.key + "\n");
		                
                        balance(currentNode.left);
		                currentNode = null;
	           
		            } else {
	               
		                System.out.println("put: current.left found switching current node \n");
		                
		                currentNode = currentNode.left;
                        
		                System.out.println("current: " + currentNode.key + 
		                                    "; parent: " + currentNode.parent.key + "\n");
		            }
	                 
		        } else if(cmp > 0) {
	                     
		            if(currentNode.right == null) {
                   
		                System.out.println("put: null node. adding node to right \n");
		                
		                currentNode.right = add(key, val, currentNode);
                        
		                System.out.println("current.right: " + currentNode.right.key + 
                                            "; current.right.parent: " + currentNode.right.parent.key + "\n");
		                
                        balance(currentNode.right);
		                currentNode = null;
               
		            } else {
                   
		                System.out.println("put: current.right found switching current node \n");
		                
		                currentNode = currentNode.right;
		                
		                System.out.println("current: " + currentNode.key + 
                                            "; parent: " + currentNode.parent.key + "\n");
		            }
		        
		        } else if(cmp == 0) {
		            
		            System.out.println("put: identical key found. replacing value \n");
		            currentNode.setValue(val);
		            currentNode = null;
		        }
		    }
		}
		
        return val;
	}
	
	private Node add(K key, V val, Node parent) {
	    
	    size++;
	    return new Node(key, val, RED, parent);
	}

    public boolean ContainsKey(K key) throws ClassCastException, NullPointerException {
        
        if(key == null)
            
            throw new NullPointerException("contains: key is null");
        
        try {
            
            V value = get(key);
            if(value == null) {
                 
                return false;
            
            } else {
                
                return true;
            }
        
        } catch(NullPointerException npe) {
            
            return false;
        }
    }
    
    public int size() {
        
        return size;
    }
    
    //UTILITY METHODS
    private void rotateLeft(Node n) {
    	
        if (n != null) {
            
            Node temp = n.right;
            
            n.right = temp.left;
            
            if (temp.left != null) {
                
                temp.left.parent = n;
            }
            
            temp.parent = n.parent;
            
            if (parentOf(n) == null) {
                
                root = temp;
                
            } else if (n.parent.left == n) {
                
                n.parent.left = temp;
                
            } else {
                
                n.parent.right = temp;
                
            }
            
            temp.left = n;
            n.parent = temp;
        }
    }
    
    private void rotateRight(Node n) {
        
        if (n != null) {
            
            Node temp = n.left;
            
            n.left = temp.right;

            if (temp.right != null) {
                
                temp.right.parent = n;
                
            }
            
            temp.parent = n.parent;
            
            if (n.parent == null) {
                
                root = temp;
                
            } else if (n.parent.right == n) {
                
                n.parent.right = temp;
                
            } else {
                
                n.parent.left = temp;
                
            }
            
            temp.right = n;
            n.parent = temp;

        }
    }
    
    private void balance(Node n) {
    
        if(n.parent == null) {
            
            System.out.println("bal: no parent found, must be root. root.color = black \n");
            n.color = BLACK;
            return;
        }
        
        if(n.parent.color == BLACK) {
            
            System.out.println("bal: parent is black. return \n");
            return;
        }
            
        Node parent = n.parent;
        Node grandParent = grandParentOf(n);
        Node uncle = uncleOf(n);
        
        if(uncle != null && uncle.color == RED) {
            
            parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED; 
            System.out.println("bal: uncle found and is red. doing recursive balance on grandpa \n");
            balance(grandParent);
            return;
        }
        
        if(n == parent.right && parent == grandParent.left) {
            
            System.out.println("bal: conditions for left rotation met. left rotation at parent \n");
            
            rotateLeft(parent);
            n = parent;
            parent = n.parent;
        
        } else if(n == parent.left && parent == grandParent.right) {
            
            System.out.println("bal: conditions for right rotation met. first right rotation at parent \n");
            
            rotateRight(parent);
            n = parent;
            parent = n.parent;
        }
        
        parent.color = BLACK;
        grandParent.color = RED;
        
        if(n == parent.left) {
            
            System.out.println("bal: second right rotation at grandparent \n");
            rotateRight(grandParent);
        
        } else { 
            
            System.out.println("bal: second left rotation at grandparent \n");
            rotateLeft(grandParent);
        }
    }
    
    public void test1() {
        
        System.out.println(printNode(root));
        System.out.println(printNode(root.left));
        System.out.println(printNode(root.right));
        System.out.println(printNode(root.right.left));
        System.out.println(printNode(root.right.right));
    }
    
    public void test2() {
        
        System.out.println(printNode(root));
        System.out.println(printNode(root.right));
        System.out.println(printNode(root.left));
        System.out.println(printNode(root.left.right));
        System.out.println(printNode(root.left.left));
    }
    
    public String printNode(Node n) {
        
        return "(" + n.key + "/" + n.value + "/" + 
                            n.color + ")" + "\n";
    }
    
    //GET METHODS
    private Node parentOf(Node n) {
        
        if(n == null) return null;
        
        else return n.parent;
    }

    private Node grandParentOf(Node n) {
        
        if(n.parent == null) return null;
        
        return n.parent.parent;
    }
    
    private Node uncleOf(Node n) {
        
        Node grandpa = null;
        
        if(n.parent != null)
            
            grandpa = n.parent.parent;
        
        if(grandpa == null) 
            
            return null;
        
        if(grandpa.left == n.parent)
            
            return grandpa.right;
        
        else
            
            return grandpa.left;
    }
    
    //NODE CLASS
	private class Node {
		 
		K key;
	    V value;
	    Node left;
	    Node right;
	    Node parent;
	    boolean color;

	    public Node(K k, V v, boolean c, Node p) {
	        
            key = k;
            value = v;
            color = c;
            parent = p;
        }
	
	    private V nodeGet(K k) throws NullPointerException{
            
            int diff = k.compareTo(key);
            
            if(diff == 0) {
                
                return value;
            
            } else if(diff < 0) {
                
                return left.nodeGet(k);
            
            } else {
                
                return right.nodeGet(k);
            }
        }
	    
	    public void setValue(V v) {
	        
	        value = v;
	    }
	    
	    public String toString() {
	        
	        return new String(key.toString() + "/" + 
	                            value.toString() + "/" + 
	                            color);
	    }
	}
}

