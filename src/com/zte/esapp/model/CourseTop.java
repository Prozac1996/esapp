package com.zte.esapp.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/18.
 */
public class CourseTop implements Serializable {
    private UUID courseId;
    private String courseCover;
    private String courseName;
    private UUID expertId;
    private int courseLength;
    private int courseNum;
    private int coursePeople;
    private float coursePrice;
    private String courseCreateTime;
    private String expertName;

    public CourseTop(UUID courseId, String courseCover, String courseName, UUID expertId, int courseLength, int courseNum, int coursePeople, float coursePrice, String courseCreateTime, String expertName) {
        this.courseId = courseId;
        this.courseCover = courseCover;
        this.courseName = courseName;
        this.expertId = expertId;
        this.courseLength = courseLength;
        this.courseNum = courseNum;
        this.coursePeople = coursePeople;
        this.coursePrice = coursePrice;
        this.courseCreateTime = courseCreateTime;
        this.expertName = expertName;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public UUID getExpertId() {
        return expertId;
    }

    public void setExpertId(UUID expertId) {
        this.expertId = expertId;
    }

    public int getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(int courseLength) {
        this.courseLength = courseLength;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getCoursePeople() {
        return coursePeople;
    }

    public void setCoursePeople(int coursePeople) {
        this.coursePeople = coursePeople;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseCreateTime() {
        return courseCreateTime;
    }

    public void setCourseCreateTime(String courseCreateTime) {
        this.courseCreateTime = courseCreateTime;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }
}