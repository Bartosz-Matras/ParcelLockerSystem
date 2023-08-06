package pl.matrasbartosz.parcellockersystem.entity.user.mappers;

import pl.matrasbartosz.parcellockersystem.entity.user.Attempt;

public class AttemptMapper {

    private AttemptMapper(){}

    public static Attempt createAttempt(String email, int attemptCount) {
        Attempt attempt = new Attempt();
        attempt.setEmail(email);
        attempt.setAttemptCount(attemptCount);
        return attempt;
    }
}
