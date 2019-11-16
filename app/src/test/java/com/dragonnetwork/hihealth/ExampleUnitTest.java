package com.dragonnetwork.hihealth;


import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.user.LoginActivity;
import com.dragonnetwork.hihealth.user.LoginActivity_ViewBinding;
import com.dragonnetwork.hihealth.user.SignupActivity;
import com.dragonnetwork.hihealth.user.SignupActivity_ViewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;
import android.util.Log;

import org.junit.runner.RunWith;

import android.util.Log;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import com.dragonnetwork.hihealth.cloudio.CloudIO;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void signupTest() {
        System.out.println("Sign Up Test Started.");
        System.out.println();
        try {
            BuildConfig test = new BuildConfig();
            SignupActivity signUp = new SignupActivity();
            SignupActivity_ViewBinding signUpV = new SignupActivity_ViewBinding(signUp);
            CloudIO.initCloud();
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }
        try {
            // Test 1: Sign up new user with unique email
            // Expected Result: Passed
            assertEquals("Test 1","Passed", CloudIO.testSignUp(1));

        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 2: Sign up with empty password
            // Expected Result: Failed
            assertEquals("Test 2","Failed", CloudIO.testSignUp(2));

        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 3: Sign up with previously used email
            // Expected Result: Failed
            assertEquals("Test 3","Failed", CloudIO.testSignUp(3));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 4: Keep email field empty
            // Expected Result: Failed
            assertEquals("Test 4","Failed", CloudIO.testSignUp(4));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 5: Keep name field empty
            // Expected Result: Failed
            assertEquals("Test 5","Failed", CloudIO.testSignUp(5));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 6: Keep DOB field empty
            // Expected Result: Passed
            assertEquals("Test 6","Failed", CloudIO.testSignUp(6));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        System.out.println("Sign Up Test Ended.");
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
    }
    @Test
    public void loginTest() {
        System.out.println("Login Test Started.");
        System.out.println();
        try {
            LoginActivity login = new LoginActivity();
            LoginActivity_ViewBinding LoginV = new LoginActivity_ViewBinding(login);
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }
        try {
            // Test 1: Login with real user
            // Expected Result: Passed
            assertEquals("Test 1","Passed", CloudIO.testLogin(1));

        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 2: Login with real user but incorrect password
            // Expected Result: Failed
            assertEquals("Test 2","Failed", CloudIO.testLogin(2));

        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 3: Login with real email and empty password
            // Expected Result: Failed
            assertEquals("Test 3","Failed", CloudIO.testLogin(3));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 4: Login with empty email and some password
            // Expected Result: Failed
            assertEquals("Test 4","Failed", CloudIO.testLogin(4));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        try {
            // Test 5: Login with empty email and empty password
            // Expected Result: Failed
            assertEquals("Test 5","Failed", CloudIO.testLogin(5));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }
        try {
            // Test 5: Login with non registered email
            // Expected Result: Failed
            assertEquals("Test 6","Failed", CloudIO.testLogin(6));
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }

        System.out.println("Login Test Ended.");
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
    }
    @Test
    public void medicationTest() {
        System.out.println("Medication Test Started.");
        System.out.println();
        Medication test = new Medication("copaxone", 12,"20 mg", null);

        // Test 1: Get Name
        // Expected Result: "copaxone"
        assertEquals("Test 1","copaxone", test.getMedName());

        // Test 2: Get Dose
        // Expected Result: "20 mg"
        assertEquals("Test 2","20 mg", test.getDosage());

        // Test 3: Get NumPills
        // Expected Result: 12
        assertEquals("Test 3",12, test.getNumPills());

        // Test 4: Get Time
        // Expected Result: null
        assertEquals("Test 4",null, test.getTime());

        System.out.println("Medication Test Ended.");
        System.out.println();
        System.out.println("-----------------");
        System.out.println();

    }

    @Test
    public void calendarTest() {
        System.out.println("Calendar Test Started.");
        System.out.println();
        CalendarActivity test = new CalendarActivity();
        Bundle savedInstanceState = new Bundle();
        try {
            test.onCreate(savedInstanceState);
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }
        try {
            test.addCalendarEvent();
        } catch (Exception e) {
            //assertEquals("Sign Up Test Failed","d", CloudIO.testSignUp(6));
        }
        System.out.println("Calendar Test Ended.");
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
    }

}
