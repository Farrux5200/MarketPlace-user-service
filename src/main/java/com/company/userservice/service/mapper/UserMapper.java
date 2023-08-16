package com.company.userservice.service.mapper;

import com.company.userservice.client.service.FileClient;
import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
     @Autowired
    protected FileClient fileClient;
     @Autowired
    protected UserRepository userRepository;
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "file", ignore = true)
    public abstract UserDto toDto(User user);

    @Mapping(target = "file", expression = "java(fileClient.download(user.getUserId()).getData())")
    public abstract UserDto toDtoWithFile(User user);


  void aVoid(){
      UserDto userDto=new UserDto();
      User user=new User();
      //userDto.setFileModelDto(userRepository.findAll());
      userDto.setFile(fileClient.download(user.getUserId()).getData());
  }

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);
}
