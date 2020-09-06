package com.example.demo.database.datasource;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.example.demo.database.parsers.ReplaceSql;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
@Slf4j
public class DynamicTableNameInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        //if (InterceptorIgnoreHelper.willIgnoreDynamicTableName(ms.getId())) return;
        mpBs.sql(this.changeTable(mpBs.sql()));
    }
    protected String changeTable(String sql) {
        String tenantDatabasePrefix="";
        // 本项目所有服务连接的默认数据库都是zuihou_defaults， 不执行以下代码，将在默认库中执行sql
        // 想要 执行sql时， 不切换到 zuihou_base_{TENANT} 库, 请直接返回null
        String tenantCode = "hq_user";//BaseContextHandler.getTenant();
        if (StrUtil.isEmpty(tenantCode)) {
            return null;
        }

        String schemaName = StrUtil.format("{}{}", tenantDatabasePrefix, tenantCode);
        // 想要 执行sql时， 切换到 切换到自己指定的库， 直接修改 setSchemaName
        String parsedSql = ReplaceSql.replaceSql(schemaName, sql);
        log.info("parsedSql-------------->"+parsedSql);
        return parsedSql;
    }
    /**
     * 判断是否允许执行
     * <p>例如：逻辑删除只解析 delete , update 操作</p>
     *
     * @param metaObject 元对象
     * @return true
     */
    private boolean allowProcess(MetaObject metaObject) {
        return true;
    }
}
