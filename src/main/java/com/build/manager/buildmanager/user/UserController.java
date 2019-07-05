package com.build.manager.buildmanager.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    private Mono <UserModel> getUserById(@PathVariable String id) {
        return userRepository.findByEmail(id)
        .onErrorResume(exception -> {
            System.out.println("Error");
            exception.printStackTrace();
            return Mono.just(new UserModel());
        })
        .map(value -> {
            return value;
        }).switchIfEmpty(Mono.just(
            new UserModel()).map(value -> {
            value.setStatusCode(1101);
            value.setStatusMessage("User not Found");
            return value;
            })
        );
    }

    @GetMapping("/")
    private Flux <UserModel> getAllUsers() {
        return userRepository.findAll().onErrorResume(exception -> {
            System.out.println("Error");
            exception.printStackTrace();
            return Mono.just(new UserModel());
        });
    }

    @PostMapping("add/user")
    private Mono <UserModel> addUser(@RequestBody UserModel user) {
        System.out.println(user.toString());
        return userRepository.save(user).
        onErrorResume(exception -> {
            System.out.println("Error");
            exception.printStackTrace();
            return Mono.just(new UserModel());
        });
    }
}