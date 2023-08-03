package com.company.userservice.service.validate;

import com.company.userservice.dto.ErrorDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;
    public List<ErrorDto> validate(UserDto userDto){
        List<ErrorDto> errors=new ArrayList<>();

        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            errors.add(new ErrorDto("PhoneNumber ", "PhoneNumber already exist! "));
        }
        return errors;
    }
}
