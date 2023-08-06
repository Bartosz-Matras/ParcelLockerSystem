package pl.matrasbartosz.parcellockersystem.schedule;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import pl.matrasbartosz.parcellockersystem.repository.user.AttemptRepository;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class SystemScheduler {

    private final Logger logger = LoggerFactory.getLogger(SystemScheduler.class);

    private final AttemptRepository attemptRepository;

}
