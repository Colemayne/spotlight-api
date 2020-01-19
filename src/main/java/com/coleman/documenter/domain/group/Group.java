package com.coleman.documenter.domain.group;

import com.coleman.documenter.domain.application.Application;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="documenter_groups")
public class Group {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="group_name")
    private String groupName;
    @OneToMany(mappedBy = "group",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<GroupMembership> groupMembership;
    @OneToMany(mappedBy = "group",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<Application> applications;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<GroupMembership> getGroupMembership() {
        return groupMembership;
    }

    public void setGroupMembership(Set<GroupMembership> groupMembership) {
        this.groupMembership = groupMembership;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupMemberships=" + groupMembership +
                '}';
    }
}
