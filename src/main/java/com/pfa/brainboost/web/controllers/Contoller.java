package com.pfa.brainboost.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfa.brainboost.DAO.UsersRepository;
import com.pfa.brainboost.web.models.Cours;
import com.pfa.brainboost.web.models.User;
import com.pfa.brainboost.web.models.request.UserForm;
import com.pfa.brainboost.web.models.request.CourseForm;
import com.pfa.brainboost.DAO.CoursRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class Contoller {

    private  List<Cours> courses = new ArrayList<Cours>();
   // private  List<User> users = new ArrayList<User>();
    private static Long idCount =0L;


    @Value("${error.message:First Name & Last Name is required!}")
    private String errorMessage;

    @Autowired
    private CoursRepository rep;

    @Autowired
    private UsersRepository urep;

    //@RequestMapping(value = { "/courselist" } , method = RequestMethod.GET)
    //public String showcourselist(Model model){
        //courses = rep.findAll();
        //model.addAttribute("courses", courses);
        //return "courselist";
    //}

    @RequestMapping(value = { "/", "/Brainboost" , "/pageMain" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", " ");
        return "pageMain";
    }
    @RequestMapping(value = { "/freecourse" }, method = RequestMethod.GET)
    public String freecourses(Model model) {
        model.addAttribute("message", " ");
        return "freecourse";
    }

    @RequestMapping(value = { "/crud" }, method = RequestMethod.GET)
    public String showcrud(Model model) {
        model.addAttribute("message", " ");
        return "crud";
    }
    //@RequestMapping(value = { "/studentslist" , "/studentslist.html" }, method = RequestMethod.GET)
    //public String showstudentslist(Model model) {
    //    model.addAttribute("message", " ");
    //    return "studentslist";
    //}


    @RequestMapping(value = "/studentslist", method = RequestMethod.GET)
    public String showstudentslist(Model model, @RequestParam(required = false) String name) {
         List<User> users;
      if (name != null && !name.isEmpty()) {
        users = urep.findByNameContainingIgnoreCase(name);
      } else {
        users = this.urep.findAll();
      }
      model.addAttribute("users", users);
      return "studentslist";
    }

    @RequestMapping(value = { "/addstudents" }, method = RequestMethod.GET)
    public String addstudents(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "addstudents";
    }

    @RequestMapping(value = { "/addstudents" }, method = RequestMethod.POST)
    public String savestudents(Model model , @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Please fill in all required fields correctly.");
            return "addstudents";
              
        }
        String name = userForm.getName();
        String email = userForm.getEmail();
        String phone = userForm.getPhone();


        if (name != null && name.length() > 0 && email != null && email.length() > 0 && phone != null && phone.length() > 0 ) {
            User newUser = new User(++idCount, name, email, phone);
            urep.save(newUser);
            return "redirect:/studentslist";
       } else {
            model.addAttribute("errorMessage", "Please fill in all required fields correctly.");
            return "addstudents";
          }

    }
    

    @RequestMapping(value = { "/addcourse" }, method = RequestMethod.GET)
    public String showAddcoursePage(Model model) {
        CourseForm courseForm = new CourseForm();
        model.addAttribute("courseForm", courseForm);
        return "addCourses";
        
    }

    @RequestMapping(value = { "/addcourse" }, method = RequestMethod.POST)
    public String saveCourse(Model model, @Valid @ModelAttribute("courseForm") CourseForm courseForm, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
        model.addAttribute("errorMessage", "Please fill in all required fields correctly.");
        return "addcourse";
       }

      String title = courseForm.getTitle();
      String description = courseForm.getDescription();
      boolean isFree = courseForm.getIsFree();
      double price = courseForm.getPrice();
      List<String> users = new ArrayList<String>();
      if (!isFree) {
          price = 0;
        }
      if (title != null && title.length() > 0 && description != null && description.length() > 0 && price >= 0) {
          Cours newCourse = new Cours(title, description, price, isFree, users);
          rep.save(newCourse);
          return "redirect:/courselist";
     } else {
          model.addAttribute("errorMessage", "Please fill in all required fields correctly.");
          return "addcourse";
        }
    }


    //@RequestMapping(value = { "/addcourse" }, method = RequestMethod.POST)
    //public String saveCourse(Model model, @Valid @ModelAttribute("courseForm") CourseForm courseForm) {
    //    String Title = courseForm.getTitle();
    //    String description = courseForm.getDescription();
    //    boolean isFree = courseForm.getIsFree();
    //    double price = courseForm.getPrice();
    //    if(isFree == false){
    //        price = 0;
    //    }
    //    if (Title != null && Title.length() > 0 
    //    & description != null && description.length() > 0  && price >= 0) {
    //        Cours newCourse = new Cours(++idCount,Title, description , isFree , price);
    //        //courses.add(newCourse);
    //        rep.save(newCourse);
    //        return "redirect:/courselist";
    //    }
    //    else{
    //        model.addAttribute("errorMessage", errorMessage);
    //        return "/addcourse";
    //    }
    //    
    //}
    @RequestMapping(value={"/updatestudent"}, method=RequestMethod.GET)
    public String showedit(Model model, @RequestParam Long id) {
       try {
           User user = urep.findById(id).orElse(null);
           if (user == null) {
               // Gérer le cas où l'utilisateur n'est pas trouvé
               model.addAttribute("errorMessage", "User not found");
               return "updatestudent";
           }
   
           UserForm userForm = new UserForm();
           userForm.setName(user.getName());
           userForm.setEmail(user.getEmail());
           userForm.setPhone(user.getPhone());
   
           model.addAttribute("user", user);
           model.addAttribute("userForm", userForm);
       } catch (Exception e) {
           // Gérer toute exception survenue pendant la recherche de l'utilisateur
           System.out.println("Exception: " + e.getMessage());
           model.addAttribute("errorMessage", "An error occurred");
           return "updatestudent";
       }
       return "updatestudent";
   }


    @RequestMapping(value = { "/updatestudent" }, method = RequestMethod.POST)
    public String updatestudent(Model model, @RequestParam("id") Long id, @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // S'il y a des erreurs de validation, retournez à la page de mise à jour avec les erreurs affichées
                return "updatestudent";
            }
    
            // Récupérez le cours à mettre à jour en utilisant l'identifiant
            User user = urep.findById(id).orElse(null);
            if (user == null) {
                // Gérer le cas où le cours n'est pas trouvé
                return "redirect:/studentslist";
            }
    
            // Mettez à jour les attributs du cours avec ceux du formulaire
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPhone(userForm.getPhone());

    
            // Enregistrez le cours mis à jour dans la base de données
            urep.save(user);
    
        } catch (Exception e) {
            // Gérer toute exception survenue pendant le processus de mise à jour
            System.out.println("Exception: " + e.getMessage());
        }
    
    // Redirigez l'utilisateur vers la liste des cours après la mise à jour réussie
        return "redirect:/studentslist";
    }


    @RequestMapping(value={"/updatecourse"}, method=RequestMethod.GET)
    public String showeditpage( Model model,@RequestParam Long id) {

        try {
            Cours course = rep.findById(id).get();
            model.addAttribute("course", course);


            CourseForm courseForm = new CourseForm();
            courseForm.setTitle(course.getTitle());
            courseForm.setDescription(course.getDescription());
            courseForm.setIsFree(course.getIsFree());
            courseForm.setPrice(course.getPrice());

            model.addAttribute("courseForm", courseForm);

        } catch (Exception e) {
            System.out.println("Exception: "+ e.getMessage());
            return "redirect:/courselist";
        }
        return "updatecourse";
    }

    @RequestMapping(value = { "/updatecourse" }, method = RequestMethod.POST)
    public String update(Model model, @RequestParam("id") Long id, @Valid @ModelAttribute("courseForm") CourseForm courseForm, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // S'il y a des erreurs de validation, retournez à la page de mise à jour avec les erreurs affichées
                return "updatecourse";
            }
    
            // Récupérez le cours à mettre à jour en utilisant l'identifiant
            Cours course = rep.findById(id).orElse(null);
            if (course == null) {
                // Gérer le cas où le cours n'est pas trouvé
                return "redirect:/courselist";
            }
    
            // Mettez à jour les attributs du cours avec ceux du formulaire
            course.setTitle(courseForm.getTitle());
            course.setDescription(courseForm.getDescription());
            course.setFree(courseForm.getIsFree());
            course.setPrice(courseForm.getPrice());
    
            // Enregistrez le cours mis à jour dans la base de données
            rep.save(course);
    
        } catch (Exception e) {
            // Gérer toute exception survenue pendant le processus de mise à jour
            System.out.println("Exception: " + e.getMessage());
        }
    
    // Redirigez l'utilisateur vers la liste des cours après la mise à jour réussie
        return "redirect:/courselist";
    }


    //@RequestMapping(value = { "/updatecourse" }, method = RequestMethod.POST)
    //public String update(Model model , @RequestParam Long id , @Valid @ModelAttribute CourseForm courseForm , BindingResult result) {
    //    try {
    //        Cours course = rep.findById(id).get();
    //        model.addAttribute("course", course);  

    //        if (result.hasErrors()) {
    //            return "updatecourse";
    //        }

    //        course.setTitle(courseForm.getTitle());
    //        course.setDescription(courseForm.getDescription());
    //        course.setIsFree(courseForm.getIsFree());
    //        course.setPrice(courseForm.getPrice());
    //        rep.save(course);
    //        
    //    } catch (Exception e) {
    //        System.out.println("Exception: "+ e.getMessage());
    //    }

    //    return "redirect:/courselist";

    //}


    @RequestMapping(value = {"/deletestudent"} ,method=RequestMethod.GET )
    public String deletestudent(@RequestParam Long id){

        try {
            User user = urep.findById(id).get();

            urep.delete(user);

        } catch (Exception e) {
            System.out.println("Exception: "+ e.getMessage());
        }

        return "redirect:/studentslist";
    }


    @RequestMapping(value = {"/deletecourse"} ,method=RequestMethod.GET )
    public String deletecourse(@RequestParam Long id){

        try {
            Cours course = rep.findById(id).get();

            rep.delete(course);

        } catch (Exception e) {
            System.out.println("Exception: "+ e.getMessage());
        }

        return "redirect:/courselist";
    }

    @RequestMapping(value = "/courselist", method = RequestMethod.GET)
    public String showcourselist(Model model, @RequestParam(required = false) String title) {
      if (title != null && !title.isEmpty()) {
        courses = rep.findByTitleContainingIgnoreCase(title);
      } else {
        courses = rep.findAll();
      }
      model.addAttribute("courses", courses);
      return "courselist";
    }



}
  
