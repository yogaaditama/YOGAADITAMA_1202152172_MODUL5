package com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.Model;

/**
 * Created by asusx453sa on 25/03/18.
 */

public class Activity {

    private long id;
    private String pvtodo;
    private String pvdescription;
    private String priority;

    public Activity(){

    }

    public Activity(String todo, String description, String priority){
        this.pvtodo = todo;
        this.pvdescription = description;
        this.priority = priority;
    }

    public Activity(int id, String todo, String description, String priority){
        this.id = id;
        this.pvtodo = todo;
        this.pvdescription = description;
        this.priority = priority;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodo() {
        return pvtodo;
    }

    public void setTodo(String todo) {
        this.pvtodo = todo;
    }

    public String getDescription() {
        return pvdescription;
    }

    public void setDescription(String description) {
        this.pvdescription = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
