/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.myservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author coderom
 */
@WebService(serviceName = "MyCalculaterWebService")
public class MyCalculaterWebService {



    /**
     * Web service operation
     */
    @WebMethod(operationName = "addition")
    public double addition(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1 + num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "subraction")
    public double subraction(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1 - num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "multi")
    public double multi(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1 * num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "division")
    public double division(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1 / num2;
    }

}
