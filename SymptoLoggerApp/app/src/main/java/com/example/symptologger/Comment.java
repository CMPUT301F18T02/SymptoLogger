package com.example.symptologger;

import java.util.Date;

public interface Comment {
    void setDate(Date date);

    Date getDate();

    User getUser();

    //void setUser(User user);

    String getComment();

    void setComment(String comment);
}
