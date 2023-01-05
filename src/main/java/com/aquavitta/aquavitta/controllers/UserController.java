package com.aquavitta.aquavitta.controllers;

import com.aquavitta.aquavitta.dtos.UserDto;
import com.aquavitta.aquavitta.models.UserModel;
import com.aquavitta.aquavitta.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/adding-user")
    public ResponseEntity<UserModel> addingUser(@RequestBody UserDto userDto){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userService.save(userModel);
        return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(userModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("User updated with sucessfuly");
    }
}
