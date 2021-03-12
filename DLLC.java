
class play {
    public static void main(String[] args) {

        System.out.println("\n-҉-҉ Program Double Linked List Circular untuk Tugas nomor 1 ҉-҉-");

        DLLC hmm = new DLLC();


        // Menambahkan node baru dengan data 40, 20, 10 dari depan
        System.out.println("\nMenambahkan node baru dengan data 40, 20, 10 dari depan");
        hmm.addFirst(80);
        hmm.addFirst(new Node(90));
        hmm.addFirst(new Node());
        hmm.printDLLC();

        // Menambahkan node baru dengan data 60, 80, 90 dari belakang
        System.out.println("\nMenambahkan node baru dengan data 60, 80, 90 dari belakang");
        hmm.addLast(new Node());
        hmm.addLast(new Node());
        hmm.addLast(new Node());
        hmm.printDLLC();

        // Hapus node depan
        System.out.println("\nMenghapus node depan");
        hmm.removeFirst();
        hmm.printDLLC();

        // Hapus node belakang
        System.out.println("\nMenghapus node belakang");
        hmm.removeLast();
        hmm.printDLLC();

        // Melihat data
        System.out.println("\nMelihat data");
        hmm.printDLLC();

    }
}


class DLLC {
    Node head, tail;
    int size = 0;

    DLLC() {
    }

    DLLC(Node input) {
        this.head = this.tail = input;
        input.next = input;
        input.prev = input;
        size++;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Node input) {
        if (isEmpty()) {
            input.next = input;
            input.prev = input;
            this.head = this.tail = input;
        } else {
            input.next = head;
            input.prev = tail;
            this.head.prev = input;
            this.tail.next = input;
            this.head = input;
        }
        size++;
    }

    public void addLast(Node input) {
        if (isEmpty()) {
            input.next = input;
            input.prev = input;
            this.head = this.tail = input;
        } else {
            input.next = this.head;
            input.prev = this.tail;
            this.head.prev = input;
            this.tail.next = input;
            this.tail = input;
        }
        size--;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NullPointerException();
        } else if (size == 1)
            this.head = this.tail = null;
        else {
            this.tail.next = this.head.next;
            this.head.next.prev = this.tail;
            this.head = this.head.next;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NullPointerException();
        } else if (size == 1) {
            this.head = this.tail = null;
        } else {
            this.tail.prev.next = this.head;
            this.head.prev = this.tail.prev;
            this.tail = this.tail.prev;
        }
        size--;
    }


    public void printDLLC() {
        Node temp = head;
        System.out.println("{ ");
        int i = 1;
        if (!isEmpty()) {
            while (i <= size) {
                System.out.println(temp.data);
                temp = temp.next;
                i++;
            }
        }
        System.out.println(" }");
    }

}


class Node{
    Node next,prev;
    Object data;
}
