/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * RedisTemplate  配置
 *
 * @author L.cm
 */
@EnableCaching
@Configuration
@AllArgsConstructor
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfig {

	@Bean(name = "redisTemplate")
	//@ConditionalOnMissingBean(RedisTemplate.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		// key 序列化
		RedisKeySerializer redisKeySerializer = new RedisKeySerializer();
		redisTemplate.setKeySerializer(redisKeySerializer);
		redisTemplate.setHashKeySerializer(redisKeySerializer);

		// 值采用json序列化
		GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

//	@Bean
//	@ConditionalOnProperty(value = "spring.redis.cluster.enable",havingValue = "true")
//	public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
//		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
//
//		// https://github.com/lettuce-io/lettuce-core/wiki/Redis-Cluster#user-content-refreshing-the-cluster-topology-view
//		ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
//				.enablePeriodicRefresh()
//				.enableAllAdaptiveRefreshTriggers()
//				.refreshPeriod(Duration.ofSeconds(5))
//				.build();
//
//		ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
//				.topologyRefreshOptions(clusterTopologyRefreshOptions).build();
//
//		// https://github.com/lettuce-io/lettuce-core/wiki/ReadFrom-Settings
//		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
//				.readFrom(ReadFrom.REPLICA_PREFERRED)
//				.clientOptions(clusterClientOptions).build();
//
//		return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
//	}
}
