package exercise;

import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private static Map<String, String> mapInspectedBeans = new HashMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String infoLevel = bean.getClass().getAnnotation(Inspect.class).level();
            mapInspectedBeans.put(beanName, infoLevel);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (mapInspectedBeans.containsKey(beanName)) {
            String level = mapInspectedBeans.get(beanName);
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        LOGGER.info("-----> " + bean.getClass().getName());
                        String msg = String.format("Was called method: %s() with arguments: %s",
                                method.getName(), Arrays.toString(args));
                        if (level.equals("info")) {
                            LOGGER.info(msg);
                        }
                        if (level.equals("debug")) {
                            LOGGER.debug(msg);   // для вывода сообщения требуется повысить уровень логирования
                        }
                        return method.invoke(bean, args);
                    }
            );
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
