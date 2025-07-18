package kr.tatine.manibogo_oms_v2.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RedisPersistentTokenRepository implements PersistentTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private final int timeToLiveSeconds;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        final Map<String, String> entries = new HashMap<>();
        entries.put("username", token.getUsername());
        entries.put("series", token.getSeries());
        entries.put("token", token.getTokenValue());
        entries.put("last_used", String.valueOf(token.getDate().getTime()));

        final String hashKey = getHashKey(token.getSeries());
        hashOps.putAll(hashKey, entries);

        final String userKey = getUserKey(token.getUsername());
        final SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.add(userKey, hashKey);

        redisTemplate.expire(hashKey, timeToLiveSeconds, TimeUnit.SECONDS);
        redisTemplate.expire(userKey, timeToLiveSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        final String hashKey = getHashKey(series);
        hashOps.put(hashKey, "token", tokenValue);
        hashOps.put(hashKey, "last_used", String.valueOf(lastUsed.getTime()));

        final String userKey = getUserKey(hashOps.get(hashKey, "username"));

        redisTemplate.expire(hashKey, timeToLiveSeconds, TimeUnit.SECONDS);
        redisTemplate.expire(userKey, timeToLiveSeconds, TimeUnit.SECONDS);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        final HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        final Map<String, String> entries = hashOps.entries(getHashKey(seriesId));

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
        final SetOperations<String, String> setOps = redisTemplate.opsForSet();
        final Set<String> keys = setOps.members(getUserKey(username));
        redisTemplate.delete(getUserKey(username));

        if (keys.isEmpty()) return;

        redisTemplate.delete(keys);
    }

    private String getHashKey(String series) {
        return "rem_me:%s".formatted( series);
    }

    private String getUserKey(String username) {
        return "user_rem_me:%s".formatted(username);
    }
}
