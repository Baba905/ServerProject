package com.example.Server.model;

import com.example.Server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(unique = true)
    @NotEmpty(message  ="IP address cannot be empty or null")
    private  String ipAddress;
    private  String name;
    private  String memory;
    private  String type;
    private  String imageUrl;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
