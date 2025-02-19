package com.example.OnlineBookStore.entity;

public enum Role {
    ADMIN, USER, GUEST;



    public static void main(String[] args) {
        Role role = Role.ADMIN;
        System.out.println(role.name()); // Should print "ADMIN"
    }
}


