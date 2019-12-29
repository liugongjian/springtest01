package spring.anotationUsage.autowireAndQualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//bean的名字是类名小写
@Component
public class AutowireBean {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
