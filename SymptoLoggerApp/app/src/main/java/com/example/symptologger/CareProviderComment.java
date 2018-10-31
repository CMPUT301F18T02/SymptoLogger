package com.example.symptologger;

import java.util.Date;

class CareProviderComment {
    
    /*Should contain something so that it has the user ID or the actual class of the care provider? So it can know who made this comment?*/ 
    CareProviderComment(Date date, String comment){
	setDate(date);
	setComment(comment);        
    }

   public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

