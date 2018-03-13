package com.totogp.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = true)
@Table(name = "rider", uniqueConstraints = @UniqueConstraint(columnNames = { "firstname", "lastname" }))
@NamedQueries(value = { @NamedQuery(name = Rider.GET_BY_NUMBER, query = "select r from Rider r where r.number = ?1") })
public class Rider {
	public static final String GET_BY_NUMBER = "Rider.GET_BY_NUMBER";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "firstname", nullable = true, length = 30)
	private String firstname;

	@Column(name = "lastname", nullable = true, length = 30)
	private String lastname;

	@Column(name = "nickname", nullable = true, length = 30)
	private String nickname;

	@Column(name = "twitterUser", nullable = true, length = 255, unique = true)
	private String twitterUser;

	@Column(name = "twitterHashtags", nullable = true, length = 255)
	private String twitterHashtags;

	@Column(name = "championship_homepage_url", nullable = true, length = 255)
	private String championshipHomepageUrl;

	@Column(name = "picture_url", nullable = true, length = 255)
	private String pictureUrl;

	@OneToOne(fetch = FetchType.LAZY)
	private Country country;

	@Column(name = "number", nullable = false)
	private Integer number;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Rider other = (Rider) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	public String getChampionshipHomepageUrl() {
		return championshipHomepageUrl;
	}

	public Country getCountry() {
		return country;
	}

	public String getFirstname() {
		return firstname;
	}

	public Integer getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public Integer getNumber() {
		return number;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public String getTwitterHashtags() {
		return twitterHashtags;
	}

	public String getTwitterUser() {
		return twitterUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	public void setChampionshipHomepageUrl(final String championshipHomepageUrl) {
		this.championshipHomepageUrl = championshipHomepageUrl;
	}

	public void setCountry(final Country country) {
		this.country = country;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public void setNickname(final String nickname) {
		this.nickname = nickname;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	public void setPictureUrl(final String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public void setTwitterHashtags(final String twitterHashtags) {
		this.twitterHashtags = twitterHashtags;
	}

	public void setTwitterUser(final String twitterUser) {
		this.twitterUser = twitterUser;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
}
