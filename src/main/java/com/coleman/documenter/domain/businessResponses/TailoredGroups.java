package com.coleman.documenter.domain.businessResponses;

import com.coleman.documenter.domain.group.Group;

import java.util.List;

public class TailoredGroups {
    private List<Group> myGroups;
    private List<Group> otherGroups;

    public TailoredGroups(List<Group> myGroups, List<Group> otherGroups) {
        this.myGroups = myGroups;
        this.otherGroups = otherGroups;
    }

    public List<Group> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<Group> myGroups) {
        this.myGroups = myGroups;
    }

    public List<Group> getOtherGroups() {
        return otherGroups;
    }

    public void setOtherGroups(List<Group> otherGroups) {
        this.otherGroups = otherGroups;
    }
}
