package com.sept.javlets.userauth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "students")
public class AccountBean {

    @Id
    private String id;

    private String email;
    private String givenName;
    private String familyName;
    private String imageUrl;
    private boolean isMentor;

    private List<String> connections;
    private List<String> connectRequest;
    private List<String> connectSent;


    public AccountBean() {
    }

    public AccountBean(String id) {
        this.id = id;
        this.isMentor = false;

        // Connection management
        this.connections = new ArrayList<>();
        this.connectSent = new ArrayList<>();
        this.connectRequest = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean removeConnection(AccountBean connection) {
        return connections.remove(connection);
    }

    public boolean isMentor() {
        return isMentor;
    }

    public void setMentor(boolean isMentor) {
        this.isMentor = isMentor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return String.format("Student ID: %s, familyName: %s, GivenName: %s, Email: %s%n", id, familyName, givenName, email);
    }

    public List<String> getConnectRequest() {
        return connectRequest;
    }

    public void addConnectRequest(String id) {
        this.connectRequest.add(id);
    }

    public List<String> getConnectSent() {
        return connectSent;
    }

    public void addConnectSent(String id) {
        this.connectSent.add(id);
    }

    public void removeConnectRequest(String id) {
        this.connectRequest.remove(id);
    }

    public void removeConnectSent(String id) {
        this.connectSent.remove(id);
    }

    public List<String> getConnections() {
        return connections;
    }

    public void addConnection(String id) {
        this.connections.add(id);
    }

    public void removeConnection(String id) {
        this.connections.remove(id);
    }

}
