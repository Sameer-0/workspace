console.log("Connected faculty form");

function formValidation(event) {
  event.preventDefault();

  clearError();

  // Validating The Name.........................
  var name1 = nameValidation();

  // Validating Username....................................
  var username1 = usernameValidation();

  // Validating the Email ID...........................
  // var email1 = emailValidation(email);

  // Validating Password....................................
  var password1 = passwordValidation();

  if (!name1 || !username1 || !password1) {
    return;
  }
  document.getElementById("faculty-form").submit();
}
