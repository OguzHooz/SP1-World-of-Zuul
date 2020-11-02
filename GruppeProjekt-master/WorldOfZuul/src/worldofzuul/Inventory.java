package worldofzuul;

import java.util.HashMap;

public class Inventory {

    // Hashmap til opbevaring af skraldeobjekter i spillerens inventar
    public HashMap<String, Trash> trash;

    // Tilføj skrald fra rummet til spillerens inventar
    public boolean addTrash(Room room, Trash trash) {
        // Check om rummet indeholder skraldet
        if (room.trash.containsValue(trash)) {
            // Check om spilleren har plads
            if (this.trash.size() < 2) {
                this.trash.put(trash.toString(), trash);
                return true;
            } else {
                System.out.println("Du har ikke mere plads i hænderne!");
                return false;
            }
        }
        return false;
    }

    // Opret trash-hashmappet i constructeren
    public Inventory() {
        trash = new HashMap<>();
    }

    // Print alle navne på skrald i spillerens inventory
    public void printInventory() {
        System.out.println("-----Inventory-----");

        for (String trash : trash.keySet()) {
            System.out.println(trash);
        }

        System.out.println("-------------------");
    }
}
