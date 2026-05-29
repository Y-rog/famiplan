package com.yrog.famiplan;

import com.yrog.famiplan.entity.User;
import com.yrog.famiplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FamiplanApplication {

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    public CommandLineRunner initUser(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepository.findUserByUsername(adminUsername) == null)  {
                User user = new User();
                user.setUsername(adminUsername);
                user.setPassword(encoder.encode(adminPassword));
                userRepository.save(user);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FamiplanApplication.class, args);
    }

}
