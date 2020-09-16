package com.example.demo.beanfactory;

import com.example.demo.aop.TestProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.StandardServletEnvironment;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 第1行： specialBeanForEngine  bean 先生成
 *
 * 第2行： EngineFactory 调用 getObject()方法生产 Engine代理对象
 *
 * 第3行、4行： BenzCar调用构造方法，此时 engine属性还未被设置。
 *
 * 第5行、6： BenzCar调用@PostConstruct注解的方法，此时engine属性已经设置。
 *
 * 第7行： BenzCar调用 InitializingBean接口方法。
 *
 * 第10行： BenzCar调用 initMethod指定的方法，
 *
 * 第11行： BenzCar调用了代理对象的方法，SpecialBeanForEngine 类中第44行代码。
 */
@Slf4j
public class SpecialBeanForEngine implements BeanFactoryPostProcessor, BeanNameAware {

    String name;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("运行到这里postProcessBeanFactory:"+((DefaultListableBeanFactory)beanFactory).getSerializationId());
////        ConfigurableApplicationContext context = createApplicationContext();
//        ConfigurableEnvironment environment= new StandardServletEnvironment();
//       String name= environment.getProperty("spring.application.name");
        String value=null;
        try {
            Properties properties = new Properties();
            InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("common.properties");
            properties.load(in);
            value=properties.getProperty("jf.content.browse.weight");
            in.close();
        } catch (Exception e) {
            log.error(" zhelid->>>>>>>>>>>");
        }

        System.out.println(value);

        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry)beanFactory;
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(EngineFactory.class);
        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
        gbd.setAutowireCandidate(true);
        bdr.registerBeanDefinition("engine01-gbd", gbd);
    }

    public static class EngineFactory implements FactoryBean<Engine>, BeanNameAware, InvocationHandler {

        String name;

        @Override
        public Engine getObject() throws Exception {
            System.out.println("EngineFactory  to build Engine01 , EngineFactory :"+ name);
            Engine prox = (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Engine.class}, this);
            return prox;
        }

        @Override
        public Class<?> getObjectType() {
            return Engine.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }

        @Override
        public void setBeanName(String name) {
            this.name = name;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("here is invoke  engine:"+method.getName());
            return null;
        }
    }

    @Override
    public void setBeanName(String name) {
        this.name =name;
    }
}