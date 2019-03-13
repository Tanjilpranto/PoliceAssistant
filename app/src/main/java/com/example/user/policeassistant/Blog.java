package com.example.user.policeassistant;

public class Blog {

    private String Title;
    private String Description;
    private String Dist;
    private String Reward;
    private String Name;
    private String Father;


    public Blog(String title, String description, String dist, String reward,String name,String father) {
        Title = title;
        Description = description;
        Dist = dist;
        Reward = reward;
        Name=name;
        Father=father;
    }

    public String getTitle() {
        return Title;
    }

    public String getName() {
        return Name;
    }

    public String getFather() {
        return Father;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDist() {
        return Dist;
    }

    public void setDist(String dist) {
        Dist = dist;
    }

    public String getReward() {
        return Reward;
    }

    public void setReward(String reward) {
        Reward = reward;
    }

    public Blog(){

    }


}
