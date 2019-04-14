package com.example.user.policeassistant;


//This information will be saved into Firebase database


import android.location.Address;

public class Information {



    public String Title,CriminalsName,FathersName,MothersName,PresentAdd,PermanentAdd,Description,Rewards,Date;

    public String FullName,Password,Username,Email,District,Phone;

    public Information() {

    }

    public Information(String Title,String CriminalsName,String FathersName,String MothersName,String PresentAddress,String PermanentAddress,String Description,String rewards,String mydate)
    {
        this.Title=Title;
        this.CriminalsName=CriminalsName;
        this.FathersName=FathersName;
        this.MothersName=MothersName;
        this.PresentAdd=PresentAddress;
        this.PermanentAdd=PermanentAddress;
        this.Description=Description;
        this.Rewards=rewards;
        this.Date=mydate;


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
