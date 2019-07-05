package com.build.manager.buildmanager.build;

import java.time.ZonedDateTime;

import com.build.manager.buildmanager.user.UserModel;
import com.build.manager.buildmanager.user.UserRepository;
import com.build.manager.buildmanager.utils.StatusModal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/builds")
class BuildController {
    @Autowired
    private BuildRepository buildRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/upcomming")
    private Flux<BuildResponse> upcommingBuils()
    {
        return this.buildRepository.findAll(Sort.by("buildEndDate").descending())
        .onErrorResume(exception -> {
            exception.printStackTrace();
            BuildModel errorStatus = new BuildModel();
            errorStatus.setStatusCode(100);
            errorStatus.setStatusMessage("Error during fetching build");
            return Flux.just(errorStatus);
        }).limitRequest(5)
        .map(build -> {
            return new BuildResponse(build);
        })
        .switchIfEmpty(
            Mono.just(new BuildResponse())
            .map(status -> {
                status.setStatusCode(100);
                status.setStatusMessage("no upcomming builds");
                return status;
            })
        );
    }

    @PostMapping("/add")
    private Mono<StatusModal> addBuild(@RequestBody BuildRequest newBuild) {
        BuildModel build;
        try {
            build = new BuildModel(
                ZonedDateTime.parse(newBuild.getBuildEndDate()) ,
                ZonedDateTime.parse(newBuild.getBuildStartDate()),
                BuildStatus.valueOf(newBuild.getStatus()),
                Platform.valueOf(newBuild.getPlatform()));
        } catch (Exception e) {
            e.printStackTrace();
            BuildModel errorStatus = new BuildModel();
            errorStatus.setStatusCode(100);
            errorStatus.setStatusMessage(e.getMessage());
            return Mono.just(errorStatus);
        }

        return this.userRepository.findByEmail(newBuild.getAssignedTo()).onErrorResume(exception -> {
            exception.printStackTrace();
            UserModel errorStatus = new UserModel();
            errorStatus.setStatusCode(100);
            errorStatus.setStatusMessage("Error during saving build");
            return Mono.just(errorStatus);
        }).switchIfEmpty(Mono.just(new UserModel())
        .map(status -> {
            status.setStatusCode(100);
            status.setStatusMessage("user not found");
            return status;
        }))
        .flatMap(user -> {
            if(user.getStatusCode() == null){
                build.setAssignedTo(user);
                return this.buildRepository.save(build)
                .map(value -> {
                    StatusModal status = new StatusModal();
                    status.setStatusCode(100);
                    status.setStatusMessage("Build added");
                    return status;
                });
            } else {
                return Mono.just(new BuildModel(user));
            }
        });
    }
}