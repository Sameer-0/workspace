console.log("Js connected.........");

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
  document.getElementById("myform").submit();
}

const name = document.getElementById("name");
const username = document.getElementById("id");
let email = document.getElementById("email").value;
const password = document.getElementById("password");

function isCharNumber(res) {
  for (let i = 0; i < checkLength(res); i++) {
    var c = res.charCodeAt(i);

    if (!(c > 47 && c < 58)) {
      return false;
    }
  }
  return true;
}

function isCharAlphabet(res) {
  for (let i = 0; i < checkLength(res); i++) {
    var c = res.charCodeAt(i);

    if (
      !(c > 47 && c < 58) &&
      !(c > 64 && c < 91) &&
      !(c > 96 && c < 123) &&
      c != 32
    ) {
      return false;
    }
  }
  return true;
}

function isSpecialChar(char) {
  return (
    (char >= 65 && char < 91) ||
    (char >= 97 && char < 123) ||
    (char >= "0" && char <= "9")
  );
}

function checkLength(value) {
  return value.length;
}

function clearError() {
  errors = document.getElementsByClassName("error");
  for (const item of errors) {
    item.innerHTML = "";
  }
}

function nameValidation() {
  let res = name.value;

  if (checkLength(res) > 2) {
    if (isCharAlphabet(res)) {
      return true;
    } else {
      document.getElementById("name-message").innerHTML =
        "special characters are not allowed";
      return false;
    }
  } else {
    document.getElementById("name-message").innerHTML = "invalid length";
    return false;
  }
}

function usernameValidation() {
  let res = username.value;

  if (checkLength(res) < 1) {
    document.getElementById("id-message").innerHTML =
      "please enter valid username";
    return false;
  } else {
    return true;
  }
}

function emailValidation(email) {
  if (checkLength(email) > 3) {
    return true;
  } else {
    document.getElementById("email-message").innerHTML =
      "please enter valid email id";
    return false;
  }
}

function passwordValidation() {
  let res = password.value;

  if (checkLength(res) < 1) {
    document.getElementById("password-message").innerHTML =
      "please enter valid address";
    return false;
  } else {
    return true;
  }
}
