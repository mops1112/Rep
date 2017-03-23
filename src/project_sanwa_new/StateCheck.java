/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

/**
 *
 * @author yotsathon
 */
public class StateCheck {

    public static Boolean state = false;

 

    public static void setState(Boolean state) {
        StateCheck.state = state;
    }

    public static Boolean getState() {
        return state;
    }
}
