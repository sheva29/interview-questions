/**
     * Calculates the beginning of a cycle for a given LinkedList that has a loop
     * @param node - initial node of LinkedList
     * @return The position of the node at which the cycle(loop) begins
*/
public LinkedList beginOfCycle(LinkedList node){

    LinkedList p1 = node;
    LinkedList p2 = node.next;

    boolean cycle = false
    while(p2.next != null) {

        if( p1 == p2) {
            cycle = true;
            break;

        }else {
            p1 = p1.next;
            p2 = p1.next;
        }     
    }

    if (cycle){
        p2 = node;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
    }

    return p1;
}

/**
     * Return the kth element from the end of a given LinkedList
     * @param node - initial node of LinkedList
     * @param kth - element from last of the list
     * @return current node at the kth position
*/
public LinkedList findKth(LinkedList node, int kth ){
    
    int kthp = 0;
    LinkedList p1 = node;
    LinkedList p2 = node;

    while (kthp < kth){
        p1 = p1.next;
        kthp++;
    }

    while( p1 != null){
        p1 = p1.next;
        p2 = p2.next;
    }

    return p2;
}

