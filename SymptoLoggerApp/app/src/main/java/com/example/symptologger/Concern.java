package com.example.symptologger;

class Concern {
    private String title;

    Concern(){
        this.title = null;
    }

    Concern(String title){
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
