package org.rnd.util.logging;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import org.jboss.logging.Logger;

import io.quarkus.qute.TemplateInstance;

@Logged
@Priority(2020)
@Interceptor
public class LoggingInterceptor {

   @Inject
   Logger logger;

   @AroundInvoke
   Object logInvocation(InvocationContext context) throws Exception {
      // ...log before
      logger.trace("Calling " + context.getMethod());

      Object[] obj = context.getParameters();
      for (Object object : obj) {
         logger.trace("With Parameters " + object);
      }

      Object ret = context.proceed();

      try {

         if (ret instanceof TemplateInstance) {
            TemplateInstance instance = (TemplateInstance) ret;
            logger.trace("Response " + instance.render());
         }
      } catch (Exception ex) {
         logger.error("Error while casting return data (TemplateInstnace) to string " + ex.getMessage());
         logger.trace("Response " + ret);
      }

      // ...log after
      logger.trace("Called " + context.getMethod());

      return ret;
   }

}