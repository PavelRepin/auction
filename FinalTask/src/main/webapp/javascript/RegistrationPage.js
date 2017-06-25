function validateuser() {

    var username = document.getElementById("username1").value;
    var regexpname = /^[a-zA-Z][a-zA-Z0-9_]{4,}$/;

    if (!username.match(regexpname)) {
        document.getElementById("username1").style.backgroundColor = "#FFA594";
        document.getElementById("incorname").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("username1").style.backgroundColor = "#fff";
        document.getElementById("incorname").style.visibility = "hidden";
        return true;
    }
}

function validatepass() {

    var pass = document.getElementById("password").value;
    var regexppass = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/;

    if (!pass.match(regexppass) || pass.length < 6) {
        document.getElementById("password").style.backgroundColor = "#FFA594";
        document.getElementById("incorpass").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("password").style.backgroundColor = "#fff";
        document.getElementById("incorpass").style.visibility = "hidden";
        return true;
    }
}

function validatereppass() {

    var pass = document.getElementById("password").value;
    var pass1 = document.getElementById("password1").value;

    if (pass != pass1 || pass1 == "") {
        document.getElementById("password1").style.backgroundColor = "#FFA594";
        document.getElementById("incorpass1").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("password1").style.backgroundColor = "#fff";
        document.getElementById("incorpass1").style.visibility = "hidden";
        return true;
    }
}

function validateemail() {

    var email = document.getElementById("email").value;
    var regexpemail = /\w+@\w+\.\w{2,}/;

    if (!email.match(regexpemail)) {
        document.getElementById("email").style.backgroundColor = "#FFA594";
        document.getElementById("incoremail").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("email").style.backgroundColor = "#fff";
        document.getElementById("incoremail").style.visibility = "hidden";
        return true;
    }
}

function validateage() {

    var age = document.getElementById("age").value;
    var check = true;


    if (age != "" && age < 18) {
        document.getElementById("incoragey").style.visibility = "visible";
        check = false;
    } else {
        document.getElementById("incoragey").style.visibility = "hidden";
    }

    if (age != "" && age > 120) {
        document.getElementById("incorageo").style.visibility = "visible";
        check = false;
    } else {
        document.getElementById("incorageo").style.visibility = "hidden";
    }

    if (age == "") {
        document.getElementById("incorageempty").style.visibility = "visible";
        check = false;
    } else {
        document.getElementById("incorageempty").style.visibility = "hidden";
    }

    if (!check) {
        document.getElementById("age").style.backgroundColor = "#FFA594";
    } else {
        document.getElementById("age").style.backgroundColor = "#fff";
    }
    return check;
}

function validate() {

    var valid = true;
    if (!validateuser()) {
        valid = false;
    }
    if (!validatepass()) {
        valid = false;
    }
    if (!validatereppass()) {
        valid = false;
    }
    if (!validateemail()) {
        valid = false;
    }
    if (!validateage()) {
        valid = false;
    }

    return valid;
}