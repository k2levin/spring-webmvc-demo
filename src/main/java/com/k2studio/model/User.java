package com.k2studio.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min = 2, max = 8)
    private String name;

    @NotNull
    @Size(min = 2, max = 8)
    private String name2;

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

}
