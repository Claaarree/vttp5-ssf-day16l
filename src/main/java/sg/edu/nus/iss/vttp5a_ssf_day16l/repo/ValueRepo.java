package sg.edu.nus.iss.vttp5a_ssf_day16l.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_ssf_day16l.constant.Constant;

@Repository
public class ValueRepo {
    
    // Slide 20
    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, String> template;

    // Slide 24: Create/update a value
    public void createValue(String key, String value) {
        template.opsForValue().set(key, value);

        // setIfPresent() or setIfAbsent() also available
    }

    // Slide 25: Retrieve a value
    public String getValue(String key) {
        return template.opsForValue().get(key);
    }

    // Slide 27: Delete a value
    public Boolean deleteValue(String key) {
        return template.delete(key);
    }

    // Slide 26: Increment only works for key with int value
    public void incrementValue(String key) {
        template.opsForValue().increment(key);
    }
    
    public void decrementValue(String key) {
        template.opsForValue().decrement(key);
    }

    public void incrementValueBy(String key, Integer value) {
        template.opsForValue().increment(key, value);
    }
    
    public void decrementValueBy(String key, Integer value) {
        template.opsForValue().decrement(key);
    }

    // Slide 28
    public Boolean checkExists(String key) {
        return template.hasKey(key);
    }
}
