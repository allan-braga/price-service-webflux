package com.emirates.priceengine.config;

import com.emirates.priceengine.model.PriceModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Bean
  public ReactiveRedisOperations<String, PriceModel> redisOperations(
      ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<PriceModel> serializer = new Jackson2JsonRedisSerializer<>(
        PriceModel.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, PriceModel> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, PriceModel> context = builder.value(serializer).build();
    return new ReactiveRedisTemplate<>(factory, context);
  }
}
