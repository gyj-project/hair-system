package cn.bzu.hair.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.*;

@Configuration
public class RedisConfig {




    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws Exception{
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<String> ser = new Jackson2JsonRedisSerializer<String>(String.class);
        template.setDefaultSerializer(ser);
        return template;
    }
   /* @Bean   //在没有指定缓存Key的情况下，key生成策略
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("#"+method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }                return sb.toString();
            }
        };
    }*/
    /*@Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        //spring cache注解序列化配置
         RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
             .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
         //key序列化方式
                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
        // value序列化方式
                 .disableCachingNullValues()
         //不缓存null值
                  .entryTtl(Duration.ofSeconds(60));
        // 默认缓存过期时间
        // 设置一个初始化的缓存名称set集合
         HashSet<String> cacheNames =  new HashSet<>();
         cacheNames.add("user");
         //对每个缓存名称应用不同的配置，自定义过期时间
         Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
         configMap.put("user", redisCacheConfiguration.entryTtl(Duration.ofSeconds(120)));
         RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisTemplate.getConnectionFactory())
         .cacheDefaults(redisCacheConfiguration)
         .transactionAware()
         .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
         .withInitialCacheConfigurations(configMap)
         .build();
         return redisCacheManager;
         }
     @Bean
     public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    // 初始化缓存管理器，在这里我们可以缓存的整体过期时间等//
         // 生成一个默认配置，通过config对象即可对缓存进行自定义配置//
          RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();//
          config = config.entryTtl(Duration.ofSeconds(60))     // 设置缓存的默认过期时间，也是使用Duration设置//        //

          .disableCachingNullValues();                // 不缓存空值//
          RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();//
       //  RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).build();//
           return redisCacheManager;
}
      @Bean
      public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    // 配置redisTemplate
          RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
          redisTemplate.setConnectionFactory(redisConnectionFactory);
          //设置序列化
          Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//
          ObjectMapper om = new ObjectMapper();//
          om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
          //{"id":"1","name":"张三","age":18}////
          om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
          //json数据带类的名称 //["com.urthink.upfs.model.entity.User",{"id":"1","name":"张三","age":18}]//
          jackson2JsonRedisSerializer.setObjectMapper(om);
          RedisSerializer stringSerializer = new StringRedisSerializer();
          redisTemplate.setKeySerializer(stringSerializer);
          // key序列化
          redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
          // value序列化
          redisTemplate.setHashKeySerializer(stringSerializer);
          // Hash key序列化
          redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
          // Hash value序列化
          redisTemplate.afterPropertiesSet();
          return redisTemplate;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/



}
