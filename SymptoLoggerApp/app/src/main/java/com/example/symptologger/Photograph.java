package com.example.symptologger;

import java.util.Date;

class Photograph {
    private URL photo;
    private Date date;
    private String description;

    Photograph(){
        setDate(new Date());
	setImage(new URL(""));
	setDescription("");
    }

    Photograph(Date date, URL photo, String description){
	setDate(date);
	setImage(photo);
	setDescription(description);        
    }

    public void setImage(URL url) {
        this.photo = url;
    }

    public URL getImage(){
        return this.photo;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

