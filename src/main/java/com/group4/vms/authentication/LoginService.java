package com.group4.vms.authentication;


import com.group4.vms.Employee;
import com.group4.vms.Volunteer;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class LoginService {
    // fields

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final VolunteerRepository volunteerRepository;
   // private final MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();
    //constructors
   public LoginService(VolunteerRepository loginVolunteerRepository, EmployeeRepository employeeRepository){ //dependency injection, makes testing easier b/c you can mock up a repository instead of spinning up a real instance of mongodb

        this.volunteerRepository = loginVolunteerRepository;
        //this.mongoTemplate = mongoTemplate;
        this.employeeRepository = employeeRepository;
    }
    //methods
    public LoginState addAccount(String name, String email, String password, String pronouns){ // TODO See if there is a better way to search the database for the Username & pAssword
        //1. check for account
        //2. add account to database
        //log the user in

        return new LoginState(counter.incrementAndGet(), false);

    }
    public LoginState verifyLogin(String email, String password){ //is this the correct way to implement this?
                                                                    //How do you give this user a session? how do i protect my other APIs
        //verification: string
        if(email.matches("/w+[@]/w+[.]/w{3,}")){//ensuring the email string matches a general email string format:
            //1 or more characters, followed by an '@',  followed by a domain name.

            //1. search for user
            //2. if user exists, good login
            //3. if user does not exist, bad login
            List<Employee> employees = this.employeeRepository.getLoginInfo(email, password);
            List<Volunteer> volunteers = this.volunteerRepository.getLoginInfo(email, password);

            if(employees.isEmpty() && volunteers.isEmpty()){
                return new LoginState(counter.incrementAndGet(), false);
            }
            else {
                return new LoginState(counter.incrementAndGet(), true);
            }
        }
        else{ //email provided does not match a general email string format. send bad login.
            return new LoginState(counter.incrementAndGet(), true);
        }


    }

    ////utility methods to support the service methods
    //private List<Volunteer> findUser(Query query){
    //
    //    return this.mongoTemplate.find(query, Volunteer.class);
    //}
    //private List<Employee> findEmployee(Query query){
    //    return this.mongoTemplate.find(query, Employee.class);
    //}

}
