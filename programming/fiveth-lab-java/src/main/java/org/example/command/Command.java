package org.example.command;


public interface Command {

    String execute(String[] args);


    String getName();


    String getDescription();
} 