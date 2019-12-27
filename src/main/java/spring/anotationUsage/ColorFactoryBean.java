package spring.anotationUsage;

import org.springframework.beans.factory.FactoryBean;
import spring.anotationUsage.bean.Color;

public class ColorFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
