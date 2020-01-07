package hu.molnaran.todobackend.validation;


import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName=constraintAnnotation.fieldOne();
        secondFieldName=constraintAnnotation.fieldTwo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstFieldValue= new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        Object secondFieldValue= new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
        return firstFieldValue == null && secondFieldValue == null ||
                firstFieldValue != null && firstFieldValue.equals(secondFieldValue);
    }
}
