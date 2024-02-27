package Social;

public class Account {
	private String nickname;
	private int numFollowers;
	private int numFollowing;
	private int numPosts;
	
	public Account(String nickname, int numFollowers, int numFollowing, int numPosts) {
		this.nickname = nickname;
		this.numFollowers = numFollowers;
		this.numFollowing = numFollowing;
		this.numPosts = numPosts;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getNumFollowers() {
		return numFollowers;
	}
	
	public void setNumFollowers(int numFollowers) {
		this.numFollowers = numFollowers;
	}
	
	public int getNumFollowing() {
		return numFollowing;
	}
	
	public void setNumFollowing(int numFollowing) {
		this.numFollowing = numFollowing;
	}
	
	public int getNumPosts() {
		return numPosts;
	}
	
	public void setNumPosts(int numPosts) {
		this.numPosts = numPosts;
	}
	
	@Override
	public String toString() {
		return "Account [nickname: " + nickname + ", " + numFollowers + " followers, " + numFollowing
				+ " seguiti, " + numPosts + " post]";
	}
	
}
