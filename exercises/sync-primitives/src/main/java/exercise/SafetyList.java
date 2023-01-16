package exercise;

class SafetyList {
    // BEGIN
    private int[] list = new int[10000];
    private int size = 0;

    public boolean add(int e) {
        // обязательно нужно сихронизировать операции изменения, иначе
        // фактический размер 'size' может быть меньше количества элементов в 'list'
        // т.к. одновременно 2 потока могут вставить элементы в list, но size может
        // увеличиться только на 1 элемент, т.к. поток, перед выполнением сначала скопирует себе в память
        // 'size', вставит в список и увеличит размер 'size', значение которой возьмёт из памяти
        // когда 2-й поток уже этот 'size' мог изменить. В итоге 'size' перепишется меньшим значением по сравнению с
        // текущим. Из-за этого 'size' в результате будет меньше
        synchronized (this) {
            list[size] = e;
            size++;
        }
        return true;
    }

    public int get(int index) {
        return list[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
