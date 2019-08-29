package com.java.springboot.utils;

import com.java.springboot.user.User;
import com.java.springboot.user.UserEntity;

public class UserUtils {

	public static User toUserModel(UserEntity entity) {
		return User.builder().id(entity.getId()).username(entity.getUsername()).password(entity.getPassword()).build();
	}

	public static UserEntity toUserEntity(User model) {
		return UserEntity.builder().username(model.getUsername()).password(model.getPassword()).build();
	}

}
