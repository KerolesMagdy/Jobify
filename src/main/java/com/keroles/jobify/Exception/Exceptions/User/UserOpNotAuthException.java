/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keroles.jobify.Exception.Exceptions.User;
/**
 *
 * @author Keroles Magdy
 */

public class UserOpNotAuthException extends RuntimeException{

    public UserOpNotAuthException() {
    }

    public UserOpNotAuthException(String message) {
        super(message);
    }
    
}
