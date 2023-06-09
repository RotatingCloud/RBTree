public class Main {

    public static void main(String[] args) {

        RBTreeMap<Integer, Character> tree = new RBTreeMap<Integer, Character>();
        
        //insert root 
        System.out.println("[ADD ROOT]: \n");
        
        tree.put(22, 'b');
        
        //insert new left node
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD LEFT]: \n");
        
        tree.put(11, 'a');
         
        //insert new right node
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD RIGHT]: \n");
        
        tree.put(33, 'c');
        
        //left rotation simulation
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD 2 NODES TO RIGHT; LEFT ROTATION]: \n");
        
        tree.put(55, 'e');
        
        System.out.println("------------------------------------- \n");
        
        tree.put(44, 'd');
        
        System.out.println("------------------------------------- \n");
        System.out.println("[IDENTICAL KEY TEST]: \n");
        
        tree.put(44, 'f');
        
        tree.put(55, 'p');
        
        System.out.println("------------------------------------- \n");
        System.out.println("[TEST]: \n"); 
        
        System.out.println("print tree: \n");
        
        tree.test1();
        
        System.out.println("get(55): " + tree.get(55) + "\n"); //print 'p'
        System.out.println("get(66): " + tree.get(66) + "\n"); //print 'null'
        System.out.println("contains(55): " + tree.ContainsKey(55) + "\n"); //print true 
        System.out.println("contains(66): " + tree.ContainsKey(66) + "\n");//print false 
        System.out.println("size: " + tree.size() + "\n"); //print 5
         
        System.out.println("------------------------------------- \n");
        
        RBTreeMap<Integer, Character> tree2 = new RBTreeMap<Integer, Character>();

        // insert root
        System.out.println("[ADD ROOT]: \n");

        tree2.put(44, 'b');

        // insert new right node
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD RIGHT]: \n");

        tree2.put(55, 'a');

        // insert new left node
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD LEFT]: \n");

        tree2.put(33, 'c');

        // right rotation simulation
        System.out.println("------------------------------------- \n");
        System.out.println("[ADD 2 NODES TO LEFT; RIGHT ROTATION]: \n");

        tree2.put(11, 'e');

        System.out.println("------------------------------------- \n");

        tree2.put(22, 'd');
        
        System.out.println("------------------------------------- \n");
        System.out.println("[IDENTICAL KEY TEST]: \n");
        
        tree2.put(44, 'f');
        
        tree2.put(55, 'p');

        System.out.println("------------------------------------- \n");
        System.out.println("[TEST]: \n"); 
        System.out.println("print tree: \n");
        
        tree2.test2();

        System.out.println("get(55): " + tree2.get(55) + "\n"); // print 'p'
        System.out.println("get(66): " + tree2.get(66) + "\n"); // print 'null'
        System.out.println("contains(55): " + tree2.ContainsKey(55) + "\n"); // print true
        System.out.println("contains(66): " + tree2.ContainsKey(66) + "\n");// print false
        System.out.println("size: " + tree2.size() + "\n"); // print 5
    }
}
