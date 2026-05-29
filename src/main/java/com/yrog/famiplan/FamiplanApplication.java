package com.yrog.famiplan;

import com.yrog.famiplan.config.AppAdminConfig;
import com.yrog.famiplan.entity.User;
import com.yrog.famiplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties
public class FamiplanApplication {

    private final AppAdminConfig appAdminConfig;

    public FamiplanApplication(AppAdminConfig appAdminConfig) {
        this.appAdminConfig = appAdminConfig;
    }
    @Bean
    public CommandLineRunner initUser(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepository.findUserByUsername(appAdminConfig.getUsername()) == null)  {
                User user = new User();
                user.setUsername(appAdminConfig.getUsername());
                user.setPassword(encoder.encode(appAdminConfig.getPassword()));
                userRepository.save(user);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FamiplanApplication.class, args);
    }

}
