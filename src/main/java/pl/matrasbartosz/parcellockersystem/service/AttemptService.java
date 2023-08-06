package pl.matrasbartosz.parcellockersystem.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matrasbartosz.parcellockersystem.entity.user.Attempt;
import pl.matrasbartosz.parcellockersystem.repository.user.AttemptRepository;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptRepository attemptRepository;

    @Transactional
    public Attempt createAttempt(Attempt attempt) {
        return this.attemptRepository.save(attempt);
    }

    @Transactional
    public void updateAttempt(String email) {
        this.attemptRepository.findAttemptByEmail(email).ifPresent(attempt -> {
            attempt.setAttemptCount(attempt.getAttemptCount() + 1);
            this.attemptRepository.save(attempt);
        });
    }
}
