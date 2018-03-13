
package com.totogp.application.dto;

public class RiderRP {

	private Integer id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String nickname;
	private String twitterUser;
	private String twitterHashtags;
	private String championshipHomepageUrl;
	private String pictureUrl;
	private String country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public RiderRP() {
		super();
	}

	public RiderRP(Integer id, String name, String nickname, String twitterUser, String twitterHashtags,
			String championshipHomepageUrl, String pictureUrl, String country) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.twitterUser = twitterUser;
		this.twitterHashtags = twitterHashtags;
		this.championshipHomepageUrl = championshipHomepageUrl;
		this.pictureUrl = pictureUrl;
		this.country = country;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(String twitterUser) {
		this.twitterUser = twitterUser;
	}

	public String getTwitterHashtags() {
		return twitterHashtags;
	}

	public void setTwitterHashtags(String twitterHashtags) {
		this.twitterHashtags = twitterHashtags;
	}

	public String getChampionshipHomepageUrl() {
		return championshipHomepageUrl;
	}

	public void setChampionshipHomepageUrl(String championshipHomepageUrl) {
		this.championshipHomepageUrl = championshipHomepageUrl;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
