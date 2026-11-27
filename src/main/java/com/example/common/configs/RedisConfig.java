package com.example.common.configs;

import com.example.common.modules.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.user}")
    private String user;

    @Value("${spring.redis.pass}")
    private String pass;

    @Value("${spring.redis.db}")
    private Integer db;

    @Value("${spring.redis.tls}")
    private Boolean isUseTls;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setUsername(user);
        // Redis 패스워드가 없을 때, 패스 처리
        if(pass != null && !pass.isEmpty() && !pass.equals("null")){
            redisStandaloneConfiguration.setPassword(pass);
        }
        redisStandaloneConfiguration.setDatabase(db);

        LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigBuilder = LettuceClientConfiguration.builder();
        // Redis 서버가 TLS/SSL을 사용한다면, SSL을 사용하도록 설정하고, 피어 검증을 비활성화합니다.
        if (isUseTls) clientConfigBuilder.useSsl().disablePeerVerification();
        // 설정 빌더를 통해 LettuceClientConfiguration 객체를 생성합니다.
        LettuceClientConfiguration clientConfig = clientConfigBuilder.build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }

    @Bean
    public RedisService redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return new RedisService(redisTemplate);
    }
}
