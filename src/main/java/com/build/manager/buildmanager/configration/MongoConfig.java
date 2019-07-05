package com.build.manager.buildmanager.configration;

import java.util.ArrayList;
import java.util.List;

import com.build.manager.buildmanager.utils.ZonedDateTimeReadConverter;
import com.build.manager.buildmanager.utils.ZonedDateTimeWriteConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;



@Configuration
    public class MongoConfig
    {

        @Bean
        public MongoCustomConversions customConversions()
        {
            List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
            converterList.add(new ZonedDateTimeReadConverter());
            converterList.add(new ZonedDateTimeWriteConverter());
            return new MongoCustomConversions(converterList);
        }
    }