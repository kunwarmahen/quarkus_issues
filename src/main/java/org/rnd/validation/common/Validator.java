package org.rnd.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.rnd.annotation.Annotation;
import org.rnd.model.Model;

public class Validator implements ConstraintValidator<Annotation, Model> {

    @Override
    public boolean isValid(Model value, ConstraintValidatorContext context) {
        final var validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
        validatorContext.addExpressionVariable("longitude", value);
        if (value.longitude == null || value.latitude.isNaN()) {
            return false;
        } else {
            return true;
        }
    }
}
