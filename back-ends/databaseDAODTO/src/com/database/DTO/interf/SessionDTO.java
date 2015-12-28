package com.database.DTO.interf;

import com.database.container.SessionContainer;
import com.database.logic.Session;

/**
 * Created by user on 25.12.15.
 */
public interface SessionDTO {
    String convertToJsonstring(Session session);
    String convertToJsonstring(SessionContainer model);
    SessionContainer convertToContainer(String json);
    Session convertToObject(String json);
}
