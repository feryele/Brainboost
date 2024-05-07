function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }

  // Close the dropdown if the user clicks outside of it

    document.querySelector('.dropbtn').addEventListener('click', () => {
        var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    })
    window.onload = () => {
        var dropdown = document.querySelector(".logout");
        if(localStorage.getItem("user")==null){
            dropdown.style.display = 'none';
        } else {
            dropdown.style.display = 'block';

        }
    }
    function goProfile() {
                
        const user=localStorage.getItem("user");
        console.log(user);
        if (user!=null){
            window.location.href="/profil";
        }
        else{
            window.location.href="/login";
        }
    }
  const isAuthenticated =localStorage.getItem("user");


    

    


function logout(){
    console.log(isAuthenticated);
    localStorage.removeItem("user");
    window.location.href="/acceuil";
}
const user =JSON.parse(localStorage.getItem("user"));

function pay(){
   
        
       if(isAuthenticated){
        window.location.href="/coursespage";
       }
       else{
        window.location.href="/login";
    
       }
    }
   


