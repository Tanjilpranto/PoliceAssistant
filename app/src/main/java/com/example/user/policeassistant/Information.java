package com.example.user.policeassistant;


//This information will be saved into Firebase database


public class Information {



    public  String date;
    public String Title,Description,Dist,Name,Father,reward;

    public String FullName,Password,Username,Email,District,Phone;

    public Information() {

    }

    public Information(String Title, String Description,String District,String name,String date,String father,String reward)
    {
        this.Title=Title;
        this.Description=Description;
        this.Dist=District;
        this.Name=name;
        this.date=date;
        this.Father=father;
        this.reward=reward;

    }

    public Information(String Name, String password, String username, String email, String district,String phone)
    {
        FullName=Name;
        Password=password;
        Username=username;
        Email=email;
        District=district;
        Phone=phone;
    }


}
