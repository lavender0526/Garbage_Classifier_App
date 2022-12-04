package com.example.sa.store;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserStore {
    private static UserStore instance;
    private int userId;
    private String username;
    private String lastName;
    private String name;
    private String email;
    private String firebaseToken;
    private String wallet;

    private UserStore() {
    }

    public static UserStore getInstance() {
        if (instance == null) {
            synchronized (UserStore.class) {
                if (instance == null) {
                    instance = new UserStore();
                }
            }
        }
        return instance;
    }

    public void setUserInfo(
            int userId,
            String username,
            String lastName,
            String name,
            String email,
            String firebaseToken,
            String wallet
    ) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
        this.name = name;
        this.email = email;
        this.firebaseToken = firebaseToken;
        this.wallet = wallet;
    }
}
