package edu.iescamp.venteoRestApi.users.models.mappers;

import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setBornDate(userDTO.getBirthDate());
        user.setImageUrl(userDTO.getImageUrl());
        user.setRoleId(userDTO.getRoleId());

        return user;
    }
}
