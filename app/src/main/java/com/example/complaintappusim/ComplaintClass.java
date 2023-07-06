package com.example.complaintappusim;

public class ComplaintClass
{
    private String dataTitle;
    private String dataDesc;
    private String dataImage;
    private String key;
    private String status;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public  void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataDesc() {
        return dataDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataImage() {
        return dataImage;
    }
    public ComplaintClass(String dataTitle, String dataDesc, String dataImage, String email, String status) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataImage = dataImage;
        this.email = email;
        this.status = status;
    }
    public ComplaintClass(){
    }

}
