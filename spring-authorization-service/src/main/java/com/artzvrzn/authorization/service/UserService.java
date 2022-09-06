package com.artzvrzn.authorization.service;

import com.artzvrzn.authorization.dao.RoleRepository;
import com.artzvrzn.authorization.dao.UserRepository;
import com.artzvrzn.authorization.domain.Role;
import com.artzvrzn.authorization.domain.Status;
import com.artzvrzn.authorization.domain.User;
import com.artzvrzn.authorization.dto.UserDto;
import com.artzvrzn.authorization.exception.UserNotFoundException;
import com.artzvrzn.authorization.service.api.IUserService;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService implements IUserService {
  private final static String USER_ROLE = "ROLE_USER";
  private final static String ADMIN_ROLE = "ROLE_ADMIN";

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private ModelMapper mapper;

  @PostConstruct
  private void postConstruct() {
      User user1 = new User();
      user1.setId(UUID.randomUUID());
      user1.setUsername("admin");
      user1.setPassword(passwordEncoder.encode("123"));
      Role role = new Role(USER_ROLE);
      user1.getRoles().addAll(List.of(role, new Role(ADMIN_ROLE)));
      user1.setEmail("admin@email.com");
      user1.setFirstName("admin");
      user1.setLastName("admin");
      user1.setCity("New-York");
      User user2 = new User();
      user2.setId(UUID.randomUUID());
      user2.setUsername("user");
      user2.setPassword(passwordEncoder.encode("321"));
      user2.getRoles().add(role);
      user2.setFirstName("user");
      user2.setLastName("user");
      userRepository.saveAll(List.of(user1, user2));
  }

  @PreDestroy
  private void preDestroy() {
//    List<User> users = userRepository.findAll();
    roleRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Override
  public UserDto create(UserDto dto) {
    dto.setId(UUID.randomUUID());
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    User user = mapper.map(dto, User.class);
    user.getRoles().add(roleRepository.getReferenceById(USER_ROLE));
    user.setStatus(Status.ACTIVE);
    userRepository.save(user);
    return dto;
  }

  @Override
  public UserDto get(UUID id) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    return mapper.map(user, UserDto.class);
  }

  @Override
  public UserDto get(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    return mapper.map(user, UserDto.class);
  }

  @Override
  public Page<UserDto> get(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
    return userRepository.findAll(pageable).map(e -> mapper.map(e, UserDto.class));
  }

  @Override
  public UserDto update(UUID id, UserDto userDto) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    if (user.getUsername() != null) user.setUsername(userDto.getUsername());
    if (user.getFirstName() != null) user.setFirstName(userDto.getFirstName());
    if (user.getLastName() != null) user.setLastName(userDto.getLastName());
    if (user.getStatus() != null) user.setStatus(userDto.getStatus());
    if (user.getEmail() != null) user.setEmail(userDto.getEmail());
    if (user.getPhone() != null) user.setPhone(userDto.getPhone());
    if (user.getCountry() != null) user.setCountry(userDto.getCountry());
    if (user.getCity() != null) user.setCity(userDto.getCity());
    if (user.getStreetName() != null) user.setStreetName(userDto.getStreet());
    if (user.getApartment() != null) user.setApartment(userDto.getApartment());
    if (user.getPassword() != null) user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userRepository.save(user);
    return mapper.map(user, UserDto.class);
  }

  @Override
  public void delete(UUID id) {
    userRepository.deleteById(id);
  }
}
