package com.coleman.documenter.domain.test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="test_items")
public class TestItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="tag_name")
    private String tagName;
    @Column(name="tag_reason")
    private String tagReason;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonBackReference(value="test_id")
    private Test test;

    public TestItem() {
    }

    public static TestItem of(String tagName, String tagReason, Test test) {
        TestItem t = new TestItem();
        t.setTagName(tagName);
        t.setTagReason(tagReason);
        t.setTest(test);
        return t;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagReason() {
        return tagReason;
    }

    public void setTagReason(String tagReason) {
        this.tagReason = tagReason;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "TestItem{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", tagReason='" + tagReason + '\'' +
                ", test=" + test +
                '}';
    }
}
