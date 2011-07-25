/*
 * Copyright 2007-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.core.impl.util.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcher;

/**
 * Matches if the specified annotation is present on the targetClass or if it is present on the most specific method on the target
 * class corresponding to the method specified, as defined by AopUtils.
 */
public class ClassOrMethodAnnotationMatcher extends StaticMethodMatcher {
    private final Class<? extends Annotation> annotationType;

    public ClassOrMethodAnnotationMatcher(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    /**
     * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, java.lang.Class)
     */
    public boolean matches(Method method, Class<?> targetClass) {
        return targetClass.isAnnotationPresent(this.annotationType) || AopUtils.getMostSpecificMethod(method, targetClass).isAnnotationPresent(this.annotationType);
    }
}
