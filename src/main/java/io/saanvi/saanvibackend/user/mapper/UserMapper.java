package io.saanvi.saanvibackend.user.mapper;


import io.saanvi.saanvibackend.user.dto.UserRequestDto;
import io.saanvi.saanvibackend.user.model.AuthProvider;
import io.saanvi.saanvibackend.user.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        if (dto == null) return null;
        return new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                AuthProvider.LOCAL
        );
    }

    public static UserRequestDto toDto(User user) {
        if (user == null) return null;
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // only if password is safe to expose
        return dto;
    }
}
