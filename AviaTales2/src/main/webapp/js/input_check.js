function inCheck() {
 let a = document.forms["startForm"]['from'].value;
 let b = document.forms["startForm"]['to'].value;
 let c = document.forms["startForm"]["fdate"].value;
 let d = document.forms["startForm"]["adate"].value;
 if (a == null || a === "", b == null || b === "", c == null || c === "", d == null || d === "") {
     document.getElementById("alert").innerHTML="<p>Please Fill All Required Field</p>";
     return false;
 }
 return true
}




