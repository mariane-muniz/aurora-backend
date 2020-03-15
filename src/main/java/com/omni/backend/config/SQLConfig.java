package com.omni.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

@Configuration
@SqlGroup({
        @Sql(scripts = "/schema.sql", config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class SQLConfig {
}