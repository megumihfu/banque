package repositories;

import entities.BankAccount;
import java.util.HashMap;
import java.util.Map;

public class MemoryRepository {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(String key, BankAccount account) {
        accounts.put(key, account);
    }

    public BankAccount getAccount(String key) {
        return accounts.get(key);
    }

}
