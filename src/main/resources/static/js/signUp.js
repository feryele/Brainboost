
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-app.js";
import { getAuth , GoogleAuthProvider , signInWithPopup } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-auth.js";


const firebaseConfig = {
    apiKey: "AIzaSyDFgiCN25ES9ZlZW5TMCUh01E8xhgYwf0I",
    authDomain: "khalildoggui-380619.firebaseapp.com",
    projectId: "khalildoggui-380619",
    storageBucket: "khalildoggui-380619.appspot.com",
    messagingSenderId: "887888674375",
    appId: "1:887888674375:web:d94f8422e365b4e2d57b62"
};

const app = initializeApp(firebaseConfig);
const auth =getAuth(app);
auth.languageCode = 'en'
const provider = new GoogleAuthProvider();

const googleLogin = document.getElementById("google-login");
googleLogin.addEventListener("click", function(){
    signInWithPopup(auth, provider)
        .then((result) => {
            const credential = GoogleAuthProvider.credentialFromResult(result);
            const user = result.user;
            console.log(user);
            window.location.href="../Logged.html";

        }).catch((error) => {
            const errorCode = error.code;
            const errorMessage = error.message;
            
    });
})




