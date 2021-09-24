package com.example.myapplication.models;

import java.util.List;

public class ParentRelationData {

    public Relations parentDataList;

    public Relations getParentDataList() {
        return parentDataList;
    }

    public ParentRelationData(Relations parentDataList) {
        this.parentDataList = parentDataList;
    }

    public void setParentDataList(Relations parentDataList) {
        this.parentDataList = parentDataList;
    }
}
