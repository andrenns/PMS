package com.example.tadeu17.dronetrack;
import java.io.Serializable;
import java.sql.Blob;

public class listItem implements Serializable {

    private String userLI;
    private String descriptionLI;
    private String locationLI;
    private String imgUrl;
    private boolean checked;
    private Blob imgBlob;
    private int user_id;
    private byte[] imgB;



    public listItem(int user_id,String userLI, String locationLI, String imgUrl){

        this.user_id=user_id;
        this.userLI=userLI;
        this.locationLI=locationLI;
        this.imgUrl=imgUrl;


    }

    public listItem(int user_id, byte[] imgB, String locationLI ) {
        this.locationLI = locationLI;
        this.imgB = imgB;
        this.user_id = user_id;
    }

    public String getUserLI() {
        return userLI;
    }

    public String getDescriptionLI() {
        return descriptionLI;
    }

    public void setDescriptionLI(String descriptionLI) {
        this.descriptionLI = descriptionLI;
    }

    public String getLocationLI() {
        return locationLI;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public boolean isSelected() {
        return checked;
    }

    public void setSelected(boolean checked) {
        this.checked = checked;
    }

    public byte[] getImgB() {
        return imgB;
    }

    public void setImgB(byte[] imgB) {
        this.imgB = imgB;
    }

    public Blob getImgBlob() {
        return imgBlob;
    }

    public void setImgBlob(Blob imgBlob) {
        this.imgBlob = imgBlob;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



}
