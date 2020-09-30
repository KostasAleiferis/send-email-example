package com.kostas.sendemailexample;


import com.sun.istack.internal.NotNull;

public class FeedBack {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
