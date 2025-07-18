package kr.tatine.manibogo_oms_v2.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.*;

@RequiredArgsConstructor
public class RedisPersistentTokenRepository implements PersistentTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        final Map<String, String> entries = new HashMap<>();
        entries.put("username", token.getUsername());
        entries.put("series", token.getSeries());
        entries.put("token", token.getTokenValue());
        entries.put("last_used", String.valueOf(token.getDate().getTime()));

        hashOps.putAll(getKey(token.getUsername(), token.getSeries()), entries);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        hashOps.put(getKey("*", series), "token", tokenValue);
        hashOps.put(getKey("*", series), "last_used", String.valueOf(lastUsed.getTime()));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        final Map<String, String> entries = hashOps.entries(getKey(seriesId, "*"));

        if (entries.isEmpty()) {
            return null;
        }

        final String username = entries.get("username");
        final String series = entries.get("series");
        final String tokenValue = entries.get("token");
        final Date date = new Date(Long.parseLong(entries.get("last_used")));

        return new PersistentRememberMeToken(username, series, tokenValue, date);
    }

    @Override
    public void removeUserTokens(String username) {
        final Set<String> keys = redisTemplate.keys(getKey(username, "*"));
        if (keys.isEmpty()) return;

        redisTemplate.delete(keys);
    }

    private String getKey(String username, String series) {
        return "rem_me:user:%s:series:%s".formatted(username, series);
    }
}
