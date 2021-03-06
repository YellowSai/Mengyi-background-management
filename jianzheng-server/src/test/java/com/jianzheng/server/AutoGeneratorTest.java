package com.jianzheng.server;

import net.scode.commons.db.generator.AutoGenerator;
import net.scode.commons.db.generator.GeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * 代码生成
 *
 * @author tanghuang 2020年02月20日
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class AutoGeneratorTest {

    @Autowired
    private DataSource dataSource;

    /**
     * 生成DAO相关类<br>
     * <font color='red'>注意: 建议生成完后，将已生成过的auto.generate注释掉，避免重新生成覆盖</font>
     */
    @Test
    public void codeGenerate() {
        GeneratorConfig config = new GeneratorConfig();
        config.setMapperDir("resources/mybatis/");
        config.setPackagePrefix("com.jianzheng.server");
        config.setAuthor("auto");

        AutoGenerator auto = new AutoGenerator(dataSource);//测试通过，已注释
        //auto.generate(config, "sys_permission", "SysPermission");
        //auto.generate(config, "sys_role", "SysRole");
        auto.generate(config, "order", "Order",true);//测试通过，已注释

    }

}
