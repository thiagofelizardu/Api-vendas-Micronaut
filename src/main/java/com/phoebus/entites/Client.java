package com.phoebus.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Entity(name = "client")
@RequiredArgsConstructor
public class Client {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
