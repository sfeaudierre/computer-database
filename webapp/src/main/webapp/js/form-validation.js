/**
 * 
 */
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='form']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      computerName: "required",
      introduced: {
    	  required: false,
    	  maxlength: 10,
    	  minlength: 10,
  		  date: true
      },
  	  discontinued: {
  		  required: false,
  		  maxlength: 10,
  		  minlength: 10,
  		  date: true
  	  },
    },
    // Specify validation error messages
    messages: {
      computerName: "Please enter the name",
      introduced: {
        maxlength: "The date must be yyyy-mm-dd",
        minlength: "The date must be yyyy-mm-dd",
        date: "The date must be yyyy-mm-dd"
      },
      discontinued: {
          maxlength: "The date must be yyyy-mm-dd",
          minlength: "The date must be yyyy-mm-dd",
          date: "The date must be yyyy-mm-dd"
      },   
     },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});