package spring.anotationUsage.autowireAndQualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Bean {

    @Qualifier("autowireBean2")
    @Autowired
    AutowireBean autowireBean;

    public AutowireBean getAutowireBean() {
        return autowireBean;
    }

    public void setAutowireBean(AutowireBean autowireBean) {

        this.autowireBean = autowireBean;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "autowireBean=" + autowireBean +
                ", name='" + name + '\'' +
                '}';
    }
}
