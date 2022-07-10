const annotations = {
  'org.springframework': {
    'web.bind.annotation': {
      ModelAttribute:
        'Annotation that binds a method parameter or method return value to a named model attribute, exposed to a web view. Supported for controller classes with @RequestMapping methods. Can be used to expose command objects to a web view, using specific attribute names, through annotating corresponding parameters of an @RequestMapping method. Can also be used to expose reference data to a web view through annotating accessor methods in a controller class with @RequestMapping methods. Such accessor methods are allowed to have any arguments that @RequestMapping methods support, returning the model attribute value to expose. Note however that reference data and all other model content is not available to web views when request processing results in an Exception since the exception could be raised at any time making the content of the model unreliable. For this reason @ExceptionHandler methods do not provide access to a Model argument.'
    },
    'validation': {
      BindingResult:
        'BindingResult holds the result of a validation and binding and contains errors that may have occurred. The BindingResult must come right after the model object that is validated or else Spring fails to validate the object and throws an exception.',
      'annotation': {
        Validated:
          'Annotation that marks a class as being validated by Spring MVC\'s validation framework. This annotation is typically used on a controller class. When used, Spring MVC will automatically invoke the controller\'s validation methods to validate the model objects bound to the request. The annotated class must have a method annotated with @RequestMapping with a method parameter of type BindingResult. This method will be invoked if validation fails. The method must return a String view name. The view name may be a relative path to a view, e.g. "myView" or "/WEB-INF/views/myView.jsp". If the view name is relative, it is resolved against the current servlet context. If the view name is absolute, it is resolved against the root of the web application. If the view name is not specified, the default view name is "validationError". The default view name is typically "validationError" but may be overridden by specifying the viewName attribute of the @Validated annotation.'
      }
    }
  },
  'lombok': {
    Data: 'Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor.'
  }
}
