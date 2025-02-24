package com.dduongdev.dtos;

public class UnfollowRequest {
	private int targetUserId;  

    public int getTargetUserId() { 
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {  
        this.targetUserId = targetUserId;
    }
}
