public class SingleLinkedList {

    public static void main(String[] args) {

        System.out.println("\n-҉-҉ Program LinkedList untuk Latihan nomor 1 ҉-҉-");

        LinkedList1 lili = new LinkedList1();

        lili.tambahDepan(new Node(100));
        lili.tambahBelakang(new Node(200));
        lili.tambahBelakang(new Node(300));
        lili.tambahBelakang(new Node(400));
        lili.cetakLinkedList1();

        // Tambahkan node baru dengan data 500 dari belakang
        System.out.println("\n1a. Menambahkan node baru dengan data 500 dari belakang");
        lili.tambahBelakang(new Node(500));
        lili.cetakLinkedList1();

        // Tambahkan node baru dengan data 50 dari depan
        System.out.println("\n1b. Menambahkan node baru dengan data 50 dari depan");
        lili.tambahDepan(new Node(50));
        lili.cetakLinkedList1();

        // Tambahkan node dengan data 250 setelah node 200
        System.out.println("\n1c. Menambahkan node baru dengan data 250 setelah node 200");
        lili.tambahSetelah(200, new Node(250));
        lili.cetakLinkedList1();

        // Hapus node depan
        System.out.println("\n2a. Menghapus node depan");
        lili.hapusDepan();
        lili.cetakLinkedList1();

        // Hapus node belakang
        System.out.println("\n2b. Menghapus node belakang");
        lili.hapusBelakang();
        lili.cetakLinkedList1();

        // Hapus node yang memiliki data 300
        System.out.println("\n2c. Menghapus node yang memiliki data 300");
        lili.hapusData(300);
        lili.cetakLinkedList1();

        // Akses semua data dari seluruh node
        // dari node yg paling depan ke belakang
        System.out.println("\n3.  Mengakses semua data dari seluruh node");
        lili.cetakLinkedList1();

        // Replace data
        System.out.println("\n4.  Mereplace data yang memiliki index 2 ke 350");
        lili.replace(2,350);
        lili.cetakLinkedList1();
    }
}

class LinkedList1 {

    Node head;
    Node tail;
    int size;
    private int maximumIndex;

    LinkedList1() {
        this.head = tail = null;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return this.size;
    }

    void tambahDepan(Node tambah) {
        if (isEmpty()) {
            head = tail = tambah;
        } else {
            tambah.pointer = head;
            head = tambah;
        }
        size++;
    }

    void tambahBelakang(Node tambah) {
        if (isEmpty()) {
            head = tail = tambah;
        } else {
            tail.pointer = tambah;
            tail = tail.pointer;
        }
        size++;
    }

    void tambahSetelah(Object key, Node tambah) {
        Node temp = head;
        do {
            if (temp.data.equals(key)) {
                tambah.pointer = temp.pointer;
                temp.pointer = tambah;
                size++;
                System.out.println("\t" + key + " berhasil ditambah....");
                break;
            }
            temp = temp.pointer;
        } while (temp != null);

    }

    void hapusDepan() {
        Node temp = head;
        if (!isEmpty()) {
            if (head == tail)
                head = tail = null;
            else {
                temp = temp.pointer;
                head = temp;
                temp = null;
            }
            size--;
        }
    }

    void hapusBelakang() {
        Node temp = head;
        if (!isEmpty()) {
            if (tail == head) {
                head = tail = null;
            } else {
                while (temp.pointer != tail) {
                    temp = temp.pointer;
                }
                temp.pointer = null;
                tail = temp;
                temp = null;
            }
            size--;
        }
    }

    void hapusData(Object key) {
        Node temp = head;
        if (!isEmpty()) {
            while (temp != null) {
                if (temp.pointer.data.equals(key)) {
                    temp.pointer = temp.pointer.pointer;
                    if (temp.pointer == null)
                        tail = temp;
                    break;
                } else if ((temp.data.equals(key)) && (temp == head)) {
                    this.hapusDepan();
                    break;
                }
                temp = temp.pointer;
            }
        }
        System.out.println("\t" + key + " berhasil dihapus....");
        size--;
    }

    void cetakLinkedList1() {
        Node temp = head;

        System.out.print("\tCurrent data: [ ");
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.pointer;
        }
        System.out.print("]");
        System.out.println("\n");
    }

    void checkIndex (int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index = " + index + " size = " + size);
    }

    public void replace(int index, Object theElement){
        checkIndex(index);
        Node input = new Node(theElement);
        Node temp = head;

        if(index == 0){
            input.pointer = head.pointer;
            head = null;
            head = input;
        } else if(index == maximumIndex){
            this.hapusBelakang();
            this.tambahBelakang((Node) theElement);
        } else{
            hapusIndex(index);
            tambahIndex(index, theElement);
        }
    }

    public Object get(int index) {
        checkIndex(index);
        Node currentNode = head;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.pointer;
        return currentNode.data;
    }
    public int indexOf(Object theElement) {
        Node currentNode = head;
        int index = 0;
        while (currentNode != null && !currentNode.data.equals(theElement)) {
            currentNode = currentNode.pointer;
            index++;
        }
        if (currentNode == null)
            return -1;
        else
            return index;
    }
    Object hapusIndex(int index) {
        checkIndex(index);
        Object removedElement;
        if (index == 0) {
            removedElement = head.data;
            head = head.pointer;
        } else {
            Node q = head;
            for (int i = 0; i < index - 1; i++)
                q = q.pointer;
            removedElement = q.pointer.data;
            q.pointer = q.pointer.pointer;
            tail = q;
        }
        size--;
        return removedElement;
    }
    public void tambahIndex(int index, Object theElement) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index = " + index + " size = " + size);
        if (index == 0)
            head = new Node(theElement, head);
        else {
            Node p = head;
            for (int i = 0; i < index - 1; i++)
                p = p.pointer;
            p.pointer = new Node(theElement, p.pointer);
        }
        size++;
    }

}

class Node{
    Object data;
    Node pointer;
    Node(Object data){
        this.data = data;
    }
    Node(Object data, Node pointer) {
        this.data = data;
        this.pointer = pointer;
    }
}
