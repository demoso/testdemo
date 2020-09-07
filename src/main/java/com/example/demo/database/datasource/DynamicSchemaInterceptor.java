package com.example.demo.database.datasource;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.example.demo.database.parsers.ReplaceSql;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;

import java.sql.Connection;

/**
 * SELECT: willDoQuery ===> beforeQuery ===> beforePrepare ===> doQuery
 * UPDATE: willDoUpdate ===> beforeUpdate ===> beforePrepare ===> doUpdate
 */
@Slf4j
@Setter
public class DynamicSchemaInterceptor implements InnerInterceptor {
      private String tenantDatabasePrefix="";
      private BaseTenantHandler baseTenantHandler;
     // @Value("${spring.application.name};
//
//    @Override
//    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        log.info("运行到这里----》beforeQuery ==>ms.getId()"+ms.getId());
//        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
//        mpBs.sql(this.changeTable(mpBs.sql()));
//    }



//    @Override
//    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
//        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
//        MappedStatement ms = mpSh.mappedStatement();
//        SqlCommandType sct = ms.getSqlCommandType();
//        if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE || sct == SqlCommandType.DELETE) {
//            PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
//            mpBs.sql(this.changeTable(mpBs.sql()));
//        }
//    }
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
        mpBs.sql(this.changeSchema(mpBs.sql()));
    }

    protected String changeSchema(String sql) {
        // 本项目所有服务连接的默认数据库都是zuihou_defaults， 不执行以下代码，将在默认库中执行sql
        // 想要 执行sql时， 不切换到 zuihou_base_{TENANT} 库, 请直接返回null
        String tenantCode = baseTenantHandler.getTenantId();
        if (StrUtil.isEmpty(tenantCode)) {
            return null;
        }

        String schemaName = StrUtil.format("{}_{}", tenantDatabasePrefix, tenantCode);
        // 想要 执行sql时， 切换到 切换到自己指定的库， 直接修改 setSchemaName
        String parsedSql = ReplaceSql.replaceSql(schemaName, sql);
        //log.info("parsedSql-------------->"+parsedSql);
        return parsedSql;
    }

}
