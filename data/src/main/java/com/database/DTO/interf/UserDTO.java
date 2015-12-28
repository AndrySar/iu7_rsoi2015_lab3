package com.database.DTO.interf;

import com.database.container.UserContainer;
import com.database.logic.User;

/**
 * Created by user on 25.12.15.
 */
public interface UserDTO {
    String convertToJsonstring(User user);
    String convertToJsonstring(UserContainer user);
    UserContainer convertToContainer(String json);
    User convertToObject(String json);
}
