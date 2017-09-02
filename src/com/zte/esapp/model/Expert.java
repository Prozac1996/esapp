package com.zte.esapp.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/23.
 */
public class Expert implements Serializable {

    private UUID id;
    private String expertName;
    private int expertAge;
    private int expertSex;
    private String expertIntro;
    private String expertPicture;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public int getExpertAge() {
        return expertAge;
    }

    public void setExpertAge(int expertAge) {
        this.expertAge = expertAge;
    }

    public int getExpertSex() {
        return expertSex;
    }

    public void setExpertSex(int expertSex) {
        this.expertSex = expertSex;
    }

    public String getExpertIntro() {
        return expertIntro;
    }

    public void setExpertIntro(String expertIntro) {
        this.expertIntro = expertIntro;
    }

    public String getExpertPicture() {
        return expertPicture;
    }

    public void setExpertPicture(String expertPicture) {
        this.expertPicture = expertPicture;
    }
}
