package com.zte.esapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/20.
 */

public class CourseContent implements Serializable{
    UUID contentId;
    String contentTitle;
    String contentText;
    UUID courseId;
    String contentFile;
    short contentCostFlag;
    float contentCost;
    String contentFileLength;
    String contentDescription;
    String contentPicture;
    String contentData;

    public CourseContent(UUID contentId, String contentTitle, String contentText, UUID courseId, String contentFile, short contentCostFlag, float contentCost, String contentFileLength, String contentDescription, String contentPicture, String contentData) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.courseId = courseId;
        this.contentFile = contentFile;
        this.contentCostFlag = contentCostFlag;
        this.contentCost = contentCost;
        this.contentFileLength = contentFileLength;
        this.contentDescription = contentDescription;
        this.contentPicture = contentPicture;
        this.contentData = contentData;
    }

    public UUID getContentId() {
        return contentId;
    }

    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getContentFile() {
        return contentFile;
    }

    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
    }

    public short getContentCostFlag() {
        return contentCostFlag;
    }

    public void setContentCostFlag(short contentCostFlag) {
        this.contentCostFlag = contentCostFlag;
    }

    public float getContentCost() {
        return contentCost;
    }

    public void setContentCost(float contentCost) {
        this.contentCost = contentCost;
    }

    public String getContentFileLength() {
        return contentFileLength;
    }

    public void setContentFileLength(String contentFileLength) {
        this.contentFileLength = contentFileLength;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getContentPicture() {
        return contentPicture;
    }

    public void setContentPicture(String contentPicture) {
        this.contentPicture = contentPicture;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public static CourseContent fromJson(JSONObject obj) throws JSONException {
        UUID contentId = UUID.fromString(obj.getString("contentId"));
        String contentTitle = obj.getString("contentTitle");
        String contentText = obj.getString("contentText");
        UUID courseId = UUID.fromString(obj.getString("courseId"));
        String contentFile = obj.getString("contentFile");
        short contentCostFlag = Short.parseShort(obj.getString("contentCostFlag"));
        float contentCost = Float.parseFloat(obj.getString("contentCost"));
        String contentFileLength = obj.getString("contentFileLength");
        String contentDescription = obj.getString("contentDescription");
        String contentPicture = obj.getString("contentPicture");
        String contentCreateTime = obj.getString("contentCreateTime");
        return new CourseContent(contentId,contentTitle,contentText,courseId,contentFile,contentCostFlag,contentCost,contentFileLength,contentDescription,contentPicture,contentCreateTime);
    }
}
