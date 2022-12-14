public class UsersArrayList implements UsersList {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private User[]  storage;
    private int     capacity;
    private int     size;

    public UsersArrayList() {
        this.storage = new User[10];
        this.capacity = 10;
        this.size = 0;
    }

    public void addUser(User user) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == user.getId()) {
                throw new UserExistException("Exception: User already exist - " + user);
            }
        }

        if (size == capacity) {
            User[] tmp = storage;
            storage = new User[this.capacity * 2];

            for (int i = 0; i < capacity; i++) {
                storage[i] = tmp[i];
            }
            this.capacity *= 2;
        }
        storage[size] = user;
        size++;
        System.out.println(ANSI_GREEN +"User added: " + user.getName() + ANSI_RESET);
    }

    public User retrieveByID(int id) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == id) {
                return storage[i];
            }
        }
        throw new UserNotFoundException("Exception: User Not Found by id " + id);
    }

    public User retrieveByIndex(int index) {
        if (index >= size) {
            throw new WrongIndexException("Exception: User Not Found by index: " + index);
        }
        if (index < 0) {
            throw new WrongIndexException("Exception: Index less then zero: " + index);
        }
        return storage[index];
    }

    public int numberOfUsers() {
        return size;
    }

    @Override
    public String toString() {
        String str = "UsersArrayList:" +
                "capacity: " + this.capacity +
                ", size: " + this.size + "\n";

        for (int i = 0; i < this.size; i++) {
            str += "\t[" + i + "] " + storage[i] + "\n";
        }

        return str;
    }
}