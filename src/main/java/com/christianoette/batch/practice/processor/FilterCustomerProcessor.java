package com.christianoette.batch.practice.processor;

import com.christianoette.batch.practice.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FilterCustomerProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        if (person.getIsCustomer()) {
            return person;
        }
        return null;
    }
}
