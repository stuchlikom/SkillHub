package com.wildcodeschool.skillhub.entity;

public class Avatar {

    private Long avatarId;
    private byte[] avatar;
    
    public Avatar() {
	}
    
	public Avatar(Long avatarId, byte[] avatar) {
		this.avatarId = avatarId;
		this.avatar = avatar;
	}
	
	public Long getAvatarId() {
		return avatarId;
	}
	
	public void setAvatarId(Long avatarId) {
		this.avatarId = avatarId;
	}
	
	public byte[] getAvatar() {
		return avatar;
	}
	
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
