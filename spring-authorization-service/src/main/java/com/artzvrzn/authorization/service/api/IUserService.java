package com.artzvrzn.authorization.service.api;

import com.artzvrzn.authorization.dto.UserDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IUserService {

  UserDto create(UserDto dto);

  UserDto get(UUID id);

  UserDto get(String username);

  Page<UserDto> get(int page, int size);

  UserDto update(UUID id, UserDto userDto);

  void delete(UUID id);
}
