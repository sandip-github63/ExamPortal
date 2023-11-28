package com.exam.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cId")
	private Long cId;

	@Column(length = 5000)
	private String title;

	@Column(length = 5000)
	private String discription;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quizzes = new LinkedHashSet<>();

	public Set<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Category() {
		super();
	}

	public Category(Long cId, String title, String discription) {
		super();
		this.cId = cId;
		this.title = title;
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "Category [cId=" + cId + ", title=" + title + ", discription=" + discription + ", quizzes=" + quizzes
				+ "]";
	}

}
